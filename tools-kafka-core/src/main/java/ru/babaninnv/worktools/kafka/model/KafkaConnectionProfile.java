package ru.babaninnv.worktools.kafka.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.babaninnv.worktools.kafka.model.profile.AdminConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.BrokerConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.ConnectConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.ConsumerConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.KafkaStreamsConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.ProducerConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.SchemaRegistryConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.SinkConnectorConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.SourceConnectorConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.TopicLevelConfiguration;

import java.io.File;

@Data
@Document("kafka_connection_profile")
public class KafkaConnectionProfile {
    @Id
    private String id;
    private String name;
    private AdminConfiguration adminConfiguration = new AdminConfiguration();
    private BrokerConfiguration brokerConfiguration = new BrokerConfiguration();
    private ConnectConfiguration connectConfiguration = new ConnectConfiguration();
    private ConsumerConfiguration consumerConfiguration = new ConsumerConfiguration();
    private KafkaStreamsConfiguration kafkaStreamsConfiguration = new KafkaStreamsConfiguration();
    private ProducerConfiguration producerConfiguration = new ProducerConfiguration();
    private SinkConnectorConfiguration sinkConnectorConfiguration = new SinkConnectorConfiguration();
    private SourceConnectorConfiguration sourceConnectorConfiguration = new SourceConnectorConfiguration();
    private TopicLevelConfiguration topicLevelConfiguration = new TopicLevelConfiguration();

    private SchemaRegistryConfiguration schemaRegistryConfiguration = new SchemaRegistryConfiguration();

    private String avroSchemaRootFolder;
    private String avroSchemasFolder;
    private String avroJavaSourcesFolder;
    private String avroClassesFolder;
}
