package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaProfileGetResponse {
    private String profileId;
    private String data;
}
