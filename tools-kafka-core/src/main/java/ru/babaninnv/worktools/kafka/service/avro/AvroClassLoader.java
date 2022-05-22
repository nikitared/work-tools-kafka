package ru.babaninnv.worktools.kafka.service.avro;

import java.net.URL;
import java.net.URLClassLoader;

public class AvroClassLoader extends URLClassLoader {
    public AvroClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
