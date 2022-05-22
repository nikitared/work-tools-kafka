package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompileAvroRequest {
    /**
     * Папки с расположенными в них файлами .avsc
     * */
    private List<String> schemaFolders;

    /**
     * Папка с собранными схемами .avsc
     * */
    private String avroSchemasFolder;

    /**
     * Папка для сгенерированных исходников .java
     * */
    private String avroJavaSourcesFolder;

    /**
     * Папка для собранных бинарников .class
     * */
    private String avroClassesFolder;
}
