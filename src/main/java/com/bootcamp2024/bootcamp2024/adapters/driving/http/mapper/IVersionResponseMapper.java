package com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.VersionResponse;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionResponseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "maximumCapacity", target = "maximumCapacity")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "bootcamp.name", target = "bootcampName")
    VersionResponse toVersionResponse(Version version);

    List<VersionResponse> toVersionResponseList(List<Version> versionList);
}
