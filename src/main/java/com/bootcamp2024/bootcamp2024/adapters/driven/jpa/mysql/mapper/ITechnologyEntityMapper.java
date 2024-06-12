package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper;


import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {




    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Technology toModel(TechnologyEntity technologyEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    TechnologyEntity toEntity(Technology technology);

    List<Technology> toModelList(List<TechnologyEntity> tecnologiaEntities);
}
