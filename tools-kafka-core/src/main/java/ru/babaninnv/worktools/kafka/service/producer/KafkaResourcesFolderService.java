package ru.babaninnv.worktools.kafka.service.producer;

import ru.babaninnv.worktools.kafka.service.producer.exceptions.GeneratedResourcesOutputFolderNotFoundException;

import java.io.File;

public interface KafkaResourcesFolderService {
    Folders create(File generatedResourcesOutputFolder,
                   String profile,
                   String topic) throws GeneratedResourcesOutputFolderNotFoundException;

    void delete(Folders folders);
}
