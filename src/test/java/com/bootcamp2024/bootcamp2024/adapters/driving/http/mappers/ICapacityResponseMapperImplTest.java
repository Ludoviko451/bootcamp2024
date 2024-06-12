package com.bootcamp2024.bootcamp2024.adapters.driving.http.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyCapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityResponseMapperImpl;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ICapacityResponseMapperImplTest {

    private final ICapacityResponseMapperImpl mapper = new ICapacityResponseMapperImpl();

    @Test
    @DisplayName("Should map capacity to capacity response with empty technology list")
    void shouldMapCapacityToCapacityResponseWithEmptyTechnologyList() {
        Capacity capacity = new Capacity(1L, "Capacity", "Description", Collections.emptyList());

        CapacityResponse capacityResponse = mapper.toCapacityResponse(capacity);

        assertEquals(capacity.getId(), capacityResponse.getId());
        assertEquals(capacity.getName(), capacityResponse.getName());
        assertEquals(capacity.getDescription(), capacityResponse.getDescription());
        assertThat(capacityResponse.getTechnologyList()).isEmpty();
    }

    @Test
    @DisplayName("Should map capacity to capacity response with technologies")
    void shouldMapCapacityToCapacityResponseWithTechnologies() {
        Technology technology1 = new Technology(1L, "Java", "description");
        Technology technology2 = new Technology(2L, "Python", "description");
        List<Technology> technologies = List.of(technology1, technology2);
        Capacity capacity = new Capacity(1L, "Capacity", "Description", technologies);

        CapacityResponse capacityResponse = mapper.toCapacityResponse(capacity);

        assertEquals(capacity.getId(), capacityResponse.getId());
        assertEquals(capacity.getName(), capacityResponse.getName());
        assertEquals(capacity.getDescription(), capacityResponse.getDescription());
        assertThat(capacityResponse.getTechnologyList()).hasSize(2);

        TechnologyCapacityResponse techResponse1 = capacityResponse.getTechnologyList().get(0);
        TechnologyCapacityResponse techResponse2 = capacityResponse.getTechnologyList().get(1);

        assertEquals(technology1.getId(), techResponse1.getId());
        assertEquals(technology1.getName(), techResponse1.getName());

        assertEquals(technology2.getId(), techResponse2.getId());
        assertEquals(technology2.getName(), techResponse2.getName());
    }

    @Test
    @DisplayName("Should map empty capacity list to capacity response list")
    void shouldMapEmptyCapacityListToCapacityResponseList() {
        List<CapacityResponse> capacityResponseList = mapper.toCapacityResponseList(Collections.emptyList());

        assertThat(capacityResponseList).isEmpty();
    }

    @Test
    @DisplayName("Should map capacity list to capacity response list")
    void shouldMapCapacityListToCapacityResponseList() {
        Technology technology = new Technology(1L, "Java", "description");
        Capacity capacity1 = new Capacity(1L, "Capacity1", "Description1", List.of(technology));
        Capacity capacity2 = new Capacity(2L, "Capacity2", "Description2", Collections.emptyList());
        List<Capacity> capacities = List.of(capacity1, capacity2);

        List<CapacityResponse> capacityResponseList = mapper.toCapacityResponseList(capacities);

        assertThat(capacityResponseList).hasSize(2);

        CapacityResponse capacityResponse1 = capacityResponseList.get(0);
        CapacityResponse capacityResponse2 = capacityResponseList.get(1);

        assertEquals(capacity1.getId(), capacityResponse1.getId());
        assertEquals(capacity1.getName(), capacityResponse1.getName());
        assertEquals(capacity1.getDescription(), capacityResponse1.getDescription());
        assertThat(capacityResponse1.getTechnologyList()).hasSize(1);
        TechnologyCapacityResponse techResponse = capacityResponse1.getTechnologyList().get(0);
        assertEquals(technology.getId(), techResponse.getId());
        assertEquals(technology.getName(), techResponse.getName());

        assertEquals(capacity2.getId(), capacityResponse2.getId());
        assertEquals(capacity2.getName(), capacityResponse2.getName());
        assertEquals(capacity2.getDescription(), capacityResponse2.getDescription());
        assertThat(capacityResponse2.getTechnologyList()).isEmpty();
    }

    @Test
    @DisplayName("Should map null capacity to null capacity response")
    void shouldMapNullCapacityToNullCapacityResponse() {
        CapacityResponse capacityResponse = mapper.toCapacityResponse(null);

        assertNull(capacityResponse);
    }
}
