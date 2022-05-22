package ru.babaninnv.worktools.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaBrokerInfo;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.repository.KafkaConnectionProfileRepository;
import ru.babaninnv.worktools.kafka.utils.KafkaPropertiesUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaBrokerInfoService {

    private final KafkaConnectionProfileRepository kafkaConnectionProfileRepository;

    public List<KafkaBrokerInfo> info(String profileId) {
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileRepository
                .findTopByName(profileId).orElseThrow(RuntimeException::new);

        Properties properties = KafkaPropertiesUtils.convert(kafkaConnectionProfile);
        List<KafkaBrokerInfo> kafkaBrokerInfos = new ArrayList<>();

        try(AdminClient adminClient = KafkaAdminClient.create(properties)) {
            DescribeClusterResult describeClusterResult = adminClient.describeCluster();
            Collection<Node> nodes = describeClusterResult.nodes().get();

            for (Node node : nodes) {
                ConfigResource cr = new ConfigResource(ConfigResource.Type.BROKER, node.idString());
                DescribeConfigsResult describeConfigsResult = adminClient.describeConfigs(Collections.singleton(cr));
                Map<ConfigResource, Config> configResourceConfigMap = describeConfigsResult.all().get();
                Config config = configResourceConfigMap.get(cr);

                KafkaBrokerInfo kafkaBrokerInfo = new KafkaBrokerInfo();
                kafkaBrokerInfo.setZookeeperConnect(getValue(config, "zookeeper.connect"));
                kafkaBrokerInfo.setAdvertisedHostName(getValue(config, "advertised.host.name"));
                kafkaBrokerInfo.setAdvertisedListeners(getValue(config, "advertised.listeners"));
                kafkaBrokerInfo.setAdvertisedPort(getValue(config, "advertised.port"));
                kafkaBrokerInfo.setAutoCreateTopicsEnable(getValue(config, "auto.create.topics.enable"));
                kafkaBrokerInfo.setAutoLeaderRebalanceEnable(getValue(config, "auto.leader.rebalance.enable"));
                kafkaBrokerInfo.setBackgroundThreads(getValue(config, "background.threads"));
                kafkaBrokerInfo.setBrokerId(getValue(config, "broker.id"));
                kafkaBrokerInfo.setCompressionType(getValue(config, "compression.type"));
                kafkaBrokerInfo.setControlPlaneListenerName(getValue(config, "control.plane.listener.name"));
                kafkaBrokerInfo.setDeleteTopicEnable(getValue(config, "delete.topic.enable"));
                kafkaBrokerInfo.setHostName(getValue(config, "host.name"));
                kafkaBrokerInfo.setLeaderImbalanceCheckIntervalSeconds(getValue(config, "leader.imbalance.check.interval.seconds"));
                kafkaBrokerInfo.setLeaderImbalancePerBrokerPercentage(getValue(config, "leader.imbalance.per.broker.percentage"));
                kafkaBrokerInfo.setListeners(getValue(config, "listeners"));
                kafkaBrokerInfo.setLogDir(getValue(config, "log.dir"));
                kafkaBrokerInfo.setLogDirs(getValue(config, "log.dirs"));
                kafkaBrokerInfo.setLogFlushIntervalMessages(getValue(config, "log.flush.interval.messages"));
                kafkaBrokerInfo.setLogFlushIntervalMs(getValue(config, "log.flush.interval.ms"));
                kafkaBrokerInfo.setLogFlushOffsetCheckpointIntervalMs(getValue(config, "log.flush.offset.checkpoint.interval.ms"));
                kafkaBrokerInfo.setLogFlushSchedulerIntervalMs(getValue(config, "log.flush.scheduler.interval.ms"));
                kafkaBrokerInfo.setLogFlushStartOffsetCheckpointIntervalMs(getValue(config, "log.flush.start.offset.checkpoint.interval.ms"));
                kafkaBrokerInfo.setLogRetentionBytes(getValue(config, "log.retention.bytes"));
                kafkaBrokerInfo.setLogRetentionHours(getValue(config, "log.retention.hours"));
                kafkaBrokerInfo.setLogRetentionMinutes(getValue(config, "log.retention.minutes"));
                kafkaBrokerInfo.setLogRetentionMs(getValue(config, "log.retention.ms"));
                kafkaBrokerInfo.setLogRollHours(getValue(config, "log.roll.hours"));
                kafkaBrokerInfo.setLogRollJitterHours(getValue(config, "log.roll.jitter.hours"));
                kafkaBrokerInfo.setLogRollJitterMs(getValue(config, "log.roll.jitter.ms"));
                kafkaBrokerInfo.setLogRollMs(getValue(config, "log.roll.ms"));
                kafkaBrokerInfo.setLogSegmentBytes(getValue(config, "log.segment.bytes"));
                kafkaBrokerInfo.setLogSegmentDeleteDelayMs(getValue(config, "log.segment.delete.delay.ms"));
                kafkaBrokerInfo.setMessageMaxBytes(getValue(config, "message.max.bytes"));
                kafkaBrokerInfo.setMinInsyncReplicas(getValue(config, "min.insync.replicas"));
                kafkaBrokerInfo.setNumIoThreads(getValue(config, "num.io.threads"));
                kafkaBrokerInfo.setNumNetworkThreads(getValue(config, "num.network.threads"));
                kafkaBrokerInfo.setNumRecoveryThreadsPerDataDir(getValue(config, "num.recovery.threads.per.data.dir"));
                kafkaBrokerInfo.setNumReplicaAlterLogDirsThreads(getValue(config, "num.replica.alter.log.dirs.threads"));
                kafkaBrokerInfo.setNumReplicaFetchers(getValue(config, "num.replica.fetchers"));
                kafkaBrokerInfo.setOffsetMetadataMaxBytes(getValue(config, "offset.metadata.max.bytes"));
                kafkaBrokerInfo.setOffsetsCommitRequiredAcks(getValue(config, "offsets.commit.required.acks"));
                kafkaBrokerInfo.setOffsetsCommitTimeoutMs(getValue(config, "offsets.commit.timeout.ms"));
                kafkaBrokerInfo.setOffsetsLoadBufferSize(getValue(config, "offsets.load.buffer.size"));
                kafkaBrokerInfo.setOffsetsRetentionCheckIntervalMs(getValue(config, "offsets.retention.check.interval.ms"));
                kafkaBrokerInfo.setOffsetsRetentionMinutes(getValue(config, "offsets.retention.minutes"));
                kafkaBrokerInfo.setOffsetsTopicCompressionCodec(getValue(config, "offsets.topic.compression.codec"));
                kafkaBrokerInfo.setOffsetsTopicNumPartitions(getValue(config, "offsets.topic.num.partitions"));
                kafkaBrokerInfo.setOffsetsTopicReplicationFactor(getValue(config, "offsets.topic.replication.factor"));
                kafkaBrokerInfo.setOffsetsTopicSegmentBytes(getValue(config, "offsets.topic.segment.bytes"));
                kafkaBrokerInfo.setPort(getValue(config, "port"));
                kafkaBrokerInfo.setQueuedMaxRequests(getValue(config, "queued.max.requests"));
                kafkaBrokerInfo.setQuotaConsumerDefault(getValue(config, "quota.consumer.default"));
                kafkaBrokerInfo.setQuotaProducerDefault(getValue(config, "quota.producer.default"));
                kafkaBrokerInfo.setReplicaFetchMinBytes(getValue(config, "replica.fetch.min.bytes"));
                kafkaBrokerInfo.setReplicaFetchWaitMaxMs(getValue(config, "replica.fetch.wait.max.ms"));
                kafkaBrokerInfo.setReplicaHighWatermarkCheckpointIntervalMs(getValue(config, "replica.high.watermark.checkpoint.interval.ms"));
                kafkaBrokerInfo.setReplicaLagTimeMaxMs(getValue(config, "replica.lag.time.max.ms"));
                kafkaBrokerInfo.setReplicaSocketReceiveBufferBytes(getValue(config, "replica.socket.receive.buffer.bytes"));
                kafkaBrokerInfo.setReplicaSocketTimeoutMs(getValue(config, "replica.socket.timeout.ms"));
                kafkaBrokerInfo.setRequestTimeoutMs(getValue(config, "request.timeout.ms"));
                kafkaBrokerInfo.setSocketReceiveBufferBytes(getValue(config, "socket.receive.buffer.bytes"));
                kafkaBrokerInfo.setSocketRequestMaxBytes(getValue(config, "socket.request.max.bytes"));
                kafkaBrokerInfo.setSocketSendBufferBytes(getValue(config, "socket.send.buffer.bytes"));
                kafkaBrokerInfo.setTransactionMaxTimeoutMs(getValue(config, "transaction.max.timeout.ms"));
                kafkaBrokerInfo.setTransactionStateLogLoadBufferSize(getValue(config, "transaction.state.log.load.buffer.size"));
                kafkaBrokerInfo.setTransactionStateLogMinIsr(getValue(config, "transaction.state.log.min.isr"));
                kafkaBrokerInfo.setTransactionStateLogNumPartitions(getValue(config, "transaction.state.log.num.partitions"));
                kafkaBrokerInfo.setTransactionStateLogReplicationFactor(getValue(config, "transaction.state.log.replication.factor"));
                kafkaBrokerInfo.setTransactionStateLogSegmentBytes(getValue(config, "transaction.state.log.segment.bytes"));
                kafkaBrokerInfo.setTransactionalIdExpirationMs(getValue(config, "transactional.id.expiration.ms"));
                kafkaBrokerInfo.setUncleanLeaderElectionEnable(getValue(config, "unclean.leader.election.enable"));
                kafkaBrokerInfo.setZookeeperConnectionTimeoutMs(getValue(config, "zookeeper.connection.timeout.ms"));
                kafkaBrokerInfo.setZookeeperMaxInFlightRequests(getValue(config, "zookeeper.max.in.flight.requests"));
                kafkaBrokerInfo.setZookeeperSessionTimeoutMs(getValue(config, "zookeeper.session.timeout.ms"));
                kafkaBrokerInfo.setZookeeperSetAcl(getValue(config, "zookeeper.set.acl"));
                kafkaBrokerInfo.setBrokerIdGenerationEnable(getValue(config, "broker.id.generation.enable"));
                kafkaBrokerInfo.setBrokerRack(getValue(config, "broker.rack"));
                kafkaBrokerInfo.setConnectionsMaxIdleMs(getValue(config, "connections.max.idle.ms"));
                kafkaBrokerInfo.setConnectionsMaxReauthMs(getValue(config, "connections.max.reauth.ms"));
                kafkaBrokerInfo.setControlledShutdownEnable(getValue(config, "controlled.shutdown.enable"));
                kafkaBrokerInfo.setControlledShutdownMaxRetries(getValue(config, "controlled.shutdown.max.retries"));
                kafkaBrokerInfo.setControlledShutdownRetryBackoffMs(getValue(config, "controlled.shutdown.retry.backoff.ms"));
                kafkaBrokerInfo.setControllerSocketTimeoutMs(getValue(config, "controller.socket.timeout.ms"));
                kafkaBrokerInfo.setDefaultReplicationFactor(getValue(config, "default.replication.factor"));
                kafkaBrokerInfo.setDelegationTokenExpiryTimeMs(getValue(config, "delegation.token.expiry.time.ms"));
                kafkaBrokerInfo.setDelegationTokenMasterKey(getValue(config, "delegation.token.master.key"));
                kafkaBrokerInfo.setDelegationTokenMaxLifetimeMs(getValue(config, "delegation.token.max.lifetime.ms"));
                kafkaBrokerInfo.setDeleteRecordsPurgatoryPurgeIntervalRequests(getValue(config, "delete.records.purgatory.purge.interval.requests"));
                kafkaBrokerInfo.setFetchMaxBytes(getValue(config, "fetch.max.bytes"));
                kafkaBrokerInfo.setFetchPurgatoryPurgeIntervalRequests(getValue(config, "fetch.purgatory.purge.interval.requests"));
                kafkaBrokerInfo.setGroupInitialRebalanceDelayMs(getValue(config, "group.initial.rebalance.delay.ms"));
                kafkaBrokerInfo.setGroupMaxSessionTimeoutMs(getValue(config, "group.max.session.timeout.ms"));
                kafkaBrokerInfo.setGroupMaxSize(getValue(config, "group.max.size"));
                kafkaBrokerInfo.setGroupMinSessionTimeoutMs(getValue(config, "group.min.session.timeout.ms"));
                kafkaBrokerInfo.setInterBrokerListenerName(getValue(config, "inter.broker.listener.name"));
                kafkaBrokerInfo.setInterBrokerProtocolVersion(getValue(config, "inter.broker.protocol.version"));
                kafkaBrokerInfo.setLogCleanerBackoffMs(getValue(config, "log.cleaner.backoff.ms"));
                kafkaBrokerInfo.setLogCleanerDedupeBufferSize(getValue(config, "log.cleaner.dedupe.buffer.size"));
                kafkaBrokerInfo.setLogCleanerDeleteRetentionMs(getValue(config, "log.cleaner.delete.retention.ms"));
                kafkaBrokerInfo.setLogCleanerEnable(getValue(config, "log.cleaner.enable"));
                kafkaBrokerInfo.setLogCleanerIoBufferLoadFactor(getValue(config, "log.cleaner.io.buffer.load.factor"));
                kafkaBrokerInfo.setLogCleanerIoBufferSize(getValue(config, "log.cleaner.io.buffer.size"));
                kafkaBrokerInfo.setLogCleanerIoMaxBytesPerSecond(getValue(config, "log.cleaner.io.max.bytes.per.second"));
                kafkaBrokerInfo.setLogCleanerMaxCompactionLagMs(getValue(config, "log.cleaner.max.compaction.lag.ms"));
                kafkaBrokerInfo.setLogCleanerMinCleanableRatio(getValue(config, "log.cleaner.min.cleanable.ratio"));
                kafkaBrokerInfo.setLogCleanerMinCompactionLagMs(getValue(config, "log.cleaner.min.compaction.lag.ms"));
                kafkaBrokerInfo.setLogCleanerThreads(getValue(config, "log.cleaner.threads"));
                kafkaBrokerInfo.setLogCleanupPolicy(getValue(config, "log.cleanup.policy"));
                kafkaBrokerInfo.setLogIndexIntervalBytes(getValue(config, "log.index.interval.bytes"));
                kafkaBrokerInfo.setLogIndexSizeMaxBytes(getValue(config, "log.index.size.max.bytes"));
                kafkaBrokerInfo.setLogMessageFormatVersion(getValue(config, "log.message.format.version"));
                kafkaBrokerInfo.setLogMessageTimestampDifferenceMaxMs(getValue(config, "log.message.timestamp.difference.max.ms"));
                kafkaBrokerInfo.setLogMessageTimestampType(getValue(config, "log.message.timestamp.type"));
                kafkaBrokerInfo.setLogPreallocate(getValue(config, "log.preallocate"));
                kafkaBrokerInfo.setLogRetentionCheckIntervalMs(getValue(config, "log.retention.check.interval.ms"));
                kafkaBrokerInfo.setMaxConnections(getValue(config, "max.connections"));
                kafkaBrokerInfo.setMaxConnectionsPerIp(getValue(config, "max.connections.per.ip"));
                kafkaBrokerInfo.setMaxConnectionsPerIpOverrides(getValue(config, "max.connections.per.ip.overrides"));
                kafkaBrokerInfo.setMaxIncrementalFetchSessionCacheSlots(getValue(config, "max.incremental.fetch.session.cache.slots"));
                kafkaBrokerInfo.setNumPartitions(getValue(config, "num.partitions"));
                kafkaBrokerInfo.setPasswordEncoderOldSecret(getValue(config, "password.encoder.old.secret"));
                kafkaBrokerInfo.setPasswordEncoderSecret(getValue(config, "password.encoder.secret"));
                kafkaBrokerInfo.setPrincipalBuilderClass(getValue(config, "principal.builder.class"));
                kafkaBrokerInfo.setProducerPurgatoryPurgeIntervalRequests(getValue(config, "producer.purgatory.purge.interval.requests"));
                kafkaBrokerInfo.setQueuedMaxRequestBytes(getValue(config, "queued.max.request.bytes"));
                kafkaBrokerInfo.setReplicaFetchBackoffMs(getValue(config, "replica.fetch.backoff.ms"));
                kafkaBrokerInfo.setReplicaFetchMaxBytes(getValue(config, "replica.fetch.max.bytes"));
                kafkaBrokerInfo.setReplicaFetchResponseMaxBytes(getValue(config, "replica.fetch.response.max.bytes"));
                kafkaBrokerInfo.setReplicaSelectorClass(getValue(config, "replica.selector.class"));
                kafkaBrokerInfo.setReservedBrokerMaxId(getValue(config, "reserved.broker.max.id"));
                kafkaBrokerInfo.setSaslClientCallbackHandlerClass(getValue(config, "sasl.client.callback.handler.class"));
                kafkaBrokerInfo.setSaslEnabledMechanisms(getValue(config, "sasl.enabled.mechanisms"));
                kafkaBrokerInfo.setSaslJaasConfig(getValue(config, "sasl.jaas.config"));
                kafkaBrokerInfo.setSaslKerberosKinitCmd(getValue(config, "sasl.kerberos.kinit.cmd"));
                kafkaBrokerInfo.setSaslKerberosMinTimeBeforeRelogin(getValue(config, "sasl.kerberos.min.time.before.relogin"));
                kafkaBrokerInfo.setSaslKerberosPrincipalToLocalRules(getValue(config, "sasl.kerberos.principal.to.local.rules"));
                kafkaBrokerInfo.setSaslKerberosServiceName(getValue(config, "sasl.kerberos.service.name"));
                kafkaBrokerInfo.setSaslKerberosTicketRenewJitter(getValue(config, "sasl.kerberos.ticket.renew.jitter"));
                kafkaBrokerInfo.setSaslKerberosTicketRenewWindowFactor(getValue(config, "sasl.kerberos.ticket.renew.window.factor"));
                kafkaBrokerInfo.setSaslLoginCallbackHandlerClass(getValue(config, "sasl.login.callback.handler.class"));
                kafkaBrokerInfo.setSaslLoginClass(getValue(config, "sasl.login.class"));
                kafkaBrokerInfo.setSaslLoginRefreshBufferSeconds(getValue(config, "sasl.login.refresh.buffer.seconds"));
                kafkaBrokerInfo.setSaslLoginRefreshMinPeriodSeconds(getValue(config, "sasl.login.refresh.min.period.seconds"));
                kafkaBrokerInfo.setSaslLoginRefreshWindowFactor(getValue(config, "sasl.login.refresh.window.factor"));
                kafkaBrokerInfo.setSaslLoginRefreshWindowJitter(getValue(config, "sasl.login.refresh.window.jitter"));
                kafkaBrokerInfo.setSaslMechanismInterBrokerProtocol(getValue(config, "sasl.mechanism.inter.broker.protocol"));
                kafkaBrokerInfo.setSaslServerCallbackHandlerClass(getValue(config, "sasl.server.callback.handler.class"));
                kafkaBrokerInfo.setSecurityInterBrokerProtocol(getValue(config, "security.inter.broker.protocol"));
                kafkaBrokerInfo.setSslCipherSuites(getValue(config, "ssl.cipher.suites"));
                kafkaBrokerInfo.setSslClientAuth(getValue(config, "ssl.client.auth"));
                kafkaBrokerInfo.setSslEnabledProtocols(getValue(config, "ssl.enabled.protocols"));
                kafkaBrokerInfo.setSslKeyPassword(getValue(config, "ssl.key.password"));
                kafkaBrokerInfo.setSslKeymanagerAlgorithm(getValue(config, "ssl.keymanager.algorithm"));
                kafkaBrokerInfo.setSslKeystoreLocation(getValue(config, "ssl.keystore.location"));
                kafkaBrokerInfo.setSslKeystorePassword(getValue(config, "ssl.keystore.password"));
                kafkaBrokerInfo.setSslKeystoreType(getValue(config, "ssl.keystore.type"));
                kafkaBrokerInfo.setSslProtocol(getValue(config, "ssl.protocol"));
                kafkaBrokerInfo.setSslProvider(getValue(config, "ssl.provider"));
                kafkaBrokerInfo.setSslTrustmanagerAlgorithm(getValue(config, "ssl.trustmanager.algorithm"));
                kafkaBrokerInfo.setSslTruststoreLocation(getValue(config, "ssl.truststore.location"));
                kafkaBrokerInfo.setSslTruststorePassword(getValue(config, "ssl.truststore.password"));
                kafkaBrokerInfo.setSslTruststoreType(getValue(config, "ssl.truststore.type"));
                kafkaBrokerInfo.setZookeeperClientCnxnSocket(getValue(config, "zookeeper.client.cnxn.socket"));
                kafkaBrokerInfo.setZookeeperSslClientEnable(getValue(config, "zookeeper.ssl.client.enable"));
                kafkaBrokerInfo.setZookeeperSslKeystoreLocation(getValue(config, "zookeeper.ssl.keystore.location"));
                kafkaBrokerInfo.setZookeeperSslKeystorePassword(getValue(config, "zookeeper.ssl.keystore.password"));
                kafkaBrokerInfo.setZookeeperSslKeystoreType(getValue(config, "zookeeper.ssl.keystore.type"));
                kafkaBrokerInfo.setZookeeperSslTruststoreLocation(getValue(config, "zookeeper.ssl.truststore.location"));
                kafkaBrokerInfo.setZookeeperSslTruststorePassword(getValue(config, "zookeeper.ssl.truststore.password"));
                kafkaBrokerInfo.setZookeeperSslTruststoreType(getValue(config, "zookeeper.ssl.truststore.type"));
                kafkaBrokerInfo.setAlterConfigPolicyClassName(getValue(config, "alter.config.policy.class.name"));
                kafkaBrokerInfo.setAlterLogDirsReplicationQuotaWindowNum(getValue(config, "alter.log.dirs.replication.quota.window.num"));
                kafkaBrokerInfo.setAlterLogDirsReplicationQuotaWindowSizeSeconds(getValue(config, "alter.log.dirs.replication.quota.window.size.seconds"));
                kafkaBrokerInfo.setAuthorizerClassName(getValue(config, "authorizer.class.name"));
                kafkaBrokerInfo.setClientQuotaCallbackClass(getValue(config, "client.quota.callback.class"));
                kafkaBrokerInfo.setConnectionFailedAuthenticationDelayMs(getValue(config, "connection.failed.authentication.delay.ms"));
                kafkaBrokerInfo.setCreateTopicPolicyClassName(getValue(config, "create.topic.policy.class.name"));
                kafkaBrokerInfo.setDelegationTokenExpiryCheckIntervalMs(getValue(config, "delegation.token.expiry.check.interval.ms"));
                kafkaBrokerInfo.setKafkaMetricsPollingIntervalSecs(getValue(config, "kafka.metrics.polling.interval.secs"));
                kafkaBrokerInfo.setKafkaMetricsReporters(getValue(config, "kafka.metrics.reporters"));
                kafkaBrokerInfo.setListenerSecurityProtocolMap(getValue(config, "listener.security.protocol.map"));
                kafkaBrokerInfo.setLogMessageDownconversionEnable(getValue(config, "log.message.downconversion.enable"));
                kafkaBrokerInfo.setMetricReporters(getValue(config, "metric.reporters"));
                kafkaBrokerInfo.setMetricsNumSamples(getValue(config, "metrics.num.samples"));
                kafkaBrokerInfo.setMetricsRecordingLevel(getValue(config, "metrics.recording.level"));
                kafkaBrokerInfo.setMetricsSampleWindowMs(getValue(config, "metrics.sample.window.ms"));
                kafkaBrokerInfo.setPasswordEncoderCipherAlgorithm(getValue(config, "password.encoder.cipher.algorithm"));
                kafkaBrokerInfo.setPasswordEncoderIterations(getValue(config, "password.encoder.iterations"));
                kafkaBrokerInfo.setPasswordEncoderKeyLength(getValue(config, "password.encoder.key.length"));
                kafkaBrokerInfo.setPasswordEncoderKeyfactoryAlgorithm(getValue(config, "password.encoder.keyfactory.algorithm"));
                kafkaBrokerInfo.setQuotaWindowNum(getValue(config, "quota.window.num"));
                kafkaBrokerInfo.setQuotaWindowSizeSeconds(getValue(config, "quota.window.size.seconds"));
                kafkaBrokerInfo.setReplicationQuotaWindowNum(getValue(config, "replication.quota.window.num"));
                kafkaBrokerInfo.setReplicationQuotaWindowSizeSeconds(getValue(config, "replication.quota.window.size.seconds"));
                kafkaBrokerInfo.setSecurityProviders(getValue(config, "security.providers"));
                kafkaBrokerInfo.setSslEndpointIdentificationAlgorithm(getValue(config, "ssl.endpoint.identification.algorithm"));
                kafkaBrokerInfo.setSslPrincipalMappingRules(getValue(config, "ssl.principal.mapping.rules"));
                kafkaBrokerInfo.setSslSecureRandomImplementation(getValue(config, "ssl.secure.random.implementation"));
                kafkaBrokerInfo.setTransactionAbortTimedOutTransactionCleanupIntervalMs(getValue(config, "transaction.abort.timed.out.transaction.cleanup.interval.ms"));
                kafkaBrokerInfo.setTransactionRemoveExpiredTransactionCleanupIntervalMs(getValue(config, "transaction.remove.expired.transaction.cleanup.interval.ms"));
                kafkaBrokerInfo.setZookeeperSslCipherSuites(getValue(config, "zookeeper.ssl.cipher.suites"));
                kafkaBrokerInfo.setZookeeperSslCrlEnable(getValue(config, "zookeeper.ssl.crl.enable"));
                kafkaBrokerInfo.setZookeeperSslEnabledProtocols(getValue(config, "zookeeper.ssl.enabled.protocols"));
                kafkaBrokerInfo.setZookeeperSslEndpointIdentificationAlgorithm(getValue(config, "zookeeper.ssl.endpoint.identification.algorithm"));
                kafkaBrokerInfo.setZookeeperSslOcspEnable(getValue(config, "zookeeper.ssl.ocsp.enable"));
                kafkaBrokerInfo.setZookeeperSslProtocol(getValue(config, "zookeeper.ssl.protocol"));
                kafkaBrokerInfo.setZookeeperSyncTimeMs(getValue(config, "zookeeper.sync.time.ms"));

                kafkaBrokerInfos.add(kafkaBrokerInfo);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return kafkaBrokerInfos;
    }

    private String getValue(Config config, String name) {
        ConfigEntry configEntry = config.get(name);
        return configEntry != null ? configEntry.value() : null;
    }
}
