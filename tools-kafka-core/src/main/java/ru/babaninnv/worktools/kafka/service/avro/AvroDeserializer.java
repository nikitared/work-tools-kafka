package ru.babaninnv.worktools.kafka.service.avro;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.DecoderFactory;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

@Slf4j
@Service
public class AvroDeserializer {
    public String deserialize(byte[] bytes, Schema schema) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.get();
        buffer.getInt();

        int length = buffer.limit() - 1 - 4;
        int start = buffer.position() + buffer.arrayOffset();

        GenericData.Record record = (GenericData.Record) new GenericDatumReader<>(schema)
                .read(null, DecoderFactory.get().binaryDecoder(buffer.array(), start, length, null));
        return record.toString();
    }
}
