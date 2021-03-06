package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.serialization.ByteBufferSerializer;

@Getter
@Setter
public class ProducerConfiguration {

    /**
     * key.serializer
     * Serializer class for key that implements the org.apache.kafka.common.serialization.Serializer interface.
     * <p>
     * Type:	class
     * Default:
     * Valid Values:
     * Importance:	high
     */
    private String keySerializer = ByteBufferSerializer.class.getName();

    /**
     * value.serializer
     * Serializer class for value that implements the org.apache.kafka.common.serialization.Serializer interface.
     * <p>
     * Type:	class
     * Default:
     * Valid Values:
     * Importance:	high
     */
    private String valueSerializer = ByteBufferSerializer.class.getName();

    /**
     * acks
     * The number of acknowledgments the producer requires the leader to have received before considering a request complete. This controls the durability of records that are sent. The following settings are allowed:
     * <p>
     * acks=0 If set to zero then the producer will not wait for any acknowledgment from the server at all. The record will be immediately added to the socket buffer and considered sent. No guarantee can be made that the server has received the record in this case, and the retries configuration will not take effect (as the client won't generally know of any failures). The offset given back for each record will always be set to -1.
     * acks=1 This will mean the leader will write the record to its local log but will respond without awaiting full acknowledgement from all followers. In this case should the leader fail immediately after acknowledging the record but before the followers have replicated it then the record will be lost.
     * acks=all This means the leader will wait for the full set of in-sync replicas to acknowledge the record. This guarantees that the record will not be lost as long as at least one in-sync replica remains alive. This is the strongest available guarantee. This is equivalent to the acks=-1 setting.
     * Type:	string
     * Default:	1
     * Valid Values:	[all, -1, 0, 1]
     * Importance:	high
     * bootstrap.servers
     * A list of host/port pairs to use for establishing the initial connection to the Kafka cluster. The client will make use of all servers irrespective of which servers are specified here for bootstrapping???this list only impacts the initial hosts used to discover the full set of servers. This list should be in the form host1:port1,host2:port2,.... Since these servers are just used for the initial connection to discover the full cluster membership (which may change dynamically), this list need not contain the full set of servers (you may want more than one, though, in case a server is down).
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:	non-null string
     * Importance:	high
     */
    private String acks;

    /**
     * buffer.memory
     * The total bytes of memory the producer can use to buffer records waiting to be sent to the server. If records are sent faster than they can be delivered to the server the producer will block for max.block.ms after which it will throw an exception.
     * <p>
     * This setting should correspond roughly to the total memory the producer will use, but is not a hard bound since not all memory the producer uses is used for buffering. Some additional memory will be used for compression (if compression is enabled) as well as for maintaining in-flight requests.
     * <p>
     * Type:	long
     * Default:	33554432
     * Valid Values:	[0,...]
     * Importance:	high
     */
    private String bufferMemory;

    /**
     * compression.type
     * The compression type for all data generated by the producer. The default is none (i.e. no compression). Valid values are none, gzip, snappy, lz4, or zstd. Compression is of full batches of data, so the efficacy of batching will also impact the compression ratio (more batching means better compression).
     * <p>
     * Type:	string
     * Default:	none
     * Valid Values:
     * Importance:	high
     */
    private String compressionType;

    /**
     * retries
     * Setting a value greater than zero will cause the client to resend any record whose send fails with a potentially transient error. Note that this retry is no different than if the client resent the record upon receiving the error. Allowing retries without setting max.in.flight.requests.per.connection to 1 will potentially change the ordering of records because if two batches are sent to a single partition, and the first fails and is retried but the second succeeds, then the records in the second batch may appear first. Note additionally that produce requests will be failed before the number of retries has been exhausted if the timeout configured by delivery.timeout.ms expires first before successful acknowledgement. Users should generally prefer to leave this config unset and instead use delivery.timeout.ms to control retry behavior.
     * <p>
     * Type:	int
     * Default:	2147483647
     * Valid Values:	[0,...,2147483647]
     * Importance:	high
     */
    private String retries;

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
     * batch.size
     * The producer will attempt to batch records together into fewer requests whenever multiple records are being sent to the same partition. This helps performance on both the client and the server. This configuration controls the default batch size in bytes.
     * <p>
     * No attempt will be made to batch records larger than this size.
     * <p>
     * Requests sent to brokers will contain multiple batches, one for each partition with data available to be sent.
     * <p>
     * A small batch size will make batching less common and may reduce throughput (a batch size of zero will disable batching entirely). A very large batch size may use memory a bit more wastefully as we will always allocate a buffer of the specified batch size in anticipation of additional records.
     * <p>
     * Type:	int
     * Default:	16384
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String batchSize;

    /**
     * client.dns.lookup
     * Controls how the client uses DNS lookups. If set to use_all_dns_ips then, when the lookup returns multiple IP addresses for a hostname, they will all be attempted to connect to before failing the connection. Applies to both bootstrap and advertised servers. If the value is resolve_canonical_bootstrap_servers_only each entry will be resolved and expanded into a list of canonical names.
     * <p>
     * Type:	string
     * Default:	default
     * Valid Values:	[default, use_all_dns_ips, resolve_canonical_bootstrap_servers_only]
     * Importance:	medium
     */
    private String clientDnsLookup;

    /**
     * client.id
     * An id string to pass to the server when making requests. The purpose of this is to be able to track the source of requests beyond just ip/port by allowing a logical application name to be included in server-side request logging.
     * <p>
     * Type:	string
     * Default:	""
     * Valid Values:
     * Importance:	medium
     */
    private String clientId;

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
     * delivery.timeout.ms
     * An upper bound on the time to report success or failure after a call to send() returns. This limits the total time that a record will be delayed prior to sending, the time to await acknowledgement from the broker (if expected), and the time allowed for retriable send failures. The producer may report failure to send a record earlier than this config if either an unrecoverable error is encountered, the retries have been exhausted, or the record is added to a batch which reached an earlier delivery expiration deadline. The value of this config should be greater than or equal to the sum of request.timeout.ms and linger.ms.
     * <p>
     * Type:	int
     * Default:	120000
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String deliveryTimeoutMs;

    /**
     * linger.ms
     * The producer groups together any records that arrive in between request transmissions into a single batched request. Normally this occurs only under load when records arrive faster than they can be sent out. However in some circumstances the client may want to reduce the number of requests even under moderate load. This setting accomplishes this by adding a small amount of artificial delay???that is, rather than immediately sending out a record the producer will wait for up to the given delay to allow other records to be sent so that the sends can be batched together. This can be thought of as analogous to Nagle's algorithm in TCP. This setting gives the upper bound on the delay for batching: once we get batch.size worth of records for a partition it will be sent immediately regardless of this setting, however if we have fewer than this many bytes accumulated for this partition we will 'linger' for the specified time waiting for more records to show up. This setting defaults to 0 (i.e. no delay). Setting linger.ms=5, for example, would have the effect of reducing the number of requests sent but would add up to 5ms of latency to records sent in the absence of load.
     * <p>
     * Type:	long
     * Default:	0
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String lingerMs;

    /**
     * max.block.ms
     * The configuration controls how long KafkaProducer.send() and KafkaProducer.partitionsFor() will block.These methods can be blocked either because the buffer is full or metadata unavailable.Blocking in the user-supplied serializers or partitioner will not be counted against this timeout.
     * <p>
     * Type:	long
     * Default:	60000
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String maxBlockMs;

    /**
     * max.request.size
     * The maximum size of a request in bytes. This setting will limit the number of record batches the producer will send in a single request to avoid sending huge requests. This is also effectively a cap on the maximum uncompressed record batch size. Note that the server has its own cap on the record batch size (after compression if compression is enabled) which may be different from this.
     * <p>
     * Type:	int
     * Default:	1048576
     * Valid Values:	[0,...]
     * Importance:	medium
     */
    private String maxRequestSize;

    /**
     * partitioner.class
     * Partitioner class that implements the org.apache.kafka.clients.producer.Partitioner interface.
     * <p>
     * Type:	class
     * Default:	org.apache.kafka.clients.producer.internals.DefaultPartitioner
     * Valid Values:
     * Importance:	medium
     */
    private String partitionerClass;

    /**
     * receive.buffer.bytes
     * The size of the TCP receive buffer (SO_RCVBUF) to use when reading data. If the value is -1, the OS default will be used.
     * <p>
     * Type:	int
     * Default:	32768
     * Valid Values:	[-1,...]
     * Importance:	medium
     */
    private String receiveBufferBytes;

    /**
     * request.timeout.ms
     * The configuration controls the maximum amount of time the client will wait for the response of a request. If the response is not received before the timeout elapses the client will resend the request if necessary or fail the request if retries are exhausted. This should be larger than replica.lag.time.max.ms (a broker configuration) to reduce the possibility of message duplication due to unnecessary producer retries.
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
     * enable.idempotence
     * When set to 'true', the producer will ensure that exactly one copy of each message is written in the stream. If 'false', producer retries due to broker failures, etc., may write duplicates of the retried message in the stream. Note that enabling idempotence requires max.in.flight.requests.per.connection to be less than or equal to 5, retries to be greater than 0 and acks must be 'all'. If these values are not explicitly set by the user, suitable values will be chosen. If incompatible values are set, a ConfigException will be thrown.
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	low
     */
    private String enableIdempotence;

    /**
     * interceptor.classes
     * A list of classes to use as interceptors. Implementing the org.apache.kafka.clients.producer.ProducerInterceptor interface allows you to intercept (and possibly mutate) the records received by the producer before they are published to the Kafka cluster. By default, there are no interceptors.
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:	non-null string
     * Importance:	low
     */
    private String interceptorClasses;

    /**
     * max.in.flight.requests.per.connection
     * The maximum number of unacknowledged requests the client will send on a single connection before blocking. Note that if this setting is set to be greater than 1 and there are failed sends, there is a risk of message re-ordering due to retries (i.e., if retries are enabled).
     * <p>
     * Type:	int
     * Default:	5
     * Valid Values:	[1,...]
     * Importance:	low
     */
    private String maxInFlightRequestsPerConnection;

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
     * metadata.max.idle.ms
     * Controls how long the producer will cache metadata for a topic that's idle. If the elapsed time since a topic was last produced to exceeds the metadata idle duration, then the topic's metadata is forgotten and the next access to it will force a metadata fetch request.
     * <p>
     * Type:	long
     * Default:	300000
     * Valid Values:	[5000,...]
     * Importance:	low
     */
    private String metadataMaxIdleMs;

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

    /**
     * transaction.timeout.ms
     * The maximum amount of time in ms that the transaction coordinator will wait for a transaction status update from the producer before proactively aborting the ongoing transaction.If this value is larger than the transaction.max.timeout.ms setting in the broker, the request will fail with a InvalidTransactionTimeout error.
     * <p>
     * Type:	int
     * Default:	60000
     * Valid Values:
     * Importance:	low
     */
    private String transactionTimeoutMs;

    /**
     * transactional.id
     * The TransactionalId to use for transactional delivery. This enables reliability semantics which span multiple producer sessions since it allows the client to guarantee that transactions using the same TransactionalId have been completed prior to starting any new transactions. If no TransactionalId is provided, then the producer is limited to idempotent delivery. Note that enable.idempotence must be enabled if a TransactionalId is configured. The default is null, which means transactions cannot be used. Note that, by default, transactions require a cluster of at least three brokers which is the recommended setting for production; for development you can change this, by adjusting broker setting transaction.state.log.replication.factor.
     * <p>
     * Type:	string
     * Default:	null
     * Valid Values:	non-empty string
     * Importance:	low
     */
    private String transactionalId;

    /**
     * ???????????????????????? ???? ?????????????????? ?????????????? ?????? ???????????????????????????? json ?? avro,
     * ???????????? ?????????????? ???????????????????????????? ?? ?????????????? jsonconverter
     * */
    private boolean useClassGenerating = false;

    /**
     * ?????????????? ?????? ???????????????? ?????????????????? ???????????????????? ?? ???????????? ??????????????. ???? ???????? ??????
     * ????????????????(???????????????????? ??????????????????, ?????????????????? ??????????????, ?????????????????????? json ?? ????????) ?????????????????????? ??????????,
     * ???? ?????????????????????? ???????????????? ?? ?????????? ???? ??????????
     * */
    private boolean debug;
}
