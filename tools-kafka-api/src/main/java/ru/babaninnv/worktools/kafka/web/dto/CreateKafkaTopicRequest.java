package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateKafkaTopicRequest extends BaseProfiledRequest {
    private String topic;
    private int numPartitions;
    private short replicationFactor;
}
