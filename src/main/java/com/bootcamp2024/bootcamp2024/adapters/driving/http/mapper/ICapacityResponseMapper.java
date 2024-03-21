package com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapacityResponseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    CapacityResponse toCapacityResponse(Capacity capacity);

    List<CapacityResponse> toCapacityResponseList(List<Capacity> capacityList);

}
