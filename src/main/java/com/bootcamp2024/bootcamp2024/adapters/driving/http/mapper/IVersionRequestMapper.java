package com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IVersionRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bootcamp.id", source = "addVersionRequest.bootcampId")
    Version addRequestToVersion(AddVersionRequest addVersionRequest);

}
