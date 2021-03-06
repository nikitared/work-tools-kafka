package ru.babaninnv.worktools.kafka.service.jsonconverter;

import org.apache.avro.AvroTypeException;

import java.util.Deque;

class AvroTypeExceptions {
    static AvroTypeException enumException(Deque<String> fieldPath, String expectedSymbols) {
        return new AvroTypeException(new StringBuilder()
                .append("Field ")
                .append(PathsPrinter.print(fieldPath))
                .append(" is expected to be of enum type and be one of ")
                .append(expectedSymbols)
                .toString());
    }

    static AvroTypeException unionException(String fieldName, String expectedTypes, Deque<String> offendingPath) {
        return new AvroTypeException(new StringBuilder()
                .append("Could not evaluate union, field ")
                .append(fieldName)
                .append(" is expected to be one of these: ")
                .append(expectedTypes)
                .append(". If this is a complex type, check if offending field: ")
                .append(PathsPrinter.print(offendingPath))
                .append(" adheres to schema.")
                .toString());
    }

    static AvroTypeException typeException(Deque<String> fieldPath, String expectedType) {
        return new AvroTypeException(new StringBuilder()
            .append("Field ")
            .append(PathsPrinter.print(fieldPath))
            .append(" is expected to be type: ")
            .append(expectedType)
            .toString());
    }
}
