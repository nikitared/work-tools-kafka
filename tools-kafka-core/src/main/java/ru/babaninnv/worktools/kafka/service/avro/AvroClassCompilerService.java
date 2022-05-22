package ru.babaninnv.worktools.kafka.service.avro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.compiler.specific.SpecificCompiler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class AvroClassCompilerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void structurizeSchemas(List<File> schemaFolders, File avroSchemaFolder) throws Exception {
        List<File> avroFiles = new ArrayList<>();
        for (File path : schemaFolders) {
            avroFiles.addAll(getAvroFiles(path.getAbsolutePath()));
        }

        SchemaResolver resolver = new SchemaResolver();
        Set<File> files = new HashSet<>(avroFiles);
        ProcessingState processingState = resolver.resolve(files);

        FileUtils.forceDelete(avroSchemaFolder);
        FileUtils.forceMkdir(avroSchemaFolder);

        for (File file : files) {
            Iterable<? extends Schema> schemasForLocation = processingState.getSchemasForLocation(file.getAbsolutePath());
            for (Schema schema : schemasForLocation) {
                File specificSchemaFolder = new File(avroSchemaFolder, schema.getNamespace().replaceAll("\\.", File.separator));
                File specificSchemaFile = new File(specificSchemaFolder, schema.getName() + ".avsc");
                FileUtils.writeStringToFile(specificSchemaFile, objectMapper.readTree(schema.toString()).toPrettyString(), StandardCharsets.UTF_8);
            }
        }
    }

    public void loadAvro(List<File> schemaFolders, File avroSchemaFolder, File avroJavaSourcesFolder, File avroClassesFolder) throws Exception {
        List<File> avroFiles = new ArrayList<>();
        for (File path : schemaFolders) {
            avroFiles.addAll(getAvroFiles(path.getAbsolutePath()));
        }

        SchemaResolver resolver = new SchemaResolver();
        Set<File> files = new HashSet<>(avroFiles);
        ProcessingState processingState = resolver.resolve(files);

        FileUtils.forceDelete(avroSchemaFolder);
        FileUtils.forceMkdir(avroSchemaFolder);

        FileUtils.forceDelete(avroJavaSourcesFolder);
        FileUtils.forceMkdir(avroJavaSourcesFolder);

        FileUtils.forceDelete(avroClassesFolder);
        FileUtils.forceMkdir(avroClassesFolder);

        for (File file : files) {
            Iterable<? extends Schema> schemasForLocation = processingState.getSchemasForLocation(file.getAbsolutePath());

            for (Schema schema : schemasForLocation) {

                // генерируем консолидированные файлы схемы
                File specificSchemaFolder = new File(avroSchemaFolder, schema.getNamespace().replaceAll("\\.", File.separator));
                File specificSchemaFile = new File(specificSchemaFolder, schema.getName() + ".avsc");
                FileUtils.writeStringToFile(specificSchemaFile, objectMapper.readTree(schema.toString()).toPrettyString(), StandardCharsets.UTF_8);

                // генерируем исходный код java-класс'а
                try {
                    SpecificCompiler specificCompiler = new SpecificCompiler(schema);
                    specificCompiler.compileToDestination(file, avroJavaSourcesFolder);
                } catch (IOException ex) {
                    throw new RuntimeException(String.format("Failed to compile schema definition file %s", file.getAbsolutePath()), ex);
                }
            }
        }

        compileAll(avroJavaSourcesFolder, avroClassesFolder);
    }

    public File createSchemaFile(File schemaTmpFolder, Schema schema) throws IOException {
        String name = schema.getName();
        String schemaStr = schema.toString();
        File schemaFile = new File(schemaTmpFolder, name + ".avsc");
        FileUtils.writeStringToFile(schemaFile, schemaStr, StandardCharsets.UTF_8);
        return schemaFile;
    }

    public File generateAvroClasses(File schemaFile) throws IOException {
        Schema schema = new Schema.Parser().parse(FileUtils.readFileToString(schemaFile, StandardCharsets.UTF_8));

        File schemaAcroClassesSrcFolder = new File(schemaFile.getParentFile(), "src");
        if (schemaAcroClassesSrcFolder.exists()) {
            FileUtils.forceDelete(schemaAcroClassesSrcFolder);
        }
        FileUtils.forceMkdir(schemaAcroClassesSrcFolder);

        try {
            SpecificCompiler specificCompiler = new SpecificCompiler(schema);
            specificCompiler.compileToDestination(schemaFile, schemaAcroClassesSrcFolder);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Failed to compile schema definition file %s",
                    schemaFile.getAbsolutePath()), ex);
        }

        return schemaAcroClassesSrcFolder;
    }

    private static List<File> getAvroFiles(String filePath) throws IOException {
        final List<File> avroFiles = new ArrayList<>();
        Files.walkFileTree(Paths.get(filePath), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (FilenameUtils.getExtension(file.toFile().getAbsolutePath()).equals("avsc")) {
                    avroFiles.add(file.toFile());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return avroFiles;
    }

    public Map<String, Class<?>> compileAll(File avroJavaSourcesFolder, File avroClassesFolder) throws Exception {

        List<SourceCode> sourceCodes = new ArrayList<>();

        Files.walkFileTree(avroJavaSourcesFolder.toPath(), new SimpleFileVisitor<>() {
            @Override
            @SneakyThrows
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                CompilationUnit compilationUnit = StaticJavaParser.parse(file);
                String packageName = compilationUnit.getPackageDeclaration().get().getName().toString();
                String typeName = compilationUnit.getType(0).getName().toString();
                String className = packageName + "." + typeName;

                SourceCode sourceCode = new SourceCode(className, file.toFile().toURI(),
                        FileUtils.readFileToString(file.toFile(), StandardCharsets.UTF_8));
                sourceCodes.add(sourceCode);
                return FileVisitResult.CONTINUE;
            }
        });

        Iterable<String> options = Arrays.asList();

        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        AvroClassLoader classLoader = new AvroClassLoader(new URL[]{ avroJavaSourcesFolder.toURI().toURL() },
                ClassLoader.getSystemClassLoader());

        Collection<SourceCode> compilationUnits = sourceCodes;
        List<CompiledCode> code = new ArrayList<>();

        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        try(ExtendedStandardJavaFileManager fileManager = new ExtendedStandardJavaFileManager(javac.getStandardFileManager(null, null, null), classLoader)) {
            JavaCompiler.CompilationTask task = javac.getTask(null, fileManager, collector, options, null, compilationUnits);
            boolean result = task.call();
            if (!result || collector.getDiagnostics().size() > 0) {
                StringBuffer exceptionMsg = new StringBuffer();
                exceptionMsg.append("Unable to compile the source");
                boolean hasWarnings = false;
                boolean hasErrors = false;
                for (Diagnostic<? extends JavaFileObject> d : collector.getDiagnostics()) {
                    switch (d.getKind()) {
                        case NOTE:
                        case MANDATORY_WARNING:
                        case WARNING:
                            hasWarnings = true;
                            break;
                        case OTHER:
                        case ERROR:
                        default:
                            hasErrors = true;
                            break;
                    }
                    exceptionMsg.append("\n").append("[kind=").append(d.getKind());
                    exceptionMsg.append(", ").append("line=").append(d.getLineNumber());
                    exceptionMsg.append(", ").append("message=").append(d.getMessage(Locale.US)).append("]");
                }
                if (hasWarnings || hasErrors) {
                    throw new RuntimeException(exceptionMsg.toString());
                }
            }

            code.addAll(fileManager.getCompiledCode());
        }

        for (CompiledCode compiledCode : code) {
            String classPath = compiledCode.getClassName().replaceAll("\\.", File.separator) + ".class";
            File outputClassFile = new File(avroClassesFolder, classPath);
            FileUtils.writeByteArrayToFile(outputClassFile, compiledCode.getByteCode());
        }

        return null;
    }
}
