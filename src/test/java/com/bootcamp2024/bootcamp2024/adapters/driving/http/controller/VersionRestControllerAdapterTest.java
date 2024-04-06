package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.VersionResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IVersionRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IVersionResponseMapper;
import com.bootcamp2024.bootcamp2024.domain.api.IVersionServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VersionRestControllerAdapterTest {

    @Mock
    private IVersionServicePort versionServicePort;

    @Mock
    private IVersionRequestMapper versionRequestMapper;

    @Mock
    private IVersionResponseMapper versionResponseMapper;

    @InjectMocks
    private VersionRestControllerAdapter versionRestControllerAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        versionRestControllerAdapter = new VersionRestControllerAdapter(versionServicePort, versionRequestMapper, versionResponseMapper);
    }


    @Test
    void shouldReturnCreatedHttpStatusWhenVersionIsSuccessfullySaved() {

        String startDate = "2024-04-05";
        String endDate = "2024-04-12";
        AddVersionRequest addVersionRequest = new AddVersionRequest(10, startDate, endDate, 1L);

        Version version = new Version(1L, 10, startDate, endDate, new Bootcamp(1L, "Version Name", "Description", Collections.emptyList()));

        when(versionRequestMapper.addRequestToVersion(addVersionRequest)).thenReturn(version);

        ResponseEntity<String> response = versionRestControllerAdapter.addVersion(addVersionRequest);

        verify(versionServicePort, times(1)).saveVersion(any(Version.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnAllVersions(){

        Integer page = 0;
        Integer size = 10;
        String sortBy = "";
        String field = "id";
        String startDate = "2024-04-05";
        String endDate = "2024-04-12";
        
        Bootcamp testBootcamp = new Bootcamp(1L, "Bootcamp Name", "Description", Collections.emptyList());

        List<Version> versionList = Arrays.asList(
                new Version(1L, 10, startDate, endDate, testBootcamp),
                new Version(2L, 20, startDate, endDate, testBootcamp)
        );

        List<VersionResponse> expectedResponses = List.of(
                new VersionResponse(1L, "Bootcamp Name", 10, LocalDate.now(), LocalDate.now().plusDays(7))
        );
        when(versionServicePort.getAllVersion(page, size, sortBy, field, null)).thenReturn(versionList);
        when(versionResponseMapper.toVersionResponseList(versionList)).thenReturn(expectedResponses);

        ResponseEntity<List<VersionResponse>> responseEntity = versionRestControllerAdapter.getAllVersions(page, size, sortBy, field, null);
        List<VersionResponse> response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, response);
        verify(versionServicePort, times(1)).getAllVersion(page, size, sortBy, field, null);
        verify(versionResponseMapper, times(1)).toVersionResponseList(versionList);

    }

}