package ru.babaninnv.worktools.kafka.exceptions;

import java.text.MessageFormat;
import java.util.List;

public class SchemaRegistrySubjectAlreadyExistsException extends Exception {
    public SchemaRegistrySubjectAlreadyExistsException(List<String> allSubjects, String subject) {
        super(MessageFormat.format("subject {0} уже есть в списке {1}", subject, allSubjects));
    }
}
