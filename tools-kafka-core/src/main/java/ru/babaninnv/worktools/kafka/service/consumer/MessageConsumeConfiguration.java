package ru.babaninnv.worktools.kafka.service.consumer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageConsumeConfiguration {
    /**
     * Топик
     * */
    private String topic;

    /**
     * Субьект
     * */
    private String subject;

    /**
     * Консьюмер группа
     * */
    private String groupId;

    /**
     * Исходник схемы, на случай если schema registry не указан, либо отсутствует субъект, либо версия
     * */
    private String customSchema;

    /**
     * Формат ключа сообщения
     * */
    private MessageDataFormat messageKeyDataFormat;

    /**
     * Формат значения сообщения
     * */
    private MessageDataFormat messageValueDataFormat;
}
