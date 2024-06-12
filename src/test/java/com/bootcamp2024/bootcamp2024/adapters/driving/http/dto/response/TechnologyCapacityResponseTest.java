package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TechnologyCapacityResponseTest {

    @Test
    @DisplayName("Should create a TechnologyCapacityResponse")
    void shouldCreateATechnologyCapacityResponse() {
        Long id = 1L;
        String name = "Technology";

        TechnologyCapacityResponse technologyCapacityResponse = new TechnologyCapacityResponse(id, name);
        assertEquals(id, technologyCapacityResponse.getId());
        assertEquals(name, technologyCapacityResponse.getName());
    }

    @Test
    @DisplayName("Should Getters and Setters")
    void shouldGettersAndSetters() {
        Long id = 1L;
        String name = "Technology";
        TechnologyCapacityResponse technologyCapacityResponse = new TechnologyCapacityResponse();
        technologyCapacityResponse.setId(id);
        technologyCapacityResponse.setName(name);
        assertEquals(id, technologyCapacityResponse.getId());
        assertEquals(name, technologyCapacityResponse.getName());
    }
}
