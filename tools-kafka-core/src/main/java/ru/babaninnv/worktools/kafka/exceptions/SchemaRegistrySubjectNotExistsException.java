package ru.babaninnv.worktools.kafka.exceptions;

public class SchemaRegistrySubjectNotExistsException extends Exception {
    public SchemaRegistrySubjectNotExistsException(String schemaRegistryUrl, String subject) {
        super("Subject c наименованием " + subject + " не найден в Schema Registry " + schemaRegistryUrl);
    }
}
