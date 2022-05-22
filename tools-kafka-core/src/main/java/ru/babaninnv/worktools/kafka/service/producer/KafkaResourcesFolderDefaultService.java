package ru.babaninnv.worktools.kafka.service.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import ru.babaninnv.worktools.kafka.exceptions.FolderCreationException;
import ru.babaninnv.worktools.kafka.service.producer.exceptions.GeneratedResourcesOutputFolderNotFoundException;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

@Slf4j
public class KafkaResourcesFolderDefaultService implements KafkaResourcesFolderService {

    @Override
    public Folders create(File generatedResourcesOutputFolder,
                                               String profile,
                                               String topic) throws GeneratedResourcesOutputFolderNotFoundException {
        if (!generatedResourcesOutputFolder.exists()) {
            log.error("Папки {} не существует", generatedResourcesOutputFolder.getAbsolutePath());
            throw new GeneratedResourcesOutputFolderNotFoundException(generatedResourcesOutputFolder);
        }

        Folders folders = new Folders();

        folders.profiledRootAvroFolder = generatedResourcesOutputFolder.toPath().resolve(profile + "_" + topic).toFile();
        mkdir(folders.profiledRootAvroFolder);

        folders.schemasFolder = folders.profiledRootAvroFolder.toPath().resolve("schemas").toFile();
        mkdir(folders.schemasFolder);

        folders.binariesFolder = new File(folders.profiledRootAvroFolder, "classes");
        mkdir(folders.binariesFolder);

        folders.sourcesFolder = new File(folders.profiledRootAvroFolder, "sources");
        mkdir(folders.sourcesFolder);

        return folders;
    }

    @Override
    public void delete(Folders folders) {
        log.info("Удаляем все папки, связанные с этим продюсером");
        File profiledRootAvroFolder = folders.profiledRootAvroFolder;
        if (profiledRootAvroFolder.exists()) {
            try {
                FileUtils.forceDelete(profiledRootAvroFolder);
            } catch (IOException e) {
                log.error("Ошибка при удалении папки: {}", profiledRootAvroFolder.getAbsolutePath(), e);
            }
        } else {
            log.warn("Папки не существует: {}", profiledRootAvroFolder.getAbsolutePath());
        }
    }

    private void mkdir(File folder) {
        try {
            FileUtils.forceMkdir(folder);
            log.trace("Создали папку");
        } catch (IOException e) {
            throw new FolderCreationException(MessageFormat.format("Невозможно создать папку {0}", folder.getAbsolutePath()), e);
        }
    }
}
