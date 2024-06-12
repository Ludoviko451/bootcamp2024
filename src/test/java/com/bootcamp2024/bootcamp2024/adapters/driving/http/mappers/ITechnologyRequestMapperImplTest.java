package com.bootcamp2024.bootcamp2024.adapters.driving.http.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ITechnologyRequestMapperImpl;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ITechnologyRequestMapperImplTest {

    private final ITechnologyRequestMapperImpl mapper = new ITechnologyRequestMapperImpl();

    @Test
    @DisplayName("Should map TechnologyRequest")
    void shouldMapTechnologyRequest() {
        AddTechnologyRequest technologyRequest = new AddTechnologyRequest("Technology", "Description");

        Technology technology = mapper.addRequestToTechnology(technologyRequest);

        assertEquals(technologyRequest.getName(), technology.getName());
        assertEquals(technologyRequest.getDescription(), technology.getDescription());
    }


    @Test
    @DisplayName("Should map null AddTechnologyRequest to null Technology")
    void shouldMapNullAddTechnologyRequestToNullTechnology() {
        Technology technology = mapper.addRequestToTechnology(null);

        assertNull(technology);
    }
}
