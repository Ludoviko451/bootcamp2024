package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BootcampResponse {

    private final Long id;

    private final String name;
    private final String description;

    private final List<CapacityBootcampResponse> capacityList;

}
