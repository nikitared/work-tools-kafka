package ru.babaninnv.worktools.kafka.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ConsumerProcess implements Runnable {
    private final KafkaConsumer consumer;
    private final List<String> topics;
    private final AtomicBoolean shutdown;
    private final CountDownLatch shutdownLatch;

    public ConsumerProcess(Properties config, List<String> topics) {
        this.consumer = new KafkaConsumer<>(config);
        this.topics = topics;
        this.shutdown = new AtomicBoolean(false);
        this.shutdownLatch = new CountDownLatch(1);
    }

    @Override
    public void run() {
        // взять текущее подключение

        // проверить что оно активно

        // создать консьюмер

        // запустить цикл
        try {
            consumer.subscribe(topics);

            while (!shutdown.get()) {
                ConsumerRecords records = consumer.poll(Duration.ofSeconds(2));
                records.forEach(record -> {

                });
            }
        } finally {
            consumer.close();
            shutdownLatch.countDown();
        }

        // в цикле проверять shutdown
    }

    public void shutdown() throws InterruptedException {
        shutdown.set(true);
        shutdownLatch.await();
    }
}
