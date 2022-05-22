package ru.babaninnv.worktools.kafka.service.schemaregistry;

import lombok.Getter;
import lombok.Setter;
import org.apache.avro.Schema;

@Getter
@Setter
public class SchemaData {
    private Integer id;
    private Integer version;
    private String subject;
    private Schema schema;
}
