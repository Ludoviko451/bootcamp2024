package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddCapacityRequest;
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
}