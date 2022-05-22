package ru.babaninnv.worktools.kafka.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.babaninnv.worktools.kafka.service.avro.SchemaId;
import ru.babaninnv.worktools.kafka.service.jsonconverter.JsonAvroConverter;
import ru.babaninnv.worktools.kafka.service.schemaregistry.SchemaData;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ConsumeRecordHandler {

    private JsonAvroConverter jsonAvroConverter = new JsonAvroConverter();

    /**
     * Обработчик сообщения
     * */
    public String handle(Object message, MessageConsumeConfiguration messageConsumeConfiguration,
                         ConsumerRecordHandlerProperties consumerRecordHandlerProperties) {
        // здесь нужно понять, что за тип сообщения пришел: массив байт, строка, авро
        // для этого в аргументе нужно передать тип сообщения

        // если пришла строка, ничего не делаем, просто выводим в исходящий поток
        // outputStream.write(message.toString().getBytes());

        // если это массив байт, нужно понять как обрабатывать, для этого смотрим на тип messageType

        MessageDataFormat messageValueDataFormat = messageConsumeConfiguration.getMessageValueDataFormat();

        if (messageValueDataFormat != null) {
            switch (messageValueDataFormat) {
                case AVRO:
                    ConsumerRecord<Object, Object> consumerRecord = (ConsumerRecord<Object, Object>) message;
                    return handleMessageAsAvro((byte[]) consumerRecord.value(), consumerRecordHandlerProperties);
                default:
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * Обработчик сообщения как бинарника avro
     */
    private String handleMessageAsAvro(byte[] byteArray, ConsumerRecordHandlerProperties properties) {
        Map<SchemaId, SchemaData> schemsDatas = properties.getAvroSchemas();
        return new String(jsonAvroConverter.convertToJson(byteArray, schemsDatas));
    }
}
