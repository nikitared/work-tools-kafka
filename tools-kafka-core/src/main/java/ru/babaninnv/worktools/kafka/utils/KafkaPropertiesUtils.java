package ru.babaninnv.worktools.kafka.utils;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.model.profile.ConnectConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.ConsumerConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.KafkaSslConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.ProducerConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.SchemaRegistryConfiguration;

import java.util.Properties;

public class KafkaPropertiesUtils {
    private KafkaPropertiesUtils() {}

    public static Properties convert(KafkaConnectionProfile kafkaConnectionProfile) {
        ConnectConfiguration connectConfiguration = kafkaConnectionProfile.getConnectConfiguration();
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, connectConfiguration.getBootstrapServers());
        properties.setProperty(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, connectConfiguration.getSecurityProtocol());

        updateSsl(kafkaConnectionProfile.getConnectConfiguration(), properties);

        return properties;
    }

    public static Properties convertForConsumer(KafkaConnectionProfile kafkaConnectionProfile) {
        ConnectConfiguration connectConfiguration = kafkaConnectionProfile.getConnectConfiguration();
        Properties properties = new Properties();
        setProperty(properties, AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, connectConfiguration.getBootstrapServers());
        setProperty(properties, AdminClientConfig.SECURITY_PROTOCOL_CONFIG, connectConfiguration.getSecurityProtocol());

        updateSsl(connectConfiguration, properties);

        // Если указана конфигурация для consumer
        ConsumerConfiguration consumerConfiguration = kafkaConnectionProfile.getConsumerConfiguration();

        setProperty(properties, ConsumerConfig.GROUP_ID_CONFIG, consumerConfiguration.getGroupId());
        setProperty(properties, ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, consumerConfiguration.getGroupInstanceId());
        setProperty(properties, ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerConfiguration.getMaxPollRecords());
        setProperty(properties, ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, consumerConfiguration.getMaxPollIntervalMs());
        setProperty(properties, ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerConfiguration.getSessionTimeoutMs());
        setProperty(properties, ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, consumerConfiguration.getHeartbeatIntervalMs());
        setProperty(properties, ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerConfiguration.getBootstrapServers());
        setProperty(properties, ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerConfiguration.getEnableAutoCommit());
        setProperty(properties, ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerConfiguration.getAutoCommitIntervalMs());
        setProperty(properties, ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, consumerConfiguration.getPartitionAssignmentStrategy());
        setProperty(properties, ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerConfiguration.getAutoOffsetReset());
        setProperty(properties, ConsumerConfig.FETCH_MIN_BYTES_CONFIG, consumerConfiguration.getFetchMinBytes());
        setProperty(properties, ConsumerConfig.FETCH_MAX_BYTES_CONFIG, consumerConfiguration.getFetchMaxBytes());
        setProperty(properties, ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, consumerConfiguration.getFetchMaxWaitMs());
        setProperty(properties, ConsumerConfig.METADATA_MAX_AGE_CONFIG, consumerConfiguration.getMetadataMaxAgeMs());
        setProperty(properties, ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, consumerConfiguration.getMaxPartitionFetchBytes());
        setProperty(properties, ConsumerConfig.SEND_BUFFER_CONFIG, consumerConfiguration.getSendBufferBytes());
        setProperty(properties, ConsumerConfig.RECEIVE_BUFFER_CONFIG, consumerConfiguration.getReceiveBufferBytes());
        setProperty(properties, ConsumerConfig.CLIENT_ID_CONFIG, consumerConfiguration.getClientId());
        setProperty(properties, ConsumerConfig.CLIENT_RACK_CONFIG, consumerConfiguration.getClientRack());
        setProperty(properties, ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, consumerConfiguration.getReconnectBackoffMs());
        setProperty(properties, ConsumerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, consumerConfiguration.getReconnectBackoffMaxMs());
        setProperty(properties, ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, consumerConfiguration.getRetryBackoffMs());
        setProperty(properties, ConsumerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG, consumerConfiguration.getMetricsSampleWindowMs());
        setProperty(properties, ConsumerConfig.METRICS_NUM_SAMPLES_CONFIG, consumerConfiguration.getMetricsNumSamples());
        setProperty(properties, ConsumerConfig.METRICS_RECORDING_LEVEL_CONFIG,consumerConfiguration.getMetricsRecordingLevel());
        setProperty(properties, ConsumerConfig.METRIC_REPORTER_CLASSES_CONFIG, consumerConfiguration.getMetricReporters());
        setProperty(properties, ConsumerConfig.CHECK_CRCS_CONFIG,consumerConfiguration.getCheckCrcs());
        setProperty(properties, ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumerConfiguration.getKeyDeserializer());
        setProperty(properties, ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,consumerConfiguration.getValueDeserializer());
        setProperty(properties, ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, consumerConfiguration.getConnectionsMaxIdleMs());
        setProperty(properties, ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG,consumerConfiguration.getRequestTimeoutMs());
        setProperty(properties, ConsumerConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, consumerConfiguration.getDefaultApiTimeoutMs());
        setProperty(properties, ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG,consumerConfiguration.getInterceptorClasses());
        setProperty(properties, ConsumerConfig.EXCLUDE_INTERNAL_TOPICS_CONFIG, consumerConfiguration.getExcludeInternalTopics());
        setProperty(properties, ConsumerConfig.ISOLATION_LEVEL_CONFIG, consumerConfiguration.getIsolationLevel());
        setProperty(properties, ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG,consumerConfiguration.getAllowAutoCreateTopics());
        // setProperty(properties, ConsumerConfig.SECURITY_PROVIDERS_CONFIG, consumerConfiguration.getSecurityProviders());

        // дополнительно указываем данные о Schema Registry если есть
        if (kafkaConnectionProfile.getSchemaRegistryConfiguration() != null
                && kafkaConnectionProfile.getSchemaRegistryConfiguration().getSchemaRegistryUrl() != null) {
            setProperty(properties, "schema.registry.url",
                    kafkaConnectionProfile.getSchemaRegistryConfiguration().getSchemaRegistryUrl());
        }

        return properties;
    }

    public static Properties convertForProducer(KafkaConnectionProfile kafkaConnectionProfile) {
        ConnectConfiguration connectConfiguration = kafkaConnectionProfile.getConnectConfiguration();
        Properties properties = new Properties();
        setProperty(properties, AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, connectConfiguration.getBootstrapServers());
        setProperty(properties, AdminClientConfig.SECURITY_PROTOCOL_CONFIG, connectConfiguration.getSecurityProtocol());

        updateSsl(connectConfiguration, properties);

        ProducerConfiguration producerConfiguration = kafkaConnectionProfile.getProducerConfiguration();

        setProperty(properties, ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG, producerConfiguration.getClientDnsLookup());
        setProperty(properties, ProducerConfig.METADATA_MAX_AGE_CONFIG, producerConfiguration.getMetadataMaxAgeMs());
        setProperty(properties, ProducerConfig.METADATA_MAX_IDLE_CONFIG, producerConfiguration.getMetadataMaxIdleMs());
        setProperty(properties, ProducerConfig.BATCH_SIZE_CONFIG, producerConfiguration.getBatchSize());
        setProperty(properties, ProducerConfig.ACKS_CONFIG, producerConfiguration.getAcks());
        setProperty(properties, ProducerConfig.LINGER_MS_CONFIG, producerConfiguration.getLingerMs());
        setProperty(properties, ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, producerConfiguration.getRequestTimeoutMs());
        setProperty(properties, ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, producerConfiguration.getDeliveryTimeoutMs());
        setProperty(properties, ProducerConfig.CLIENT_ID_CONFIG, producerConfiguration.getClientId());
        setProperty(properties, ProducerConfig.SEND_BUFFER_CONFIG, producerConfiguration.getSendBufferBytes());
        setProperty(properties, ProducerConfig.RECEIVE_BUFFER_CONFIG, producerConfiguration.getReceiveBufferBytes());
        setProperty(properties, ProducerConfig.MAX_REQUEST_SIZE_CONFIG, producerConfiguration.getMaxRequestSize());
        setProperty(properties, ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, producerConfiguration.getReconnectBackoffMs());
        setProperty(properties, ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, producerConfiguration.getReconnectBackoffMaxMs());
        setProperty(properties, ProducerConfig.MAX_BLOCK_MS_CONFIG, producerConfiguration.getMaxBlockMs());
        setProperty(properties, ProducerConfig.BUFFER_MEMORY_CONFIG, producerConfiguration.getBufferMemory());
        setProperty(properties, ProducerConfig.RETRY_BACKOFF_MS_CONFIG, producerConfiguration.getRetryBackoffMs());
        setProperty(properties, ProducerConfig.COMPRESSION_TYPE_CONFIG, producerConfiguration.getCompressionType());
        setProperty(properties, ProducerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG, producerConfiguration.getMetricsSampleWindowMs());
        setProperty(properties, ProducerConfig.METRICS_NUM_SAMPLES_CONFIG, producerConfiguration.getMetricsNumSamples());
        setProperty(properties, ProducerConfig.METRICS_RECORDING_LEVEL_CONFIG, producerConfiguration.getMetricsRecordingLevel());
        setProperty(properties, ProducerConfig.METRIC_REPORTER_CLASSES_CONFIG, producerConfiguration.getMetricReporters());
        setProperty(properties, ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, producerConfiguration.getMaxInFlightRequestsPerConnection());
        setProperty(properties, ProducerConfig.RETRIES_CONFIG, producerConfiguration.getRetries());
        setProperty(properties, ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, producerConfiguration.getConnectionsMaxIdleMs());
        setProperty(properties, ProducerConfig.PARTITIONER_CLASS_CONFIG, producerConfiguration.getPartitionerClass());
        setProperty(properties, ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, producerConfiguration.getInterceptorClasses());
        setProperty(properties, ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, producerConfiguration.getEnableIdempotence());
        setProperty(properties, ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, producerConfiguration.getTransactionTimeoutMs());
        setProperty(properties, ProducerConfig.TRANSACTIONAL_ID_CONFIG, producerConfiguration.getTransactionalId());
        setProperty(properties, ProducerConfig.SECURITY_PROVIDERS_CONFIG, producerConfiguration.getSecurityProviders());

        setProperty(properties, ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerConfiguration.getKeySerializer());
        setProperty(properties, ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerConfiguration.getValueSerializer());

        SchemaRegistryConfiguration schemaRegistryConfiguration = kafkaConnectionProfile.getSchemaRegistryConfiguration();
        setProperty(properties, "schema.registry.url", schemaRegistryConfiguration.getSchemaRegistryUrl());
        setProperty(properties, "auto.register.schemas", schemaRegistryConfiguration.getAutoRegisterSchemas());
        setProperty(properties, "use.latest.version", schemaRegistryConfiguration.getUseLatestVersion());
        setProperty(properties, "max.schemas.per.subject", schemaRegistryConfiguration.getMaxSchemasPerSubject());
        setProperty(properties, "key.subject.name.strategy", schemaRegistryConfiguration.getKeySubjectNameStrategy());
        setProperty(properties, "value.subject.name.strategy", schemaRegistryConfiguration.getValueSubjectNameStrategy());
        setProperty(properties, "basic.auth.credentials.source", schemaRegistryConfiguration.getBasicAuthCredentialsSource());
        setProperty(properties, "basic.auth.user.info", schemaRegistryConfiguration.getBasicAuthUserInfo());
        setProperty(properties, "schema.registry.ssl.truststore.location", schemaRegistryConfiguration.getSchemaRegistrySslTruststoreLocation());
        setProperty(properties, "schema.registry.ssl.truststore.password", schemaRegistryConfiguration.getSchemaRegistrySslTruststorePassword());
        setProperty(properties, "schema.registry.ssl.keystore.location", schemaRegistryConfiguration.getSchemaRegistrySslKeystoreLocation());
        setProperty(properties, "schema.registry.ssl.keystore.password", schemaRegistryConfiguration.getSchemaRegistrySslKeystorePassword());
        setProperty(properties, "schema.registry.ssl.key.password", schemaRegistryConfiguration.getSchemaRegistrySslKeyPassword());

        return properties;
    }


    private static void updateSsl(ConnectConfiguration connectConfiguration, Properties properties) {
        if (connectConfiguration.getSecurityProtocol().equals("SSL")) {
            KafkaSslConfiguration sslConfiguration = connectConfiguration.getSslConfiguration();
            setProperty(properties, SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
            setProperty(properties, SslConfigs.SSL_KEY_PASSWORD_CONFIG, sslConfiguration.getSslKeyPassword());
            setProperty(properties, SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, sslConfiguration.getSslKeystoreType());
            setProperty(properties, SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, sslConfiguration.getSslKeystoreLocation());
            setProperty(properties, SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, sslConfiguration.getSslKeystorePassword());
            setProperty(properties, SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, sslConfiguration.getSslTruststoreType());
            setProperty(properties, SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, sslConfiguration.getSslTruststoreLocation());
            setProperty(properties, SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, sslConfiguration.getSslTruststorePassword());
        }
    }

    private static void setProperty(Properties properties, String propertyName, String value) {
        if (value != null) {
            properties.setProperty(propertyName, value);
        }
    }
}
