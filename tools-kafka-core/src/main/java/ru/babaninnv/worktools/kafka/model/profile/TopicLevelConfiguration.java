package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Data;

@Data
public class TopicLevelConfiguration {

    /**
     * <b>cleanup.policy</b>
     * <br/>
     * <p>A string that is either "delete" or "compact" or both. This string designates the retention policy to use on old log segments. The default policy ("delete") will discard old segments when their retention time or size limit has been reached. The "compact" setting will enable log compaction on the topic.</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	delete</li>
     *   <li>Valid Values:	[compact, delete]</li>
     *   <li>Server Default Property:	log.cleanup.policy</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String cleanupPolicy;

    /**
     * <b>compression.type</b>
     * <br/>
     * <p>Specify the final compression type for a given topic. This configuration accepts the standard compression codecs ('gzip', 'snappy', 'lz4', 'zstd'). It additionally accepts 'uncompressed' which is equivalent to no compression; and 'producer' which means retain the original compression codec set by the producer.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	producer</li>
     *   <li>Valid Values:	[uncompressed, zstd, lz4, snappy, gzip, producer]</li>
     *   <li>Server Default Property:	compression.type</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String compressionType;

    /**
     * <b>delete.retention.ms</b>
     * <br/>
     * <p>The amount of time to retain delete tombstone markers for log compacted topics. This setting also gives a bound on the time in which a consumer must complete a read if they begin from offset 0 to ensure that they get a valid snapshot of the final stage (otherwise delete tombstones may be collected before they complete their scan).</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	86400000</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.cleaner.delete.retention.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String deleteRetentionMs;

    /**
     * <b>file.delete.delay.ms</b>
     * <br/>
     * <p>The time to wait before deleting a file from the filesystem</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	60000</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.segment.delete.delay.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String fileDeleteDelayMs;

    /**
     * <b>flush.messages</b>
     * <br/>
     * <p>This setting allows specifying an interval at which we will force an fsync of data written to the log. For example if this was set to 1 we would fsync after every message; if it were 5 we would fsync after every five messages. In general we recommend you not set this and use replication for durability and allow the operating system's background flush capabilities as it is more efficient. This setting can be overridden on a per-topic basis (see the per-topic configuration section).</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	9223372036854775807</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.flush.interval.messages</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String flushMessages;

    /**
     * <b>flush.ms</b>
     * <br/>
     * <p>This setting allows specifying a time interval at which we will force an fsync of data written to the log. For example if this was set to 1000 we would fsync after 1000 ms had passed. In general we recommend you not set this and use replication for durability and allow the operating system's background flush capabilities as it is more efficient.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	9223372036854775807</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.flush.interval.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String flushMs;

    /**
     * <b>follower.replication.throttled.replicas</b>
     * <br/>
     * <p>A list of replicas for which log replication should be throttled on the follower side. The list should describe a set of replicas in the form [PartitionId]:[BrokerId],[PartitionId]:[BrokerId]:... or alternatively the wildcard '*' can be used to throttle all replicas for this topic.</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	""</li>
     *   <li>Valid Values:	[partitionId]:[brokerId],[partitionId]:[brokerId],...</li>
     *   <li>Server Default Property:	follower.replication.throttled.replicas</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String followerReplicationThrottledReplicas;

    /**
     * <b>index.interval.bytes</b>
     * <br/>
     * <p>This setting controls how frequently Kafka adds an index entry to its offset index. The default setting ensures that we index a message roughly every 4096 bytes. More indexing allows reads to jump closer to the exact position in the log but makes the index larger. You probably don't need to change this.</p>
     * <ul>
     *   <li>Type:	int</li>
     *   <li>Default:	4096</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.index.interval.bytes</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String indexIntervalBytes;

    /**
     * <b>leader.replication.throttled.replicas</b>
     * <br/>
     * <p>A list of replicas for which log replication should be throttled on the leader side. The list should describe a set of replicas in the form [PartitionId]:[BrokerId],[PartitionId]:[BrokerId]:... or alternatively the wildcard '*' can be used to throttle all replicas for this topic.</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	""</li>
     *   <li>Valid Values:	[partitionId]:[brokerId],[partitionId]:[brokerId],...</li>
     *   <li>Server Default Property:	leader.replication.throttled.replicas</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String leaderReplicationThrottledReplicas;

    /**
     * <b>max.compaction.lag.ms</b>
     * <br/>
     * <p>The maximum time a message will remain ineligible for compaction in the log. Only applicable for logs that are being compacted.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	9223372036854775807</li>
     *   <li>Valid Values:	[1,...]</li>
     *   <li>Server Default Property:	log.cleaner.max.compaction.lag.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String maxCompactionLagMs;

    /**
     * <b>max.message.bytes</b>
     * <br/>
     * <p>The largest record batch size allowed by Kafka (after compression if compression is enabled). If this is increased and there are consumers older than 0.10.2, the consumers' fetch size must also be increased so that the they can fetch record batches this large. In the latest message format version, records are always grouped into batches for efficiency. In previous message format versions, uncompressed records are not grouped into batches and this limit only applies to a single record in that case.</p>
     * <ul>
     *   <li>Type:	int</li>
     *   <li>Default:	1048588</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	message.max.bytes</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String maxMessageBytes;

    /**
     * <b>message.format.version</b>
     * <br/>
     * <p>Specify the message format version the broker will use to append messages to the logs. The value should be a valid ApiVersion. Some examples are: 0.8.2, 0.9.0.0, 0.10.0, check ApiVersion for more details. By setting a particular message format version, the user is certifying that all the existing messages on disk are smaller or equal than the specified version. Setting this value incorrectly will cause consumers with older versions to break as they will receive messages with a format that they don't understand.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	2.5-IV0</li>
     *   <li>Valid Values:	[0.8.0, 0.8.1, 0.8.2, 0.9.0, 0.10.0-IV0, 0.10.0-IV1, 0.10.1-IV0, 0.10.1-IV1, 0.10.1-IV2, 0.10.2-IV0, 0.11.0-IV0, 0.11.0-IV1, 0.11.0-IV2, 1.0-IV0, 1.1-IV0, 2.0-IV0, 2.0-IV1, 2.1-IV0, 2.1-IV1, 2.1-IV2, 2.2-IV0, 2.2-IV1, 2.3-IV0, 2.3-IV1, 2.4-IV0, 2.4-IV1, 2.5-IV0]</li>
     *   <li>Server Default Property:	log.message.format.version</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String messageFormatVersion;

    /**
     * <b>message.timestamp.difference.max.ms</b>
     * <br/>
     * <p>The maximum difference allowed between the timestamp when a broker receives a message and the timestamp specified in the message. If message.timestamp.type=CreateTime, a message will be rejected if the difference in timestamp exceeds this threshold. This configuration is ignored if message.timestamp.type=LogAppendTime.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	9223372036854775807</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.message.timestamp.difference.max.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String messageTimestampDifferenceMaxMs;

    /**
     * <b>message.timestamp.type</b>
     * <br/>
     * <p>Define whether the timestamp in the message is message create time or log append time. The value should be either `CreateTime` or `LogAppendTime`</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	CreateTime</li>
     *   <li>Valid Values:	[CreateTime, LogAppendTime]</li>
     *   <li>Server Default Property:	log.message.timestamp.type</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String messageTimestampType;

    /**
     * <b>min.cleanable.dirty.ratio</b>
     * <br/>
     * <p>This configuration controls how frequently the log compactor will attempt to clean the log (assuming log compaction is enabled). By default we will avoid cleaning a log where more than 50% of the log has been compacted. This ratio bounds the maximum space wasted in the log by duplicates (at 50% at most 50% of the log could be duplicates). A higher ratio will mean fewer, more efficient cleanings but will mean more wasted space in the log. If the max.compaction.lag.ms or the min.compaction.lag.ms configurations are also specified, then the log compactor considers the log to be eligible for compaction as soon as either: (i) the dirty ratio threshold has been met and the log has had dirty (uncompacted) records for at least the min.compaction.lag.ms duration, or (ii) if the log has had dirty (uncompacted) records for at most the max.compaction.lag.ms period.</p>
     * <ul>
     *   <li>Type:	double</li>
     *   <li>Default:	0.5</li>
     *   <li>Valid Values:	[0,...,1]</li>
     *   <li>Server Default Property:	log.cleaner.min.cleanable.ratio</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String minCleanableDirtyRatio;

    /**
     * <b>min.compaction.lag.ms</b>
     * <br/>
     * <p>The minimum time a message will remain uncompacted in the log. Only applicable for logs that are being compacted.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	0</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.cleaner.min.compaction.lag.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String minCompactionLagMs;

    /**
     * min.insync.replicas
     * <b>When a producer sets acks to "all" (or "-1"), this configuration specifies the minimum number of replicas that must acknowledge a write for the write to be considered successful. If this minimum cannot be met, then the producer will raise an exception (either NotEnoughReplicas or NotEnoughReplicasAfterAppend).</b>
     * <br/>
     * <p>When used together, min.insync.replicas and acks allow you to enforce greater durability guarantees. A typical scenario would be to create a topic with a replication factor of 3, set min.insync.replicas to 2, and produce with acks of "all". This will ensure that the producer raises an exception if a majority of replicas do not receive a write.</p>
     * <ul>
     *   <li>Type:	int</li>
     *   <li>Default:	1</li>
     *   <li>Valid Values:	[1,...]</li>
     *   <li>Server Default Property:	min.insync.replicas</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String minInsyncReplicas;

    /**
     * <b>preallocate</b>
     * <br/>
     * <p>True if we should preallocate the file on disk when creating a new log segment.</p>
     * <ul>
     *   <li>Type:	boolean</li>
     *   <li>Default:	false</li>
     *   <li>Valid Values:</li>
     *   <li>Server Default Property:	log.preallocate</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String preallocate;

    /**
     * <b>retention.bytes</b>
     * <br/>
     * <p>This configuration controls the maximum size a partition (which consists of log segments) can grow to before we will discard old log segments to free up space if we are using the "delete" retention policy. By default there is no size limit only a time limit. Since this limit is enforced at the partition level, multiply it by the number of partitions to compute the topic retention in bytes.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	-1</li>
     *   <li>Valid Values:</li>
     *   <li>Server Default Property:	log.retention.bytes</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String retentionBytes;

    /**
     * <b>retention.ms</b>
     * <br/>
     * <p>This configuration controls the maximum time we will retain a log before we will discard old log segments to free up space if we are using the "delete" retention policy. This represents an SLA on how soon consumers must read their data. If set to -1, no time limit is applied.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	604800000</li>
     *   <li>Valid Values:	[-1,...]</li>
     *   <li>Server Default Property:	log.retention.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String retentionMs;

    /**
     * <b>segment.bytes</b>
     * <br/>
     * <p>This configuration controls the segment file size for the log. Retention and cleaning is always done a file at a time so a larger segment size means fewer files but less granular control over retention.</p>
     * <ul>
     *   <li>Type:	int</li>
     *   <li>Default:	1073741824</li>
     *   <li>Valid Values:	[14,...]</li>
     *   <li>Server Default Property:	log.segment.bytes</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String segmentBytes;

    /**
     * <b>segment.index.bytes</b>
     * <br/>
     * <p>This configuration controls the size of the index that maps offsets to file positions. We preallocate this index file and shrink it only after log rolls. You generally should not need to change this setting.</p>
     * <ul>
     *   <li>Type:	int</li>
     *   <li>Default:	10485760</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.index.size.max.bytes</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String segmentIndexBytes;

    /**
     * <b>segment.jitter.ms</b>
     * <br/>
     * <p>The maximum random jitter subtracted from the scheduled segment roll time to avoid thundering herds of segment rolling</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	0</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Server Default Property:	log.roll.jitter.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String segmentJitterMs;

    /**
     * <b>segment.ms</b>
     * <br/>
     * <p>This configuration controls the period of time after which Kafka will force the log to roll even if the segment file isn't full to ensure that retention can delete or compact old data.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	604800000</li>
     *   <li>Valid Values:	[1,...]</li>
     *   <li>Server Default Property:	log.roll.ms</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String segmentMs;

    /**
     * <b>unclean.leader.election.enable</b>
     * <br/>
     * <p>Indicates whether to enable replicas not in the ISR set to be elected as leader as a last resort, even though doing so may result in data loss.</p>
     * <ul>
     *   <li>Type:	boolean</li>
     *   <li>Default:	false</li>
     *   <li>Valid Values:</li>
     *   <li>Server Default Property:	unclean.leader.election.enable</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String uncleanLeaderElectionEnable;

    /**
     * <b>message.downconversion.enable</b>
     * <br/>
     * <p>This configuration controls whether down-conversion of message formats is enabled to satisfy consume requests. When set to false, broker will not perform down-conversion for consumers expecting an older message format. The broker responds with UNSUPPORTED_VERSION error for consume requests from such older clients. This configurationdoes not apply to any message format conversion that might be required for replication to followers.</p>
     * <ul>
     *   <li>Type:	boolean</li>
     *   <li>Default:	true</li>
     *   <li>Valid Values:</li>
     *   <li>Server Default Property:	log.message.downconversion.enable</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String messageDownconversionEnable;

}
