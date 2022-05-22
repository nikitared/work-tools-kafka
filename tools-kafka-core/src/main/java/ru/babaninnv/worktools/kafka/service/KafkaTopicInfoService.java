package ru.babaninnv.worktools.kafka.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.ConfigResource.Type;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.model.KafkaTopicInfo;
import ru.babaninnv.worktools.kafka.repository.KafkaConnectionProfileRepository;
import ru.babaninnv.worktools.kafka.utils.KafkaPropertiesUtils;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaTopicInfoService {

    private final KafkaConnectionProfileService kafkaConnectionProfileService;

    public KafkaTopicInfo info(String profileId, String topic) {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profileId);

        Properties properties = KafkaPropertiesUtils.convert(kafkaConnectionProfile);

        try(AdminClient adminClient = KafkaAdminClient.create(properties)) {
            ListTopicsResult listTopicsResult = adminClient.listTopics();
            Set<String> kafkaTopics = listTopicsResult.names().get();
            log.info("kafka topics: {}", kafkaTopics);

            ConfigResource configResource = new ConfigResource(Type.TOPIC, topic);

            DescribeConfigsResult describeConfigsResult = adminClient.describeConfigs(Lists.newArrayList(configResource));
            Map<ConfigResource, Config> configResourceConfigMap = describeConfigsResult.all().get();

            for (Config config : configResourceConfigMap.values()) {
                KafkaTopicInfo kafkaTopicInfo = new KafkaTopicInfo();

                kafkaTopicInfo.setCleanupPolicy(getValue(config, TopicConfig.CLEANUP_POLICY_CONFIG));
                kafkaTopicInfo.setCompressionType(getValue(config, TopicConfig.COMPRESSION_TYPE_CONFIG));
                kafkaTopicInfo.setConfluentKeySchemaValidation(getValue(config, "confluent.key.schema.validation"));
                kafkaTopicInfo.setConfluentKeySubjectNameStrategy(getValue(config, "confluent.key.subject.name.strategy"));
                kafkaTopicInfo.setConfluentValueSchemaValidation(getValue(config, "confluent.value.schema.validation"));
                kafkaTopicInfo.setConfluentValueSubjectNameStrategy(getValue(config, "confluent.value.subject.name.strategy"));
                kafkaTopicInfo.setDeleteRetentionMs(getValue(config, TopicConfig.DELETE_RETENTION_MS_CONFIG));
                kafkaTopicInfo.setFileDeleteDelayMs(getValue(config, TopicConfig.FILE_DELETE_DELAY_MS_CONFIG));
                kafkaTopicInfo.setFlushMessages(getValue(config, TopicConfig.FLUSH_MESSAGES_INTERVAL_CONFIG));
                kafkaTopicInfo.setFlushMs(getValue(config, TopicConfig.FLUSH_MS_CONFIG));
                kafkaTopicInfo.setFollowerReplicationThrottledReplicas(getValue(config, "follower.replication.throttled.replicas"));
                kafkaTopicInfo.setIndexIntervalBytes(getValue(config, TopicConfig.INDEX_INTERVAL_BYTES_CONFIG));
                kafkaTopicInfo.setLeaderReplicationThrottledReplicas(getValue(config, "leader.replication.throttled.replicas"));
                kafkaTopicInfo.setMaxCompactionLagMs(getValue(config, TopicConfig.MAX_COMPACTION_LAG_MS_CONFIG));
                kafkaTopicInfo.setMaxMessageBytes(getValue(config, TopicConfig.MAX_MESSAGE_BYTES_CONFIG));
                kafkaTopicInfo.setMessageFormatVersion(getValue(config, TopicConfig.MESSAGE_FORMAT_VERSION_CONFIG));
                kafkaTopicInfo.setMessageTimestampDifferenceMaxMs(getValue(config, TopicConfig.MESSAGE_TIMESTAMP_DIFFERENCE_MAX_MS_CONFIG));
                kafkaTopicInfo.setMessageTimestampType(getValue(config, TopicConfig.MESSAGE_TIMESTAMP_TYPE_CONFIG));
                kafkaTopicInfo.setMinCleanableDirtyRatio(getValue(config, TopicConfig.MIN_CLEANABLE_DIRTY_RATIO_CONFIG));
                kafkaTopicInfo.setMinInsyncReplicas(getValue(config, TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG));
                kafkaTopicInfo.setPreallocate(getValue(config, TopicConfig.PREALLOCATE_CONFIG));
                kafkaTopicInfo.setRetentionBytes(getValue(config, TopicConfig.RETENTION_BYTES_CONFIG));
                kafkaTopicInfo.setRetentionMs(getValue(config, TopicConfig.RETENTION_MS_CONFIG));
                kafkaTopicInfo.setSegmentIndexBytes(getValue(config, TopicConfig.SEGMENT_INDEX_BYTES_CONFIG));
                kafkaTopicInfo.setSegmentJitterMs(getValue(config, TopicConfig.SEGMENT_JITTER_MS_CONFIG));
                kafkaTopicInfo.setSegmentMs(getValue(config, TopicConfig.SEGMENT_MS_CONFIG));
                kafkaTopicInfo.setUncleanLeaderElectionEnable(getValue(config, TopicConfig.UNCLEAN_LEADER_ELECTION_ENABLE_CONFIG));
                kafkaTopicInfo.setConfluentPlacementConstraints(getValue(config, "confluent.placement.constraints"));
                kafkaTopicInfo.setMessageDownconversionEnable(getValue(config, TopicConfig.MESSAGE_DOWNCONVERSION_ENABLE_CONFIG));
                return kafkaTopicInfo;
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return null;
    }

    private String getValue(Config config, String name) {
        ConfigEntry configEntry = config.get(name);
        return configEntry != null ? configEntry.value() : null;
    }
}
