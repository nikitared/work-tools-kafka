package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Data;

@Data
public class SourceConnectorConfiguration {
    /**
     * name
     * Globally unique name to use for this connector.
     * <p>
     * Type:	string
     * Default:
     * Valid Values:	non-empty string without ISO control characters
     * Importance:	high
     */
    private String name;

    /**
     * connector.class
     * Name or alias of the class for this connector. Must be a subclass of org.apache.kafka.connect.connector.Connector. If the connector is org.apache.kafka.connect.file.FileStreamSinkConnector, you can either specify this full name, or use "FileStreamSink" or "FileStreamSinkConnector" to make the configuration a bit shorter
     * <p>
     * Type:	string
     * Default:
     * Valid Values:
     * Importance:	high
     */
    private String connectorClass;

    /**
     * tasks.max
     * Maximum number of tasks to use for this connector.
     * <p>
     * Type:	int
     * Default:	1
     * Valid Values:	[1,...]
     * Importance:	high
     */
    private String tasksMax;

    /**
     * key.converter
     * Converter class used to convert between Kafka Connect format and the serialized form that is written to Kafka. This controls the format of the keys in messages written to or read from Kafka, and since this is independent of connectors it allows any connector to work with any serialization format. Examples of common formats include JSON and Avro.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	low
     */
    private String keyConverter;

    /**
     * value.converter
     * Converter class used to convert between Kafka Connect format and the serialized form that is written to Kafka. This controls the format of the values in messages written to or read from Kafka, and since this is independent of connectors it allows any connector to work with any serialization format. Examples of common formats include JSON and Avro.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	low
     */
    private String valueConverter;

    /**
     * header.converter
     * HeaderConverter class used to convert between Kafka Connect format and the serialized form that is written to Kafka. This controls the format of the header values in messages written to or read from Kafka, and since this is independent of connectors it allows any connector to work with any serialization format. Examples of common formats include JSON and Avro. By default, the SimpleHeaderConverter is used to serialize header values to strings and deserialize them by inferring the schemas.
     * <p>
     * Type:	class
     * Default:	null
     * Valid Values:
     * Importance:	low
     */
    private String headerConverter;

    /**
     * config.action.reload
     * The action that Connect should take on the connector when changes in external configuration providers result in a change in the connector's configuration properties. A value of 'none' indicates that Connect will do nothing. A value of 'restart' indicates that Connect should restart/reload the connector with the updated configuration properties.The restart may actually be scheduled in the future if the external configuration provider indicates that a configuration value will expire in the future.
     * <p>
     * Type:	string
     * Default:	restart
     * Valid Values:	[none, restart]
     * Importance:	low
     */
    private String configActionReload;

    /**
     * transforms
     * Aliases for the transformations to be applied to records.
     * <p>
     * Type:	list
     * Default:	""
     * Valid Values:	non-null string, unique transformation aliases
     * Importance:	low
     */
    private String transforms;

    /**
     * errors.retry.timeout
     * The maximum duration in milliseconds that a failed operation will be reattempted. The default is 0, which means no retries will be attempted. Use -1 for infinite retries.
     * <p>
     * Type:	long
     * Default:	0
     * Valid Values:
     * Importance:	medium
     */
    private String errorsRetryTimeout;

    /**
     * errors.retry.delay.max.ms
     * The maximum duration in milliseconds between consecutive retry attempts. Jitter will be added to the delay once this limit is reached to prevent thundering herd issues.
     * <p>
     * Type:	long
     * Default:	60000
     * Valid Values:
     * Importance:	medium
     */
    private String errorsRetryDelayMaxMs;

    /**
     * errors.tolerance
     * Behavior for tolerating errors during connector operation. 'none' is the default value and signals that any error will result in an immediate connector task failure; 'all' changes the behavior to skip over problematic records.
     * <p>
     * Type:	string
     * Default:	none
     * Valid Values:	[none, all]
     * Importance:	medium
     */
    private String errorsTolerance;

    /**
     * errors.log.enable
     * If true, write each error and the details of the failed operation and problematic record to the Connect application log. This is 'false' by default, so that only errors that are not tolerated are reported.
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	medium
     */
    private String errorsLogEnable;

    /**
     * errors.log.include.messages
     * Whether to the include in the log the Connect record that resulted in a failure. This is 'false' by default, which will prevent record keys, values, and headers from being written to log files, although some information such as topic and partition number will still be logged.
     * <p>
     * Type:	boolean
     * Default:	false
     * Valid Values:
     * Importance:	medium
     */
    private String errorsLogIncludeMessages;
}
