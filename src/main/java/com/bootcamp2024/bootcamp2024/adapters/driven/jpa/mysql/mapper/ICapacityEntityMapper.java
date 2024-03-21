package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper;


import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapacityEntityMapper {

    //  Convierte una entidad JPA de tecnología a un modelo de dominio de tecnología.
//  @param tecnologiaEntity La entidad JPA de tecnología.
//  @return El modelo de dominio de tecnología.

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "technologyList", target = "technologyList")
    Capacity toModel(CapacityEntity capacityEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "technologyList", target = "technologyList")
    CapacityEntity toEntity(Capacity capacity);

    List<Capacity> toModelList(List<CapacityEntity> capacityEntities);


}
