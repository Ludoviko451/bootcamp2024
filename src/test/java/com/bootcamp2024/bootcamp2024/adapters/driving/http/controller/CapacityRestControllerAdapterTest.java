package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyCapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CapacityRestControllerAdapterTest {

    @Mock
    private ICapacityServicePort capacityServicePort;
    @Mock
    private ICapacityRequestMapper capacityRequestMapper;
    @Mock
    private ICapacityResponseMapper capacityResponseMapper;
    private CapacityRestControllerAdapter capacityRestControllerAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        capacityRestControllerAdapter = new CapacityRestControllerAdapter(capacityServicePort, capacityRequestMapper, capacityResponseMapper);
    }
    @Test
    void shouldReturnCreatedHttpStatusWhenCapacityIsSuccessfullySaved(){
        List<TechnologyCapacityResponse> technologyCapacityList = new ArrayList<>();
        for (long  i = 1L; i <= 3L ; i++) {
            technologyCapacityList.add(new TechnologyCapacityResponse(i, "technology"+i));
        }
        List<Technology> technologyList = new ArrayList<>();
        for (long  i = 1L; i <= 3L ; i++) {
            technologyList.add(new Technology(i, "technology"+i, "description"));
        }
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("Capacity", "Description", technologyCapacityList);
        Capacity capacity = new Capacity(1L, "capacidad", "description", technologyList);
        when(capacityRequestMapper.addRequestToCapacity(addCapacityRequest)).thenReturn(capacity);

        ResponseEntity<?> responseEntity = capacityRestControllerAdapter.addCapacity(addCapacityRequest);
        verify(capacityServicePort, times(1)).saveCapacity(capacity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }


    @Test
    void shouldReturnAllCapacities() {

        Integer page = 0;
        Integer size = 10;
        String sortBy = "";
        boolean technologies = false;
        String field = "id";

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "Capacity 1", "Description 1", Collections.emptyList()),
                new Capacity(2L, "Capacity 2", "Description 2", Collections.emptyList())
        );

        List<CapacityResponse> expectedResponses = Arrays.asList(
                new CapacityResponse(1L, "Capacity 1", "Description 1", Collections.emptyList()),
                new CapacityResponse(2L, "Capacity 2", "Description 2", Collections.emptyList())
        );

        when(capacityServicePort.getAllCapacity(page, size, sortBy, technologies, field)).thenReturn(capacityList);
        when(capacityResponseMapper.toCapacityResponseList(capacityList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<CapacityResponse>> responseEntity = capacityRestControllerAdapter.getAllCapacity(page, size, sortBy, technologies, field);

        List<CapacityResponse> capacityResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("Capacity 1", capacityResponses.get(0).getName());
        assertEquals("Capacity 2", capacityResponses.get(1).getName());
        verify(capacityServicePort, times(1)).getAllCapacity(page, size, sortBy, technologies, field);
        verify(capacityResponseMapper, times(1)).toCapacityResponseList(capacityList);
    }

    @Test
    void shouldReturnCapacitiesInAscendingOrder() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortBy = "asc";
        boolean technologies = false;
        String field = "id";

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "ACapacity 1", "Description 1", Collections.emptyList()),
                new Capacity(2L, "BCapacity 2", "Description 2", Collections.emptyList())
        );

        List<CapacityResponse> expectedResponses = Arrays.asList(
                new CapacityResponse(1L, "ACapacity 1", "Description 1", Collections.emptyList()),
                new CapacityResponse(2L, "BCapacity 2", "Description 2", Collections.emptyList())
        );

        when(capacityServicePort.getAllCapacity(page, size, sortBy, technologies, field)).thenReturn(capacityList);
        when(capacityResponseMapper.toCapacityResponseList(capacityList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<CapacityResponse>> responseEntity = capacityRestControllerAdapter.getAllCapacity(page, size, sortBy, technologies, field);

        List<CapacityResponse> capacityResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("BCapacity 2", capacityResponses.get(1).getName());
        assertEquals("ACapacity 1", capacityResponses.get(0).getName());
        verify(capacityServicePort, times(1)).getAllCapacity(page, size, sortBy, technologies, field);
        verify(capacityResponseMapper, times(1)).toCapacityResponseList(capacityList);
    }

    @Test
    void shouldReturnCapacitiesInDescendingOrder() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortBy = "desc";
        String field = "id";
        boolean technologies = false;

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(2L, "ZCapacity 2", "Description 2", Collections.emptyList()),
                new Capacity(1L, "ACapacity 1", "Description 1", Collections.emptyList())
        );

        List<CapacityResponse> expectedResponses = Arrays.asList(
                new CapacityResponse(2L, "ZCapacity 2", "Description 2", Collections.emptyList()),
                new CapacityResponse(1L, "ACapacity 1", "Description 1", Collections.emptyList())
        );

        when(capacityServicePort.getAllCapacity(page, size, sortBy, technologies, field)).thenReturn(capacityList);
        when(capacityResponseMapper.toCapacityResponseList(capacityList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<CapacityResponse>> responseEntity = capacityRestControllerAdapter.getAllCapacity(page, size, sortBy, technologies, field);

        List<CapacityResponse> capacityResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("ZCapacity 2", capacityResponses.get(0).getName());
        assertEquals("ACapacity 1", capacityResponses.get(1).getName());

        verify(capacityServicePort, times(1)).getAllCapacity(page, size, sortBy, technologies, field);
        verify(capacityResponseMapper, times(1)).toCapacityResponseList(capacityList);
    }

    @Test
    void shouldReturnCapacitiesIsAscendingOrderByTechnologiesCount(){
        Integer page = 0;
        Integer size = 10;
        String sortBy = "asc";
        boolean technologies = true;
        String field = "id";
        List<Technology> technologyList = List.of(
                new Technology(1L, "Technology", "Description")
        );

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(2L, "Capacity 2", "Description 2", technologyList),
                new Capacity(1L, "Capacity 1", "Description 1", Collections.emptyList())
        );

        List<TechnologyCapacityResponse> technologyCapacityResponseList = List.of(
                new TechnologyCapacityResponse(1L, "Technology")
        );
        List<CapacityResponse> expectedResponses = Arrays.asList(
                new CapacityResponse(2L, "Capacity 2", "Description 2", technologyCapacityResponseList),
                new CapacityResponse(1L, "Capacity 1", "Description 1", Collections.emptyList())
        );

        when(capacityServicePort.getAllCapacity(page, size, sortBy, technologies, field)).thenReturn(capacityList);
        when(capacityResponseMapper.toCapacityResponseList(capacityList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<CapacityResponse>> responseEntity = capacityRestControllerAdapter.getAllCapacity(page, size, sortBy, technologies, field);

        List<CapacityResponse> capacityResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("Capacity 2", capacityResponses.get(0).getName());
        assertEquals("Capacity 1", capacityResponses.get(1).getName());

        verify(capacityServicePort, times(1)).getAllCapacity(page, size, sortBy, technologies, field);
        verify(capacityResponseMapper, times(1)).toCapacityResponseList(capacityList);
    }

    @Test
    void shouldReturnCapacitiesIsDescendingOrderByTechnologiesCount(){
        Integer page = 0;
        Integer size = 10;
        String sortBy = "desc";
        boolean technologies = true;
        String field = "id";

        List<Technology> technologyList = List.of(
                new Technology(1L, "Technology", "Description")
        );

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "Capacity 1", "Description 1", Collections.emptyList()),
                new Capacity(2L, "Capacity 2", "Description 2", technologyList)
        );

        List<TechnologyCapacityResponse> technologyCapacityResponseList = List.of(
                new TechnologyCapacityResponse(1L, "Technology")
        );
        List<CapacityResponse> expectedResponses = Arrays.asList(
                new CapacityResponse(1L, "Capacity 1", "Description 1", Collections.emptyList()),
                new CapacityResponse(2L, "Capacity 2", "Description 2", technologyCapacityResponseList)
        );

        when(capacityServicePort.getAllCapacity(page, size, sortBy, technologies, field)).thenReturn(capacityList);
        when(capacityResponseMapper.toCapacityResponseList(capacityList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<CapacityResponse>> responseEntity = capacityRestControllerAdapter.getAllCapacity(page, size, sortBy, technologies, field);

        List<CapacityResponse> capacityResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("Capacity 2", capacityResponses.get(1).getName());
        assertEquals("Capacity 1", capacityResponses.get(0).getName());

        verify(capacityServicePort, times(1)).getAllCapacity(page, size, sortBy, technologies, field);
        verify(capacityResponseMapper, times(1)).toCapacityResponseList(capacityList);
    }
}