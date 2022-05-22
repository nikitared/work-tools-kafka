package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Data;

@Data
public class KafkaStreamsConfiguration {

    /**
     * application.id
     * An identifier for the stream processing application. Must be unique within the Kafka cluster. It is used as 1) the default client-id prefix, 2) the group-id for membership management, 3) the changelog topic prefix.
     * <p>
     * Type:	string
     * Default:
     * Valid Values:
     * Importance:	high
     */
    private String applicationId;

    /**
     * bootstrap.servers
     * A list of host/port pairs to use for establishing the initial connection to the Kafka cluster. The client will make use of all servers irrespective of which servers are specified here for bootstrapping—this list only impacts the initial hosts used to discover the full set of servers. This list should be in the form host1:port1,host2:port2,.... Since these servers are just used for the initial connection to discover the full cluster membership (which may change dynamically), this list need not contain the full set of servers (you may want more than one, though, in case a server is down).
     * <p>
     * Type:	list
     * Default:
     * Valid Values:
     * Importance:	high
     */
    private String bootstrapServers;

    /**
     * replication.factor
     * The replication factor for change log topics and repartition topics created by the stream processing application.
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:
     * Importance:	high
     */
    private String replicationFactor;

    /**
     * state.dir
     * Directory location for state store. This path must be unique for each streams instance sharing the same underlying filesystem.
     * <p>
     * Type:	string
     * Default:	/tmp/kafka-streams
     * Valid Values:
     * Importance:	high
     */
    private String stateDir;

    /**
     * cache.max.bytes.buffering
     * Maximum number of memory bytes to be used for buffering across all threads
     * <p>
     * Type:	long
     * Default:	10485760
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String cacheMaxBytesBuffering;

    /**
     * client.id
     * An ID prefix string used for the client IDs of internal consumer, producer and restore-consumer, with pattern '-StreamThread--'.
     * <p>
     * Type:	string
     * Default:	""
     * Valid Values:
     * Importance:	medium
     */
    private String clientId;

    /**
     * default.deserialization.exception.handler
     * Exception handling class that implements the org.apache.kafka.streams.errors.DeserializationExceptionHandler interface.
     * <p>
     * Type:	class
     * Default:	org.apache.kafka.streams.errors.LogAndFailExceptionHandler
     * Valid Values:
     * Importance:	medium
     */
    private String defaultDeserializationExceptionHandler;

    /**
     * default.key.serde
     * Default serializer / deserializer class for key that implements the org.apache.kafka.common.serialization.Serde interface. Note when windowed serde class is used, one needs to set the inner serde class that implements the org.apache.kafka.common.serialization.Serde interface via 'default.windowed.key.serde.inner' or 'default.windowed.value.serde.inner' as well
     * <p>
     * Type:	class
     * Default:	org.apache.kafka.common.serialization.Serdes$ByteArraySerde
     * Valid Values:
     * Importance:	medium
     */
    private String defaultKeySerde;

    /**
     * default.production.exception.handler
     * Exception handling class that implements the org.apache.kafka.streams.errors.ProductionExceptionHandler interface.
     * <p>
     * Type:	class
     * Default:	org.apache.kafka.streams.errors.DefaultProductionExceptionHandler
     * Valid Values:
     * Importance:	medium
     */
    private String defaultProductionExceptionHandler;

    /**
     * default.timestamp.extractor
     * Default timestamp extractor class that implements the org.apache.kafka.streams.processor.TimestampExtractor interface.
     * <p>
     * Type:	class
     * Default:	org.apache.kafka.streams.processor.FailOnInvalidTimestamp
     * Valid Values:
     * Importance:	medium
     */
    private String defaultTimestampExtractor;

    /**
     * default.value.serde
     * Default serializer / deserializer class for value that implements the org.apache.kafka.common.serialization.Serde interface. Note when windowed serde class is used, one needs to set the inner serde class that implements the org.apache.kafka.common.serialization.Serde interface via 'default.windowed.key.serde.inner' or 'default.windowed.value.serde.inner' as well
     * <p>
     * Type:	class
     * Default:	org.apache.kafka.common.serialization.Serdes$ByteArraySerde
     * Valid Values:
     * Importance:	medium
     */
    private String defaultValueSerde;

    /**
     * max.task.idle.ms
     * Maximum amount of time a stream task will stay idle when not all of its partition buffers contain records, to avoid potential out-of-order record processing across multiple input streams.
     * <p>
     * Type:	long
     * Default:	0
     * Valid Values:
     * Importance:	medium
     */
    private String maxTaskIdleMs;

    /**
     * num.standby.replicas
     * The number of standby replicas for each task.
     * <p>
     * Type:	int
     * Default:	0
     * Valid Values:
     * Importance:	medium
     */
    private String numStandbyReplicas;

    /**
     * num.stream.threads
     * The number of threads to execute stream processing.
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:
     * Importance:	medium
     */
    private String numStreamThreads;

    /**
     * processing.guarantee
     * The processing guarantee that should be used. Possible values are at_least_once (default) and exactly_once. Note that exactly-once processing requires a cluster of at least three brokers by default what is the recommended setting for production; for development you can change this, by adjusting broker setting transaction.state.log.replication.factor and transaction.state.log.min.isr.
     * <p>
     * Type:	string
     * Default:	at_least_once
     * Valid Values:	[at_least_once, exactly_once]
     * Importance:	medium
     */
    private String processingGuarantee;

    /**
     * security.protocol
     * Protocol used to communicate with brokers. Valid values are: PLAINTEXT, SSL, SASL_PLAINTEXT, SASL_SSL.
     * <p>
     * Type:	string
     * Default:	PLAINTEXT
     * Valid Values:
     * Importance:	medium
     */
    private String securityProtocol;

    /**
     * topology.optimization
     * A configuration telling Kafka Streams if it should optimize the topology, disabled by default
     * <p>
     * Type:	string
     * Default:	none
     * Valid Values:	[none, all]
     * Importance:	medium
     */
    private String topologyOptimization;

    /**
     * application.server
     * A host:port pair pointing to a user-defined endpoint that can be used for state store discovery and interactive queries on this KafkaStreams instance.
     * <p>
     * Type:	string
     * Default:	""
     * Valid Values:
     * Importance:	low
     */
    private String applicationServer;

    /**
     * buffered.records.per.partition
     * Maximum number of records to buffer per partition.
     * <p>
     * Type:	int
     * Default:	1000
     * Valid Values:
     * Importance:	low
     */
    private String bufferedRecordsPerPartition;

    /**
     * built.in.metrics.version
     * Version of the built-in metrics to use.
     * <p>
     * Type:	string
     * Default:	latest
     * Valid Values:	[0.10.0-2.4, latest]
     * Importance:	low
     */
    private String builtInMetricsVersion;

    /**
     * commit.interval.ms
     * The frequency with which to save the position of the processor. (Note, if processing.guarantee is set to exactly_once, the default value is 100, otherwise the default value is 30000.
     * <p>
     * Type:	long
     * Default:	30000
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String commitIntervalMs;

    /**
     * connections.max.idle.ms
     * Close idle connections after the number of milliseconds specified by this config.
     * <p>
     * Type:	long
     * Default:	540000
     * Valid Values:
     * Importance:	low
     */
    private String connectionsMaxIdleMs;

    /**
     * metadata.max.age.ms
     * The period of time in milliseconds after which we force a refresh of metadata even if we haven't seen any partition leadership changes to proactively discover any new brokers or partitions.
     * <p>
     * Type:	long
     * Default:	300000
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String metadataMaxAgeMs;

    /**
     * metric.reporters
     * A list of classes to use as metrics reporters. Implementing the org.apache.kafka.common.metrics.MetricsReporter interface allows plugging in classes that will be notified of new metric creation. The JmxReporter is always included to register JMX statistics.
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:
     * Importance:	low
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
     */
    private String metricsNumSamples;

    /**
     * metrics.recording.level
     * The highest recording level for metrics.
     * <p>
     * Type:	string
     * Default:	INFO
     * Valid Values:	[INFO, DEBUG]
     * Importance:	low
     */
    private String metricsRecordingLevel;

    /**
     * metrics.sample.window.ms
     * The window of time a metrics sample is computed over.
     * <p>
     * Type:	long
     * Default:	30000
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String metricsSampleWindowMs;

    /**
     * partition.grouper
     * Partition grouper class that implements the org.apache.kafka.streams.processor.PartitionGrouper interface. WARNING: This config is deprecated and will be removed in 3.0.0 release.
     * <p>
     * Type:	class
     * Default:	org.apache.kafka.streams.processor.DefaultPartitionGrouper
     * Valid Values:
     * Importance:	low
     */
    private String partitionGrouper;

    /**
     * poll.ms
     * The amount of time in milliseconds to block waiting for input.
     * <p>
     * Type:	long
     * Default:	100
     * Valid Values:
     * Importance:	low
     */
    private String pollMs;

    /**
     * receive.buffer.bytes
     * The size of the TCP receive buffer (SO_RCVBUF) to use when reading data. If the value is -1, the OS default will be used.
     * <p>
     * Type:	int
     * Default:	32768
     * Valid Values:	[-1,...]
     * Importance:	low
     */
    private String receiveBufferBytes;

    /**
     * reconnect.backoff.max.ms
     * The maximum amount of time in milliseconds to wait when reconnecting to a broker that has repeatedly failed to connect. If provided, the backoff per host will increase exponentially for each consecutive connection failure, up to this maximum. After calculating the backoff increase, 20% random jitter is added to avoid connection storms.
     * <p>
     * Type:	long
     * Default:	1000
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String reconnectBackoffMaxMs;

    /**
     * reconnect.backoff.ms
     * The base amount of time to wait before attempting to reconnect to a given host. This avoids repeatedly connecting to a host in a tight loop. This backoff applies to all connection attempts by the client to a broker.
     * <p>
     * Type:	long
     * Default:	50
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String reconnectBackoffMs;

    /**
     * request.timeout.ms
     * The configuration controls the maximum amount of time the client will wait for the response of a request. If the response is not received before the timeout elapses the client will resend the request if necessary or fail the request if retries are exhausted.
     * <p>
     * Type:	int
     * Default:	40000
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String requestTimeoutMs;

    /**
     * retries
     * Setting a value greater than zero will cause the client to resend any request that fails with a potentially transient error.
     * <p>
     * Type:	int
     * Default:	0
     * Valid Values:	[0,...,2147483647]
     * Importance:	low
     */
    private String retries;

    /**
     * retry.backoff.ms
     * The amount of time to wait before attempting to retry a failed request to a given topic partition. This avoids repeatedly sending requests in a tight loop under some failure scenarios.
     * <p>
     * Type:	long
     * Default:	100
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String retryBackoffMs;

    /**
     * rocksdb.config.setter
     * A Rocks DB config setter class or class name that implements the org.apache.kafka.streams.state.RocksDBConfigSetter interface
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	low
     */
    private String rocksdbConfigSetter;

    /**
     * send.buffer.bytes
     * The size of the TCP send buffer (SO_SNDBUF) to use when sending data. If the value is -1, the OS default will be used.
     * <p>
     * Type:	int
     * Default:	131072
     * Valid Values:	[-1,...]
     * Importance:	low
     */
    private String sendBufferBytes;

    /**
     * state.cleanup.delay.ms
     * The amount of time in milliseconds to wait before deleting state when a partition has migrated. Only state directories that have not been modified for at least state.cleanup.delay.ms will be removed
     * <p>
     * Type:	long
     * Default:	600000
     * Valid Values:
     * Importance:	low
     */
    private String stateCleanupDelayMs;

    /**
     * upgrade.from
     * Allows upgrading in a backward compatible way. This is needed when upgrading from [0.10.0, 1.1] to 2.0+, or when upgrading from [2.0, 2.3] to 2.4+. When upgrading from 2.4 to a newer version it is not required to specify this config. Default is null. Accepted values are "0.10.0", "0.10.1", "0.10.2", "0.11.0", "1.0", "1.1", "2.0", "2.1", "2.2", "2.3" (for upgrading from the corresponding old version).
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:	[null, 0.10.0, 0.10.1, 0.10.2, 0.11.0, 1.0, 1.1, 2.0, 2.1, 2.2, 2.3]
     * Importance:	low
     */
    private String upgradeFrom;

    /**
     * windowstore.changelog.additional.retention.ms
     * Added to a windows maintainMs to ensure data is not deleted from the log prematurely. Allows for clock drift. Default is 1 day
     * <p>
     * Type:	long
     * Default:	86400000
     * Valid Values:
     * Importance:	low
     */
    private String windowstoreChangelogAdditionalRetentionMs;

}
