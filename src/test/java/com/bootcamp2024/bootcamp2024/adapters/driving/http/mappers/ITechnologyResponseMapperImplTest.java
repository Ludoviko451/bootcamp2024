package com.bootcamp2024.bootcamp2024.adapters.driving.http.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ITechnologyResponseMapperImpl;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ITechnologyResponseMapperImplTest {

    private final ITechnologyResponseMapperImpl mapper = new ITechnologyResponseMapperImpl();

    @Test
    @DisplayName("Should map technology to technology response")
    void shouldMapTechnologyToTechnologyResponse() {
        Technology technology = new Technology(1L, "Java", "Description");

        TechnologyResponse technologyResponse = mapper.toTechnologyResponse(technology);

        assertEquals(technology.getId(), technologyResponse.getId());
        assertEquals(technology.getName(), technologyResponse.getName());
        assertEquals(technology.getDescription(), technologyResponse.getDescription());
    }

    @Test
    @DisplayName("Should map empty technology list to technology response list")
    void shouldMapEmptyTechnologyListToTechnologyResponseList() {
        List<TechnologyResponse> technologyResponseList = mapper.toTechnologyResponseList(Collections.emptyList());

        assertThat(technologyResponseList).isEmpty();
    }

    @Test
    @DisplayName("Should map technology list to technology response list")
    void shouldMapTechnologyListToTechnologyResponseList() {
        Technology technology1 = new Technology(1L, "Java", "Description1");
        Technology technology2 = new Technology(2L, "Python", "Description2");
        List<Technology> technologies = List.of(technology1, technology2);

        List<TechnologyResponse> technologyResponseList = mapper.toTechnologyResponseList(technologies);

        assertThat(technologyResponseList).hasSize(2);

        TechnologyResponse technologyResponse1 = technologyResponseList.get(0);
        TechnologyResponse technologyResponse2 = technologyResponseList.get(1);

        assertEquals(technology1.getId(), technologyResponse1.getId());
        assertEquals(technology1.getName(), technologyResponse1.getName());
        assertEquals(technology1.getDescription(), technologyResponse1.getDescription());

        assertEquals(technology2.getId(), technologyResponse2.getId());
        assertEquals(technology2.getName(), technologyResponse2.getName());
        assertEquals(technology2.getDescription(), technologyResponse2.getDescription());
    }

    @Test
    @DisplayName("Should map null technology to null technology response")
    void shouldMapNullTechnologyToNullTechnologyResponse() {
        TechnologyResponse technologyResponse = mapper.toTechnologyResponse(null);

        assertNull(technologyResponse);
    }

    @Test
    @DisplayName("Should map null technology list to null technology response list")
    void shouldMapNullTechnologyListToNullTechnologyResponseList() {
        List<TechnologyResponse> technologyResponseList = mapper.toTechnologyResponseList(null);

        assertNull(technologyResponseList);
    }
}
