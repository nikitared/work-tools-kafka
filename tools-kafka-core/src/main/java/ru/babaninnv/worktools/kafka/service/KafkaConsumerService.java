package ru.babaninnv.worktools.kafka.service;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.internals.ConsumerCoordinator;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.acl.AclBinding;
import org.apache.kafka.common.acl.AclBindingFilter;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.model.profile.ConsumerConfiguration;
import ru.babaninnv.worktools.kafka.service.avro.SchemaId;
import ru.babaninnv.worktools.kafka.service.consumer.ConsumeRecordHandler;
import ru.babaninnv.worktools.kafka.service.consumer.ConsumerRecordHandlerProperties;
import ru.babaninnv.worktools.kafka.service.consumer.MessageConsumeConfiguration;
import ru.babaninnv.worktools.kafka.service.consumer.MessageDataFormat;
import ru.babaninnv.worktools.kafka.service.schemaregistry.KafkaSchemaRegistryService;
import ru.babaninnv.worktools.kafka.service.schemaregistry.KafkaShemaRegistryService;
import ru.babaninnv.worktools.kafka.service.schemaregistry.SchemaData;
import ru.babaninnv.worktools.kafka.utils.KafkaPropertiesUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static java.lang.System.out;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final KafkaConnectionProfileService kafkaConnectionProfileService;

    private final KafkaSchemaRegistryService kafkaSchemaRegistryService;

    private final ConsumeRecordHandler consumeRecordHandler = new ConsumeRecordHandler();

    public void consumeJson(String profileId, MessageConsumeConfiguration messageConsumeConfiguration) {
        // получаем из монги профиль
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profileId);

        if (messageConsumeConfiguration.getGroupId() != null) {
            kafkaConnectionProfile.getConsumerConfiguration().setGroupId(messageConsumeConfiguration.getGroupId());
        }

        // конвертируем данные профиля в настройки создания консьюмера
        Properties properties = KafkaPropertiesUtils.convertForConsumer(kafkaConnectionProfile);

        // создаем консьюмер
        try (Consumer<Object, Object> consumer = new KafkaConsumer<>(properties)) {
            String topic = messageConsumeConfiguration.getTopic();
            consumer.subscribe(Collections.singletonList(topic));

            ConsumerRecordHandlerProperties consumerRecordHandlerProperties = new ConsumerRecordHandlerProperties();

            // String subject = messageConsumeConfiguration.getSubject();
            // if (messageConsumeConfiguration.getMessageValueDataFormat() == MessageDataFormat.AVRO && StringUtils.isNotBlank(subject)) {
            //     Map<SchemaId, SchemaData> avroSchemasFromSubject = getAvroSchemasFromSubject(subject,
            //             kafkaConnectionProfile.getSchemaRegistryConfiguration().getSchemaRegistryUrl());
            //     if (MapUtils.isNotEmpty(avroSchemasFromSubject)) {
            //         consumerRecordHandlerProperties.setAvroSchemas(avroSchemasFromSubject);
            //     } else {
            //         Map<Long, Schema> schemas = new HashMap<>();
            //         String customSchema = messageConsumeConfiguration.getCustomSchema();
            //         schemas.put(null, new Parser().parse(customSchema));
            //     }
            // }

            for (int i = 0; i < 20; i++) {
                ConsumerRecords<Object, Object> records = consumer.poll(Duration.ofSeconds(10L));
                for (TopicPartition partition : records.partitions()) {
                    for (ConsumerRecord<Object, Object> record : records.records(partition)) {
                        // String json = consumeRecordHandler.handle(record, messageConsumeConfiguration, consumerRecordHandlerProperties);
                        out.println(record);
                    }
                }
            }
        }
    }

    private Map<SchemaId, SchemaData> getAvroSchemasFromSubject(String subject, String schemaRegistryUrl) {
        return kafkaSchemaRegistryService.getAvroSchemasById(schemaRegistryUrl, subject);
    }

    private Set<TopicPartition> getTopicPartitions(AdminClient kafkaAdminClient) throws InterruptedException, ExecutionException {
        for (AclBinding aclBinding : kafkaAdminClient.describeAcls(AclBindingFilter.ANY).values().get()) {
            out.println(aclBinding.entry().host() + " - " + aclBinding.pattern().name() + " - " + aclBinding.entry().operation().name() + ": " + aclBinding.entry().permissionType().name());
        }

        Map<String, ConsumerGroupDescription> stringConsumerGroupDescriptionMap = kafkaAdminClient.describeConsumerGroups(List.of("dotnet-masstransit-test")).all().get();
        for (Map.Entry<String, ConsumerGroupDescription> stringConsumerGroupDescriptionEntry : stringConsumerGroupDescriptionMap.entrySet()) {
            if (stringConsumerGroupDescriptionEntry.getValue().authorizedOperations() != null) {
                for (AclOperation authorizedOperation : stringConsumerGroupDescriptionEntry.getValue().authorizedOperations()) {
                    out.println(authorizedOperation.name());
                }
            }
        }

        for (ConsumerGroupListing consumerGroupListing : kafkaAdminClient.listConsumerGroups().all().get()) {
            out.println(consumerGroupListing.groupId());
        }

        Map<TopicPartition, OffsetAndMetadata> topicPartitionOffsetAndMetadataMap = kafkaAdminClient.listConsumerGroupOffsets("dotnet-masstransit-test").partitionsToOffsetAndMetadata().get();
        Set<TopicPartition> topicPartitions = new HashSet<>();
        for (Map.Entry<TopicPartition, OffsetAndMetadata> topicPartitionOffsetAndMetadataEntry : topicPartitionOffsetAndMetadataMap.entrySet()) {
            out.println(topicPartitionOffsetAndMetadataEntry.getKey().topic() + ": " + topicPartitionOffsetAndMetadataEntry.getValue().offset());
            topicPartitions.add(topicPartitionOffsetAndMetadataEntry.getKey());

        }
        return topicPartitions;
    }

    private void handleRecord(ConsumerRecord<Object, Object> record) {

    }

    public void consume(String profileId, String topic, String avroClassName) throws Exception {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profileId);

        Schema schema = null;
        if (StringUtils.isNotBlank(avroClassName)) {
            // извлекаем путь до avro-схемы с учётом kafkaConnectionProfile.avroSchemasFolder avroClassName
            File avroSchemaFile = new File(kafkaConnectionProfile.getAvroSchemasFolder(),
                    avroClassName.replaceAll("\\.", File.separator) + ".avsc");
            if (!avroSchemaFile.exists()) {
                throw new FileNotFoundException(String.format("Файл схемы %s не найден в папке %s по адресу %s",
                        avroClassName,
                        kafkaConnectionProfile.getAvroSchemasFolder(),
                        avroSchemaFile.getAbsolutePath()));
            }
            schema = new Parser().parse(FileUtils.readFileToString(avroSchemaFile, StandardCharsets.UTF_8));
        }

        Properties properties = KafkaPropertiesUtils.convertForConsumer(kafkaConnectionProfile);

        try (Consumer<Object, Object> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singletonList(topic));
            for (int i = 0; i < 20; i++) {
                ConsumerRecords<Object, Object> records = consumer.poll(Duration.ofSeconds(25L));
                for (ConsumerRecord<Object, Object> record : records) {

                    // если указан avroClassName предполагаем, что приходит авро-объект
                    // String json;
                    // if (schema != null) {
                    //     byte[] bytes = ((ByteBuffer) record.value()).array();
                    //     json = avroDeserializer.deserialize(bytes, schema);
                    //     if (StringUtils.isNotBlank(json)) {
                    //         json = objectMapper.readTree(json).toPrettyString();
                    //     }
                    // } else {
                    //     // иначе, json
                    //     json = (String) record.value();
                    // }

                    // out.println(json);
                }
            }
        }
    }

    public List<String> consumeLastMessages(String profileId, MessageConsumeConfiguration messageConsumeConfiguration,
                                            Integer numberOfLastMessages) {
        // получаем из монги профиль
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profileId);

        // конвертируем данные профиля в настройки создания консьюмера
        ConsumerConfiguration consumerConfiguration = kafkaConnectionProfile.getConsumerConfiguration();
        consumerConfiguration.setAutoOffsetReset("latest");
        consumerConfiguration.setGroupId(messageConsumeConfiguration.getGroupId());
        consumerConfiguration.setKeyDeserializer(ByteArrayDeserializer.class.getName());
        consumerConfiguration.setValueDeserializer(ByteArrayDeserializer.class.getName());
        Properties properties = KafkaPropertiesUtils.convertForConsumer(kafkaConnectionProfile);

        // создаем консьюмер
        try (Consumer<Object, Object> consumer = new KafkaConsumer<>(properties)) {

            String topic = messageConsumeConfiguration.getTopic();
            Set<String> subscription = consumer.subscription();
            consumer.subscribe(Collections.singletonList(topic));
            try {
                ConsumerRecordHandlerProperties consumerRecordHandlerProperties = new ConsumerRecordHandlerProperties();

                // String subject = messageConsumeConfiguration.getSubject();
                // if (messageConsumeConfiguration.getMessageValueDataFormat() == MessageDataFormat.AVRO && StringUtils.isNotBlank(subject)) {
                //     Map<SchemaId, SchemaData> avroSchemasFromSubject = getAvroSchemasFromSubject(subject,
                //             kafkaConnectionProfile.getSchemaRegistryConfiguration().getSchemaRegistryUrl());
                //     if (MapUtils.isNotEmpty(avroSchemasFromSubject)) {
                //         consumerRecordHandlerProperties.setAvroSchemas(avroSchemasFromSubject);
                //     } else {
                //         SchemaData schemaData = new SchemaData();
                //         schemaData.setSchema(new Schema.Parser().parse(messageConsumeConfiguration.getCustomSchema()));

                //         Map<SchemaId, SchemaData> schemas = new HashMap<>();
                //         schemas.put(null, schemaData);
                //         consumerRecordHandlerProperties.setAvroSchemas(schemas);
                //     }
                // }

                List<String> result = new ArrayList<>();
                ConsumerRecords<Object, Object> records = consumer.poll(Duration.ofSeconds(10L));
                for (TopicPartition partition : records.partitions()) {
                    for (ConsumerRecord<Object, Object> record : records.records(partition)) {
                        // String json = consumeRecordHandler.handle(record, messageConsumeConfiguration, consumerRecordHandlerProperties);
                        // result.add(json);
                        out.println(record);
                        // if (result.size() == numberOfLastMessages) {
                        //     return result;
                        // }
                    }
                }
                consumer.commitSync();
            } finally {
                consumer.unsubscribe();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        return new ArrayList<>();
    }

    private String getDeserializerClass(MessageDataFormat messageDataFormat) {
        return switch (messageDataFormat) {
            case AVRO -> KafkaAvroDeserializer.class.getName();
            case STRING -> StringDeserializer.class.getName();
            case BYTEARRAY -> ByteArrayDeserializer.class.getName();
        };
    }
}
