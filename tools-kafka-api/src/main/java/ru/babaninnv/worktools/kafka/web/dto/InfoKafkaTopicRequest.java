package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoKafkaTopicRequest extends BaseProfiledRequest {
    private String topic;
}
