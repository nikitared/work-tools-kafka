package ru.babaninnv.worktools.kafka.service.schemaregistry;

import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.entities.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class KafkaShemaRegistriesSyncService {

    public void sync(String fromSchemaRegistryUrl, String toSchemaRegistryUrl) {
        try {
            SchemaRegistryClient fromSchemaRegistryClient = new CachedSchemaRegistryClient(fromSchemaRegistryUrl, 99);
            SchemaRegistryClient toSchemaRegistryClient = new CachedSchemaRegistryClient(toSchemaRegistryUrl, 99);
            List<String> fromSubjects = (List<String>) Optional.ofNullable(fromSchemaRegistryClient.getAllSubjects()).orElse(new ArrayList<>());
            List<String> toSubjects = (List<String>) Optional.ofNullable(toSchemaRegistryClient.getAllSubjects()).orElse(new ArrayList<>());

            for (String toSubject : toSubjects) {
                toSchemaRegistryClient.deleteSubject(toSubject);
            }

            for (String fromSubject : fromSubjects) {
                List<Integer> versions = fromSchemaRegistryClient.getAllVersions(fromSubject);
                for (Integer version : versions) {
                    Schema schemaSource = fromSchemaRegistryClient.getByVersion(fromSubject, version, false);
                    try {
                        toSchemaRegistryClient.register(fromSubject, new AvroSchema(schemaSource.getSchema()));
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }


        } catch (Exception e) {
            log.error("Ошибка поиска схемы", e);
        }
    }

    public void sync(String fromSchemaRegistryUrl, String fromSubject, String toSchemaRegistryUrl, String toSubject) {
        try {
            SchemaRegistryClient fromSchemaRegistryClient = new CachedSchemaRegistryClient(fromSchemaRegistryUrl, 99);
            SchemaRegistryClient toSchemaRegistryClient = new CachedSchemaRegistryClient(toSchemaRegistryUrl, 99);

            boolean toSubjectExists = toSchemaRegistryClient.getAllSubjects().stream()
                    .anyMatch(s -> s.equals(toSubject));
            if (toSubjectExists) {
                List<Integer> toVersions = toSchemaRegistryClient.getAllVersions(toSubject);
                for (Integer toVersion : toVersions) {
                    toSchemaRegistryClient.deleteSchemaVersion(toSubject, toVersion.toString());
                }
            }

            toSubjectExists = toSchemaRegistryClient.getAllSubjects().stream()
                    .anyMatch(s -> s.equals(toSubject));
            if (!toSubjectExists) {

            }

            List<Integer> fromVersions = fromSchemaRegistryClient.getAllVersions(fromSubject);
            for (Integer fromVersion : fromVersions) {
                Schema schema = fromSchemaRegistryClient.getByVersion(fromSubject, fromVersion, false);
                ParsedSchema parsedSchema = new AvroSchema(schema.getSchema());
                toSchemaRegistryClient.register(toSubject, parsedSchema);
            }

        } catch (Exception e) {
            log.error("Ошибка поиска схемы", e);
        }
    }
}
