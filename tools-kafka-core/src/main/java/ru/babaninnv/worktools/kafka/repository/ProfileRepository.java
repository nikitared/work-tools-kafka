package ru.babaninnv.worktools.kafka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.babaninnv.worktools.kafka.model.profile.KafkaProfile;

@Deprecated
@Repository
public interface ProfileRepository extends MongoRepository<KafkaProfile, String> {

}
