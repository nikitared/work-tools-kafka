package ru.babaninnv.worktools.kafka.service.avro;

import javax.tools.SimpleJavaFileObject;

import java.io.IOException;
import java.net.URI;

public class SourceCode extends SimpleJavaFileObject {
    private String content = null;
    private String className;

    public SourceCode(String className, URI uri, String content) throws Exception {
        super(uri, Kind.SOURCE);
        this.content = content;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors)
            throws IOException {
        return content;
    }
}
