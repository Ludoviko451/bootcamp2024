package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CapacityBootcampResponse {

    private final Long id;
    private final String name;
    private final List<TechnologyCapacityResponse> technologyList;

}
