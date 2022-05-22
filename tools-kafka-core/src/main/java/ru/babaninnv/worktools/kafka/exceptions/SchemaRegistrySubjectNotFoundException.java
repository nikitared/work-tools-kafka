package ru.babaninnv.worktools.kafka.exceptions;

import java.text.MessageFormat;
import java.util.List;

public class SchemaRegistrySubjectNotFoundException extends Exception {
    public SchemaRegistrySubjectNotFoundException(List<String> allSubjects, String subject) {
        super(MessageFormat.format("Не смогли найти subject {0} в списке {1}", subject, allSubjects));
    }
}
