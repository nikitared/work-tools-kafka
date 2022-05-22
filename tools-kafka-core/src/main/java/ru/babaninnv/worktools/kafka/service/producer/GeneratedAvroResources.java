package ru.babaninnv.worktools.kafka.service.producer;

import lombok.Getter;
import lombok.Setter;
import ru.babaninnv.worktools.kafka.service.KafkaProducerFactory;

import java.io.File;

@Getter
@Setter
public class GeneratedAvroResources {
    private File profiledRootAvroFolder;
    private File schemasFolder;
    private File binariesFolder;
    private File sourcesFolder;
    private Class<?> clazz;
}
