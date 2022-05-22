package ru.babaninnv.worktools.kafka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaTopicInfo {
    /**
     * cleanup.policy
     * A string that is either "delete" or "compact" or both. This string designates the retention policy to use on old log segments. The default policy ("delete") will discard old segments when their retention time or size limit has been reached. The "compact" setting will enable log compaction on the topic.
     *
     * Type:	list
     * Default:	delete
     * Valid Values:	[compact, delete]
     * Server Default Property:	log.cleanup.policy
     * Importance:	medium
     * */
    private String cleanupPolicy;

    /**
     * compression.type
     * Specify the final compression type for a given topic. This configuration accepts the standard compression codecs ('gzip', 'snappy', 'lz4', 'zstd'). It additionally accepts 'uncompressed' which is equivalent to no compression; and 'producer' which means retain the original compression codec set by the producer.
     *
     * Type:	string
     * Default:	producer
     * Valid Values:	[uncompressed, zstd, lz4, snappy, gzip, producer]
     * Server Default Property:	compression.type
     * Importance:	medium
     * */
    private String compressionType;

    /**
     * confluent.key.schema.validation
     * True if schema validation at record key is enabled for this topic.
     *
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Server Default Property:	null
     * Importance:	medium
     */
    private String confluentKeySchemaValidation;

    /**
     * confluent.key.subject.name.strategy
     * Determines how to construct the subject name under which the key schema is registered with the schema registry. By default, TopicNameStrategy is used
     *
     * Type:	string
     * Default:	io.confluent.kafka.serializers.subject.TopicNameStrategy
     * Valid Values:
     * Server Default Property:	null
     * Importance:	medium
     */
    private String confluentKeySubjectNameStrategy;

    /**
     * confluent.value.schema.validation
     * True if schema validation at record value is enabled for this topic.
     *
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Server Default Property:	null
     * Importance:	medium
     */
    private String confluentValueSchemaValidation;

    /**
     * confluent.value.subject.name.strategy
     * Determines how to construct the subject name under which the value schema is registered with the schema registry. By default, TopicNameStrategy is used
     *
     * Type:	string
     * Default:	io.confluent.kafka.serializers.subject.TopicNameStrategy
     * Valid Values:
     * Server Default Property:	null
     * Importance:	medium
     */
    private String confluentValueSubjectNameStrategy;

    /**
     * delete.retention.ms
     * The amount of time to retain delete tombstone markers for log compacted topics. This setting also gives a bound on the time in which a consumer must complete a read if they begin from offset 0 to ensure that they get a valid snapshot of the final stage (otherwise delete tombstones may be collected before they complete their scan).
     *
     * Type:	long
     * Default:	86400000
     * Valid Values:	[0,...]
     * Server Default Property:	log.cleaner.delete.retention.ms
     * Importance:	medium
     */
    private String deleteRetentionMs;

    /**
     * file.delete.delay.ms
     * The time to wait before deleting a file from the filesystem
     *
     * Type:	long
     * Default:	60000
     * Valid Values:	[0,...]
     * Server Default Property:	log.segment.delete.delay.ms
     * Importance:	medium
     */
    private String fileDeleteDelayMs;

    /**
     * flush.messages
     * This setting allows specifying an interval at which we will force an fsync of data written to the log. For example if this was set to 1 we would fsync after every message; if it were 5 we would fsync after every five messages. In general we recommend you not set this and use replication for durability and allow the operating system's background flush capabilities as it is more efficient. This setting can be overridden on a per-topic basis (see the per-topic configuration section).
     *
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:	[0,...]
     * Server Default Property:	log.flush.interval.messages
     * Importance:	medium
     */
    private String flushMessages;

    /**
     * flush.ms
     * This setting allows specifying a time interval at which we will force an fsync of data written to the log. For example if this was set to 1000 we would fsync after 1000 ms had passed. In general we recommend you not set this and use replication for durability and allow the operating system's background flush capabilities as it is more efficient.
     *
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:	[0,...]
     * Server Default Property:	log.flush.interval.ms
     * Importance:	medium
     */
    private String flushMs;

    /**
     * follower.replication.throttled.replicas
     * A list of replicas for which log replication should be throttled on the follower side. The list should describe a set of replicas in the form [PartitionId]:[BrokerId],[PartitionId]:[BrokerId]:... or alternatively the wildcard '*' can be used to throttle all replicas for this topic.
     *
     * Type:	list
     * Default:	""
     * Valid Values:	[partitionId]:[brokerId],[partitionId]:[brokerId],...
     * Server Default Property:	follower.replication.throttled.replicas
     * Importance:	medium
     */
    private String followerReplicationThrottledReplicas;

    /**
     * index.interval.bytes
     * This setting controls how frequently Kafka adds an index entry to its offset index. The default setting ensures that we index a message roughly every 4096 bytes. More indexing allows reads to jump closer to the exact position in the log but makes the index larger. You probably don't need to change this.
     *
     * Type:	int
     * Default:	4096
     * Valid Values:	[0,...]
     * Server Default Property:	log.index.interval.bytes
     * Importance:	medium
     */
    private String indexIntervalBytes;

    /**
     * leader.replication.throttled.replicas
     * A list of replicas for which log replication should be throttled on the leader side. The list should describe a set of replicas in the form [PartitionId]:[BrokerId],[PartitionId]:[BrokerId]:... or alternatively the wildcard '*' can be used to throttle all replicas for this topic.
     *
     * Type:	list
     * Default:	""
     * Valid Values:	[partitionId]:[brokerId],[partitionId]:[brokerId],...
     * Server Default Property:	leader.replication.throttled.replicas
     * Importance:	medium
     */
    private String leaderReplicationThrottledReplicas;

    /**
     * max.compaction.lag.ms
     * The maximum time a message will remain ineligible for compaction in the log. Only applicable for logs that are being compacted.
     *
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:	[1,...]
     * Server Default Property:	log.cleaner.max.compaction.lag.ms
     * Importance:	medium
     */
    private String maxCompactionLagMs;

    /**
     * max.message.bytes
     * The largest record batch size allowed by Kafka (after compression if compression is enabled). If this is increased and there are consumers older than 0.10.2, the consumers' fetch size must also be increased so that the they can fetch record batches this large. In the latest message format version, records are always grouped into batches for efficiency. In previous message format versions, uncompressed records are not grouped into batches and this limit only applies to a single record in that case.
     *
     * Type:	int
     * Default:	1048588
     * Valid Values:	[0,...]
     * Server Default Property:	message.max.bytes
     * Importance:	medium
     */
    private String maxMessageBytes;

    /**
     * message.format.version
     * Specify the message format version the broker will use to append messages to the logs. The value should be a valid ApiVersion. Some examples are: 0.8.2, 0.9.0.0, 0.10.0, check ApiVersion for more details. By setting a particular message format version, the user is certifying that all the existing messages on disk are smaller or equal than the specified version. Setting this value incorrectly will cause consumers with older versions to break as they will receive messages with a format that they don't understand.
     *
     * Type:	string
     * Default:	2.5-IV0
     * Valid Values:	[0.8.0, 0.8.1, 0.8.2, 0.9.0, 0.10.0-IV0, 0.10.0-IV1, 0.10.1-IV0, 0.10.1-IV1, 0.10.1-IV2, 0.10.2-IV0, 0.11.0-IV0, 0.11.0-IV1, 0.11.0-IV2, 1.0-IV0, 1.1-IV0, 2.0-IV0, 2.0-IV1, 2.1-IV0, 2.1-IV1, 2.1-IV2, 2.2-IV0, 2.2-IV1, 2.3-IV0, 2.3-IV1, 2.4-IV0, 2.4-IV1, 2.5-IV0]
     * Server Default Property:	log.message.format.version
     * Importance:	medium
     */
    private String messageFormatVersion;

    /**
     * message.timestamp.difference.max.ms
     * The maximum difference allowed between the timestamp when a broker receives a message and the timestamp specified in the message. If message.timestamp.type=CreateTime, a message will be rejected if the difference in timestamp exceeds this threshold. This configuration is ignored if message.timestamp.type=LogAppendTime.
     *
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:	[0,...]
     * Server Default Property:	log.message.timestamp.difference.max.ms
     * Importance:	medium
     */
    private String messageTimestampDifferenceMaxMs;

    /**
     * message.timestamp.type
     * Define whether the timestamp in the message is message create time or log append time. The value should be either `CreateTime` or `LogAppendTime`
     *
     * Type:	string
     * Default:	CreateTime
     * Valid Values:	[CreateTime, LogAppendTime]
     * Server Default Property:	log.message.timestamp.type
     * Importance:	medium
     */
    private String messageTimestampType;

    /**
     * min.cleanable.dirty.ratio
     * This configuration controls how frequently the log compactor will attempt to clean the log (assuming log compaction is enabled). By default we will avoid cleaning a log where more than 50% of the log has been compacted. This ratio bounds the maximum space wasted in the log by duplicates (at 50% at most 50% of the log could be duplicates). A higher ratio will mean fewer, more efficient cleanings but will mean more wasted space in the log. If the max.compaction.lag.ms or the min.compaction.lag.ms configurations are also specified, then the log compactor considers the log to be eligible for compaction as soon as either: (i) the dirty ratio threshold has been met and the log has had dirty (uncompacted) records for at least the min.compaction.lag.ms duration, or (ii) if the log has had dirty (uncompacted) records for at most the max.compaction.lag.ms period.
     *
     * Type:	double
     * Default:	0.5
     * Valid Values:	[0,...,1]
     * Server Default Property:	log.cleaner.min.cleanable.ratio
     * Importance:	medium
     */
    private String minCleanableDirtyRatio;

    /**
     * min.insync.replicas
     * When a producer sets acks to "all" (or "-1"), this configuration specifies the minimum number of replicas that must acknowledge a write for the write to be considered successful. If this minimum cannot be met, then the producer will raise an exception (either NotEnoughReplicas or NotEnoughReplicasAfterAppend).
     * When used together, min.insync.replicas and acks allow you to enforce greater durability guarantees. A typical scenario would be to create a topic with a replication factor of 3, set min.insync.replicas to 2, and produce with acks of "all". This will ensure that the producer raises an exception if a majority of replicas do not receive a write.
     *
     * Type:	int
     * Default:	1
     * Valid Values:	[1,...]
     * Server Default Property:	min.insync.replicas
     * Importance:	medium
     */
    private String minInsyncReplicas;
    /**
     * preallocate
     * True if we should preallocate the file on disk when creating a new log segment.
     *
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Server Default Property:	log.preallocate
     * Importance:	medium
     */
    private String preallocate;

    /**
     * retention.bytes
     * This configuration controls the maximum size a partition (which consists of log segments) can grow to before we will discard old log segments to free up space if we are using the "delete" retention policy. By default there is no size limit only a time limit. Since this limit is enforced at the partition level, multiply it by the number of partitions to compute the topic retention in bytes.
     *
     * Type:	long
     * Default:	-1
     * Valid Values:
     * Server Default Property:	log.retention.bytes
     * Importance:	medium
     */
    private String retentionBytes;

    /**
     * retention.ms
     * This configuration controls the maximum time we will retain a log before we will discard old log segments to free up space if we are using the "delete" retention policy. This represents an SLA on how soon consumers must read their data. If set to -1, no time limit is applied.
     *
     * Type:	long
     * Default:	604800000
     * Valid Values:	[-1,...]
     * Server Default Property:	log.retention.ms
     * Importance:	medium
     */
    private String retentionMs;

    /**
     * segment.index.bytes
     * This configuration controls the size of the index that maps offsets to file positions. We preallocate this index file and shrink it only after log rolls. You generally should not need to change this setting.
     *
     * Type:	int
     * Default:	10485760
     * Valid Values:	[0,...]
     * Server Default Property:	log.index.size.max.bytes
     * Importance:	medium
     */
    private String segmentIndexBytes;

    /**
     * segment.jitter.ms
     * The maximum random jitter subtracted from the scheduled segment roll time to avoid thundering herds of segment rolling
     *
     * Type:	long
     * Default:	0
     * Valid Values:	[0,...]
     * Server Default Property:	log.roll.jitter.ms
     * Importance:	medium
     */
    private String segmentJitterMs;

    /**
     * segment.ms
     * This configuration controls the period of time after which Kafka will force the log to roll even if the segment file isn't full to ensure that retention can delete or compact old data.
     *
     * Type:	long
     * Default:	604800000
     * Valid Values:	[1,...]
     * Server Default Property:	log.roll.ms
     * Importance:	medium
     */
    private String segmentMs;

    /**
     * unclean.leader.election.enable
     * Indicates whether to enable replicas not in the ISR set to be elected as leader as a last resort, even though doing so may result in data loss.
     *
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Server Default Property:	unclean.leader.election.enable
     * Importance:	medium
     */
    private String uncleanLeaderElectionEnable;

    /**
     * confluent.placement.constraints
     * This configuration is a JSON object that controls the set of brokers (replicas) which will always be allowed to join the ISR. And the set of brokers (observers) which are not allowed to join the ISR. The format of JSON is:
     * {
     * "version": 1,
     * "replicas": [
     * {
     * "count": 2,
     * "constraints": {"rack": "east-1"}
     * },
     * {
     * "count": 1,
     * "constraints": {"rack": "east-2"}
     * }
     * ],
     * "observers":[
     * {
     * "count": 1,
     * "constraints": {"rack": "west-1"}
     * }
     * ]
     * }
     *
     * Type:	string
     * Default:	""
     * Valid Values:	kafka.common.TopicPlacement$TopicPlacementValidator@79efed2d
     * Server Default Property:	null
     * Importance:	low
     */
    private String confluentPlacementConstraints;

    /**
     * message.downconversion.enable
     * This configuration controls whether down-conversion of message formats is enabled to satisfy consume requests. When set to false, broker will not perform down-conversion for consumers expecting an older message format. The broker responds with UNSUPPORTED_VERSION error for consume requests from such older clients. This configurationdoes not apply to any message format conversion that might be required for replication to followers.
     *
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Server Default Property:	log.message.downconversion.enable
     * Importance:	low
     */
    private String messageDownconversionEnable;
}
