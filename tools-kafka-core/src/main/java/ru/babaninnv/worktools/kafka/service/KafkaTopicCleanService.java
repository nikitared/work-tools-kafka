package ru.babaninnv.worktools.kafka.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
// import org.apache.kafka.clients.admin.ListOffsetsResult;
import org.apache.kafka.clients.admin.ListOffsetsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
// import org.apache.kafka.clients.admin.OffsetSpec;
import org.apache.kafka.clients.admin.OffsetSpec;
import org.apache.kafka.clients.admin.RecordsToDelete;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.repository.KafkaConnectionProfileRepository;
import ru.babaninnv.worktools.kafka.utils.KafkaPropertiesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaTopicCleanService {

    private final KafkaConnectionProfileRepository kafkaConnectionProfileRepository;

    public void clean(KafkaConnectionProfile kafkaConnectionProfile, String topic) {

        Properties properties = KafkaPropertiesUtils.convert(kafkaConnectionProfile);

        try (AdminClient adminClient = KafkaAdminClient.create(properties)) {

            ListTopicsResult listTopicsResult = adminClient.listTopics();
            Set<String> kafkaTopics = listTopicsResult.names().get();
            log.info("kafka topics: {}", kafkaTopics);

            DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Lists.newArrayList(topic));
            TopicDescription topicDescription = describeTopicsResult.values().get(topic).get();
            List<TopicPartitionInfo> partitions = topicDescription.partitions();
            log.info("topicDescription: {}", topicDescription);

            Map<TopicPartition, OffsetSpec> partitionsOffsetSpec = new HashMap<>();
            for (TopicPartitionInfo partition : partitions) {
                partitionsOffsetSpec.put(new TopicPartition(topic, partition.partition()), OffsetSpec.latest());
            }

            Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> topicPartitionListOffsetsResultInfoMap
                    = adminClient.listOffsets(partitionsOffsetSpec).all().get();
            log.info("topicPartitionListOffsetsResultInfoMap: {}", topicPartitionListOffsetsResultInfoMap);

            Map<TopicPartition, RecordsToDelete> partitionsDeletes = new HashMap<>();
            for (Map.Entry<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> entry : topicPartitionListOffsetsResultInfoMap.entrySet()) {
                TopicPartition topicPartition = entry.getKey();
                ListOffsetsResult.ListOffsetsResultInfo value = entry.getValue();
                log.info("topicPartition: {}, offset: {}", topicPartition.partition(), value.offset());
                partitionsDeletes.put(topicPartition, RecordsToDelete.beforeOffset(value.offset()));
            }

            adminClient.deleteRecords(partitionsDeletes).all().get();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void cleanAll(String profileId) {

        if ("prod".equals(profileId)) {
            return;
        }

        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileRepository
                .findTopByName(profileId).orElseThrow(RuntimeException::new);

        Properties properties = KafkaPropertiesUtils.convert(kafkaConnectionProfile);

        try (AdminClient adminClient = KafkaAdminClient.create(properties)) {

            ListTopicsResult listTopicsResult = adminClient.listTopics();
            Set<String> kafkaTopics = listTopicsResult.names().get();
            log.info("kafka topics: {}", kafkaTopics);

            for (String topic : kafkaTopics) {
                DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Lists.newArrayList(topic));
                TopicDescription topicDescription = describeTopicsResult.values().get(topic).get();
                List<TopicPartitionInfo> partitions = topicDescription.partitions();
                log.info("topicDescription: {}", topicDescription);

                Map<TopicPartition, OffsetSpec> partitionsOffsetSpec = new HashMap<>();
                for (TopicPartitionInfo partition : partitions) {
                    partitionsOffsetSpec.put(new TopicPartition(topic, partition.partition()), OffsetSpec.latest());
                }

                Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> topicPartitionListOffsetsResultInfoMap
                        = adminClient.listOffsets(partitionsOffsetSpec).all().get();
                log.info("topicPartitionListOffsetsResultInfoMap: {}", topicPartitionListOffsetsResultInfoMap);

                Map<TopicPartition, RecordsToDelete> partitionsDeletes = new HashMap<>();
                for (Map.Entry<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> entry : topicPartitionListOffsetsResultInfoMap.entrySet()) {
                    TopicPartition topicPartition = entry.getKey();
                    ListOffsetsResult.ListOffsetsResultInfo value = entry.getValue();
                    log.info("topicPartition: {}, offset: {}", topicPartition.partition(), value.offset());
                    partitionsDeletes.put(topicPartition, RecordsToDelete.beforeOffset(value.offset()));
                }

                try {
                    adminClient.deleteRecords(partitionsDeletes).all().get();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
