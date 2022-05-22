package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterSchemaRequest {
    private String profile;
    private String subject;
    private String schemaJson;
    private String avroSchemaFolder;
    private String avroSchemaClassName;
}