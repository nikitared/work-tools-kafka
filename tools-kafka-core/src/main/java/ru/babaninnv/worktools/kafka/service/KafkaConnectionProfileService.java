package ru.babaninnv.worktools.kafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.babaninnv.worktools.kafka.model.KafkaConnectionProfile;
import ru.babaninnv.worktools.kafka.repository.KafkaConnectionProfileRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaConnectionProfileService {

    private final KafkaConnectionProfileRepository kafkaConnectionProfileRepository;

    public List<KafkaConnectionProfile> list() {
        return kafkaConnectionProfileRepository.findAll();
    }

    public KafkaConnectionProfile getByName(String name) {
        // сначала идем в базу
        KafkaConnectionProfile kafkaConnectionProfile = kafkaConnectionProfileRepository.findTopByName(name).orElse(null);

        if (kafkaConnectionProfile == null) {
            // если в базе не нашли, идем в файл
        }

        return kafkaConnectionProfileRepository.findTopByName(name).orElse(null);
    }

    public void save(KafkaConnectionProfile kafkaConnectionProfile) {
        kafkaConnectionProfileRepository.findTopByName(kafkaConnectionProfile.getName())
                .ifPresent(kafkaConnectionProfileRepository::delete);
        kafkaConnectionProfileRepository.save(kafkaConnectionProfile);
    }

    public List<KafkaConnectionProfile> delete(String id) {
        kafkaConnectionProfileRepository.deleteById(id);
        return kafkaConnectionProfileRepository.findAll();
    }
}
