package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaProfile {
    private String id;
    private AdminConfiguration adminConfiguration;
    private BrokerConfiguration brokerConfiguration;
    private ConnectConfiguration connectConfiguration;
    private ConsumerConfiguration consumerConfiguration;
    private KafkaStreamsConfiguration kafkaStreamsConfiguration;
    private ProducerConfiguration producerConfiguration;
    private SinkConnectorConfiguration sinkConnectorConfiguration;
    private SourceConnectorConfiguration sourceConnectorConfiguration;
    private TopicLevelConfiguration topicLevelConfiguration;
}
