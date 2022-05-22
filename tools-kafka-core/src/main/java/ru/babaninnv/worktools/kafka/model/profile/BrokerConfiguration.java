package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Data;

@Data
public class BrokerConfiguration {

    /**
     * zookeeper.connect
     * Specifies the ZooKeeper connection string in the form hostname:port where host and port are the host and port of a ZooKeeper server. To allow connecting through other ZooKeeper nodes when that ZooKeeper machine is down you can also specify multiple hosts in the form hostname1:port1,hostname2:port2,hostname3:port3.
     * The server can also have a ZooKeeper chroot path as part of its ZooKeeper connection string which puts its data under some path in the global ZooKeeper namespace. For example to give a chroot path of /chroot/path you would give the connection string as hostname1:port1,hostname2:port2,hostname3:port3/chroot/path.
     * <p>
     * Type:	string
     * Default:
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String zookeeperConnect;
    /**
     * advertised.host.name
     * DEPRECATED: only used when advertised.listeners or listeners are not set. Use advertised.listeners instead.
     * Hostname to publish to ZooKeeper for clients to use. In IaaS environments, this may need to be different from the interface to which the broker binds. If this is not set, it will use the value for host.name if configured. Otherwise it will use the value returned from java.net.InetAddress.getCanonicalHostName().
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String advertisedHostName;
    /**
     * advertised.listeners
     * Listeners to publish to ZooKeeper for clients to use, if different than the listeners config property. In IaaS environments, this may need to be different from the interface to which the broker binds. If this is not set, the value for listeners will be used. Unlike listeners it is not valid to advertise the 0.0.0.0 meta-address.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	per-broker
     */
    private String advertisedListeners;
    /**
     * advertised.port
     * DEPRECATED: only used when advertised.listeners or listeners are not set. Use advertised.listeners instead.
     * The port to publish to ZooKeeper for clients to use. In IaaS environments, this may need to be different from the port to which the broker binds. If this is not set, it will publish the same port that the broker binds to.
     * <p>
     * Type:	int
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String advertisedPort;
    /**
     * auto.create.topics.enable
     * Enable auto creation of topic on the server
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String autoCreateTopicsEnable;
    /**
     * auto.leader.rebalance.enable
     * Enables auto leader balancing. A background thread checks the distribution of partition leaders at regular intervals, configurable by `leader.imbalance.check.interval.seconds`. If the leader imbalance exceeds `leader.imbalance.per.broker.percentage`, leader rebalance to the preferred leader for partitions is triggered.
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String autoLeaderRebalanceEnable;
    /**
     * background.threads
     * The number of threads to use for various background processing tasks
     * <p>
     * Type:	int
     * Default:	10
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String backgroundThreads;
    /**
     * broker.id
     * The broker id for this server. If unset, a unique broker id will be generated.To avoid conflicts between zookeeper generated broker id's and user configured broker id's, generated broker ids start from reserved.broker.max.id + 1.
     * <p>
     * Type:	int
     * Default:	-1
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String brokerId;
    /**
     * compression.type
     * Specify the final compression type for a given topic. This configuration accepts the standard compression codecs ('gzip', 'snappy', 'lz4', 'zstd'). It additionally accepts 'uncompressed' which is equivalent to no compression; and 'producer' which means retain the original compression codec set by the producer.
     * <p>
     * Type:	string
     * Default:	producer
     * Valid Values:
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String compressionType;

    /**
     * control.plane.listener.name
     * Name of listener used for communication between controller and brokers. Broker will use the control.plane.listener.name to locate the endpoint in listeners list, to listen for connections from the controller. For example, if a broker's config is :
     * listeners = INTERNAL://192.1.1.8:9092, EXTERNAL://10.1.1.5:9093, CONTROLLER://192.1.1.8:9094
     * listener.security.protocol.map = INTERNAL:PLAINTEXT, EXTERNAL:SSL, CONTROLLER:SSL
     * control.plane.listener.name = CONTROLLER
     * On startup, the broker will start listening on "192.1.1.8:9094" with security protocol "SSL".
     * On controller side, when it discovers a broker's published endpoints through zookeeper, it will use the control.plane.listener.name to find the endpoint, which it will use to establish connection to the broker.
     * For example, if the broker's published endpoints on zookeeper are :
     * "endpoints" : ["INTERNAL://broker1.example.com:9092","EXTERNAL://broker1.example.com:9093","CONTROLLER://broker1.example.com:9094"]
     * and the controller's config is :
     * listener.security.protocol.map = INTERNAL:PLAINTEXT, EXTERNAL:SSL, CONTROLLER:SSL
     * control.plane.listener.name = CONTROLLER
     * then controller will use "broker1.example.com:9094" with security protocol "SSL" to connect to the broker.
     * If not explicitly configured, the default value will be null and there will be no dedicated endpoints for controller connections.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String controlPlaneListenerName;
    /**
     * delete.topic.enable
     * Enables delete topic. Delete topic through the admin tool will have no effect if this config is turned off
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String deleteTopicEnable;


    /**
     * host.name
     * DEPRECATED: only used when listeners is not set. Use listeners instead.
     * hostname of broker. If this is set, it will only bind to this address. If this is not set, it will bind to all interfaces
     * <p>
     * Type:	string
     * Default:	""
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String hostName;

    /**
     * leader.imbalance.check.interval.seconds
     * The frequency with which the partition rebalance check is triggered by the controller
     * <p>
     * Type:	long
     * Default:	300
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String leaderImbalanceCheckIntervalSeconds;
    /**
     * leader.imbalance.per.broker.percentage
     * The ratio of leader imbalance allowed per broker. The controller would trigger a leader balance if it goes above this value per broker. The value is specified in percentage.
     * <p>
     * Type:	int
     * Default:	10
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String leaderImbalancePerBrokerPercentage;

    /**
     * listeners
     * Listener List - Comma-separated list of URIs we will listen on and the listener names. If the listener name is not a security protocol, listener.security.protocol.map must also be set.
     * Specify hostname as 0.0.0.0 to bind to all interfaces.
     * Leave hostname empty to bind to default interface.
     * Examples of legal listener lists:
     * PLAINTEXT://myhost:9092,SSL://:9091
     * CLIENT://0.0.0.0:9092,REPLICATION://localhost:9093
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	per-broker
     */
    private String listeners;

    /**
     * log.dir
     * The directory in which the log data is kept (supplemental for log.dirs property)
     * <p>
     * Type:	string
     * Default:	/tmp/kafka-logs
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logDir;
    /**
     * log.dirs
     * The directories in which the log data is kept. If not set, the value in log.dir is used
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logDirs;
    /**
     * log.flush.interval.messages
     * The number of messages accumulated on a log partition before messages are flushed to disk
     * <p>
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String logFlushIntervalMessages;
    /**
     * log.flush.interval.ms
     * The maximum time in ms that a message in any topic is kept in memory before flushed to disk. If not set, the value in log.flush.scheduler.interval.ms is used
     * <p>
     * Type:	long
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String logFlushIntervalMs;
    /**
     * log.flush.offset.checkpoint.interval.ms
     * The frequency with which we update the persistent record of the last flush which acts as the log recovery point
     * <p>
     * Type:	int
     * Default:	60000
     * Valid Values:	[0,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logFlushOffsetCheckpointIntervalMs;
    /**
     * log.flush.scheduler.interval.ms
     * The frequency in ms that the log flusher checks whether any log needs to be flushed to disk
     * <p>
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logFlushSchedulerIntervalMs;
    /**
     * log.flush.start.offset.checkpoint.interval.ms
     * The frequency with which we update the persistent record of log start offset
     * <p>
     * Type:	int
     * Default:	60000
     * Valid Values:	[0,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logFlushStartOffsetCheckpointIntervalMs;
    /**
     * log.retention.bytes
     * The maximum size of the log before deleting it
     * <p>
     * Type:	long
     * Default:	-1
     * Valid Values:
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String logRetentionBytes;
    /**
     * log.retention.hours
     * The number of hours to keep a log file before deleting it (in hours), tertiary to log.retention.ms property
     * <p>
     * Type:	int
     * Default:	168
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logRetentionHours;
    /**
     * log.retention.minutes
     * The number of minutes to keep a log file before deleting it (in minutes), secondary to log.retention.ms property. If not set, the value in log.retention.hours is used
     * <p>
     * Type:	int
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logRetentionMinutes;
    /**
     * log.retention.ms
     * The number of milliseconds to keep a log file before deleting it (in milliseconds), If not set, the value in log.retention.minutes is used. If set to -1, no time limit is applied.
     * <p>
     * Type:	long
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String logRetentionMs;
    /**
     * log.roll.hours
     * The maximum time before a new log segment is rolled out (in hours), secondary to log.roll.ms property
     * <p>
     * Type:	int
     * Default:	168
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logRollHours;
    /**
     * log.roll.jitter.hours
     * The maximum jitter to subtract from logRollTimeMillis (in hours), secondary to log.roll.jitter.ms property
     * <p>
     * Type:	int
     * Default:	0
     * Valid Values:	[0,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String logRollJitterHours;
    /**
     * log.roll.jitter.ms
     * The maximum jitter to subtract from logRollTimeMillis (in milliseconds). If not set, the value in log.roll.jitter.hours is used
     * <p>
     * Type:	long
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String logRollJitterMs;
    /**
     * log.roll.ms
     * The maximum time before a new log segment is rolled out (in milliseconds). If not set, the value in log.roll.hours is used
     * <p>
     * Type:	long
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String logRollMs;
    /**
     * log.segment.bytes
     * The maximum size of a single log file
     * <p>
     * Type:	int
     * Default:	1073741824
     * Valid Values:	[14,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String logSegmentBytes;
    /**
     * log.segment.delete.delay.ms
     * The amount of time to wait before deleting a file from the filesystem
     * <p>
     * Type:	long
     * Default:	60000
     * Valid Values:	[0,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String logSegmentDeleteDelayMs;
    /**
     * message.max.bytes
     * The largest record batch size allowed by Kafka (after compression if compression is enabled). If this is increased and there are consumers older than 0.10.2, the consumers' fetch size must also be increased so that the they can fetch record batches this large. In the latest message format version, records are always grouped into batches for efficiency. In previous message format versions, uncompressed records are not grouped into batches and this limit only applies to a single record in that case.This can be set per topic with the topic level max.message.bytes config.
     * <p>
     * Type:	int
     * Default:	1048588
     * Valid Values:	[0,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String messageMaxBytes;

    /**
     * min.insync.replicas
     * When a producer sets acks to "all" (or "-1"), min.insync.replicas specifies the minimum number of replicas that must acknowledge a write for the write to be considered successful. If this minimum cannot be met, then the producer will raise an exception (either NotEnoughReplicas or NotEnoughReplicasAfterAppend).
     * When used together, min.insync.replicas and acks allow you to enforce greater durability guarantees. A typical scenario would be to create a topic with a replication factor of 3, set min.insync.replicas to 2, and produce with acks of "all". This will ensure that the producer raises an exception if a majority of replicas do not receive a write.
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String minInsyncReplicas;
    /**
     * num.io.threads
     * The number of threads that the server uses for processing requests, which may include disk I/O
     * <p>
     * Type:	int
     * Default:	8
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String numIoThreads;
    /**
     * num.network.threads
     * The number of threads that the server uses for receiving requests from the network and sending responses to the network
     * <p>
     * Type:	int
     * Default:	3
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String numNetworkThreads;
    /**
     * num.recovery.threads.per.data.dir
     * The number of threads per data directory to be used for log recovery at startup and flushing at shutdown
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String numRecoveryThreadsPerDataDir;
    /**
     * num.replica.alter.log.dirs.threads
     * The number of threads that can move replicas between log directories, which may include disk I/O
     * <p>
     * Type:	int
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String numReplicaAlterLogDirsThreads;
    /**
     * num.replica.fetchers
     * Number of fetcher threads used to replicate messages from a source broker. Increasing this value can increase the degree of I/O parallelism in the follower broker.
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String numReplicaFetchers;
    /**
     * offset.metadata.max.bytes
     * The maximum size for a metadata entry associated with an offset commit
     * <p>
     * Type:	int
     * Default:	4096
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetMetadataMaxBytes;
    /**
     * offsets.commit.required.acks
     * The required acks before the commit can be accepted. In general, the default (-1) should not be overridden
     * <p>
     * Type:	short
     * Default:	-1
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsCommitRequiredAcks;
    /**
     * offsets.commit.timeout.ms
     * Offset commit will be delayed until all replicas for the offsets topic receive the commit or this timeout is reached. This is similar to the producer request timeout.
     * <p>
     * Type:	int
     * Default:	5000
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsCommitTimeoutMs;
    /**
     * offsets.load.buffer.size
     * Batch size for reading from the offsets segments when loading offsets into the cache (soft-limit, overridden if records are too large).
     * <p>
     * Type:	int
     * Default:	5242880
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsLoadBufferSize;
    /**
     * offsets.retention.check.interval.ms
     * Frequency at which to check for stale offsets
     * <p>
     * Type:	long
     * Default:	600000
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsRetentionCheckIntervalMs;
    /**
     * offsets.retention.minutes
     * After a consumer group loses all its consumers (i.e. becomes empty) its offsets will be kept for this retention period before getting discarded. For standalone consumers (using manual assignment), offsets will be expired after the time of last commit plus this retention period.
     * <p>
     * Type:	int
     * Default:	10080
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsRetentionMinutes;
    /**
     * offsets.topic.compression.codec
     * Compression codec for the offsets topic - compression may be used to achieve "atomic" commits
     * <p>
     * Type:	int
     * Default:	0
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsTopicCompressionCodec;
    /**
     * offsets.topic.num.partitions
     * The number of partitions for the offset commit topic (should not change after deployment)
     * <p>
     * Type:	int
     * Default:	50
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsTopicNumPartitions;
    /**
     * offsets.topic.replication.factor
     * The replication factor for the offsets topic (set higher to ensure availability). Internal topic creation will fail until the cluster size meets this replication factor requirement.
     * <p>
     * Type:	short
     * Default:	3
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsTopicReplicationFactor;
    /**
     * offsets.topic.segment.bytes
     * The offsets topic segment bytes should be kept relatively small in order to facilitate faster log compaction and cache loads
     * <p>
     * Type:	int
     * Default:	104857600
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String offsetsTopicSegmentBytes;

    /**
     * port
     * DEPRECATED: only used when listeners is not set. Use listeners instead.
     * the port to listen and accept connections on
     * <p>
     * Type:	int
     * Default:	9092
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String port;
    /**
     * queued.max.requests
     * The number of queued requests allowed for data-plane, before blocking the network threads
     * <p>
     * Type:	int
     * Default:	500
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String queuedMaxRequests;
    /**
     * quota.consumer.default
     * DEPRECATED: Used only when dynamic default quotas are not configured for or in Zookeeper. Any consumer distinguished by clientId/consumer group will get throttled if it fetches more bytes than this value per-second
     * <p>
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String quotaConsumerDefault;
    /**
     * quota.producer.default
     * DEPRECATED: Used only when dynamic default quotas are not configured for , or in Zookeeper. Any producer distinguished by clientId will get throttled if it produces more bytes than this value per-second
     * <p>
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String quotaProducerDefault;
    /**
     * replica.fetch.min.bytes
     * Minimum bytes expected for each fetch response. If not enough bytes, wait up to replicaMaxWaitTimeMs
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String replicaFetchMinBytes;
    /**
     * replica.fetch.wait.max.ms
     * max wait time for each fetcher request issued by follower replicas. This value should always be less than the replica.lag.time.max.ms at all times to prevent frequent shrinking of ISR for low throughput topics
     * <p>
     * Type:	int
     * Default:	500
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String replicaFetchWaitMaxMs;
    /**
     * replica.high.watermark.checkpoint.interval.ms
     * The frequency with which the high watermark is saved out to disk
     * <p>
     * Type:	long
     * Default:	5000
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String replicaHighWatermarkCheckpointIntervalMs;
    /**
     * replica.lag.time.max.ms
     * If a follower hasn't sent any fetch requests or hasn't consumed up to the leaders log end offset for at least this time, the leader will remove the follower from isr
     * <p>
     * Type:	long
     * Default:	30000
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String replicaLagTimeMaxMs;
    /**
     * replica.socket.receive.buffer.bytes
     * The socket receive buffer for network requests
     * <p>
     * Type:	int
     * Default:	65536
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String replicaSocketReceiveBufferBytes;
    /**
     * replica.socket.timeout.ms
     * The socket timeout for network requests. Its value should be at least replica.fetch.wait.max.ms
     * <p>
     * Type:	int
     * Default:	30000
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String replicaSocketTimeoutMs;
    /**
     * request.timeout.ms
     * The configuration controls the maximum amount of time the client will wait for the response of a request. If the response is not received before the timeout elapses the client will resend the request if necessary or fail the request if retries are exhausted.
     * <p>
     * Type:	int
     * Default:	30000
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String requestTimeoutMs;
    /**
     * socket.receive.buffer.bytes
     * The SO_RCVBUF buffer of the socket server sockets. If the value is -1, the OS default will be used.
     * <p>
     * Type:	int
     * Default:	102400
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String socketReceiveBufferBytes;
    /**
     * socket.request.max.bytes
     * The maximum number of bytes in a socket request
     * <p>
     * Type:	int
     * Default:	104857600
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String socketRequestMaxBytes;
    /**
     * socket.send.buffer.bytes
     * The SO_SNDBUF buffer of the socket server sockets. If the value is -1, the OS default will be used.
     * <p>
     * Type:	int
     * Default:	102400
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String socketSendBufferBytes;
    /**
     * transaction.max.timeout.ms
     * The maximum allowed timeout for transactions. If a client’s requested transaction time exceed this, then the broker will return an error in InitProducerIdRequest. This prevents a client from too large of a timeout, which can stall consumers reading from topics included in the transaction.
     * <p>
     * Type:	int
     * Default:	900000
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String transactionMaxTimeoutMs;
    /**
     * transaction.state.log.load.buffer.size
     * Batch size for reading from the transaction log segments when loading producer ids and transactions into the cache (soft-limit, overridden if records are too large).
     * <p>
     * Type:	int
     * Default:	5242880
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String transactionStateLogLoadBufferSize;
    /**
     * transaction.state.log.min.isr
     * Overridden min.insync.replicas config for the transaction topic.
     * <p>
     * Type:	int
     * Default:	2
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String transactionStateLogMinIsr;
    /**
     * transaction.state.log.num.partitions
     * The number of partitions for the transaction topic (should not change after deployment).
     * <p>
     * Type:	int
     * Default:	50
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String transactionStateLogNumPartitions;
    /**
     * transaction.state.log.replication.factor
     * The replication factor for the transaction topic (set higher to ensure availability). Internal topic creation will fail until the cluster size meets this replication factor requirement.
     * <p>
     * Type:	short
     * Default:	3
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String transactionStateLogReplicationFactor;
    /**
     * transaction.state.log.segment.bytes
     * The transaction topic segment bytes should be kept relatively small in order to facilitate faster log compaction and cache loads
     * <p>
     * Type:	int
     * Default:	104857600
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String transactionStateLogSegmentBytes;
    /**
     * transactional.id.expiration.ms
     * The time in ms that the transaction coordinator will wait without receiving any transaction status updates for the current transaction before expiring its transactional id. This setting also influences producer id expiration - producer ids are expired once this time has elapsed after the last write with the given producer id. Note that producer ids may expire sooner if the last write from the producer id is deleted due to the topic's retention settings.
     * <p>
     * Type:	int
     * Default:	604800000
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String transactionalIdExpirationMs;
    /**
     * unclean.leader.election.enable
     * Indicates whether to enable replicas not in the ISR set to be elected as leader as a last resort, even though doing so may result in data loss
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	high
     * Update Mode:	cluster-wide
     */
    private String uncleanLeaderElectionEnable;
    /**
     * zookeeper.connection.timeout.ms
     * The max time that the client waits to establish a connection to zookeeper. If not set, the value in zookeeper.session.timeout.ms is used
     * <p>
     * Type:	int
     * Default:	null
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String zookeeperConnectionTimeoutMs;
    /**
     * zookeeper.max.in.flight.requests
     * The maximum number of unacknowledged requests the client will send to Zookeeper before blocking.
     * <p>
     * Type:	int
     * Default:	10
     * Valid Values:	[1,...]
     * Importance:	high
     * Update Mode:	read-only
     */
    private String zookeeperMaxInFlightRequests;
    /**
     * zookeeper.session.timeout.ms
     * Zookeeper session timeout
     * <p>
     * Type:	int
     * Default:	18000
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String zookeeperSessionTimeoutMs;
    /**
     * zookeeper.set.acl
     * Set client to use secure ACLs
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	high
     * Update Mode:	read-only
     */
    private String zookeeperSetAcl;
    /**
     * broker.id.generation.enable
     * Enable automatic broker id generation on the server. When enabled the value configured for reserved.broker.max.id should be reviewed.
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String brokerIdGenerationEnable;
    /**
     * broker.rack
     * Rack of the broker. This will be used in rack aware replication assignment for fault tolerance. Examples: `RACK1`, `us-east-1d`
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String brokerRack;
    /**
     * connections.max.idle.ms
     * Idle connections timeout: the server socket processor threads close the connections that idle more than this
     * <p>
     * Type:	long
     * Default:	600000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String connectionsMaxIdleMs;
    /**
     * connections.max.reauth.ms
     * When explicitly set to a positive number (the default is 0, not a positive number), a session lifetime that will not exceed the configured value will be communicated to v2.2.0 or later clients when they authenticate. The broker will disconnect any such connection that is not re-authenticated within the session lifetime and that is then subsequently used for any purpose other than re-authentication. Configuration names can optionally be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.oauthbearer.connections.max.reauth.ms=3600000
     * <p>
     * Type:	long
     * Default:	0
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String connectionsMaxReauthMs;
    /**
     * controlled.shutdown.enable
     * Enable controlled shutdown of the server
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String controlledShutdownEnable;
    /**
     * controlled.shutdown.max.retries
     * Controlled shutdown can fail for multiple reasons. This determines the number of retries when such failure happens
     * <p>
     * Type:	int
     * Default:	3
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String controlledShutdownMaxRetries;
    /**
     * controlled.shutdown.retry.backoff.ms
     * Before each retry, the system needs time to recover from the state that caused the previous failure (Controller fail over, replica lag etc). This config determines the amount of time to wait before retrying.
     * <p>
     * Type:	long
     * Default:	5000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String controlledShutdownRetryBackoffMs;
    /**
     * controller.socket.timeout.ms
     * The socket timeout for controller-to-broker channels
     * <p>
     * Type:	int
     * Default:	30000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String controllerSocketTimeoutMs;
    /**
     * default.replication.factor
     * default replication factors for automatically created topics
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String defaultReplicationFactor;
    /**
     * delegation.token.expiry.time.ms
     * The token validity time in miliseconds before the token needs to be renewed. Default value 1 day.
     * <p>
     * Type:	long
     * Default:	86400000
     * Valid Values:	[1,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String delegationTokenExpiryTimeMs;
    /**
     * delegation.token.master.key
     * Master/secret key to generate and verify delegation tokens. Same key must be configured across all the brokers. If the key is not set or set to empty string, brokers will disable the delegation token support.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String delegationTokenMasterKey;
    /**
     * delegation.token.max.lifetime.ms
     * The token has a maximum lifetime beyond which it cannot be renewed anymore. Default value 7 days.
     * <p>
     * Type:	long
     * Default:	604800000
     * Valid Values:	[1,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String delegationTokenMaxLifetimeMs;
    /**
     * delete.records.purgatory.purge.interval.requests
     * The purge interval (in number of requests) of the delete records request purgatory
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String deleteRecordsPurgatoryPurgeIntervalRequests;
    /**
     * fetch.max.bytes
     * The maximum number of bytes we will return for a fetch request. Must be at least 1024.
     * <p>
     * Type:	int
     * Default:	57671680
     * Valid Values:	[1024,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String fetchMaxBytes;
    /**
     * fetch.purgatory.purge.interval.requests
     * The purge interval (in number of requests) of the fetch request purgatory
     * <p>
     * Type:	int
     * Default:	1000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String fetchPurgatoryPurgeIntervalRequests;
    /**
     * group.initial.rebalance.delay.ms
     * The amount of time the group coordinator will wait for more consumers to join a new group before performing the first rebalance. A longer delay means potentially fewer rebalances, but increases the time until processing begins.
     * <p>
     * Type:	int
     * Default:	3000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String groupInitialRebalanceDelayMs;
    /**
     * group.max.session.timeout.ms
     * The maximum allowed session timeout for registered consumers. Longer timeouts give consumers more time to process messages in between heartbeats at the cost of a longer time to detect failures.
     * <p>
     * Type:	int
     * Default:	1800000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String groupMaxSessionTimeoutMs;
    /**
     * group.max.size
     * The maximum number of consumers that a single consumer group can accommodate.
     * <p>
     * Type:	int
     * Default:	2147483647
     * Valid Values:	[1,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String groupMaxSize;
    /**
     * group.min.session.timeout.ms
     * The minimum allowed session timeout for registered consumers. Shorter timeouts result in quicker failure detection at the cost of more frequent consumer heartbeating, which can overwhelm broker resources.
     * <p>
     * Type:	int
     * Default:	6000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String groupMinSessionTimeoutMs;
    /**
     * inter.broker.listener.name
     * Name of listener used for communication between brokers. If this is unset, the listener name is defined by security.inter.broker.protocol. It is an error to set this and security.inter.broker.protocol properties at the same time.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String interBrokerListenerName;

    /**
     * inter.broker.protocol.version
     * Specify which version of the inter-broker protocol will be used.
     * This is typically bumped after all brokers were upgraded to a new version.
     * Example of some valid values are: 0.8.0, 0.8.1, 0.8.1.1, 0.8.2, 0.8.2.0, 0.8.2.1, 0.9.0.0, 0.9.0.1 Check ApiVersion for the full list.
     * <p>
     * Type:	string
     * Default:	2.5-IV0
     * Valid Values:	[0.8.0, 0.8.1, 0.8.2, 0.9.0, 0.10.0-IV0, 0.10.0-IV1, 0.10.1-IV0, 0.10.1-IV1, 0.10.1-IV2, 0.10.2-IV0, 0.11.0-IV0, 0.11.0-IV1, 0.11.0-IV2, 1.0-IV0, 1.1-IV0, 2.0-IV0, 2.0-IV1, 2.1-IV0, 2.1-IV1, 2.1-IV2, 2.2-IV0, 2.2-IV1, 2.3-IV0, 2.3-IV1, 2.4-IV0, 2.4-IV1, 2.5-IV0]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String interBrokerProtocolVersion;

    /**
     * log.cleaner.backoff.ms
     * The amount of time to sleep when there are no logs to clean
     * <p>
     * Type:	long
     * Default:	15000
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerBackoffMs;
    /**
     * log.cleaner.dedupe.buffer.size
     * The total memory used for log deduplication across all cleaner threads
     * <p>
     * Type:	long
     * Default:	134217728
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerDedupeBufferSize;
    /**
     * log.cleaner.delete.retention.ms
     * How long are delete records retained?
     * <p>
     * Type:	long
     * Default:	86400000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerDeleteRetentionMs;
    /**
     * log.cleaner.enable
     * Enable the log cleaner process to run on the server. Should be enabled if using any topics with a cleanup.policy=compact including the internal offsets topic. If disabled those topics will not be compacted and continually grow in size.
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String logCleanerEnable;
    /**
     * log.cleaner.io.buffer.load.factor
     * Log cleaner dedupe buffer load factor. The percentage full the dedupe buffer can become. A higher value will allow more log to be cleaned at once but will lead to more hash collisions
     * <p>
     * Type:	double
     * Default:	0.9
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerIoBufferLoadFactor;
    /**
     * log.cleaner.io.buffer.size
     * The total memory used for log cleaner I/O buffers across all cleaner threads
     * <p>
     * Type:	int
     * Default:	524288
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerIoBufferSize;
    /**
     * log.cleaner.io.max.bytes.per.second
     * The log cleaner will be throttled so that the sum of its read and write i/o will be less than this value on average
     * <p>
     * Type:	double
     * Default:	1.7976931348623157E308
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerIoMaxBytesPerSecond;
    /**
     * log.cleaner.max.compaction.lag.ms
     * The maximum time a message will remain ineligible for compaction in the log. Only applicable for logs that are being compacted.
     * <p>
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerMaxCompactionLagMs;
    /**
     * log.cleaner.min.cleanable.ratio
     * The minimum ratio of dirty log to total log for a log to eligible for cleaning. If the log.cleaner.max.compaction.lag.ms or the log.cleaner.min.compaction.lag.ms configurations are also specified, then the log compactor considers the log eligible for compaction as soon as either: (i) the dirty ratio threshold has been met and the log has had dirty (uncompacted) records for at least the log.cleaner.min.compaction.lag.ms duration, or (ii) if the log has had dirty (uncompacted) records for at most the log.cleaner.max.compaction.lag.ms period.
     * <p>
     * Type:	double
     * Default:	0.5
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerMinCleanableRatio;
    /**
     * log.cleaner.min.compaction.lag.ms
     * The minimum time a message will remain uncompacted in the log. Only applicable for logs that are being compacted.
     * <p>
     * Type:	long
     * Default:	0
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerMinCompactionLagMs;
    /**
     * log.cleaner.threads
     * The number of background threads to use for log cleaning
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanerThreads;
    /**
     * log.cleanup.policy
     * The default cleanup policy for segments beyond the retention window. A comma separated list of valid policies. Valid policies are: "delete" and "compact"
     * <p>
     * Type:	list
     * Default:	delete
     * Valid Values:	[compact, delete]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logCleanupPolicy;
    /**
     * log.index.interval.bytes
     * The interval with which we add an entry to the offset index
     * <p>
     * Type:	int
     * Default:	4096
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logIndexIntervalBytes;
    /**
     * log.index.size.max.bytes
     * The maximum size in bytes of the offset index
     * <p>
     * Type:	int
     * Default:	10485760
     * Valid Values:	[4,...]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logIndexSizeMaxBytes;
    /**
     * log.message.format.version
     * Specify the message format version the broker will use to append messages to the logs. The value should be a valid ApiVersion. Some examples are: 0.8.2, 0.9.0.0, 0.10.0, check ApiVersion for more details. By setting a particular message format version, the user is certifying that all the existing messages on disk are smaller or equal than the specified version. Setting this value incorrectly will cause consumers with older versions to break as they will receive messages with a format that they don't understand.
     * <p>
     * Type:	string
     * Default:	2.5-IV0
     * Valid Values:	[0.8.0, 0.8.1, 0.8.2, 0.9.0, 0.10.0-IV0, 0.10.0-IV1, 0.10.1-IV0, 0.10.1-IV1, 0.10.1-IV2, 0.10.2-IV0, 0.11.0-IV0, 0.11.0-IV1, 0.11.0-IV2, 1.0-IV0, 1.1-IV0, 2.0-IV0, 2.0-IV1, 2.1-IV0, 2.1-IV1, 2.1-IV2, 2.2-IV0, 2.2-IV1, 2.3-IV0, 2.3-IV1, 2.4-IV0, 2.4-IV1, 2.5-IV0]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String logMessageFormatVersion;
    /**
     * log.message.timestamp.difference.max.ms
     * The maximum difference allowed between the timestamp when a broker receives a message and the timestamp specified in the message. If log.message.timestamp.type=CreateTime, a message will be rejected if the difference in timestamp exceeds this threshold. This configuration is ignored if log.message.timestamp.type=LogAppendTime.The maximum timestamp difference allowed should be no greater than log.retention.ms to avoid unnecessarily frequent log rolling.
     * <p>
     * Type:	long
     * Default:	9223372036854775807
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logMessageTimestampDifferenceMaxMs;
    /**
     * log.message.timestamp.type
     * Define whether the timestamp in the message is message create time or log append time. The value should be either `CreateTime` or `LogAppendTime`
     * <p>
     * Type:	string
     * Default:	CreateTime
     * Valid Values:	[CreateTime, LogAppendTime]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logMessageTimestampType;
    /**
     * log.preallocate
     * Should pre allocate file when create new segment? If you are using Kafka on Windows, you probably need to set it to true.
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String logPreallocate;
    /**
     * log.retention.check.interval.ms
     * The frequency in milliseconds that the log cleaner checks whether any log is eligible for deletion
     * <p>
     * Type:	long
     * Default:	300000
     * Valid Values:	[1,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String logRetentionCheckIntervalMs;
    /**
     * max.connections
     * The maximum number of connections we allow in the broker at any time. This limit is applied in addition to any per-ip limits configured using max.connections.per.ip. Listener-level limits may also be configured by prefixing the config name with the listener prefix, for example, listener.name.internal.max.connections. Broker-wide limit should be configured based on broker capacity while listener limits should be configured based on application requirements. New connections are blocked if either the listener or broker limit is reached. Connections on the inter-broker listener are permitted even if broker-wide limit is reached. The least recently used connection on another listener will be closed in this case.
     * <p>
     * Type:	int
     * Default:	2147483647
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String maxConnections;
    /**
     * max.connections.per.ip
     * The maximum number of connections we allow from each ip address. This can be set to 0 if there are overrides configured using max.connections.per.ip.overrides property. New connections from the ip address are dropped if the limit is reached.
     * <p>
     * Type:	int
     * Default:	2147483647
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String maxConnectionsPerIp;
    /**
     * max.connections.per.ip.overrides
     * A comma-separated list of per-ip or hostname overrides to the default maximum number of connections. An example value is "hostName:100,127.0.0.1:200"
     * <p>
     * Type:	string
     * Default:	""
     * Valid Values:
     * Importance:	medium
     * Update Mode:	cluster-wide
     */
    private String maxConnectionsPerIpOverrides;
    /**
     * max.incremental.fetch.session.cache.slots
     * The maximum number of incremental fetch sessions that we will maintain.
     * <p>
     * Type:	int
     * Default:	1000
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String maxIncrementalFetchSessionCacheSlots;
    /**
     * num.partitions
     * The default number of log partitions per topic
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[1,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String numPartitions;
    /**
     * password.encoder.old.secret
     * The old secret that was used for encoding dynamically configured passwords. This is required only when the secret is updated. If specified, all dynamically encoded passwords are decoded using this old secret and re-encoded using password.encoder.secret when broker starts up.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String passwordEncoderOldSecret;
    /**
     * password.encoder.secret
     * The secret used for encoding dynamically configured passwords for this broker.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String passwordEncoderSecret;
    /**
     * principal.builder.class
     * The fully qualified name of a class that implements the KafkaPrincipalBuilder interface, which is used to build the KafkaPrincipal object used during authorization. This config also supports the deprecated PrincipalBuilder interface which was previously used for client authentication over SSL. If no principal builder is defined, the default behavior depends on the security protocol in use. For SSL authentication, the principal will be derived using the rules defined by ssl.principal.mapping.rules applied on the distinguished name from the client certificate if one is provided; otherwise, if client authentication is not required, the principal name will be ANONYMOUS. For SASL authentication, the principal will be derived using the rules defined by sasl.kerberos.principal.to.local.rules if GSSAPI is in use, and the SASL authentication ID for other mechanisms. For PLAINTEXT, the principal will be ANONYMOUS.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String principalBuilderClass;
    /**
     * producer.purgatory.purge.interval.requests
     * The purge interval (in number of requests) of the producer request purgatory
     * <p>
     * Type:	int
     * Default:	1000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String producerPurgatoryPurgeIntervalRequests;
    /**
     * queued.max.request.bytes
     * The number of queued bytes allowed before no more requests are read
     * <p>
     * Type:	long
     * Default:	-1
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String queuedMaxRequestBytes;
    /**
     * replica.fetch.backoff.ms
     * The amount of time to sleep when fetch partition error occurs.
     * <p>
     * Type:	int
     * Default:	1000
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String replicaFetchBackoffMs;
    /**
     * replica.fetch.max.bytes
     * The number of bytes of messages to attempt to fetch for each partition. This is not an absolute maximum, if the first record batch in the first non-empty partition of the fetch is larger than this value, the record batch will still be returned to ensure that progress can be made. The maximum record batch size accepted by the broker is defined via message.max.bytes (broker config) or max.message.bytes (topic config).
     * <p>
     * Type:	int
     * Default:	1048576
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String replicaFetchMaxBytes;
    /**
     * replica.fetch.response.max.bytes
     * Maximum bytes expected for the entire fetch response. Records are fetched in batches, and if the first record batch in the first non-empty partition of the fetch is larger than this value, the record batch will still be returned to ensure that progress can be made. As such, this is not an absolute maximum. The maximum record batch size accepted by the broker is defined via message.max.bytes (broker config) or max.message.bytes (topic config).
     * <p>
     * Type:	int
     * Default:	10485760
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String replicaFetchResponseMaxBytes;
    /**
     * replica.selector.class
     * The fully qualified class name that implements ReplicaSelector. This is used by the broker to find the preferred read replica. By default, we use an implementation that returns the leader.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String replicaSelectorClass;
    /**
     * reserved.broker.max.id
     * Max number that can be used for a broker.id
     * <p>
     * Type:	int
     * Default:	1000
     * Valid Values:	[0,...]
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String reservedBrokerMaxId;
    /**
     * sasl.client.callback.handler.class
     * The fully qualified name of a SASL client callback handler class that implements the AuthenticateCallbackHandler interface.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String saslClientCallbackHandlerClass;
    /**
     * sasl.enabled.mechanisms
     * The list of SASL mechanisms enabled in the Kafka server. The list may contain any mechanism for which a security provider is available. Only GSSAPI is enabled by default.
     * <p>
     * Type:	list
     * Default:	GSSAPI
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslEnabledMechanisms;
    /**
     * sasl.jaas.config
     * JAAS login context parameters for SASL connections in the format used by JAAS configuration files. JAAS configuration file format is described here. The format for the value is: 'loginModuleClass controlFlag (optionName=optionValue)*;'. For brokers, the config must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.scram-sha-256.sasl.jaas.config=com.example.ScramLoginModule required;
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslJaasConfig;
    /**
     * sasl.kerberos.kinit.cmd
     * Kerberos kinit command path.
     * <p>
     * Type:	string
     * Default:	/usr/bin/kinit
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslKerberosKinitCmd;
    /**
     * sasl.kerberos.min.time.before.relogin
     * Login thread sleep time between refresh attempts.
     * <p>
     * Type:	long
     * Default:	60000
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslKerberosMinTimeBeforeRelogin;
    /**
     * sasl.kerberos.principal.to.local.rules
     * A list of rules for mapping from principal names to short names (typically operating system usernames). The rules are evaluated in order and the first rule that matches a principal name is used to map it to a short name. Any later rules in the list are ignored. By default, principal names of the form {username}/{hostname}@{REALM} are mapped to {username}. For more details on the format please see security authorization and acls. Note that this configuration is ignored if an extension of KafkaPrincipalBuilder is provided by the principal.builder.class configuration.
     * <p>
     * Type:	list
     * Default:	DEFAULT
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslKerberosPrincipalToLocalRules;
    /**
     * sasl.kerberos.service.name
     * The Kerberos principal name that Kafka runs as. This can be defined either in Kafka's JAAS config or in Kafka's config.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslKerberosServiceName;
    /**
     * sasl.kerberos.ticket.renew.jitter
     * Percentage of random jitter added to the renewal time.
     * <p>
     * Type:	double
     * Default:	0.05
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslKerberosTicketRenewJitter;
    /**
     * sasl.kerberos.ticket.renew.window.factor
     * Login thread will sleep until the specified window factor of time from last refresh to ticket's expiry has been reached, at which time it will try to renew the ticket.
     * <p>
     * Type:	double
     * Default:	0.8
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslKerberosTicketRenewWindowFactor;
    /**
     * sasl.login.callback.handler.class
     * The fully qualified name of a SASL login callback handler class that implements the AuthenticateCallbackHandler interface. For brokers, login callback handler config must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.scram-sha-256.sasl.login.callback.handler.class=com.example.CustomScramLoginCallbackHandler
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String saslLoginCallbackHandlerClass;
    /**
     * sasl.login.class
     * The fully qualified name of a class that implements the Login interface. For brokers, login config must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.scram-sha-256.sasl.login.class=com.example.CustomScramLogin
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String saslLoginClass;
    /**
     * sasl.login.refresh.buffer.seconds
     * The amount of buffer time before credential expiration to maintain when refreshing a credential, in seconds. If a refresh would otherwise occur closer to expiration than the number of buffer seconds then the refresh will be moved up to maintain as much of the buffer time as possible. Legal values are between 0 and 3600 (1 hour); a default value of 300 (5 minutes) is used if no value is specified. This value and sasl.login.refresh.min.period.seconds are both ignored if their sum exceeds the remaining lifetime of a credential. Currently applies only to OAUTHBEARER.
     * <p>
     * Type:	short
     * Default:	300
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslLoginRefreshBufferSeconds;
    /**
     * sasl.login.refresh.min.period.seconds
     * The desired minimum time for the login refresh thread to wait before refreshing a credential, in seconds. Legal values are between 0 and 900 (15 minutes); a default value of 60 (1 minute) is used if no value is specified. This value and sasl.login.refresh.buffer.seconds are both ignored if their sum exceeds the remaining lifetime of a credential. Currently applies only to OAUTHBEARER.
     * <p>
     * Type:	short
     * Default:	60
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslLoginRefreshMinPeriodSeconds;
    /**
     * sasl.login.refresh.window.factor
     * Login refresh thread will sleep until the specified window factor relative to the credential's lifetime has been reached, at which time it will try to refresh the credential. Legal values are between 0.5 (50%) and 1.0 (100%) inclusive; a default value of 0.8 (80%) is used if no value is specified. Currently applies only to OAUTHBEARER.
     * <p>
     * Type:	double
     * Default:	0.8
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslLoginRefreshWindowFactor;
    /**
     * sasl.login.refresh.window.jitter
     * The maximum amount of random jitter relative to the credential's lifetime that is added to the login refresh thread's sleep time. Legal values are between 0 and 0.25 (25%) inclusive; a default value of 0.05 (5%) is used if no value is specified. Currently applies only to OAUTHBEARER.
     * <p>
     * Type:	double
     * Default:	0.05
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslLoginRefreshWindowJitter;
    /**
     * sasl.mechanism.inter.broker.protocol
     * SASL mechanism used for inter-broker communication. Default is GSSAPI.
     * <p>
     * Type:	string
     * Default:	GSSAPI
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String saslMechanismInterBrokerProtocol;
    /**
     * sasl.server.callback.handler.class
     * The fully qualified name of a SASL server callback handler class that implements the AuthenticateCallbackHandler interface. Server callback handlers must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.plain.sasl.server.callback.handler.class=com.example.CustomPlainCallbackHandler.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String saslServerCallbackHandlerClass;
    /**
     * security.inter.broker.protocol
     * Security protocol used to communicate between brokers. Valid values are: PLAINTEXT, SSL, SASL_PLAINTEXT, SASL_SSL. It is an error to set this and inter.broker.listener.name properties at the same time.
     * <p>
     * Type:	string
     * Default:	PLAINTEXT
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String securityInterBrokerProtocol;
    /**
     * ssl.cipher.suites
     * A list of cipher suites. This is a named combination of authentication, encryption, MAC and key exchange algorithm used to negotiate the security settings for a network connection using TLS or SSL network protocol. By default all the available cipher suites are supported.
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslCipherSuites;
    /**
     * ssl.client.auth
     * Configures kafka broker to request client authentication. The following settings are common:
     * <p>
     * ssl.client.auth=required If set to required client authentication is required.
     * ssl.client.auth=requested This means client authentication is optional. unlike requested , if this option is set client can choose not to provide authentication information about itself
     * ssl.client.auth=none This means client authentication is not needed.
     * Type:	string
     * Default:	none
     * Valid Values:[required,requested,none]
     * Importance:medium
     * Update Mode:per-broker
     */
    private String sslClientAuth;

    /**
     * ssl.enabled.protocols
     * The list of protocols enabled for SSL connections.
     * <p>
     * Type:	list
     * Default:	TLSv1.2
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslEnabledProtocols;
    /**
     * ssl.key.password
     * The password of the private key in the key store file. This is optional for client.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslKeyPassword;
    /**
     * ssl.keymanager.algorithm
     * The algorithm used by key manager factory for SSL connections. Default value is the key manager factory algorithm configured for the Java Virtual Machine.
     * <p>
     * Type:	string
     * Default:	SunX509
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslKeymanagerAlgorithm;
    /**
     * ssl.keystore.location
     * The location of the key store file. This is optional for client and can be used for two-way authentication for client.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslKeystoreLocation;
    /**
     * ssl.keystore.password
     * The store password for the key store file. This is optional for client and only needed if ssl.keystore.location is configured.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslKeystorePassword;
    /**
     * ssl.keystore.type
     * The file format of the key store file. This is optional for client.
     * <p>
     * Type:	string
     * Default:	JKS
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslKeystoreType;
    /**
     * ssl.protocol
     * The SSL protocol used to generate the SSLContext. Default setting is TLSv1.2, which is fine for most cases. Allowed values in recent JVMs are TLSv1.2 and TLSv1.3. TLS, TLSv1.1, SSL, SSLv2 and SSLv3 may be supported in older JVMs, but their usage is discouraged due to known security vulnerabilities.
     * <p>
     * Type:	string
     * Default:	TLSv1.2
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslProtocol;
    /**
     * ssl.provider
     * The name of the security provider used for SSL connections. Default value is the default security provider of the JVM.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslProvider;
    /**
     * ssl.trustmanager.algorithm
     * The algorithm used by trust manager factory for SSL connections. Default value is the trust manager factory algorithm configured for the Java Virtual Machine.
     * <p>
     * Type:	string
     * Default:	PKIX
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslTrustmanagerAlgorithm;
    /**
     * ssl.truststore.location
     * The location of the trust store file.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslTruststoreLocation;
    /**
     * ssl.truststore.password
     * The password for the trust store file. If a password is not set access to the truststore is still available, but integrity checking is disabled.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslTruststorePassword;
    /**
     * ssl.truststore.type
     * The file format of the trust store file.
     * <p>
     * Type:	string
     * Default:	JKS
     * Valid Values:
     * Importance:	medium
     * Update Mode:	per-broker
     */
    private String sslTruststoreType;
    /**
     * zookeeper.clientCnxnSocket
     * Typically set to org.apache.zookeeper.ClientCnxnSocketNetty when using TLS connectivity to ZooKeeper. Overrides any explicit value set via the same-named zookeeper.clientCnxnSocket system property.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String zookeeperClientCnxnSocket;
    /**
     * zookeeper.ssl.client.enable
     * Set client to use TLS when connecting to ZooKeeper. An explicit value overrides any value set via the zookeeper.client.secure system property (note the different name). Defaults to false if neither is set; when true, zookeeper.clientCnxnSocket must be set (typically to org.apache.zookeeper.ClientCnxnSocketNetty); other values to set may include zookeeper.ssl.cipher.suites, zookeeper.ssl.crl.enable, zookeeper.ssl.enabled.protocols, zookeeper.ssl.endpoint.identification.algorithm, zookeeper.ssl.keystore.location, zookeeper.ssl.keystore.password, zookeeper.ssl.keystore.type, zookeeper.ssl.ocsp.enable, zookeeper.ssl.protocol, zookeeper.ssl.truststore.location, zookeeper.ssl.truststore.password, zookeeper.ssl.truststore.type
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String zookeeperSslClientEnable;
    /**
     * zookeeper.ssl.keystore.location
     * Keystore location when using a client-side certificate with TLS connectivity to ZooKeeper. Overrides any explicit value set via the zookeeper.ssl.keyStore.location system property (note the camelCase).
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String zookeeperSslKeystoreLocation;
    /**
     * zookeeper.ssl.keystore.password
     * Keystore password when using a client-side certificate with TLS connectivity to ZooKeeper. Overrides any explicit value set via the zookeeper.ssl.keyStore.password system property (note the camelCase). Note that ZooKeeper does not support a key password different from the keystore password, so be sure to set the key password in the keystore to be identical to the keystore password; otherwise the connection attempt to Zookeeper will fail.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String zookeeperSslKeystorePassword;
    /**
     * zookeeper.ssl.keystore.type
     * Keystore type when using a client-side certificate with TLS connectivity to ZooKeeper. Overrides any explicit value set via the zookeeper.ssl.keyStore.type system property (note the camelCase). The default value of null means the type will be auto-detected based on the filename extension of the keystore.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String zookeeperSslKeystoreType;
    /**
     * zookeeper.ssl.truststore.location
     * Truststore location when using TLS connectivity to ZooKeeper. Overrides any explicit value set via the zookeeper.ssl.trustStore.location system property (note the camelCase).
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String zookeeperSslTruststoreLocation;
    /**
     * zookeeper.ssl.truststore.password
     * Truststore password when using TLS connectivity to ZooKeeper. Overrides any explicit value set via the zookeeper.ssl.trustStore.password system property (note the camelCase).
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String zookeeperSslTruststorePassword;
    /**
     * zookeeper.ssl.truststore.type
     * Truststore type when using TLS connectivity to ZooKeeper. Overrides any explicit value set via the zookeeper.ssl.trustStore.type system property (note the camelCase). The default value of null means the type will be auto-detected based on the filename extension of the truststore.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     * Update Mode:	read-only
     */
    private String zookeeperSslTruststoreType;
    /**
     * alter.config.policy.class.name
     * The alter configs policy class that should be used for validation. The class should implement the org.apache.kafka.server.policy.AlterConfigPolicy interface.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String alterConfigPolicyClassName;
    /**
     * alter.log.dirs.replication.quota.window.num
     * The number of samples to retain in memory for alter log dirs replication quotas
     * <p>
     * Type:	int
     * Default:	11
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String alterLogDirsReplicationQuotaWindowNum;
    /**
     * alter.log.dirs.replication.quota.window.size.seconds
     * The time span of each sample for alter log dirs replication quotas
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String alterLogDirsReplicationQuotaWindowSizeSeconds;
    /**
     * authorizer.class.name
     * The fully qualified name of a class that implements sorg.apache.kafka.server.authorizer.Authorizer interface, which is used by the broker for authorization. This config also supports authorizers that implement the deprecated kafka.security.auth.Authorizer trait which was previously used for authorization.
     * <p>
     * Type:	string
     * Default:	""
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String authorizerClassName;
    /**
     * client.quota.callback.class
     * The fully qualified name of a class that implements the ClientQuotaCallback interface, which is used to determine quota limits applied to client requests. By default, , or quotas stored in ZooKeeper are applied. For any given request, the most specific quota that matches the user principal of the session and the client-id of the request is applied.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String clientQuotaCallbackClass;
    /**
     * connection.failed.authentication.delay.ms
     * Connection close delay on failed authentication: this is the time (in milliseconds) by which connection close will be delayed on authentication failure. This must be configured to be less than connections.max.idle.ms to prevent connection timeout.
     * <p>
     * Type:	int
     * Default:	100
     * Valid Values:	[0,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String connectionFailedAuthenticationDelayMs;
    /**
     * create.topic.policy.class.name
     * The create topic policy class that should be used for validation. The class should implement the org.apache.kafka.server.policy.CreateTopicPolicy interface.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String createTopicPolicyClassName;
    /**
     * delegation.token.expiry.check.interval.ms
     * Scan interval to remove expired delegation tokens.
     * <p>
     * Type:	long
     * Default:	3600000
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String delegationTokenExpiryCheckIntervalMs;
    /**
     * kafka.metrics.polling.interval.secs
     * The metrics polling interval (in seconds) which can be used in kafka.metrics.reporters implementations.
     * <p>
     * Type:	int
     * Default:	10
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String kafkaMetricsPollingIntervalSecs;
    /**
     * kafka.metrics.reporters
     * A list of classes to use as Yammer metrics custom reporters. The reporters should implement kafka.metrics.KafkaMetricsReporter trait. If a client wants to expose JMX operations on a custom reporter, the custom reporter needs to additionally implement an MBean trait that extends kafka.metrics.KafkaMetricsReporterMBean trait so that the registered MBean is compliant with the standard MBean convention.
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String kafkaMetricsReporters;
    /**
     * listener.security.protocol.map
     * Map between listener names and security protocols. This must be defined for the same security protocol to be usable in more than one port or IP. For example, internal and external traffic can be separated even if SSL is required for both. Concretely, the user could define listeners with names INTERNAL and EXTERNAL and this property as: `INTERNAL:SSL,EXTERNAL:SSL`. As shown, key and value are separated by a colon and map entries are separated by commas. Each listener name should only appear once in the map. Different security (SSL and SASL) settings can be configured for each listener by adding a normalised prefix (the listener name is lowercased) to the config name. For example, to set a different keystore for the INTERNAL listener, a config with name listener.name.internal.ssl.keystore.location would be set. If the config for the listener name is not set, the config will fallback to the generic config (i.e. ssl.keystore.location).
     * <p>
     * Type:	string
     * Default:	PLAINTEXT:PLAINTEXT,SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL
     * Valid Values:
     * Importance:	low
     * Update Mode:	per-broker
     */
    private String listenerSecurityProtocolMap;
    /**
     * log.message.downconversion.enable
     * This configuration controls whether down-conversion of message formats is enabled to satisfy consume requests. When set to false, broker will not perform down-conversion for consumers expecting an older message format. The broker responds with UNSUPPORTED_VERSION error for consume requests from such older clients. This configurationdoes not apply to any message format conversion that might be required for replication to followers.
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	low
     * Update Mode:	cluster-wide
     */
    private String logMessageDownconversionEnable;
    /**
     * metric.reporters
     * A list of classes to use as metrics reporters. Implementing the org.apache.kafka.common.metrics.MetricsReporter interface allows plugging in classes that will be notified of new metric creation. The JmxReporter is always included to register JMX statistics.
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:
     * Importance:	low
     * Update Mode:	cluster-wide
     */
    private String metricReporters;
    /**
     * metrics.num.samples
     * The number of samples maintained to compute metrics.
     * <p>
     * Type:	int
     * Default:	2
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String metricsNumSamples;
    /**
     * metrics.recording.level
     * The highest recording level for metrics.
     * <p>
     * Type:	string
     * Default:	INFO
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String metricsRecordingLevel;
    /**
     * metrics.sample.window.ms
     * The window of time a metrics sample is computed over.
     * <p>
     * Type:	long
     * Default:	30000
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String metricsSampleWindowMs;
    /**
     * password.encoder.cipher.algorithm
     * The Cipher algorithm used for encoding dynamically configured passwords.
     * <p>
     * Type:	string
     * Default:	AES/CBC/PKCS5Padding
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String passwordEncoderCipherAlgorithm;
    /**
     * password.encoder.iterations
     * The iteration count used for encoding dynamically configured passwords.
     * <p>
     * Type:	int
     * Default:	4096
     * Valid Values:	[1024,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String passwordEncoderIterations;
    /**
     * password.encoder.key.length
     * The key length used for encoding dynamically configured passwords.
     * <p>
     * Type:	int
     * Default:	128
     * Valid Values:	[8,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String passwordEncoderKeyLength;
    /**
     * password.encoder.keyfactory.algorithm
     * The SecretKeyFactory algorithm used for encoding dynamically configured passwords. Default is PBKDF2WithHmacSHA512 if available and PBKDF2WithHmacSHA1 otherwise.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String passwordEncoderKeyfactoryAlgorithm;
    /**
     * quota.window.num
     * The number of samples to retain in memory for client quotas
     * <p>
     * Type:	int
     * Default:	11
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String quotaWindowNum;
    /**
     * quota.window.size.seconds
     * The time span of each sample for client quotas
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String quotaWindowSizeSeconds;
    /**
     * replication.quota.window.num
     * The number of samples to retain in memory for replication quotas
     * <p>
     * Type:	int
     * Default:	11
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String replicationQuotaWindowNum;
    /**
     * replication.quota.window.size.seconds
     * The time span of each sample for replication quotas
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String replicationQuotaWindowSizeSeconds;
    /**
     * security.providers
     * A list of configurable creator classes each returning a provider implementing security algorithms. These classes should implement the org.apache.kafka.common.security.auth.SecurityProviderCreator interface.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String securityProviders;
    /**
     * ssl.endpoint.identification.algorithm
     * The endpoint identification algorithm to validate server hostname using server certificate.
     * <p>
     * Type:	string
     * Default:	https
     * Valid Values:
     * Importance:	low
     * Update Mode:	per-broker
     */
    private String sslEndpointIdentificationAlgorithm;
    /**
     * ssl.principal.mapping.rules
     * A list of rules for mapping from distinguished name from the client certificate to short name. The rules are evaluated in order and the first rule that matches a principal name is used to map it to a short name. Any later rules in the list are ignored. By default, distinguished name of the X.500 certificate will be the principal. For more details on the format please see security authorization and acls. Note that this configuration is ignored if an extension of KafkaPrincipalBuilder is provided by the principal.builder.class configuration.
     * <p>
     * Type:	string
     * Default:	DEFAULT
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String sslPrincipalMappingRules;
    /**
     * ssl.secure.random.implementation
     * The SecureRandom PRNG implementation to use for SSL cryptography operations.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	low
     * Update Mode:	per-broker
     */
    private String sslSecureRandomImplementation;
    /**
     * transaction.abort.timed.out.transaction.cleanup.interval.ms
     * The interval at which to rollback transactions that have timed out
     * <p>
     * Type:	int
     * Default:	10000
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String transactionAbortTimedOutTransactionCleanupIntervalMs;
    /**
     * transaction.remove.expired.transaction.cleanup.interval.ms
     * The interval at which to remove transactions that have expired due to transactional.id.expiration.ms passing
     * <p>
     * Type:	int
     * Default:	3600000
     * Valid Values:	[1,...]
     * Importance:	low
     * Update Mode:	read-only
     */
    private String transactionRemoveExpiredTransactionCleanupIntervalMs;
    /**
     * zookeeper.ssl.cipher.suites
     * Specifies the enabled cipher suites to be used in ZooKeeper TLS negotiation (csv). Overrides any explicit value set via the zookeeper.ssl.ciphersuites system property (note the single word "ciphersuites"). The default value of null means the list of enabled cipher suites is determined by the Java runtime being used.
     * <p>
     * Type:	list
     * Default:	null
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String zookeeperSslCipherSuites;
    /**
     * zookeeper.ssl.crl.enable
     * Specifies whether to enable Certificate Revocation List in the ZooKeeper TLS protocols. Overrides any explicit value set via the zookeeper.ssl.crl system property (note the shorter name).
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String zookeeperSslCrlEnable;
    /**
     * zookeeper.ssl.enabled.protocols
     * Specifies the enabled protocol(s) in ZooKeeper TLS negotiation (csv). Overrides any explicit value set via the zookeeper.ssl.enabledProtocols system property (note the camelCase). The default value of null means the enabled protocol will be the value of the zookeeper.ssl.protocol configuration property.
     * <p>
     * Type:	list
     * Default:	null
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String zookeeperSslEnabledProtocols;
    /**
     * zookeeper.ssl.endpoint.identification.algorithm
     * Specifies whether to enable hostname verification in the ZooKeeper TLS negotiation process, with (case-insensitively) "https" meaning ZooKeeper hostname verification is enabled and an explicit blank value meaning it is disabled (disabling it is only recommended for testing purposes). An explicit value overrides any "true" or "false" value set via the zookeeper.ssl.hostnameVerification system property (note the different name and values; true implies https and false implies blank).
     * <p>
     * Type:	string
     * Default:	HTTPS
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String zookeeperSslEndpointIdentificationAlgorithm;
    /**
     * zookeeper.ssl.ocsp.enable
     * Specifies whether to enable Online Certificate Status Protocol in the ZooKeeper TLS protocols. Overrides any explicit value set via the zookeeper.ssl.ocsp system property (note the shorter name).
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String zookeeperSslOcspEnable;
    /**
     * zookeeper.ssl.protocol
     * Specifies the protocol to be used in ZooKeeper TLS negotiation. An explicit value overrides any value set via the same-named zookeeper.ssl.protocol system property.
     * <p>
     * Type:	string
     * Default:	TLSv1.2
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String zookeeperSslProtocol;
    /**
     * zookeeper.sync.time.ms
     * How far a ZK follower can be behind a ZK leader
     * <p>
     * Type:	int
     * Default:	2000
     * Valid Values:
     * Importance:	low
     * Update Mode:	read-only
     */
    private String zookeeperSyncTimeMs;
}
