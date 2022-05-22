package ru.babaninnv.worktools.kafka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;

import java.util.Optional;

@Repository
public interface KafkaConnectionProfileRepository extends MongoRepository<KafkaConnectionProfile, String> {
    Optional<KafkaConnectionProfile> findTopByName(String name);
}
