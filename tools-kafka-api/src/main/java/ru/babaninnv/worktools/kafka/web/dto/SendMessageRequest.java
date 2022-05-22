package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequest extends BaseProfiledRequest {
    public String topic;
    public String data;
}
