package com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper;


import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ITechnologyRequestMapper {

    @Mapping(target = "id", ignore = true)
    Technology addRequestToTechnology(AddTechnologyRequest addTechnologyRequest);
}
