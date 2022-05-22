package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaProducerSendRequest extends BaseProfiledRequest {
    private String topic;
    private String schema;
    private String subject;

    private String key;
    private String value;

    /**
     * @see ru.babaninnv.worktools.kafka.model.enums.MessageDataFormat
     */
    private String keyDataFormat;

    /**
     * @see ru.babaninnv.worktools.kafka.model.enums.MessageDataFormat
     */
    private String valueDataFormat;
}
