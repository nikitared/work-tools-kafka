package ru.babaninnv.worktools.kafka.exceptions;

public class SchemaRegistryNotAvailableException extends Exception {
    public SchemaRegistryNotAvailableException(String schemaRegistryUrl) {
        super("Schema registry недоступен по URL " + schemaRegistryUrl);
    }
}
