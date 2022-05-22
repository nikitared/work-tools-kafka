package ru.babaninnv.worktools.kafka.service.avro;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.SchemaParseException;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.babaninnv.worktools.kafka.service.avro.MapUtils.asymmetricDifference;

@Slf4j
public class SchemaResolver {
    private static Pattern ERROR_UNKNOWN_TYPE = Pattern.compile("(?i).*(undefined name|not a defined name|type not supported).*");
    private static Pattern ERROR_DUPLICATE_TYPE = Pattern.compile("Can't redefine: (.*)");

    ProcessingState resolve(Iterable<File> files) {
        ProcessingState processingState = new ProcessingState(files);
        while (processingState.isWorkRemaining()) {
            processSchemaFile(processingState, processingState.nextFileState());
        }
        Set<FileState> failedFiles = processingState.getFailedFiles();
        if (!failedFiles.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Could not resolve schema definition files:");
            for (FileState fileState : failedFiles) {
                String path = fileState.getPath();
                String fileErrorMessage = fileState.getErrorMessage();
                errorMessage.append(System.lineSeparator()).append("* ").append(path).append(": ").append(fileErrorMessage);
            }
            throw new RuntimeException(errorMessage.toString());
        }
        return processingState;
    }

    private void processSchemaFile(ProcessingState processingState, FileState fileState) {
        String path = fileState.getPath();
        log.debug("Processing {}, excluding types {}", path, fileState.getDuplicateTypeNames());
        File sourceFile = fileState.getFile();
        Map<String, Schema> parserTypes = processingState.determineParserTypes(fileState);
        try {
            Schema.Parser parser = new Schema.Parser();
            parser.addTypes(parserTypes);
            parser.parse(sourceFile);
            Map<String, Schema> typesDefinedInFile = asymmetricDifference(parser.getTypes(), parserTypes);
            processingState.processTypeDefinitions(fileState, typesDefinedInFile);
            if (log.isDebugEnabled()) {
                log.debug("Processed {}; contained types {}", path, typesDefinedInFile.keySet());
            } else {
                log.info("Processed {}", path);
            }
        } catch (SchemaParseException ex) {
            String errorMessage = ex.getMessage();
            Matcher unknownTypeMatcher = ERROR_UNKNOWN_TYPE.matcher(errorMessage);
            Matcher duplicateTypeMatcher = ERROR_DUPLICATE_TYPE.matcher(errorMessage);
            if (unknownTypeMatcher.matches()) {
                fileState.setError(ex);
                processingState.queueForDelayedProcessing(fileState);
                log.debug("Found undefined name in {} ({}); will try again", path, errorMessage);
            } else if (duplicateTypeMatcher.matches()) {
                String typeName = duplicateTypeMatcher.group(1);
                if (fileState.containsDuplicateTypeName(typeName)) {
                    throw new RuntimeException(
                        String.format("Failed to resolve schema definition file %s; contains duplicate type definition %s", path, typeName),
                        ex);
                } else {
                    fileState.setError(ex);
                    fileState.addDuplicateTypeName(typeName);
                    processingState.queueForProcessing(fileState);
                    log.debug("Identified duplicate type {} in {}; will re-process excluding it", typeName, path);
                }
            } else {
                throw new RuntimeException(String.format("Failed to resolve schema definition file %s", path), ex);
            }
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Failed to resolve schema definition file %s", path), ex);
        }
    }
}
