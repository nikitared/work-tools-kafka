package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Data;

@Data
public class ConsumerConfiguration {

    /**
     * key.deserializer
     * Deserializer class for key that implements the org.apache.kafka.common.serialization.Deserializer interface.
     * <p>
     * Type:	class
     * Default:
     * Valid Values:
     * Importance:	high
     */
    private String keyDeserializer;
    /**
     * value.deserializer
     * Deserializer class for value that implements the org.apache.kafka.common.serialization.Deserializer interface.
     * <p>
     * Type:	class
     * Default:
     * Valid Values:
     * Importance:	high
     */
    private String valueDeserializer;
    /**
     * bootstrap.servers
     * A list of host/port pairs to use for establishing the initial connection to the Kafka cluster. The client will make use of all servers irrespective of which servers are specified here for bootstrapping—this list only impacts the initial hosts used to discover the full set of servers. This list should be in the form host1:port1,host2:port2,.... Since these servers are just used for the initial connection to discover the full cluster membership (which may change dynamically), this list need not contain the full set of servers (you may want more than one, though, in case a server is down).
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:	non-null string
     * Importance:	high
     */
    private String bootstrapServers;
    /**
     * fetch.min.bytes
     * The minimum amount of data the server should return for a fetch request. If insufficient data is available the request will wait for that much data to accumulate before answering the request. The default setting of 1 byte means that fetch requests are answered as soon as a single byte of data is available or the fetch request times out waiting for data to arrive. Setting this to something greater than 1 will cause the server to wait for larger amounts of data to accumulate which can improve server throughput a bit at the cost of some additional latency.
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[0,...]
     * Importance:	high
     */
    private String fetchMinBytes;
    /**
     * group.id
     * A unique string that identifies the consumer group this consumer belongs to. This property is required if the consumer uses either the group management functionality by using subscribe(topic) or the Kafka-based offset management strategy.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	high
     */
    private String groupId;
    /**
     * heartbeat.interval.ms
     * The expected time between heartbeats to the consumer coordinator when using Kafka's group management facilities. Heartbeats are used to ensure that the consumer's session stays active and to facilitate rebalancing when new consumers join or leave the group. The value must be set lower than session.timeout.ms, but typically should be set no higher than 1/3 of that value. It can be adjusted even lower to control the expected time for normal rebalances.
     * <p>
     * Type:	int
     * Default:	3000
     * Valid Values:
     * Importance:	high
     */
    private String heartbeatIntervalMs;
    /**
     * max.partition.fetch.bytes
     * The maximum amount of data per-partition the server will return. Records are fetched in batches by the consumer. If the first record batch in the first non-empty partition of the fetch is larger than this limit, the batch will still be returned to ensure that the consumer can make progress. The maximum record batch size accepted by the broker is defined via message.max.bytes (broker config) or max.message.bytes (topic config). See fetch.max.bytes for limiting the consumer request size.
     * <p>
     * Type:	int
     * Default:	1048576
     * Valid Values:	[0,...]
     * Importance:	high
     */
    private String maxPartitionFetchBytes;
    /**
     * session.timeout.ms
     * The timeout used to detect client failures when using Kafka's group management facility. The client sends periodic heartbeats to indicate its liveness to the broker. If no heartbeats are received by the broker before the expiration of this session timeout, then the broker will remove this client from the group and initiate a rebalance. Note that the value must be in the allowable range as configured in the broker configuration by group.min.session.timeout.ms and group.max.session.timeout.ms.
     * <p>
     * Type:	int
     * Default:	10000
     * Valid Values:
     * Importance:	high
     */
    private String sessionTimeoutMs;
    /**
     * ssl.key.password
     * The password of the private key in the key store file. This is optional for client.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	high
     */
    private String sslKeyPassword;
    /**
     * ssl.keystore.location
     * The location of the key store file. This is optional for client and can be used for two-way authentication for client.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	high
     */
    private String sslKeystoreLocation;
    /**
     * ssl.keystore.password
     * The store password for the key store file. This is optional for client and only needed if ssl.keystore.location is configured.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	high
     */
    private String sslKeystorePassword;
    /**
     * ssl.truststore.location
     * The location of the trust store file.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	high
     */
    private String sslTruststoreLocation;
    /**
     * ssl.truststore.password
     * The password for the trust store file. If a password is not set access to the truststore is still available, but integrity checking is disabled.
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	high
     */
    private String sslTruststorePassword;
    /**
     * allow.auto.create.topics
     * Allow automatic topic creation on the broker when subscribing to or assigning a topic. A topic being subscribed to will be automatically created only if the broker allows for it using `auto.create.topics.enable` broker configuration. This configuration must be set to `false` when using brokers older than 0.11.0
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	medium
     */
    private String allowAutoCreateTopics;
    /**
     * <b>auto.offset.reset</b>
     * <br/>
     * <br/>
     * <p>
     * What to do when there is no initial offset in Kafka or if the current offset does not exist any more on the server (e.g. because that data has been deleted):
     * <ul>
     *   <li>earliest: automatically reset the offset to the earliest offset</li>
     *   <li>latest: automatically reset the offset to the latest offset</li>
     *   <li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li>
     *   <li>anything else: throw exception to the consumer.</li>
     * </ul>
     * <br/>
     * Type:	string
     * Default:	latest
     * Valid Values:	[latest, earliest, none]
     * Importance:	medium
     * client.dns.lookup
     * Controls how the client uses DNS lookups. If set to use_all_dns_ips then, when the lookup returns multiple IP addresses for a hostname, they will all be attempted to connect to before failing the connection. Applies to both bootstrap and advertised servers. If the value is resolve_canonical_bootstrap_servers_only each entry will be resolved and expanded into a list of canonical names.
     * <p>
     * <br/>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	default</li>
     *   <li>Valid Values:	[default, use_all_dns_ips, resolve_canonical_bootstrap_servers_only]</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String autoOffsetReset;
    /**
     * connections.max.idle.ms
     * Close idle connections after the number of milliseconds specified by this config.
     * <p>
     * Type:	long
     * Default:	540000
     * Valid Values:
     * Importance:	medium
     */
    private String connectionsMaxIdleMs;
    /**
     * default.api.timeout.ms
     * Specifies the timeout (in milliseconds) for client APIs. This configuration is used as the default timeout for all client operations that do not specify a timeout parameter.
     * <p>
     * Type:	int
     * Default:	60000
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String defaultApiTimeoutMs;
    /**
     * enable.auto.commit
     * If true the consumer's offset will be periodically committed in the background.
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	medium
     */
    private String enableAutoCommit;
    /**
     * exclude.internal.topics
     * Whether internal topics matching a subscribed pattern should be excluded from the subscription. It is always possible to explicitly subscribe to an internal topic.
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	medium
     */
    private String excludeInternalTopics;
    /**
     * fetch.max.bytes
     * The maximum amount of data the server should return for a fetch request. Records are fetched in batches by the consumer, and if the first record batch in the first non-empty partition of the fetch is larger than this value, the record batch will still be returned to ensure that the consumer can make progress. As such, this is not a absolute maximum. The maximum record batch size accepted by the broker is defined via message.max.bytes (broker config) or max.message.bytes (topic config). Note that the consumer performs multiple fetches in parallel.
     * <p>
     * Type:	int
     * Default:	52428800
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String fetchMaxBytes;
    /**
     * group.instance.id
     * A unique identifier of the consumer instance provided by the end user. Only non-empty strings are permitted. If set, the consumer is treated as a static member, which means that only one instance with this ID is allowed in the consumer group at any time. This can be used in combination with a larger session timeout to avoid group rebalances caused by transient unavailability (e.g. process restarts). If not set, the consumer will join the group as a dynamic member, which is the traditional behavior.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     */
    private String groupInstanceId;
    /**
     * isolation.level
     * Controls how to read messages written transactionally. If set to read_committed, consumer.poll() will only return transactional messages which have been committed. If set to read_uncommitted' (the default), consumer.poll() will return all messages, even transactional messages which have been aborted. Non-transactional messages will be returned unconditionally in either mode.
     * <p>
     * Messages will always be returned in offset order. Hence, in read_committed mode, consumer.poll() will only return messages up to the last stable offset (LSO), which is the one less than the offset of the first open transaction. In particular any messages appearing after messages belonging to ongoing transactions will be withheld until the relevant transaction has been completed. As a result, read_committed consumers will not be able to read up to the high watermark when there are in flight transactions.
     * <p>
     * Further, when in read_committed the seekToEnd method will return the LSO
     * <p>
     * Type:	string
     * Default:	read_uncommitted
     * Valid Values:	[read_committed, read_uncommitted]
     * Importance:	medium
     */
    private String isolationLevel;
    /**
     * max.poll.interval.ms
     * The maximum delay between invocations of poll() when using consumer group management. This places an upper bound on the amount of time that the consumer can be idle before fetching more records. If poll() is not called before expiration of this timeout, then the consumer is considered failed and the group will rebalance in order to reassign the partitions to another member. For consumers using a non-null group.instance.id which reach this timeout, partitions will not be immediately reassigned. Instead, the consumer will stop sending heartbeats and partitions will be reassigned after expiration of session.timeout.ms. This mirrors the behavior of a static consumer which has shutdown.
     * <p>
     * Type:	int
     * Default:	300000
     * Valid Values:	[1,...]
     * Importance:	medium
     */
    private String maxPollIntervalMs;
    /**
     * max.poll.records
     * The maximum number of records returned in a single call to poll().
     * <p>
     * Type:	int
     * Default:	500
     * Valid Values:	[1,...]
     * Importance:	medium
     */
    private String maxPollRecords;
    /**
     * partition.assignment.strategy
     * A list of class names or class types, ordered by preference, of supported assignors responsible for the partition assignment strategy that the client will use to distribute partition ownership amongst consumer instances when group management is used. Implementing the org.apache.kafka.clients.consumer.ConsumerPartitionAssignor interface allows you to plug in a custom assignment strategy.
     * <p>
     * Type:	list
     * Default:	class org.apache.kafka.clients.consumer.RangeAssignor
     * Valid Values:	non-null string
     * Importance:	medium
     */
    private String partitionAssignmentStrategy;
    /**
     * receive.buffer.bytes
     * The size of the TCP receive buffer (SO_RCVBUF) to use when reading data. If the value is -1, the OS default will be used.
     * <p>
     * Type:	int
     * Default:	65536
     * Valid Values:	[-1,...]
     * Importance:	medium
     */
    private String receiveBufferBytes;
    /**
     * request.timeout.ms
     * The configuration controls the maximum amount of time the client will wait for the response of a request. If the response is not received before the timeout elapses the client will resend the request if necessary or fail the request if retries are exhausted.
     * <p>
     * Type:	int
     * Default:	30000
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String requestTimeoutMs;
    /**
     * sasl.client.callback.handler.class
     * The fully qualified name of a SASL client callback handler class that implements the AuthenticateCallbackHandler interface.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	medium
     */
    private String saslClientCallbackHandlerClass;
    /**
     * sasl.jaas.config
     * JAAS login context parameters for SASL connections in the format used by JAAS configuration files. JAAS configuration file format is described here. The format for the value is: 'loginModuleClass controlFlag (optionName=optionValue)*;'. For brokers, the config must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.scram-sha-256.sasl.jaas.config=com.example.ScramLoginModule required;
     * <p>
     * Type:	password
     * Default:	null
     * Valid Values:
     * Importance:	medium
     */
    private String saslJaasConfig;
    /**
     * sasl.kerberos.service.name
     * The Kerberos principal name that Kafka runs as. This can be defined either in Kafka's JAAS config or in Kafka's config.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	medium
     */
    private String saslKerberosServiceName;
    /**
     * sasl.login.callback.handler.class
     * The fully qualified name of a SASL login callback handler class that implements the AuthenticateCallbackHandler interface. For brokers, login callback handler config must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.scram-sha-256.sasl.login.callback.handler.class=com.example.CustomScramLoginCallbackHandler
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	medium
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
     */
    private String saslLoginClass;
    /**
     * sasl.mechanism
     * SASL mechanism used for client connections. This may be any mechanism for which a security provider is available. GSSAPI is the default mechanism.
     * <p>
     * Type:	string
     * Default:	GSSAPI
     * Valid Values:
     * Importance:	medium
     */
    private String saslMechanism;
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
     * send.buffer.bytes
     * The size of the TCP send buffer (SO_SNDBUF) to use when sending data. If the value is -1, the OS default will be used.
     * <p>
     * Type:	int
     * Default:	131072
     * Valid Values:	[-1,...]
     * Importance:	medium
     */
    private String sendBufferBytes;
    /**
     * ssl.enabled.protocols
     * The list of protocols enabled for SSL connections.
     * <p>
     * Type:	list
     * Default:	TLSv1.2
     * Valid Values:
     * Importance:	medium
     */
    private String sslEnabledProtocols;
    /**
     * ssl.keystore.type
     * The file format of the key store file. This is optional for client.
     * <p>
     * Type:	string
     * Default:	JKS
     * Valid Values:
     * Importance:	medium
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
     */
    private String sslProvider;
    /**
     * ssl.truststore.type
     * The file format of the trust store file.
     * <p>
     * Type:	string
     * Default:	JKS
     * Valid Values:
     * Importance:	medium
     */
    private String sslTruststoreType;
    /**
     * auto.commit.interval.ms
     * The frequency in milliseconds that the consumer offsets are auto-committed to Kafka if enable.auto.commit is set to true.
     * <p>
     * Type:	int
     * Default:	5000
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String autoCommitIntervalMs;
    /**
     * check.crcs
     * Automatically check the CRC32 of the records consumed. This ensures no on-the-wire or on-disk corruption to the messages occurred. This check adds some overhead, so it may be disabled in cases seeking extreme performance.
     * <p>
     * Type:	boolean
     * Default:	true
     * Valid Values:
     * Importance:	low
     */
    private String checkCrcs;
    /**
     * client.id
     * An id string to pass to the server when making requests. The purpose of this is to be able to track the source of requests beyond just ip/port by allowing a logical application name to be included in server-side request logging.
     * <p>
     * Type:	string
     * Default:	""
     * Valid Values:
     * Importance:	low
     */
    private String clientId;
    /**
     * client.rack
     * A rack identifier for this client. This can be any string value which indicates where this client is physically located. It corresponds with the broker config 'broker.rack'
     * <p>
     * Type:	string
     * Default:	""
     * Valid Values:
     * Importance:	low
     */
    private String clientRack;
    /**
     * fetch.max.wait.ms
     * The maximum amount of time the server will block before answering the fetch request if there isn't sufficient data to immediately satisfy the requirement given by fetch.min.bytes.
     * <p>
     * Type:	int
     * Default:	500
     * Valid Values:	[0,...]
     * Importance:	low
     */
    private String fetchMaxWaitMs;
    /**
     * interceptor.classes
     * A list of classes to use as interceptors. Implementing the org.apache.kafka.clients.consumer.ConsumerInterceptor interface allows you to intercept (and possibly mutate) records received by the consumer. By default, there are no interceptors.
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:	non-null string
     * Importance:	low
     */
    private String interceptorClasses;
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
     * Valid Values:	non-null string
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
     * sasl.kerberos.kinit.cmd
     * Kerberos kinit command path.
     * <p>
     * Type:	string
     * Default:	/usr/bin/kinit
     * Valid Values:
     * Importance:	low
     */
    private String saslKerberosKinitCmd;
    /**
     * sasl.kerberos.min.time.before.relogin
     * Login thread sleep time between refresh attempts.
     * <p>
     * Type:	long
     * Default:	60000
     * Valid Values:
     * Importance:	low
     */
    private String saslKerberosMinTimeBeforeRelogin;
    /**
     * sasl.kerberos.ticket.renew.jitter
     * Percentage of random jitter added to the renewal time.
     * <p>
     * Type:	double
     * Default:	0.05
     * Valid Values:
     * Importance:	low
     */
    private String saslKerberosTicketRenewJitter;
    /**
     * sasl.kerberos.ticket.renew.window.factor
     * Login thread will sleep until the specified window factor of time from last refresh to ticket's expiry has been reached, at which time it will try to renew the ticket.
     * <p>
     * Type:	double
     * Default:	0.8
     * Valid Values:
     * Importance:	low
     */
    private String saslKerberosTicketRenewWindowFactor;
    /**
     * sasl.login.refresh.buffer.seconds
     * The amount of buffer time before credential expiration to maintain when refreshing a credential, in seconds. If a refresh would otherwise occur closer to expiration than the number of buffer seconds then the refresh will be moved up to maintain as much of the buffer time as possible. Legal values are between 0 and 3600 (1 hour); a default value of 300 (5 minutes) is used if no value is specified. This value and sasl.login.refresh.min.period.seconds are both ignored if their sum exceeds the remaining lifetime of a credential. Currently applies only to OAUTHBEARER.
     * <p>
     * Type:	short
     * Default:	300
     * Valid Values:	[0,...,3600]
     * Importance:	low
     */
    private String saslLoginRefreshBufferSeconds;
    /**
     * sasl.login.refresh.min.period.seconds
     * The desired minimum time for the login refresh thread to wait before refreshing a credential, in seconds. Legal values are between 0 and 900 (15 minutes); a default value of 60 (1 minute) is used if no value is specified. This value and sasl.login.refresh.buffer.seconds are both ignored if their sum exceeds the remaining lifetime of a credential. Currently applies only to OAUTHBEARER.
     * <p>
     * Type:	short
     * Default:	60
     * Valid Values:	[0,...,900]
     * Importance:	low
     */
    private String saslLoginRefreshMinPeriodSeconds;
    /**
     * sasl.login.refresh.window.factor
     * Login refresh thread will sleep until the specified window factor relative to the credential's lifetime has been reached, at which time it will try to refresh the credential. Legal values are between 0.5 (50%) and 1.0 (100%) inclusive; a default value of 0.8 (80%) is used if no value is specified. Currently applies only to OAUTHBEARER.
     * <p>
     * Type:	double
     * Default:	0.8
     * Valid Values:	[0.5,...,1.0]
     * Importance:	low
     */
    private String saslLoginRefreshWindowFactor;
    /**
     * sasl.login.refresh.window.jitter
     * The maximum amount of random jitter relative to the credential's lifetime that is added to the login refresh thread's sleep time. Legal values are between 0 and 0.25 (25%) inclusive; a default value of 0.05 (5%) is used if no value is specified. Currently applies only to OAUTHBEARER.
     * <p>
     * Type:	double
     * Default:	0.05
     * Valid Values:	[0.0,...,0.25]
     * Importance:	low
     */
    private String saslLoginRefreshWindowJitter;
    /**
     * security.providers
     * A list of configurable creator classes each returning a provider implementing security algorithms. These classes should implement the org.apache.kafka.common.security.auth.SecurityProviderCreator interface.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	low
     */
    private String securityProviders;
    /**
     * ssl.cipher.suites
     * A list of cipher suites. This is a named combination of authentication, encryption, MAC and key exchange algorithm used to negotiate the security settings for a network connection using TLS or SSL network protocol. By default all the available cipher suites are supported.
     * <p>
     * Type:	list
     * Default:	null
     * Valid Values:
     * Importance:	low
     */
    private String sslCipherSuites;
    /**
     * ssl.endpoint.identification.algorithm
     * The endpoint identification algorithm to validate server hostname using server certificate.
     * <p>
     * Type:	string
     * Default:	https
     * Valid Values:
     * Importance:	low
     */
    private String sslEndpointIdentificationAlgorithm;
    /**
     * ssl.keymanager.algorithm
     * The algorithm used by key manager factory for SSL connections. Default value is the key manager factory algorithm configured for the Java Virtual Machine.
     * <p>
     * Type:	string
     * Default:	SunX509
     * Valid Values:
     * Importance:	low
     */
    private String sslKeymanagerAlgorithm;
    /**
     * ssl.secure.random.implementation
     * The SecureRandom PRNG implementation to use for SSL cryptography operations.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:
     * Importance:	low
     */
    private String sslSecureRandomImplementation;

    /**
     * ssl.trustmanager.algorithm
     * The algorithm used by trust manager factory for SSL connections. Default value is the trust manager factory algorithm configured for the Java Virtual Machine.
     * <p>
     * Type:	string
     * Default:	PKIX
     * Valid Values:
     * Importance:	low
     */
    private String sslTrustmanagerAlgorithm;


}
