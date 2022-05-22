package ru.babaninnv.worktools.kafka.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.avro.Schema;
import org.apache.kafka.clients.producer.KafkaProducer;
import ru.babaninnv.worktools.kafka.service.producer.Folders;

import java.io.File;

@Getter
@Setter
public class KafkaProducerHolder {
    private KafkaProducer<Object, Object> kafkaProducer;
    private long lastUseTime;
    private int schemaId;
    private Folders folders;
    private File schemaAvroClassesBinaryFolder;
    private Class<?> clazz;
    private Schema schema;
}
