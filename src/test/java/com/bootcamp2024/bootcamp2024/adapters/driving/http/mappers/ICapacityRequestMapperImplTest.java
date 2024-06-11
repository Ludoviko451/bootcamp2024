package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyCapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityRequestMapperImpl;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

class ICapacityRequestMapperImplTest {

    private final ICapacityRequestMapperImpl mapper = new ICapacityRequestMapperImpl();

    @Test
    @DisplayName("Should map CapacityRequest")
    void shouldMapCapacityRequest() {
        AddCapacityRequest capacityRequest = new AddCapacityRequest("Capacity", "Description", Collections.emptyList());

        Capacity capacity = mapper.addRequestToCapacity(capacityRequest);

        assertEquals(capacityRequest.getName(), capacity.getName());
        assertEquals(capacityRequest.getDescription(), capacity.getDescription());
        assertThat(capacity.getTechnologyList()).isEmpty();
    }

    @Test
    @DisplayName("Should map empty CapacityRequest")
    void shouldMapEmptyCapacityRequest() {
        AddCapacityRequest capacityRequest = new AddCapacityRequest(null, null, null);

        Capacity capacity = mapper.addRequestToCapacity(capacityRequest);

        assertNull(capacity.getName());
        assertNull(capacity.getDescription());
        assertNull(capacity.getTechnologyList());
    }

    @Test
    @DisplayName("Should map CapacityRequest with technologies")
    void shouldMapCapacityRequestWithTechnologies() {
        TechnologyCapacityResponse technology1 = new TechnologyCapacityResponse(1L, "Java");
        TechnologyCapacityResponse technology2 = new TechnologyCapacityResponse(2L, "Python");
        List<TechnologyCapacityResponse> technologies = List.of(technology1, technology2);
        AddCapacityRequest capacityRequest = new AddCapacityRequest("Capacity", "Description", technologies);

        Capacity capacity = mapper.addRequestToCapacity(capacityRequest);

        assertEquals(capacityRequest.getName(), capacity.getName());
        assertEquals(capacityRequest.getDescription(), capacity.getDescription());
        assertThat(capacity.getTechnologyList()).hasSize(2);

        Technology tech1 = capacity.getTechnologyList().get(0);
        Technology tech2 = capacity.getTechnologyList().get(1);

        assertEquals(technology1.getId(), tech1.getId());
        assertEquals(technology1.getName(), tech1.getName());

        assertEquals(technology2.getId(), tech2.getId());
        assertEquals(technology2.getName(), tech2.getName());
    }

    @Test
    @DisplayName("Should map Capacity to CapacityResponse")
    void shouldMapCapacityToCapacityResponse() {
        Technology technology = new Technology(1L, "Java", "description");
        Capacity capacity = new Capacity(1L, "Capacity", "Description", List.of(technology));

        CapacityResponse capacityResponse = mapper.modelToResponse(capacity);

        assertEquals(capacity.getId(), capacityResponse.getId());
        assertEquals(capacity.getName(), capacityResponse.getName());
        assertEquals(capacity.getDescription(), capacityResponse.getDescription());
        assertThat(capacityResponse.getTechnologyList()).hasSize(1);

        TechnologyCapacityResponse techResponse = capacityResponse.getTechnologyList().get(0);
        assertEquals(technology.getId(), techResponse.getId());
        assertEquals(technology.getName(), techResponse.getName());
    }

    @Test
    @DisplayName("Should map null Capacity to CapacityResponse")
    void shouldMapNullCapacityToCapacityResponse() {
        CapacityResponse capacityResponse = mapper.modelToResponse(null);

        assertNull(capacityResponse);
    }
}
