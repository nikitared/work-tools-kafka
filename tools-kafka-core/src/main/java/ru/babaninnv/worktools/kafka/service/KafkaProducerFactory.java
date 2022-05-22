package ru.babaninnv.worktools.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.service.avro.AvroClassCompilerService;
import ru.babaninnv.worktools.kafka.service.avro.SchemaVersion;
import ru.babaninnv.worktools.kafka.service.producer.Folders;
import ru.babaninnv.worktools.kafka.service.producer.GeneratedAvroResources;
import ru.babaninnv.worktools.kafka.service.producer.KafkaResourcesFolderDefaultService;
import ru.babaninnv.worktools.kafka.service.producer.KafkaResourcesFolderService;
import ru.babaninnv.worktools.kafka.service.schemaregistry.KafkaSchemaRegistryService;
import ru.babaninnv.worktools.kafka.service.schemaregistry.SchemaData;
import ru.babaninnv.worktools.kafka.utils.KafkaPropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerFactory {
    private Map<String, KafkaProducerHolder> kafkaProducers = new ConcurrentHashMap<>();

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> scheduledFuture = null;

    private final AvroClassCompilerService avroClassCompilerService;

    private final KafkaSchemaRegistryService kafkaSchemaRegistryService;

    private KafkaResourcesFolderService kafkaResourcesFolderService = new KafkaResourcesFolderDefaultService();

    public void setKafkaResourcesFolderService(KafkaResourcesFolderService kafkaResourcesFolderService) {
        this.kafkaResourcesFolderService = kafkaResourcesFolderService;
    }

    // Класс потока должен иметь ConcurrentMap для хранения ключа(профиль+топик) и значения(экземпляр {kafkaProducer: KafkaProducer, lastUseTimestamp: long})
    // также при создании поток необходимо создать процесс, который через минуту пробегается по ConcurrentMap и смотрит на lastUseTimestamp. Просроченные продюсеры будут удалены
    // при каждом использовании kafkaProducer, lastUseTimestamp обновляется
    // если в ConcurrentMap не остаётся ни одной пары, процесс с таймером автоматически завершается

    // создание и удаление kafkaProducer сопрягается с генерацией и удалением классов авро-схем
    // для кадого kafkaProducer будет создана отдельная папка
    public KafkaProducerHolder get(String profile, String topic, KafkaConnectionProfile kafkaConnectionProfile) {
        return get(profile, topic, kafkaConnectionProfile, topic + "-value");
    }

    public KafkaProducerHolder get(String profile, String topic, KafkaConnectionProfile kafkaConnectionProfile, String subject) {

        // проверяем, что в мапе еще нет продюсера для профиля и топика
        if (kafkaProducers.containsKey(profile + "_" + topic)) {
            return getKafkaProducerHolderFromCache(profile, topic);
        } else {
            KafkaProducerHolder kafkaProducerHolder = setupKafkaPorducerHolder(kafkaConnectionProfile, topic, subject);

            // складываем в мапу объект с продюсером
            kafkaProducers.put(profile + "_" + topic, kafkaProducerHolder);
            log.info("Создаем новый продюсер с ключом: {}", profile + "_" + topic);

            // если при ходябы одном продюсере в мапе расписание выключено, включаем его
            if (scheduledFuture == null || scheduledFuture.isCancelled()) {
                scheduleStartUp();
            }

            return kafkaProducerHolder;
        }
    }

    private KafkaProducerHolder setupKafkaPorducerHolder(KafkaConnectionProfile kafkaConnectionProfile,
                                                         String topic, String subject) {
        Properties properties = KafkaPropertiesUtils.convertForProducer(kafkaConnectionProfile);

        // создаём экземпляр продюсера
        KafkaProducer<Object, Object> kafkaProducer = new KafkaProducer<>(properties);

        // оборачиваем продюсер в объект с указанием времени его создания
        KafkaProducerHolder holder = new KafkaProducerHolder();
        holder.setKafkaProducer(kafkaProducer);
        holder.setLastUseTime(System.currentTimeMillis());

        if (StringUtils.isNotBlank(subject)) {
            // вытаскиваем из схема-реджистри нужную схему
            Pair<Integer, Schema> highestVersionSchema = loadLastVersionSchema(kafkaConnectionProfile
                    .getSchemaRegistryConfiguration().getSchemaRegistryUrl(), subject);
            holder.setSchemaId(highestVersionSchema.getKey());
            holder.setSchema(highestVersionSchema.getValue());

            // либо грузим схему откуда-то еще
            // ... в разработке

            // если нужно генерим классы авро для этого профиля и топика
            if (kafkaConnectionProfile.getProducerConfiguration().isUseClassGenerating()) {
                try {
                    GeneratedAvroResources generatedAvroResources = generateAvroClassesV2(holder.getSchema(),
                            new File(kafkaConnectionProfile.getAvroSchemaRootFolder()), topic, subject);
                    holder.setClazz(generatedAvroResources.getClazz());
                    Folders folders = new Folders();
                    folders.profiledRootAvroFolder = generatedAvroResources.getProfiledRootAvroFolder();
                    holder.setFolders(folders);
                } catch (Exception e) {
                    log.error("Ошибка создания директорий и файлов с avro-схемами", e);
                }
            }
        }

        return holder;
    }

    private KafkaProducerHolder getKafkaProducerHolderFromCache(String profile, String topic) {
        KafkaProducerHolder kafkaProducerHolder = kafkaProducers.get(profile + "_" + topic);
        long oldLastUseTime = kafkaProducerHolder.getLastUseTime();
        kafkaProducerHolder.setLastUseTime(System.currentTimeMillis());
        long newLastUseTime = kafkaProducerHolder.getLastUseTime();
        log.info("Обновляем время для ключа {}. Текущее: {}, новое: {}",
                profile + "_" + topic, oldLastUseTime, newLastUseTime);
        return kafkaProducerHolder;
    }

    private void scheduleStartUp() {
        log.info("Запуск расписания");

        scheduledFuture = scheduler.scheduleAtFixedRate(getCommand(), 5, 5, TimeUnit.SECONDS);
    }

    private Runnable getCommand() {
        return () -> {
            // пробегаемся по каждому продюсеру и смотрим, не истекло ли его время
            for (Map.Entry<String, KafkaProducerHolder> entry : kafkaProducers.entrySet()) {
                long currentTime = System.currentTimeMillis();
                log.info("entry.getValue().getLastUseTime() + 5000 < currentTime: {} < {}",
                        entry.getValue().getLastUseTime() + 5000, currentTime);
                if (entry.getValue().getLastUseTime() + 5000 < currentTime) {
                    log.info("Удаляем продюсер: {}", entry.getKey());
                    KafkaProducerHolder kafkaProducerHolder = entry.getValue();

                    try {
                        close(kafkaProducerHolder);
                        kafkaProducers.remove(entry.getKey(), entry.getValue());
                    } catch (Exception e) {
                        log.error("Невозможно закрыть продюсер для {}", entry.getKey(), e);
                    }
                }

                if (kafkaProducers.isEmpty()) {
                    log.info("Выключаем scheduler");
                    scheduledFuture.cancel(true);
                }
            }
        };
    }

    private Pair<Integer, Schema> loadLastVersionSchema(String schemaRegistryUrl, String subject) {
        Map<SchemaVersion, SchemaData> avroSchemas = kafkaSchemaRegistryService
                .getAvroSchemasByVersion(schemaRegistryUrl, subject);
        Set<Integer> versions = avroSchemas.keySet().stream()
                .map(SchemaVersion::getVersion)
                .collect(Collectors.toSet());
        int highestVersion = Collections.max(versions);
        SchemaData schemaData = avroSchemas.get(new SchemaVersion(highestVersion));
        return Pair.of(schemaData.getId(), schemaData.getSchema());
    }

    private GeneratedAvroResources generateAvroClassesV2(Schema schema, File generatedResourcesOutputFolder,
                                                         String profile, String topic) throws Exception {
        // на вход подали
        // 1. авро-схему
        // 2. базовый путь куда схохранять сгенерированные/скомпиленные ресурсы

        // создание дочерних папок с ресурсами: для схем, сгенерированных исходников, классов
        Folders folders = kafkaResourcesFolderService.create(generatedResourcesOutputFolder, profile, topic);

        File schemaFile = avroClassCompilerService.createSchemaFile(folders.schemasFolder, schema);
        folders.sourcesFolder = avroClassCompilerService.generateAvroClasses(schemaFile);
        avroClassCompilerService.compileAll(folders.sourcesFolder, folders.binariesFolder);

        // после компиляции ненужные ресурсы можно сразу удалить
        FileUtils.forceDelete(folders.schemasFolder);
        folders.schemasFolder = null;
        FileUtils.forceDelete(folders.sourcesFolder);
        folders.sourcesFolder = null;

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {folders.binariesFolder.toURI().toURL()},
                getClass().getClassLoader());
        Class<?> clazz = urlClassLoader.loadClass(schema.getNamespace() + "." + schema.getName());

        // на выход выдаем объект:
        // 1. папка со схемами
        // 2. папка с исходниками
        // 3. папка с классами
        // 4. класс для преобразования avro <-> json
        GeneratedAvroResources generatedAvroResources = new GeneratedAvroResources();
        generatedAvroResources.setClazz(clazz);
        generatedAvroResources.setBinariesFolder(folders.binariesFolder);
        generatedAvroResources.setProfiledRootAvroFolder(folders.profiledRootAvroFolder);
        return generatedAvroResources;
    }



    public void close(KafkaProducerHolder holder) {
        // удаляем все папки, связанные с этим продюсером
        if (holder.getFolders() != null) {
            kafkaResourcesFolderService.delete(holder.getFolders());
        }

        if (holder.getClazz() != null) {
            log.info("Закрываем класслоадер");
            try {
                ((URLClassLoader) holder.getClazz().getClassLoader()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // закрываем сам продюсер
        log.info("Закрываем продюсер");
        holder.getKafkaProducer().close();
    }
}
