package ru.babaninnv.worktools.kafka.service.jsonconverter;

public interface UnknownFieldListener {

	void onUnknownField(String name, Object value, String path);
}
