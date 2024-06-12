package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CapacityBootcampResponseTest {

    @Test
    @DisplayName("Should create a CapacityBootcampResponse")
    void shouldCreateACapacityBootcampResponse() {

        Long id = 1L;
        String name = "Capacity";

        TechnologyCapacityResponse technology1 = new TechnologyCapacityResponse(1L, "Technology1");
        TechnologyCapacityResponse technology2 = new TechnologyCapacityResponse(1L, "Technology1");

        List<TechnologyCapacityResponse> capacityList = List.of(technology1, technology2);
        CapacityBootcampResponse capacityBootcampResponse = new CapacityBootcampResponse(id, name, capacityList);

        assertThat(capacityBootcampResponse.getId()).isEqualTo(id);
        assertThat(capacityBootcampResponse.getName()).isEqualTo(name);
        assertThat(capacityBootcampResponse.getTechnologyList()).isEqualTo(capacityList);
    }

    @Test
    @DisplayName("Should Getters and Setters")
    void shouldCreateACapacityBootcampResponseWithEmptyList() {

        Long id = 1L;
        String name = "Capacity";
        TechnologyCapacityResponse technology1 = new TechnologyCapacityResponse(1L, "Technology1");
        TechnologyCapacityResponse technology2 = new TechnologyCapacityResponse(1L, "Technology1");

        List<TechnologyCapacityResponse> capacityList = List.of(technology1, technology2);
        CapacityBootcampResponse capacityBootcampResponse = new CapacityBootcampResponse(id, name, capacityList);
        assertThat(capacityBootcampResponse.getId()).isEqualTo(id);
        assertThat(capacityBootcampResponse.getName()).isEqualTo(name);
        assertThat(capacityBootcampResponse.getTechnologyList()).isEqualTo(capacityList);
    }
}
