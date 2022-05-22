package ru.babaninnv.worktools.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.model.KafkaTopic;
import ru.babaninnv.worktools.kafka.repository.KafkaConnectionProfileRepository;
import ru.babaninnv.worktools.kafka.utils.KafkaPropertiesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaTopicsListService {

    private final KafkaConnectionProfileRepository kafkaConnectionProfileRepository;

    public List<KafkaTopic> list(String profileId) {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileRepository
                .findTopByName(profileId).orElseThrow(RuntimeException::new);
        return list(kafkaConnectionProfile);
    }

    public List<KafkaTopic> list(KafkaConnectionProfile kafkaConnectionProfile) {
        Properties properties = KafkaPropertiesUtils.convert(kafkaConnectionProfile);

        try (AdminClient adminClient = KafkaAdminClient.create(properties)) {
            ListTopicsResult listTopicsResult = adminClient.listTopics();
            Set<String> kafkaTopics = listTopicsResult.names().get();
            log.info("kafka topics: {}", kafkaTopics);
            return kafkaTopics.stream().sorted(String::compareTo).map(KafkaTopic::new).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ArrayList<>();
    }
}
