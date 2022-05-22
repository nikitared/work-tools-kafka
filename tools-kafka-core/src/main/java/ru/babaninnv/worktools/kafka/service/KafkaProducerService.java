package ru.babaninnv.worktools.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.repository.KafkaConnectionProfileRepository;
import ru.babaninnv.worktools.kafka.service.avro.AvroSerializer;
import ru.babaninnv.worktools.kafka.service.consumer.MessageDataFormat;
import ru.babaninnv.worktools.kafka.service.jsonconverter.JsonAvroConverter;
import ru.babaninnv.worktools.kafka.service.producer.MessageProduceConfiguration;
import ru.babaninnv.worktools.kafka.utils.KafkaPropertiesUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaConnectionProfileService kafkaConnectionProfileService;

    private final AvroSerializer avroSerializer;

    private final KafkaProducerFactory kafkaProducerFactory;

    private final JsonAvroConverter jsonAvroConverter = new JsonAvroConverter();

    public void produceString(String profileName, String topic, String key, String value) throws ExecutionException, InterruptedException {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profileName);

        Properties properties = KafkaPropertiesUtils.convertForProducer(kafkaConnectionProfile);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
        kafkaProducer.send(producerRecord).get();
    }

    public void produceWithSchemaRegistrySubject(String profileName,
                                                 MessageProduceConfiguration messageProduceConfiguration,
                                                 Object key,
                                                 Object value) throws Exception {

        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileService.getByName(profileName);
        kafkaConnectionProfile.getProducerConfiguration().setUseClassGenerating(false);

        String topic = messageProduceConfiguration.getTopic();
        String subject = messageProduceConfiguration.getSubject();

        // получаем активный настроенный продюсер с метадатой для преобразования сообщения
        KafkaProducerHolder kafkaProducerHolder = kafkaProducerFactory.get(profileName, topic, kafkaConnectionProfile, subject);

        // преобразовываем key массиву байт в сериализованном виде
        ByteBuffer keyByteBuffer = getMessageBody(key, messageProduceConfiguration.getMessageKeyDataFormat(),
                kafkaProducerHolder);

        // преобразовываем value массиву байт в сериализованном виде
        ByteBuffer valueByteBuffer = getMessageBody(value, messageProduceConfiguration.getMessageValueDataFormat(),
                kafkaProducerHolder);

        // формируем объект для отправки продьюсером
        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>(topic, keyByteBuffer, valueByteBuffer);
        log.info("Отправляем сообщение: {}", producerRecord);

        if (BooleanUtils.isNotTrue(kafkaConnectionProfile.getProducerConfiguration().isDebug())) {
            kafkaProducerHolder.getKafkaProducer().send(producerRecord).get();
        } else {
            log.warn("Включен режим отладки");
        }
    }

    private ByteBuffer getMessageBody(Object value, MessageDataFormat messageValueDataFormat,
                                      KafkaProducerHolder kafkaProducerHolder) throws IOException {
        if (value == null) {
            return null;
        }

        if (messageValueDataFormat == MessageDataFormat.AVRO) {
            int schemaId = getSchemaId(kafkaProducerHolder);
            Class<?> clazz = kafkaProducerHolder.getClazz();
            if (clazz != null) {
                return ByteBuffer.wrap(avroSerializer.serialize(value, clazz, schemaId));
            } else {
                Schema schema = kafkaProducerHolder.getSchema();
                return ByteBuffer.wrap(jsonAvroConverter.convertToAvro(value, schemaId, schema));
            }
        } else {
            return ByteBuffer.wrap(String.valueOf(ObjectUtils.defaultIfNull(value, StringUtils.EMPTY))
                    .getBytes(StandardCharsets.UTF_8));
        }
    }

    private int getSchemaId(KafkaProducerHolder kafkaProducerHolder) {
        int schemaId = kafkaProducerHolder.getSchemaId();

        if (schemaId <= 0) {
            throw new RuntimeException("schemaId must greater then 0");
        }
        return schemaId;
    }
}
