package ru.babaninnv.worktools.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.model.profile.SchemaRegistryConfiguration;
import ru.babaninnv.worktools.kafka.service.avro.AvroDeserializer;
import ru.babaninnv.worktools.kafka.service.schemaregistry.KafkaShemaRegistryService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaAvroMessageDeserializer {
    private final KafkaShemaRegistryService kafkaShemaRegistryService;
    private final KafkaConnectionProfileService kafkaConnectionProfileService;
    private final AvroDeserializer avroDeserializer = new AvroDeserializer();

    @SneakyThrows
    public String deserialize(String profileName, byte[] binaryData, String subject) {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profileName);
        SchemaRegistryConfiguration schemaRegistryConfiguration = kafkaConnectionProfile.getSchemaRegistryConfiguration();
        KafkaShemaRegistryService.SchemaData schemaData = kafkaShemaRegistryService
                .loadSchema(schemaRegistryConfiguration.getSchemaRegistryUrl(), subject);
        Schema schema = new Schema.Parser().parse(schemaData.schemaSource);
        return avroDeserializer.deserialize(binaryData, schema);
    }
}
