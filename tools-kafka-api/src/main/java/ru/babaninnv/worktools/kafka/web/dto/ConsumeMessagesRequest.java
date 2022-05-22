package ru.babaninnv.worktools.kafka.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumeMessagesRequest extends BaseProfiledRequest {
    public String topic;
    public String subject;
    public String groupId;
    public String data;
}
