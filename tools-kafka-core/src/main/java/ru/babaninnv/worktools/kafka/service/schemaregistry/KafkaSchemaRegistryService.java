package ru.babaninnv.worktools.kafka.service.schemaregistry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.exceptions.SchemaRegistryNotAvailableException;
import ru.babaninnv.worktools.kafka.exceptions.SchemaRegistrySubjectNotExistsException;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.service.KafkaConnectionProfileService;
import ru.babaninnv.worktools.kafka.service.avro.SchemaId;
import ru.babaninnv.worktools.kafka.service.avro.SchemaVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSchemaRegistryService {

    private final KafkaConnectionProfileService kafkaConnectionProfileService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void registerFromLocalStorage(String profile, String subject, String avroSchemasFolder, String avroSchemaClassName)
            throws Exception {

        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profile);

        Schema schema = null;
        if (StringUtils.isNotBlank(avroSchemaClassName)) {

            // если не передали путь до папки с авро-схемами, берём путь из профиля
            if (StringUtils.isBlank(avroSchemasFolder)) {
                avroSchemasFolder = kafkaConnectionProfile.getAvroSchemasFolder();
            }

            File avroSchemaFile = new File(avroSchemasFolder, avroSchemaClassName.replaceAll("\\.", File.separator) + ".avsc");
            if (!avroSchemaFile.exists()) {
                throw new FileNotFoundException(String.format("Файл схемы %s не найден в папке %s по адресу %s",
                        avroSchemaClassName,
                        avroSchemasFolder,
                        avroSchemaFile.getAbsolutePath()));
            }
            schema = new Schema.Parser().parse(FileUtils.readFileToString(avroSchemaFile, StandardCharsets.UTF_8));
        }

        if (schema != null) {
            String schemaRegistryUrl = kafkaConnectionProfile.getSchemaRegistryConfiguration().getSchemaRegistryUrl();
            register(schemaRegistryUrl, subject, schema);
        }
    }

    public void registerFromJson(String profile, String subject, String json) throws Exception {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profile);

        String schemaRegistryUrl = kafkaConnectionProfile.getSchemaRegistryConfiguration().getSchemaRegistryUrl();
        Schema schema = new Schema.Parser().parse(json);
        register(schemaRegistryUrl, subject, schema);
    }

    public void register(String schemaRegistryUrl, String subject, Schema schema) throws Exception {
        SchemaRegistryClient schemaRegistryClient = new CachedSchemaRegistryClient(schemaRegistryUrl, 99);
        List<String> allSubjects = (List<String>) Optional.ofNullable(schemaRegistryClient.getAllSubjects()).orElse(new ArrayList<>());
        Optional<String> foundSubject = allSubjects.stream().filter(s -> s.equals(subject)).findFirst();

        // if (foundSubject.isPresent()) {
        //     throw new SchemaRegistrySubjectAlreadyExistsException(allSubjects, subject);
        // }

         schemaRegistryClient.register(subject, new AvroSchema(schema));
    }

    public List<String> getAllSubjects(String profile) throws Exception {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profile);
        String schemaRegistryUrl = kafkaConnectionProfile.getSchemaRegistryConfiguration().getSchemaRegistryUrl();
        SchemaRegistryClient schemaRegistryClient = new CachedSchemaRegistryClient(schemaRegistryUrl, 99);
        return new ArrayList<>(schemaRegistryClient.getAllSubjects());
    }

    @SneakyThrows
    public Map<SchemaVersion, SchemaData> getAvroSchemasByVersion(String schemaRegistryUrl, String subject) {
        validate(schemaRegistryUrl, subject);

        // получаем список версий
        Integer[] versions = getSchemaVersions(schemaRegistryUrl, subject);

        // получаем схему для каждой версии с идентификатором
        Map<SchemaVersion, SchemaData> schemas = new HashMap<>();
        for (Integer version : versions) {
            String json = executGet(schemaRegistryUrl + "/subjects/" + subject + "/versions/" + version);
            SchemaData schemaData = jsonToSchemaData(json);
            schemas.put(new SchemaVersion(schemaData.getVersion()), schemaData);
        }
        return schemas;
    }

    @SneakyThrows
    public Map<SchemaId, SchemaData> getAvroSchemasById(String schemaRegistryUrl, String subject) {
        validate(schemaRegistryUrl, subject);

        // получаем список версий
        Integer[] versions = getSchemaVersions(schemaRegistryUrl, subject);

        Map<SchemaId, SchemaData> schemas = new HashMap<>();

        // получаем схему для каждой версии с идентификатором
        for (Integer version : versions) {
            String json = executGet(schemaRegistryUrl + "/subjects/" + subject + "/versions/" + version);
            SchemaData schemaData = jsonToSchemaData(json);
            schemas.put(new SchemaId(schemaData.getId()), schemaData);
        }

        return schemas;
    }

    @SneakyThrows
    private void validate(String schemaRegistryUrl, String subject) {
        if (StringUtils.isBlank(schemaRegistryUrl)) {
            throw new IllegalArgumentException("Отсутствует URL до Schema Registry");
        }

        if (StringUtils.isBlank(subject)) {
            throw new IllegalArgumentException("Отсутствует Subject");
        }

        // URL может быть недоступен
        if (isNotAvailable(schemaRegistryUrl)) {
            throw new SchemaRegistryNotAvailableException(schemaRegistryUrl);
        }

        // проверяем, что такой сабжект есть
        if (isSubjectNotExists(schemaRegistryUrl, subject)) {
            throw new SchemaRegistrySubjectNotExistsException(schemaRegistryUrl, subject);
        }
    }

    private SchemaData jsonToSchemaData(String json) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(json);
        SchemaData schemaData = new SchemaData();
        schemaData.setId(jsonNode.get("id").asInt());
        schemaData.setVersion(jsonNode.get("version").asInt());
        schemaData.setSubject(jsonNode.get("subject").asText());
        schemaData.setSchema(new Schema.Parser().parse(jsonNode.get("schema").asText()));
        return schemaData;
    }

    @SneakyThrows
    private boolean isSubjectNotExists(String schemaRegistryUrl, String subject) {
        String json = executGet(schemaRegistryUrl + "/subjects");
        ObjectMapper objectMapper = new ObjectMapper();
        String[] subjects = objectMapper.readValue(json, String[].class);
        return !ArrayUtils.contains(subjects, subject);
    }

    @SneakyThrows
    private Integer[] getSchemaVersions(String schemaRegistryUrl, String subject) {
        String json = executGet(schemaRegistryUrl + "/subjects/" + subject + "/versions");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Integer[].class);
    }

    @SneakyThrows
    private boolean isNotAvailable(String url) {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            if (response.getCode() >= 200 || response.getCode() < 300) {
                return false;
            }
        }

        return true;
    }

    @SneakyThrows
    private String executGet(String url) {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpget = new HttpGet(url);
            try (final CloseableHttpResponse response = httpclient.execute(httpget)) {
                return EntityUtils.toString(response.getEntity());
            }
        }
    }
}
