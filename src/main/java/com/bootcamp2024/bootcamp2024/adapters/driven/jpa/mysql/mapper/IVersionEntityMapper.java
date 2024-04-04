package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "maximumCapacity", target = "maximumCapacity")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    Version toModel(VersionEntity versionEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "maximumCapacity", target = "maximumCapacity")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    VersionEntity toEntity(Version version);

    List<Version> toModelList(List<VersionEntity> versionEntities);
}
