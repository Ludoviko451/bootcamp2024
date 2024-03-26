package com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.BootcampResponse;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampResponseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    BootcampResponse toBootcampResponse(Bootcamp bootcamp);

    List<BootcampResponse> toBootcampResponseList(List<Bootcamp> bootcampList);

}
