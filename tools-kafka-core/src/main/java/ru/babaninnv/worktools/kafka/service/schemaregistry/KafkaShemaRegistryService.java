package ru.babaninnv.worktools.kafka.service.schemaregistry;

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaMetadata;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.RestService;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.exceptions.SchemaRegistrySubjectNotFoundException;
import ru.babaninnv.worktools.kafka.model.profile.KafkaSchemaRegistryConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class KafkaShemaRegistryService {

    public SchemaData loadSchema(String schemaRegistryUrl, String subject) {
        try {
            SchemaRegistryClient schemaRegistryClient = new CachedSchemaRegistryClient(schemaRegistryUrl, 99);
            List<String> allSubjects = (List<String>) Optional.ofNullable(schemaRegistryClient.getAllSubjects()).orElse(new ArrayList<>());
            String foundSubject = allSubjects.stream()
                    .filter(s -> s.equals(subject))
                    .findFirst()
                    .orElseThrow(() -> new SchemaRegistrySubjectNotFoundException(allSubjects, subject));
            SchemaMetadata latestSchemaMetadata = schemaRegistryClient.getLatestSchemaMetadata(foundSubject);

            SchemaData schemaData = new SchemaData();
            schemaData.id = latestSchemaMetadata.getId();
            schemaData.schemaSource = String.copyValueOf(latestSchemaMetadata.getSchema().toCharArray());

            schemaRegistryClient.reset();

            return schemaData;
        } catch (Exception e) {
            log.error("Ошибка поиска схемы", e);
        }
        return null;
    }

    public List<String> shemasList(KafkaSchemaRegistryConfiguration kafkaSchemaRegistryConfiguration) throws IOException, RestClientException {
        RestService restService = new RestService(kafkaSchemaRegistryConfiguration.getUrl());
        return restService.getAllSubjects();
    }

    public void deleteBySubjectAndVersion(KafkaSchemaRegistryConfiguration kafkaSchemaRegistryConfiguration, String subject, String version) {
        RestService restService = new RestService(kafkaSchemaRegistryConfiguration.getUrl());
        try {
            restService.deleteSchemaVersion(new HashMap<>(), subject, version);
        } catch (IOException | RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static class SchemaData {
        public int id;
        public String schemaSource;
        public Map<Integer, Schema> schemas;
    }
}
