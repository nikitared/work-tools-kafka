package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectConfiguration {
    /**
     * <b>config.storage.topic</b>
     * <br/>
     * <p>The name of the Kafka topic where connector configurations are stored</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String configStorageTopic;

    /**
     * <b>group.id</b>
     * <br/>
     * <p>A unique string that identifies the Connect cluster group this worker belongs to.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String groupId;

    /**
     * <b>key.converter</b>
     * <br/>
     * <p>Converter class used to convert between Kafka Connect format and the serialized form that is written to Kafka. This controls the format of the keys in messages written to or read from Kafka, and since this is independent of connectors it allows any connector to work with any serialization format. Examples of common formats include JSON and Avro.</p>
     * <ul>
     *   <li>Type:	class</li>
     *   <li>Default:</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String keyConverter;

    /**
     * <b>offset.storage.topic</b>
     * <br/>
     * <p>The name of the Kafka topic where connector offsets are stored</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String offsetStorageTopic;

    /**
     * <b>status.storage.topic</b>
     * <br/>
     * <p>The name of the Kafka topic where connector and task status are stored</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String statusStorageTopic;

    /**
     * <b>value.converter</b>
     * <br/>
     * <p>Converter class used to convert between Kafka Connect format and the serialized form that is written to Kafka. This controls the format of the values in messages written to or read from Kafka, and since this is independent of connectors it allows any connector to work with any serialization format. Examples of common formats include JSON and Avro.</p>
     * <ul>
     *   <li>Type:	class</li>
     *   <li>Default:</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String valueConverter;

    /**
     * <b>bootstrap.servers</b>
     * <br/>
     * <p>A list of host/port pairs to use for establishing the initial connection to the Kafka cluster. The client will make use of all servers irrespective of which servers are specified here for bootstrapping—this list only impacts the initial hosts used to discover the full set of servers. This list should be in the form host1:port1,host2:port2,.... Since these servers are just used for the initial connection to discover the full cluster membership (which may change dynamically), this list need not contain the full set of servers (you may want more than one, though, in case a server is down).</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	localhost:9092</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String bootstrapServers;

    /**
     * <b>heartbeat.interval.ms</b>
     * <br/>
     * The expected time between heartbeats to the group coordinator when using Kafka's group management facilities. Heartbeats are used to ensure that the worker's session stays active and to facilitate rebalancing when new members join or leave the group. The value must be set lower than session.timeout.ms, but typically should be set no higher than 1/3 of that value. It can be adjusted even lower to control the expected time for normal rebalances.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	3000</li>
     *<  li> Valid Values:</li>
     *<  li> Importance:	high</li>
     * </ul>
     */
    private String heartbeatIntervalMs;

    /**
     * <b>rebalance.timeout.ms</b>
     * <br/>
     * The maximum allowed time for each worker to join the group once a rebalance has begun. This is basically a limit on the amount of time needed for all tasks to flush any pending data and commit offsets. If the timeout is exceeded, then the worker will be removed from the group, which will cause offset commit failures.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	60000</li>
     *<  li> Valid Values:</li>
     *<  li> Importance:	high</li>
     * </ul>
     */
    private String rebalanceTimeoutMs;

    /**
     * <b>session.timeout.ms</b>
     * <br/>
     * The timeout used to detect worker failures. The worker sends periodic heartbeats to indicate its liveness to the broker. If no heartbeats are received by the broker before the expiration of this session timeout, then the broker will remove the worker from the group and initiate a rebalance. Note that the value must be in the allowable range as configured in the broker configuration by group.min.session.timeout.ms and group.max.session.timeout.ms.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	10000</li>
     *<  li> Valid Values:</li>
     *<  li> Importance:	high</li>
     * </ul>
     */
    private String sessionTimeoutMs;

    /**
     * <b>client.dns.lookup</b>
     * <br/>
     * <p>Controls how the client uses DNS lookups. If set to use_all_dns_ips then, when the lookup returns multiple IP addresses for a hostname, they will all be attempted to connect to before failing the connection. Applies to both bootstrap and advertised servers. If the value is resolve_canonical_bootstrap_servers_only each entry will be resolved and expanded into a list of canonical names.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	default</li>
     *   <li>Valid Values:	[default, use_all_dns_ips, resolve_canonical_bootstrap_servers_only]</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String clientDnsLookup;

    /**
     * <b>connections.max.idle.ms</b>
     * <br/>
     * <p>Close idle connections after the number of milliseconds specified by this config.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	540000</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String connectionsMaxIdleMs;

    /**
     * <b>connector.client.config.override.policy</b>
     * <br/>
     * <p>Class name or alias of implementation of ConnectorClientConfigOverridePolicy. Defines what client configurations can be overriden by the connector. The default implementation is `None`. The other possible policies in the framework include `All` and `Principal`.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	None</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String connectorClientConfigOverridePolicy;

    /**
     * <b>receive.buffer.bytes</b>
     * <br/>
     * The size of the TCP receive buffer (SO_RCVBUF) to use when reading data. If the value is -1, the OS default will be used.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	32768</li>
     *<  li> Valid Values:	[0,...]</li>
     *<  li> Importance:	medium</li>
     * </ul>
     */
    private String receiveBufferBytes;

    /**
     * <b>request.timeout.ms</b>
     * <br/>
     * The configuration controls the maximum amount of time the client will wait for the response of a request. If the response is not received before the timeout elapses the client will resend the request if necessary or fail the request if retries are exhausted.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	40000</li>
     *<  li> Valid Values:	[0,...]</li>
     *<  li> Importance:	medium</li>
     * </ul>
     */
    private String requestTimeoutMs;

    /**
     * <b>sasl.client.callback.handler.class</b>
     * <br/>
     * <p>The fully qualified name of a SASL client callback handler class that implements the AuthenticateCallbackHandler interface.</p>
     * <ul>
     *   <li>Type:	class</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String saslClientCallbackHandlerClass;

    /**
     * <b>sasl.jaas.config</b>
     * <br/>
     * <p>JAAS login context parameters for SASL connections in the format used by JAAS configuration files. JAAS configuration file format is described here. The format for the value is: 'loginModuleClass controlFlag (optionName=optionValue)*;'. For brokers, the config must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.scram-sha-256.sasl.jaas.config=com.example.ScramLoginModule required;</p>
     * <ul>
     *   <li>Type:	password</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String saslJaasConfig;

    /**
     * <b>sasl.kerberos.service.name</b>
     * <br/>
     * <p>The Kerberos principal name that Kafka runs as. This can be defined either in Kafka's JAAS config or in Kafka's config.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String saslKerberosServiceName;

    /**
     * <b>sasl.login.callback.handler.class</b>
     * <br/>
     * <p>The fully qualified name of a SASL login callback handler class that implements the AuthenticateCallbackHandler interface. For brokers, login callback handler config must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.scram-sha-256.sasl.login.callback.handler.class=com.example.CustomScramLoginCallbackHandler</p>
     * <ul>
     *   <li>Type:	class</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String saslLoginCallbackHandlerClass;

    /**
     * <b>sasl.login.class</b>
     * <br/>
     * <p>The fully qualified name of a class that implements the Login interface. For brokers, login config must be prefixed with listener prefix and SASL mechanism name in lower-case. For example, listener.name.sasl_ssl.scram-sha-256.sasl.login.class=com.example.CustomScramLogin</p>
     * <ul>
     *   <li>Type:	class</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String saslLoginClass;

    /**
     * <b>sasl.mechanism</b>
     * <br/>
     * <p>SASL mechanism used for client connections. This may be any mechanism for which a security provider is available. GSSAPI is the default mechanism.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	GSSAPI</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String saslMechanism;

    /**
     * <b>security.protocol</b>
     * <br/>
     * <p>Protocol used to communicate with brokers. Valid values are: PLAINTEXT, SSL, SASL_PLAINTEXT, SASL_SSL.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	PLAINTEXT</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String securityProtocol;

    /**
     * <b>send.buffer.bytes</b>
     * <br/>
     * The size of the TCP send buffer (SO_SNDBUF) to use when sending data. If the value is -1, the OS default will be used.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	131072</li>
     *<  li> Valid Values:	[0,...]</li>
     *<  li> Importance:	medium</li>
     * </ul>
     */
    private String sendBufferBytes;

    /**
     * <b>worker.sync.timeout.ms</b>
     * <br/>
     * When the worker is out of sync with other workers and needs to resynchronize configurations, wait up to this amount of time before giving up, leaving the group, and waiting a backoff period before rejoining.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	3000</li>
     *<  li> Valid Values:</li>
     *<  li> Importance:	medium</li>
     * </ul>
     */
    private String workerSyncTimeoutMs;

    /**
     * <b>worker.unsync.backoff.ms</b>
     * <br/>
     * When the worker is out of sync with other workers and fails to catch up within worker.sync.timeout.ms, leave the Connect cluster for this long before rejoining.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	300000</li>
     *<  li> Valid Values:</li>
     *<  li> Importance:	medium</li>
     * </ul>
     */
    private String workerUnsyncBackoffMs;

    /**
     * <b>access.control.allow.methods</b>
     * <br/>
     * <p>Sets the methods supported for cross origin requests by setting the Access-Control-Allow-Methods header. The default value of the Access-Control-Allow-Methods header allows cross origin requests for GET, POST and HEAD.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	""</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String accessControlAllowMethods;

    /**
     * <b>access.control.allow.origin</b>
     * <br/>
     * <p>Value to set the Access-Control-Allow-Origin header to for REST API requests.To enable cross origin access, set this to the domain of the application that should be permitted to access the API, or '*' to allow access from any domain. The default value only allows access from the domain of the REST API.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	""</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String accessControlAllowOrigin;

    /**
     * <b>admin.listeners</b>
     * <br/>
     * <p>List of comma-separated URIs the Admin REST API will listen on. The supported protocols are HTTP and HTTPS. An empty or blank string will disable this feature. The default behavior is to use the regular listener (specified by the 'listeners' property).</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:	org.apache.kafka.connect.runtime.WorkerConfig$AdminListenersValidator@36d4b5c</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String adminListeners;

    /**
     * <b>client.id</b>
     * <br/>
     * <p>An id string to pass to the server when making requests. The purpose of this is to be able to track the source of requests beyond just ip/port by allowing a logical application name to be included in server-side request logging.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	""</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String clientId;

    /**
     * <b>config.providers</b>
     * <br/>
     * <p>Comma-separated names of ConfigProvider classes, loaded and used in the order specified. Implementing the interface ConfigProvider allows you to replace variable references in connector configurations, such as for externalized secrets.</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	""</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String configProviders;

    /**
     * <b>config.storage.replication.factor</b>
     * <br/>
     * <p>Replication factor used when creating the configuration storage topic</p>
     * <ul>
     *   <li>Type:	short</li>
     *   <li>Default:	3</li>
     *   <li>Valid Values:	[1,...]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String configStorageReplicationFactor;

    /**
     * <b>connect.protocol</b>
     * <br/>
     * <p>Compatibility mode for Kafka Connect Protocol</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	sessioned</li>
     *   <li>Valid Values:	[eager, compatible, sessioned]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String connectProtocol;

    /**
     * <b>header.converter</b>
     * <br/>
     * <p>HeaderConverter class used to convert between Kafka Connect format and the serialized form that is written to Kafka. This controls the format of the header values in messages written to or read from Kafka, and since this is independent of connectors it allows any connector to work with any serialization format. Examples of common formats include JSON and Avro. By default, the SimpleHeaderConverter is used to serialize header values to strings and deserialize them by inferring the schemas.</p>
     * <ul>
     *   <li>Type:	class</li>
     *   <li>Default:	org.apache.kafka.connect.storage.SimpleHeaderConverter</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String headerConverter;

    /**
     * <b>inter.worker.key.generation.algorithm</b>
     * <br/>
     * <p>The algorithm to use for generating internal request keys</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	HmacSHA256</li>
     *   <li>Valid Values:	Any KeyGenerator algorithm supported by the worker JVM</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String interWorkerKeyGenerationAlgorithm;

    /**
     * <b>inter.worker.key.size</b>
     * <br/>
     * The size of the key to use for signing internal requests, in bits. If null, the default key size for the key generation algorithm will be used.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	null</li>
     *<  li> Valid Values:</li>
     *<  li> Importance:	low</li>
     * </ul>
     */
    private String interWorkerKeySize;

    /**
     * <b>inter.worker.key.ttl.ms</b>
     * <br/>
     * The TTL of generated session keys used for internal request validation (in milliseconds)
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	3600000</li>
     *<  li> Valid Values:	[0,...,2147483647]</li>
     *<  li> Importance:	low</li>
     * </ul>
     */
    private String interWorkerKeyTtlMs;

    /**
     * <b>inter.worker.signature.algorithm</b>
     * <br/>
     * <p>The algorithm used to sign internal requests</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	HmacSHA256</li>
     *   <li>Valid Values:	Any MAC algorithm supported by the worker JVM</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String interWorkerSignatureAlgorithm;

    /**
     * <b>inter.worker.verification.algorithms</b>
     * <br/>
     * <p>A list of permitted algorithms for verifying internal requests</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	HmacSHA256</li>
     *   <li>Valid Values:	A list of one or more MAC algorithms, each supported by the worker JVM</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String interWorkerVerificationAlgorithms;

    /**
     * <b>internal.key.converter</b>
     * <br/>
     * <p>Converter class used to convert between Kafka Connect format and the serialized form that is written to Kafka. This controls the format of the keys in messages written to or read from Kafka, and since this is independent of connectors it allows any connector to work with any serialization format. Examples of common formats include JSON and Avro. This setting controls the format used for internal bookkeeping data used by the framework, such as configs and offsets, so users can typically use any functioning Converter implementation. Deprecated; will be removed in an upcoming version.</p>
     * <ul>
     *   <li>Type:	class</li>
     *   <li>Default:	org.apache.kafka.connect.json.JsonConverter</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String internalKeyConverter;

    /**
     * <b>internal.value.converter</b>
     * <br/>
     * <p>Converter class used to convert between Kafka Connect format and the serialized form that is written to Kafka. This controls the format of the values in messages written to or read from Kafka, and since this is independent of connectors it allows any connector to work with any serialization format. Examples of common formats include JSON and Avro. This setting controls the format used for internal bookkeeping data used by the framework, such as configs and offsets, so users can typically use any functioning Converter implementation. Deprecated; will be removed in an upcoming version.</p>
     * <ul>
     *   <li>Type:	class</li>
     *   <li>Default:	org.apache.kafka.connect.json.JsonConverter</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String internalValueConverter;

    /**
     * listeners
     * List of comma-separated URIs the REST API will listen on. The supported protocols are HTTP and HTTPS.
     * Specify hostname as 0.0.0.0 to bind to all interfaces.
     * <b>Leave hostname empty to bind to default interface.</b>
     * <br/>
     * <p>Examples of legal listener lists: HTTP://myhost:8083,HTTPS://myhost:8084</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String listeners;

    /**
     * <b>metadata.max.age.ms</b>
     * <br/>
     * <p>The period of time in milliseconds after which we force a refresh of metadata even if we haven't seen any partition leadership changes to proactively discover any new brokers or partitions.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	300000</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String metadataMaxAgeMs;

    /**
     * <b>metric.reporters</b>
     * <br/>
     * <p>A list of classes to use as metrics reporters. Implementing the org.apache.kafka.common.metrics.MetricsReporter interface allows plugging in classes that will be notified of new metric creation. The JmxReporter is always included to register JMX statistics.</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	""</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String metricReporters;

    /**
     * <b>metrics.num.samples</b>
     * <br/>
     * The number of samples maintained to compute metrics.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	2</li>
     *<  li> Valid Values:	[1,...]</li>
     *<  li> Importance:	low</li>
     * </ul>
     */
    private String metricsNumSamples;

    /**
     * <b>metrics.recording.level</b>
     * <br/>
     * <p>The highest recording level for metrics.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	INFO</li>
     *   <li>Valid Values:	[INFO, DEBUG]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String metricsRecordingLevel;

    /**
     * <b>metrics.sample.window.ms</b>
     * <br/>
     * <p>The window of time a metrics sample is computed over.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	30000</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String metricsSampleWindowMs;

    /**
     * <b>offset.flush.interval.ms</b>
     * <br/>
     * <p>Interval at which to try committing offsets for tasks.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	60000</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String offsetFlushIntervalMs;

    /**
     * <b>offset.flush.timeout.ms</b>
     * <br/>
     * <p>Maximum number of milliseconds to wait for records to flush and partition offset data to be committed to offset storage before cancelling the process and restoring the offset data to be committed in a future attempt.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	5000</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String offsetFlushTimeoutMs;

    /**
     * <b>offset.storage.partitions</b>
     * <br/>
     * The number of partitions used when creating the offset storage topic
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	25</li>
     *<  li> Valid Values:	[1,...]</li>
     *<  li> Importance:	low</li>
     * </ul>
     */
    private String offsetStoragePartitions;

    /**
     * <b>offset.storage.replication.factor</b>
     * <br/>
     * <p>Replication factor used when creating the offset storage topic</p>
     * <ul>
     *   <li>Type:	short</li>
     *   <li>Default:	3</li>
     *   <li>Valid Values:	[1,...]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String offsetStorageReplicationFactor;

    /**
     * plugin.path
     * List of paths separated by commas (,) that contain plugins (connectors, converters, transformations). The list should consist of top level directories that include any combination of:
     * a) directories immediately containing jars with plugins and their dependencies
     * b) uber-jars with plugins and their dependencies
     * c) directories immediately containing the package directory structure of classes of plugins and their dependencies
     * <b>Note: symlinks will be followed to discover dependencies or plugins.</b>
     * <br/>
     * <p>Examples: plugin.path=/usr/local/share/java,/usr/local/share/kafka/plugins,/opt/connectors</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String pluginPath;

    /**
     * <b>reconnect.backoff.max.ms</b>
     * <br/>
     * <p>The maximum amount of time in milliseconds to wait when reconnecting to a broker that has repeatedly failed to connect. If provided, the backoff per host will increase exponentially for each consecutive connection failure, up to this maximum. After calculating the backoff increase, 20% random jitter is added to avoid connection storms.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	1000</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String reconnectBackoffMaxMs;

    /**
     * <b>reconnect.backoff.ms</b>
     * <br/>
     * <p>The base amount of time to wait before attempting to reconnect to a given host. This avoids repeatedly connecting to a host in a tight loop. This backoff applies to all connection attempts by the client to a broker.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	50</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String reconnectBackoffMs;

    /**
     * <b>rest.advertised.host.name</b>
     * <br/>
     * <p>If this is set, this is the hostname that will be given out to other workers to connect to.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String restAdvertisedHostName;

    /**
     * <b>rest.advertised.listener</b>
     * <br/>
     * <p>Sets the advertised listener (HTTP or HTTPS) which will be given to other workers to use.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String restAdvertisedListener;

    /**
     * <b>rest.advertised.port</b>
     * <br/>
     * If this is set, this is the port that will be given out to other workers to connect to.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	null</li>
     *<  li> Valid Values:</li>
     *<  li> Importance:	low</li>
     * </ul>
     */
    private String restAdvertisedPort;

    /**
     * <b>rest.extension.classes</b>
     * <br/>
     * <p>Comma-separated names of ConnectRestExtension classes, loaded and called in the order specified. Implementing the interface ConnectRestExtension allows you to inject into Connect's REST API user defined resources like filters. Typically used to add custom capability like logging, security, etc.</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	""</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String restExtensionClasses;

    /**
     * <b>rest.host.name</b>
     * <br/>
     * <p>Hostname for the REST API. If this is set, it will only bind to this interface.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String restHostName;

    /**
     * <b>rest.port</b>
     * <br/>
     * Port for the REST API to listen on.
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	8083</li>
     *<  li> Valid Values:</li>
     *<  li> Importance:	low</li>
     * </ul>
     */
    private String restPort;

    /**
     * <b>retry.backoff.ms</b>
     * <br/>
     * <p>The amount of time to wait before attempting to retry a failed request to a given topic partition. This avoids repeatedly sending requests in a tight loop under some failure scenarios.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	100</li>
     *   <li>Valid Values:	[0,...]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String retryBackoffMs;

    /**
     * <b>sasl.kerberos.kinit.cmd</b>
     * <br/>
     * <p>Kerberos kinit command path.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	/usr/bin/kinit</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String saslKerberosKinitCmd;

    /**
     * <b>sasl.kerberos.min.time.before.relogin</b>
     * <br/>
     * <p>Login thread sleep time between refresh attempts.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	60000</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String saslKerberosMinTimeBeforeRelogin;

    /**
     * <b>sasl.kerberos.ticket.renew.jitter</b>
     * <br/>
     * <p>Percentage of random jitter added to the renewal time.</p>
     * <ul>
     *   <li>Type:	double</li>
     *   <li>Default:	0.05</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String saslKerberosTicketRenewJitter;

    /**
     * <b>sasl.kerberos.ticket.renew.window.factor</b>
     * <br/>
     * <p>Login thread will sleep until the specified window factor of time from last refresh to ticket's expiry has been reached, at which time it will try to renew the ticket.</p>
     * <ul>
     *   <li>Type:	double</li>
     *   <li>Default:	0.8</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String saslKerberosTicketRenewWindowFactor;

    /**
     * <b>sasl.login.refresh.buffer.seconds</b>
     * <br/>
     * <p>The amount of buffer time before credential expiration to maintain when refreshing a credential, in seconds. If a refresh would otherwise occur closer to expiration than the number of buffer seconds then the refresh will be moved up to maintain as much of the buffer time as possible. Legal values are between 0 and 3600 (1 hour); a default value of 300 (5 minutes) is used if no value is specified. This value and sasl.login.refresh.min.period.seconds are both ignored if their sum exceeds the remaining lifetime of a credential. Currently applies only to OAUTHBEARER.</p>
     * <ul>
     *   <li>Type:	short</li>
     *   <li>Default:	300</li>
     *   <li>Valid Values:	[0,...,3600]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String saslLoginRefreshBufferSeconds;

    /**
     * <b>sasl.login.refresh.min.period.seconds</b>
     * <br/>
     * <p>The desired minimum time for the login refresh thread to wait before refreshing a credential, in seconds. Legal values are between 0 and 900 (15 minutes); a default value of 60 (1 minute) is used if no value is specified. This value and sasl.login.refresh.buffer.seconds are both ignored if their sum exceeds the remaining lifetime of a credential. Currently applies only to OAUTHBEARER.</p>
     * <ul>
     *   <li>Type:	short</li>
     *   <li>Default:	60</li>
     *   <li>Valid Values:	[0,...,900]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String saslLoginRefreshMinPeriodSeconds;

    /**
     * <b>sasl.login.refresh.window.factor</b>
     * <br/>
     * <p>Login refresh thread will sleep until the specified window factor relative to the credential's lifetime has been reached, at which time it will try to refresh the credential. Legal values are between 0.5 (50%) and 1.0 (100%) inclusive; a default value of 0.8 (80%) is used if no value is specified. Currently applies only to OAUTHBEARER.</p>
     * <ul>
     *   <li>Type:	double</li>
     *   <li>Default:	0.8</li>
     *   <li>Valid Values:	[0.5,...,1.0]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String saslLoginRefreshWindowFactor;

    /**
     * <b>sasl.login.refresh.window.jitter</b>
     * <br/>
     * <p>The maximum amount of random jitter relative to the credential's lifetime that is added to the login refresh thread's sleep time. Legal values are between 0 and 0.25 (25%) inclusive; a default value of 0.05 (5%) is used if no value is specified. Currently applies only to OAUTHBEARER.</p>
     * <ul>
     *   <li>Type:	double</li>
     *   <li>Default:	0.05</li>
     *   <li>Valid Values:	[0.0,...,0.25]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String saslLoginRefreshWindowJitter;

    /**
     * <b>scheduled.rebalance.max.delay.ms</b>
     * <br/>
     * The maximum delay that is scheduled in order to wait for the return of one or more departed workers before rebalancing and reassigning their connectors and tasks to the group. During this period the connectors and tasks of the departed workers remain unassigned
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	300000</li>
     *<  li> Valid Values:	[0,...,2147483647]</li>
     *<  li> Importance:	low</li>
     * </ul>
     */
    private String scheduledRebalanceMaxDelayMs;

    /**
     * <b>status.storage.partitions</b>
     * <br/>
     * The number of partitions used when creating the status storage topic
     * ulp>
     *   <li>Type:	int</li>
     *<  li> Default:	5</li>
     *<  li> Valid Values:	[1,...]</li>
     *<  li> Importance:	low</li>
     * </ul>
     */
    private String statusStoragePartitions;

    /**
     * <b>status.storage.replication.factor</b>
     * <br/>
     * <p>Replication factor used when creating the status storage topic</p>
     * <ul>
     *   <li>Type:	short</li>
     *   <li>Default:	3</li>
     *   <li>Valid Values:	[1,...]</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String statusStorageReplicationFactor;

    /**
     * <b>task.shutdown.graceful.timeout.ms</b>
     * <br/>
     * <p>Amount of time to wait for tasks to shutdown gracefully. This is the total amount of time, not per task. All task have shutdown triggered, then they are waited on sequentially.</p>
     * <ul>
     *   <li>Type:	long</li>
     *   <li>Default:	5000</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String taskShutdownGracefulTimeoutMs;

    /**
     * <b>topic.tracking.allow.reset</b>
     * <br/>
     * <p>If set to true, it allows user requests to reset the set of active topics per connector.</p>
     * <ul>
     *   <li>Type:	boolean</li>
     *   <li>Default:	true</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String topicTrackingAllowReset;

    /**
     * <b>topic.tracking.enable</b>
     * <br/>
     * <p><p>Enable tracking the set of active topics per connector during runtime.</p></p>
     * <ul>
     *    <li>Type: boolean</li>
     *    <li>Default: true</li>
     *    <li>Valid Values:</li>
     *    <li>Importance: low</li>
     * </ul>
     */
    private String topicTrackingEnable;


    private KafkaSslConfiguration sslConfiguration;
}
