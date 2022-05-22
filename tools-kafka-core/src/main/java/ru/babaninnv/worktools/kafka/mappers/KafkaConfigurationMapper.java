package ru.babaninnv.worktools.kafka.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.babaninnv.worktools.kafka.model.profile.AdminConfiguration;
import ru.babaninnv.worktools.kafka.model.profile.ConnectConfiguration;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface KafkaConfigurationMapper {

    KafkaConfigurationMapper MAPPER = Mappers.getMapper(KafkaConfigurationMapper.class);

    ConnectConfiguration convert(AdminConfiguration adminConfiguration);

    AdminConfiguration convert(ConnectConfiguration connectConfiguration);

    void update(AdminConfiguration adminConfiguration, @MappingTarget ConnectConfiguration connectConfiguration);

    void update(ConnectConfiguration connectConfiguration, @MappingTarget AdminConfiguration adminConfiguration);
}
