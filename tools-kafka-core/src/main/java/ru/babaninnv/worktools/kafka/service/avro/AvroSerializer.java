package ru.babaninnv.worktools.kafka.service.avro;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.NoWrappingJsonEncoder;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.service.avro.deserializers.LocalDateDeserializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Slf4j
@Service
public class AvroSerializer {

    private final ObjectMapper objectMapper;

    public AvroSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(module);
    }

    public byte[] serialize(@NonNull Object value,
                            @NonNull Class<?> specificRecordBaseClass,
                            int id) throws IOException {
        String text = String.valueOf(ObjectUtils.defaultIfNull(value, StringUtils.EMPTY));
        return serialize(text, specificRecordBaseClass, id);
    }

    public byte[] serialize(@NonNull String json,
                            @NonNull Class<?> specificRecordBaseClass,
                            int id) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (id > -1) {
            out.write(0);
            out.write(ByteBuffer.allocate(4).putInt(id).array());
        }

        Object object = objectMapper.readValue(json, specificRecordBaseClass);
        BinaryEncoder encoder = new EncoderFactory().directBinaryEncoder(out, null);
        try {
            Schema schema = getClassSchema(specificRecordBaseClass);
            Object writer = new SpecificDatumWriter(schema);
            ((DatumWriter) writer).write(object, encoder);
        } finally {
            encoder.flush();
        }

        return out.toByteArray();
    }

    @SneakyThrows
    private Schema getClassSchema(Class<?> specificRecordBaseClass) {
        Method getClassSchemaMethod = ReflectionUtils.getMethod(specificRecordBaseClass, "getClassSchema").orElseThrow();
        return (Schema) getClassSchemaMethod.invoke(specificRecordBaseClass);
    }

    public static String fromJson(GenericRecord record) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        NoWrappingJsonEncoder jsonEncoder = new NoWrappingJsonEncoder(record.getSchema(), outputStream);
        DatumWriter<GenericRecord> writer = record instanceof SpecificRecord ?
                new SpecificDatumWriter<>(record.getSchema()) :
                new GenericDatumWriter<>(record.getSchema());
        writer.write(record, jsonEncoder);
        jsonEncoder.flush();
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }
}
