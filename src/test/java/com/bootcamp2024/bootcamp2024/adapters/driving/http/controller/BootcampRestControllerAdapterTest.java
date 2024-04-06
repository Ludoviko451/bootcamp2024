package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.CapacityBootcamp;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.BootcampResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityBootcampResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyCapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.bootcamp2024.bootcamp2024.domain.api.IBootcampServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
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

class BootcampRestControllerAdapterTest {

    @Mock
    private IBootcampServicePort bootcampServicePort;
    @Mock
    private IBootcampRequestMapper bootcampRequestMapper;
    @Mock
    private IBootcampResponseMapper bootcampResponseMapper;
    private BootcampRestControllerAdapter bootcampRestControllerAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        bootcampRestControllerAdapter = new BootcampRestControllerAdapter(bootcampServicePort, bootcampRequestMapper, bootcampResponseMapper);
    }

    @Test
    void shouldReturnCreatedHttpStatusWhenBootcampIsSuccesfullySaved() {
        List<CapacityBootcamp> capacityBootcampList = Arrays.asList(
                new CapacityBootcamp(1L, "Capacidad 1"),
                new CapacityBootcamp(2L, "Capacidad 2")
        );

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "Capacidad 1", "Description", Collections.emptyList()),
                new Capacity(2L, "Capacidad 2", "Description", Collections.emptyList())
        );

        AddBootcampRequest addBootcampRequest = new AddBootcampRequest("Bootcamp", "Description", capacityBootcampList);

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp", "Description", capacityList);

        when(bootcampRequestMapper.addRequestToBootcamp(addBootcampRequest)).thenReturn(bootcamp);

        ResponseEntity<?> responseEntity = bootcampRestControllerAdapter.addBootcamp(addBootcampRequest);
        verify(bootcampServicePort, times(1)).saveBootcamp(bootcamp);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
        @Test
        void shouldReturnAllBootcamp() {
            // Arrange
            Integer page = 0;
            Integer size = 10;
            String sortBy = "";
            String field = "id";
            boolean capacities = false;

            List<Bootcamp> bootcampList = Arrays.asList(
                    new Bootcamp(1L, "Bootcamp 1", "Description 1", Collections.emptyList()),
                    new Bootcamp(2L, "Bootcamp 2", "Description 2", Collections.emptyList())
            );

            List<BootcampResponse> expectedResponses = Arrays.asList(
                    new BootcampResponse(1L, "Bootcamp 1", "Description 1", Collections.emptyList()),
                    new BootcampResponse(2L, "Bootcamp 2", "Description 2", Collections.emptyList())
            );

            when(bootcampServicePort.getAllBootcamp(page, size, sortBy, capacities, field)).thenReturn(bootcampList);
            when(bootcampResponseMapper.toBootcampResponseList(bootcampList)).thenReturn(expectedResponses);

            // Act
            ResponseEntity<List<BootcampResponse>> responseEntity = bootcampRestControllerAdapter.getAllBootcamp(page, size, sortBy, capacities, field);

            List<BootcampResponse> bootcampResponses = responseEntity.getBody();
            // Assert
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(expectedResponses, responseEntity.getBody());
            assertEquals("Bootcamp 1", bootcampResponses.get(0).getName());
            assertEquals("Bootcamp 2", bootcampResponses.get(1).getName());
            verify(bootcampServicePort, times(1)).getAllBootcamp(page, size, sortBy, capacities, field);
            verify(bootcampResponseMapper, times(1)).toBootcampResponseList(bootcampList);
        }

    @Test
    void shouldReturnBootcampInAscendingOrder() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortBy = "asc";
        boolean capacities = false;
        String field = "id";

        List<Bootcamp> bootcampList = Arrays.asList(
                new Bootcamp(1L, "ABootcamp 1", "Description 1", Collections.emptyList()),
                new Bootcamp(2L, "ZBootcamp 2", "Description 2", Collections.emptyList())
        );

        List<BootcampResponse> expectedResponses = Arrays.asList(
                new BootcampResponse(1L, "ABootcamp 1", "Description 1", Collections.emptyList()),
                new BootcampResponse(2L, "ZBootcamp 2", "Description 2", Collections.emptyList())
        );

        when(bootcampServicePort.getAllBootcamp(page, size, sortBy, capacities, field)).thenReturn(bootcampList);
        when(bootcampResponseMapper.toBootcampResponseList(bootcampList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<BootcampResponse>> responseEntity = bootcampRestControllerAdapter.getAllBootcamp(page, size, sortBy, capacities, field);

        List<BootcampResponse> bootcampResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("ABootcamp 1", bootcampResponses.get(0).getName());
        assertEquals("ZBootcamp 2", bootcampResponses.get(1).getName());
        verify(bootcampServicePort, times(1)).getAllBootcamp(page, size, sortBy, capacities, field);
        verify(bootcampResponseMapper, times(1)).toBootcampResponseList(bootcampList);
    }

    @Test
    void shouldReturnBootcampInDescendingOrder() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortBy = "desc";
        String field = "id";
        boolean capacities = false;

        List<Bootcamp> bootcampList = Arrays.asList(
                new Bootcamp(2L, "ZBootcamp 2", "Description 2", Collections.emptyList()),
                new Bootcamp(1L, "ABootcamp 1", "Description 1", Collections.emptyList())
        );

        List<BootcampResponse> expectedResponses = Arrays.asList(
                new BootcampResponse(2L, "ZBootcamp 2", "Description 2", Collections.emptyList()),
                new BootcampResponse(1L, "ABootcamp 1", "Description 1", Collections.emptyList())
        );

        when(bootcampServicePort.getAllBootcamp(page, size, sortBy, capacities, field)).thenReturn(bootcampList);
        when(bootcampResponseMapper.toBootcampResponseList(bootcampList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<BootcampResponse>> responseEntity = bootcampRestControllerAdapter.getAllBootcamp(page, size, sortBy, capacities, field);

        List<BootcampResponse> bootcampResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("ZBootcamp 2", bootcampResponses.get(0).getName());
        assertEquals("ABootcamp 1", bootcampResponses.get(1).getName());
        verify(bootcampServicePort, times(1)).getAllBootcamp(page, size, sortBy, capacities, field);
        verify(bootcampResponseMapper, times(1)).toBootcampResponseList(bootcampList);
    }

    @Test
    void shouldReturnBootcampAscendingOrderByCapacities() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortBy = "asc";
        boolean capacities = true;
        String field = "id";
        List<CapacityBootcampResponse> capacityBootcampList = Arrays.asList(
                new CapacityBootcampResponse(1L, "Capacidad 1", Collections.emptyList()),
                new CapacityBootcampResponse(2L, "Capacidad 2", Collections.emptyList())
        );

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "Capacidad 1", "Description", Collections.emptyList()),
                new Capacity(2L, "Capacidad 2", "Description", Collections.emptyList())
        );

        List<Bootcamp> bootcampList = Arrays.asList(
                new Bootcamp(2L, "ZBootcamp 2", "Description 2", capacityList),
                new Bootcamp(1L, "ABootcamp 1", "Description 1", Collections.emptyList())
        );

        List<BootcampResponse> expectedResponses = Arrays.asList(
                new BootcampResponse(2L, "ZBootcamp 2", "Description 2", capacityBootcampList),
                new BootcampResponse(1L, "ABootcamp 1", "Description 1", Collections.emptyList())
        );

        when(bootcampServicePort.getAllBootcamp(page, size, sortBy, capacities, field)).thenReturn(bootcampList);
        when(bootcampResponseMapper.toBootcampResponseList(bootcampList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<BootcampResponse>> responseEntity = bootcampRestControllerAdapter.getAllBootcamp(page, size, sortBy, capacities, field);

        List<BootcampResponse> bootcampResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("ZBootcamp 2", bootcampResponses.get(0).getName());
        assertEquals("ABootcamp 1", bootcampResponses.get(1).getName());
        verify(bootcampServicePort, times(1)).getAllBootcamp(page, size, sortBy, capacities, field);
        verify(bootcampResponseMapper, times(1)).toBootcampResponseList(bootcampList);
    }

    @Test
    void shouldReturnBootcampDescendingOrderByCapacities() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortBy = "asc";
        boolean capacities = true;
        String field = "id";

        List<CapacityBootcampResponse> capacityBootcampList = Arrays.asList(
                new CapacityBootcampResponse(1L, "Capacidad 1", Collections.emptyList()),
                new CapacityBootcampResponse(2L, "Capacidad 2", Collections.emptyList())
        );

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "Capacidad 1", "Description", Collections.emptyList()),
                new Capacity(2L, "Capacidad 2", "Description", Collections.emptyList())
        );

        List<Bootcamp> bootcampList = Arrays.asList(
                new Bootcamp(1L, "ABootcamp 1", "Description 1", Collections.emptyList()),
                new Bootcamp(2L, "ZBootcamp 2", "Description 2", capacityList)
        );

        List<BootcampResponse> expectedResponses = Arrays.asList(
                new BootcampResponse(1L, "ABootcamp 1", "Description 1", Collections.emptyList()),
                new BootcampResponse(2L, "ZBootcamp 2", "Description 2", capacityBootcampList)
        );

        when(bootcampServicePort.getAllBootcamp(page, size, sortBy, capacities, field)).thenReturn(bootcampList);
        when(bootcampResponseMapper.toBootcampResponseList(bootcampList)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<BootcampResponse>> responseEntity = bootcampRestControllerAdapter.getAllBootcamp(page, size, sortBy, capacities, field);

        List<BootcampResponse> bootcampResponses = responseEntity.getBody();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
        assertEquals("ZBootcamp 2", bootcampResponses.get(1).getName());
        assertEquals("ABootcamp 1", bootcampResponses.get(0).getName());
        verify(bootcampServicePort, times(1)).getAllBootcamp(page, size, sortBy, capacities, field);
        verify(bootcampResponseMapper, times(1)).toBootcampResponseList(bootcampList);
    }
    }