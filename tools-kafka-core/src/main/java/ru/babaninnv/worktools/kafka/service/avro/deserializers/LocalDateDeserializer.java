package ru.babaninnv.worktools.kafka.service.avro.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.isTextual()) {
            return LocalDate.parse(node.asText());
        } else if (node.isInt()) {
            return LocalDate.ofEpochDay(node.asInt());
        } else {
            throw new IOException("Невозможно распарсить ноду: " + node);
        }
    }
}
