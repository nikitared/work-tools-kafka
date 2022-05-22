package ru.babaninnv.worktools.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.profile.ConnectConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.KafkaSslConfiguration;

import java.util.Properties;

@Slf4j
@Service
public class KafkaConnectionService {
    public boolean test(ConnectConfiguration connectConfiguration) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, connectConfiguration.getBootstrapServers());
        properties.setProperty(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, connectConfiguration.getSecurityProtocol());
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        if (connectConfiguration.getSecurityProtocol().equals("SSL")) {
            KafkaSslConfiguration sslConfiguration = connectConfiguration.getSslConfiguration();
            properties.setProperty(SslConfigs.SSL_KEY_PASSWORD_CONFIG, sslConfiguration.getSslKeyPassword());
            properties.setProperty(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, sslConfiguration.getSslKeystoreType());
            properties.setProperty(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, sslConfiguration.getSslKeystoreLocation());
            properties.setProperty(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, sslConfiguration.getSslKeystorePassword());
            properties.setProperty(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, sslConfiguration.getSslTruststoreType());
            properties.setProperty(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, sslConfiguration.getSslTruststoreLocation());
            properties.setProperty(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, sslConfiguration.getSslTruststorePassword());
        }

        try(KafkaConsumer kafkaConsumer = new KafkaConsumer(properties)) {
            kafkaConsumer.listTopics();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
