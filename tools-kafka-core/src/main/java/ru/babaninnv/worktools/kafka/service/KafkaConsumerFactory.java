package ru.babaninnv.worktools.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.exceptions.FolderCreationException;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.service.avro.AvroClassCompilerService;
import ru.babaninnv.worktools.kafka.service.schemaregistry.KafkaShemaRegistryService;
import ru.babaninnv.worktools.kafka.utils.KafkaPropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerFactory {
    private Map<String, KafkaConsumerHolder> kafkaConsumers = new ConcurrentHashMap<>();

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> scheduledFuture = null;

    private final AvroClassCompilerService avroClassCompilerService;

    private final KafkaShemaRegistryService kafkaShemaRegistryService;

    public KafkaConsumerHolder get(String profile, String topic, KafkaConnectionProfile kafkaConnectionProfile) {
        return get(profile, topic, kafkaConnectionProfile, topic + "-value");
    }

    public KafkaConsumerHolder get(String profile, String topic, KafkaConnectionProfile kafkaConnectionProfile, String subject) {

        // проверяем, что в мапе еще нет продюсера для профиля и топика
        if (!kafkaConsumers.containsKey(profile + "_" + topic)) {

            Properties properties = KafkaPropertiesUtils.convertForConsumer(kafkaConnectionProfile);

            // создаём экземпляр консьюмера
            KafkaConsumer<Object, Object> kafkaProducer = new KafkaConsumer<>(properties);

            // оборачиваем консьюмер в объект с указанием времени его создания
            KafkaConsumerHolder holder = new KafkaConsumerHolder();
            holder.setKafkaConsumer(kafkaProducer);
            holder.setLastUseTime(System.currentTimeMillis());

            // генерим классы авро для этого профиля и топика
            try {
                generateAvroClasses(holder, kafkaConnectionProfile, topic, subject);
            } catch (Exception e) {
                log.error("Ошибка создания директорий и файлов с avro-схемами", e);
            }

            // складываем в мапу объект с консьюмером
            kafkaConsumers.put(profile + "_" + topic, holder);
            log.info("Создаем новый консьюмер с ключом: {}", profile + "_" + topic);

            // если при ходябы одном консьюмере в мапе расписание выключено, включаем его
            if (scheduledFuture == null || scheduledFuture.isCancelled()) {
                log.info("Запуск расписания");
                scheduledFuture = scheduler.scheduleAtFixedRate(() -> {
                    // пробегаемся по каждому консьюмеру и смотрим, не истекло ли его время
                    for (Map.Entry<String, KafkaConsumerHolder> entry : kafkaConsumers.entrySet()) {
                        long currentTime = System.currentTimeMillis();
                        log.info("entry.getValue().getLastUseTime() + 5000 < currentTime: {} < {}", entry.getValue().getLastUseTime() + 5000, currentTime);
                        if (entry.getValue().getLastUseTime() + 5000 < currentTime) {
                            log.info("Удаляем консьюмер: {}", entry.getKey());
                            KafkaConsumerHolder kafkaConsumerHolder = entry.getValue();

                            try {
                                close(kafkaConsumerHolder);
                                kafkaConsumers.remove(entry.getKey(), entry.getValue());
                            } catch (Exception e) {
                                log.error("Невозможно закрыть консьюмер для {}", entry.getKey(), e);
                            }
                        }

                        if (kafkaConsumers.isEmpty()) {
                            log.info("Выключаем scheduler");
                            scheduler.shutdown();
                            try {
                                scheduler.awaitTermination(1000, TimeUnit.MICROSECONDS);
                            } catch (InterruptedException e) {
                                log.warn("При завершении работы расписания, произошло непредвиденное прерывание", e);
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }, 5, 5, TimeUnit.SECONDS);

            }

            return holder;
        } else {
            KafkaConsumerHolder kafkaConsumerHolder = kafkaConsumers.get(profile + "_" + topic);
            long oldLastUseTime = kafkaConsumerHolder.getLastUseTime();
            kafkaConsumerHolder.setLastUseTime(System.currentTimeMillis());
            long newLastUseTime = kafkaConsumerHolder.getLastUseTime();
            log.info("Обновляем время для ключа {}. Текущее: {}, новое: {}", profile + "_" + topic, oldLastUseTime, newLastUseTime);
            return kafkaConsumerHolder;
        }
    }

    private void generateAvroClasses(KafkaConsumerHolder holder, KafkaConnectionProfile kafkaConnectionProfile,
                                     String topic, String subject) throws Exception {
        String schemaRegistryUrl = kafkaConnectionProfile.getSchemaRegistryConfiguration().getSchemaRegistryUrl();
        if (StringUtils.isNotBlank(schemaRegistryUrl)) {
            // вытаскиваем из Schema Registry нужную схему с идентификатором
            KafkaShemaRegistryService.SchemaData schemaData = kafkaShemaRegistryService
                    .loadSchema(schemaRegistryUrl, subject);
            holder.setSchemaId(schemaData.id);

            // получаем объект схемы из строкового представления
            Schema schema = new Schema.Parser().parse(schemaData.schemaSource);

            String avroSchemaRootFolderPath = StringUtils.defaultString(kafkaConnectionProfile
                    .getAvroSchemaRootFolder(), "avro");
            File avroSchemaRootFolder = new File(avroSchemaRootFolderPath, "consumer");

            Folders folders = createFolders(avroSchemaRootFolder, kafkaConnectionProfile.getName(), topic);

            if (folders == null) {
                return;
            }

            holder.setFolders(folders);

            File schemaFile = avroClassCompilerService.createSchemaFile(folders.tmpFolder, schema);
            folders.sourcesFolder = avroClassCompilerService.generateAvroClasses(schemaFile);
            avroClassCompilerService.compileAll(folders.sourcesFolder, folders.binariesFolder);

            URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {folders.binariesFolder.toURI().toURL()},
                    getClass().getClassLoader());
            Class<?> clazz = urlClassLoader.loadClass(schema.getNamespace() + "." + schema.getName());
            holder.setClazz(clazz);
        }
    }

    private Folders createFolders(File avroSchemaRootFolder, String profile, String topic) {
        if (!avroSchemaRootFolder.exists()) {
            log.error("Папки {} не существует", avroSchemaRootFolder.getAbsolutePath());
            return null;
        }

        Folders folders = new Folders();

        folders.profiledRootAvroFolder = avroSchemaRootFolder.toPath().resolve(profile + "_" + topic).toFile();
        mkdir(folders.profiledRootAvroFolder);

        folders.tmpFolder = folders.profiledRootAvroFolder.toPath().resolve("tmp").toFile();
        mkdir(folders.tmpFolder);

        folders.binariesFolder = new File(folders.profiledRootAvroFolder, "classes");
        mkdir(folders.binariesFolder);

        return folders;
    }

    public void close(KafkaConsumerHolder holder) {
        // удаляем все папки, связанные с этим консьюмером
        if (holder.getFolders() != null) {
            log.info("Удаляем все папки, связанные с этим консьюмером");
            File profiledRootAvroFolder = holder.getFolders().profiledRootAvroFolder;
            if (profiledRootAvroFolder.exists()) {
                try {
                    FileUtils.forceDelete(profiledRootAvroFolder);
                } catch (IOException e) {
                    log.error("Ошибка при удалении папки: {}", profiledRootAvroFolder.getAbsolutePath(), e);
                }
            } else {
                log.warn("Папки не существует: {}", profiledRootAvroFolder.getAbsolutePath());
            }
        }

        if (holder.getClazz() != null) {
            log.info("Закрываем класслоадер");
            try {
                ((URLClassLoader) holder.getClazz().getClassLoader()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // закрываем сам консьюмер
        log.info("Закрываем консьюмер");
        holder.getKafkaConsumer().close();
    }

    private void mkdir(File folder) {
        try {
            FileUtils.forceMkdir(folder);
            log.trace("Создали папку");
        } catch (IOException e) {
            throw new FolderCreationException(MessageFormat.format("Невозможно создать папку {0}", folder.getAbsolutePath()), e);
        }
    }

    public static class Folders {
        public File profiledRootAvroFolder;
        public File tmpFolder;
        public File binariesFolder;
        public File sourcesFolder;
    }
}
