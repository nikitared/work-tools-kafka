package ru.babaninnv.worktools.kafka.service.consumer;

import lombok.Getter;
import lombok.Setter;
import ru.babaninnv.worktools.kafka.service.avro.SchemaId;
import ru.babaninnv.worktools.kafka.service.schemaregistry.SchemaData;

import java.util.Map;

@Getter
@Setter
public class ConsumerRecordHandlerProperties {
    private Map<SchemaId, SchemaData> avroSchemas;
}
