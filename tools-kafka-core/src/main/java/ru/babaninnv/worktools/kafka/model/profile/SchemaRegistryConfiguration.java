package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchemaRegistryConfiguration {
    /**
     * schema.registry.url
     *
     * Comma-separated list of URLs for schema registry instances that can be used to register or look up
     * schemas. If you wish to get a connection to a mocked schema registry for testing, you can specify
     * a scope using the 'mock://' pseudo-protocol. For example, 'mock://my-scope-name' corresponds to
     * 'MockSchemaRegistry.getClientForScope("my-scope-name")'
     * <p>
     * Type: list
     * Default: “”
     * Importance: high
     */
    private String schemaRegistryUrl;

    /**
     * auto.register.schemas
     * Specify if the Serializer should attempt to register the Schema with Schema Registry.
     * <p>
     * Type: boolean
     * Default: true
     * Importance: medium
     */
    private String autoRegisterSchemas;

    /**
     * use.latest.version
     * <p>
     * Only applies when auto.register.schemas is set to false. If auto.register.schemas is set to false and use.latest.version is set to true, then instead of deriving a schema for the object passed to the client for serialization, Schema Registry will use the latest version of the schema in the subject for serialization.
     * <p>
     * Type: boolean
     * Default: true
     * Importance: medium
     */
    private String useLatestVersion;

    /**
     * max.schemas.per.subject
     * <p>
     * Maximum number of schemas to create or cache locally.
     * <p>
     * Type: int
     * Default: 1000
     * Importance: low
     */
    private String maxSchemasPerSubject;

    /**
     * key.subject.name.strategy
     * <p>
     * Determines how to construct the subject name under which the key schema is registered with Schema Registry. For additional information, see Schema Registry Subject Name Strategy.
     * <p>
     * Any implementation of io.confluent.kafka.serializers.subject.strategy.SubjectNameStrategy can be specified. By default, <topic>-key is used as subject. Specifying an implementation of io.confluent.kafka.serializers.subject.SubjectNameStrategy is deprecated as of 4.1.3 and if used may have some performance degradation.
     * <p>
     * Type: class
     * Default: class io.confluent.kafka.serializers.subject.TopicNameStrategy
     * Importance: medium
     */
    private String keySubjectNameStrategy;


    /**
     * value.subject.name.strategy
     *
     * Determines how to construct the subject name under which the value schema is registered with Schema Registry. For additional information, see Schema Registry Subject Name Strategy.
     * <p>
     * Any implementation of io.confluent.kafka.serializers.subject.strategy.SubjectNameStrategy can be specified. By default, <topic>-value is used as subject. Specifying an implementation of io.confluent.kafka.serializers.subject.SubjectNameStrategy is deprecated as of 4.1.3 and if used may have some performance degradation.
     * <p>
     * Type: class
     * Default: class io.confluent.kafka.serializers.subject.TopicNameStrategy
     * Importance: medium
     */
    private String valueSubjectNameStrategy;


    /**
     * basic.auth.credentials.source
     * Specify how to pick the credentials for Basic Auth header. The supported values are URL, USER_INFO and SASL_INHERIT
     * <p>
     * Type: string
     * Default: “URL”
     * Importance: medium
     */
    private String basicAuthCredentialsSource;

    /**
     * basic.auth.user.info
     * <p>
     * Specify the user info for Basic Auth in the form of {username}:{password}. schema.registry.basic.auth.user.info is a deprecated alias for this configuration.
     * <p>
     * The following Schema Registry dedicated properties, configurable on the client, are available on Confluent Platform version 5.4.0 (and later). To learn more, see the information on configuring clients in Additional configurations for HTTPS.
     * <p>
     * Type: password
     * Default: “”
     * Importance: medium
     */
    private String basicAuthUserInfo;


    /**
     * schema.registry.ssl.truststore.location
     * <p>
     * The location of the trust store file. For example, schema.registry.kafkastore.ssl.truststore.location=/etc/kafka/secrets/kafka.client.truststore.jks
     * <p>
     * Type: string
     * Default: “”
     * Importance: medium
     */
    private String schemaRegistrySslTruststoreLocation;

    /**
     * schema.registry.ssl.truststore.password
     * <p>
     * The password for the trust store file. If a password is not set, access to the truststore is still available but integrity checking is disabled.
     * <p>
     * Type: password
     * Default: “”
     * Importance: medium
     */
    private String schemaRegistrySslTruststorePassword;

    /**
     * schema.registry.ssl.keystore.location
     * <p>
     * The location of the key store file. This is optional for the client and can be used for two-way authentication for the client. For example, schema.registry.kafkastore.ssl.keystore.location=/etc/kafka/secrets/kafka.schemaregistry.keystore.jks.
     * <p>
     * Type: string
     * Default: “”
     * Importance: medium
     */
    private String schemaRegistrySslKeystoreLocation;

    /**
     * schema.registry.ssl.keystore.password
     * <p>
     * The store password for the key store file. This is optional for the client and only needed if ssl.keystore.location is configured.
     * <p>
     * Type: password
     * Default: “”
     * Importance: medium
     */
    private String schemaRegistrySslKeystorePassword;

    /**
     * schema.registry.ssl.key.password
     * <p>
     * The password of the private key in the key store file. This is optional for the client.
     * <p>
     * Type: password
     * Default: “”
     * Importance: medium
     */
    private String schemaRegistrySslKeyPassword;
}
