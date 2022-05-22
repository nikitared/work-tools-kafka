package ru.babaninnv.worktools.kafka.service.jsonconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.NoWrappingJsonEncoder;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import ru.babaninnv.worktools.kafka.service.avro.SchemaId;
import ru.babaninnv.worktools.kafka.service.schemaregistry.SchemaData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class JsonAvroConverter {
    private JsonGenericRecordReader recordReader;

    public JsonAvroConverter() {
        this.recordReader = new JsonGenericRecordReader();
    }

    public JsonAvroConverter(ObjectMapper objectMapper) {
        this.recordReader = new JsonGenericRecordReader(objectMapper);
    }

    public JsonAvroConverter(ObjectMapper objectMapper, UnknownFieldListener unknownFieldListener) {
        this.recordReader = new JsonGenericRecordReader(objectMapper, unknownFieldListener);
    }

    public byte[] convertToAvro(byte[] data, String schema) {
        return convertToAvro(data, new Schema.Parser().parse(schema));
    }

    public byte[] convertToAvro(byte[] data, Schema schema) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
            SpecificDatumWriter<Object> writer = new SpecificDatumWriter<>(schema);

            GenericData.Record record = convertToGenericDataRecord(data, schema);

            writer.write(record, encoder);
            encoder.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new AvroConversionException("Failed to convert to AVRO.", e);
        }
    }

    public byte[] convertToAvro(Object data, Integer schemaId, Schema schema) {
        byte[] bytes = String.valueOf(ObjectUtils.defaultIfNull(data, StringUtils.EMPTY))
                .getBytes(StandardCharsets.UTF_8);
        return convertToAvro(bytes, schemaId, schema);
    }

    public byte[] convertToAvro(byte[] data, Integer schemaId, Schema schema) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(0);
            outputStream.write(ByteBuffer.allocate(4).putInt(schemaId).array());
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
            GenericDatumWriter<Object> writer = new GenericDatumWriter<>(schema);
            writer.write(convertToGenericDataRecord(data, schema), encoder);
            encoder.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new AvroConversionException("Failed to convert to AVRO.", e);
        }
    }


    public GenericData.Record convertToGenericDataRecord(byte[] data, Schema schema) {
        return recordReader.read(data, schema);
    }

    public <T extends SpecificRecordBase & SpecificRecord> T convertToSpecificRecord(byte[] jsonData, Class<T> clazz, Schema schema) {
        byte[] avroBinaryData = this.convertToAvro(jsonData, schema);
        SpecificDatumReader<T> reader = new SpecificDatumReader<T>(clazz);
        ByteArrayInputStream inStream = new ByteArrayInputStream(avroBinaryData);
        Decoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(inStream, null);
        try {
            Decoder decoder = DecoderFactory.get().validatingDecoder(schema, binaryDecoder);
            return reader.read(null, decoder);
        } catch (IOException e) {
            throw new AvroConversionException("Failed to convert to AVRO.", e);
        }
    }

    public <T extends SpecificRecordBase & SpecificRecord> T convertToSpecificRecord(byte[] data, Class<T> clazz, String schema) {
        return convertToSpecificRecord(data, clazz, new Schema.Parser().parse(schema));
    }

    public byte[] convertToJson(byte[] avro, String schema) {
        return convertToJson(avro, new Schema.Parser().parse(schema));
    }

    @SneakyThrows
    public byte[] convertToJson(byte[] avro, Map<SchemaId, SchemaData> schemas) {
        ByteBuffer buffer = ByteBuffer.wrap(avro);
        buffer.get();
        int id = buffer.getInt();

        int length = buffer.limit() - 1 - 4;
        int start = buffer.position() + buffer.arrayOffset();

        SchemaData schema = schemas.get(new SchemaId(id));
        BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(buffer.array(), start, length, null);
        GenericData.Record record = (GenericData.Record) new GenericDatumReader<>(schema.getSchema())
                .read(null, binaryDecoder);
        return convertToJson(record);
    }

    public byte[] convertToJson(byte[] avro, Schema schema) {
        try {
            BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(avro, null);
            GenericRecord record = new GenericDatumReader<GenericRecord>(schema).read(null, binaryDecoder);
            return convertToJson(record);
        } catch (IOException e) {
            throw new AvroConversionException("Failed to create avro structure.", e);
        }
    }

    public byte[] convertToJson(GenericRecord record) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            NoWrappingJsonEncoder jsonEncoder = new NoWrappingJsonEncoder(record.getSchema(), outputStream);
            DatumWriter<GenericRecord> writer = record instanceof SpecificRecord ?
                    new SpecificDatumWriter<>(record.getSchema()) :
                    new GenericDatumWriter<>(record.getSchema());
            writer.write(record, jsonEncoder);
            jsonEncoder.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new AvroConversionException("Failed to convert to JSON.", e);
        }
    }
}
