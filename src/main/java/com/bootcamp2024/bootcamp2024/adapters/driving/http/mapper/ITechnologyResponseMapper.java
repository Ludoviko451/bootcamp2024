package com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper;


import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyResponse;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyResponseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name") // Ajustar este mapeo según la estructura de tu DTO de respuesta
    @Mapping(source = "description", target = "description")
    TechnologyResponse toTechnologyResponse(Technology technology);

    List<TechnologyResponse> toTechnologyResponseList(List<Technology> technologies);
}
