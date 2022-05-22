package ru.babaninnv.worktools.kafka.service.producer.exceptions;

import java.io.File;

public class GeneratedResourcesOutputFolderNotFoundException extends Exception {
    public GeneratedResourcesOutputFolderNotFoundException(File generatedResourcesOutputFolder) {
        super("Папка для сгенерированных ресурсов не найдена: " + (generatedResourcesOutputFolder != null
                ? generatedResourcesOutputFolder.getAbsolutePath()
                : ""));
    }
}
