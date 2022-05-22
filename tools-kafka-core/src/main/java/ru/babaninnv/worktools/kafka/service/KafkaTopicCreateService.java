package ru.babaninnv.worktools.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.model.profile.ConnectConfiguration;
import ru.babaninnv.worktools.kafka.repository.KafkaConnectionProfileRepository;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaTopicCreateService {

    private final KafkaConnectionProfileRepository kafkaConnectionProfileRepository;

    public void create(String profileId, String topic, int numPartitions, short replicationFactor) {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileRepository
                .findTopByName(profileId).orElseThrow(RuntimeException::new);

        ConnectConfiguration connectConfiguration = kafkaConnectionProfile.getConnectConfiguration();

        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, connectConfiguration.getBootstrapServers());
        properties.setProperty(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, connectConfiguration.getSecurityProtocol());

        try(AdminClient adminClient = KafkaAdminClient.create(properties)) {
            ListTopicsResult listTopicsResult = adminClient.listTopics();
            Set<String> kafkaTopics = listTopicsResult.names().get();
            log.info("kafka topics: {}", kafkaTopics);

            NewTopic newTopic = new NewTopic(topic, numPartitions, replicationFactor);
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
