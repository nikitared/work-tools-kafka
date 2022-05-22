package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InfoKafkaTopicResponse {
    private Map<String, Object> body;
}
