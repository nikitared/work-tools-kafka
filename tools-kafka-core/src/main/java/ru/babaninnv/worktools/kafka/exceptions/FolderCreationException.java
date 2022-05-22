package ru.babaninnv.worktools.kafka.exceptions;

public class FolderCreationException extends RuntimeException {
    public FolderCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
