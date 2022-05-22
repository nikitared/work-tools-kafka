package ru.babaninnv.worktools.kafka.service.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import ru.babaninnv.worktools.kafka.exceptions.FolderCreationException;
import ru.babaninnv.worktools.kafka.service.producer.exceptions.GeneratedResourcesOutputFolderNotFoundException;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

@Slf4j
public class KafkaResourcesFolderIdleService implements KafkaResourcesFolderService {
    @Override
    public Folders create(File generatedResourcesOutputFolder,
                          String profile,
                          String topic) throws GeneratedResourcesOutputFolderNotFoundException {
        return null;
    }

    @Override
    public void delete(Folders folders) {
        // do nothing
    }
}
