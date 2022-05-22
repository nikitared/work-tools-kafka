package ru.babaninnv.worktools.kafka.service.avro;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtendedStandardJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private List<CompiledCode> compiledCode = new ArrayList<>();
    private AvroClassLoader cl;

    public ExtendedStandardJavaFileManager(JavaFileManager fileManager, AvroClassLoader cl) {
        super(fileManager);
        this.cl = cl;
    }

    @Override
    public JavaFileObject getJavaFileForOutput(
            JavaFileManager.Location location, String className,
            JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        try {
            CompiledCode innerClass = new CompiledCode(className);
            compiledCode.add(innerClass);
            return innerClass;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error while creating in-memory output file for "
                            + className, e);
        }
    }

    public List<CompiledCode> getCompiledCode() {
        return compiledCode;
    }

    @Override
    public ClassLoader getClassLoader(JavaFileManager.Location location) {
        return cl;
    }
}
