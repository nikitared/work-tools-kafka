package ru.babaninnv.worktools.kafka.service.producer;

import lombok.Getter;
import lombok.Setter;
import ru.babaninnv.worktools.kafka.service.consumer.MessageDataFormat;

@Getter
@Setter
public class MessageProduceConfiguration {
    /**
     * Топик
     * */
    private String topic;

    /**
     * Субьект
     * */
    private String subject;

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
