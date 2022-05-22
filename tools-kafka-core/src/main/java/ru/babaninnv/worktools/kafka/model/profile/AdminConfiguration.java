package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Data;

@Data
public class AdminConfiguration {
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
     * Default:	300000
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
     * retries
     * Setting a value greater than zero will cause the client to resend any request that fails with a potentially transient error.
     * <p>
     * Type:	int
     * Default:	2147483647
     * Valid Values:	[0,...,2147483647]
     * Importance:	low
     */
    private String retries;
    /**
     * retry.backoff.ms
     * The amount of time to wait before attempting to retry a failed request. This avoids repeatedly sending requests in a tight loop under some failure scenarios.
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


    private KafkaSslConfiguration sslConfiguration;
}
