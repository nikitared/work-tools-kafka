package ru.babaninnv.worktools.kafka.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.File;

@Getter
@Setter
public class KafkaConsumerHolder {
    private KafkaConsumer<Object, Object> kafkaConsumer;
    private long lastUseTime;
    private int schemaId;
    private KafkaConsumerFactory.Folders folders;
    private File schemaAvroClassesBinaryFolder;
    private Class<?> clazz;
}
