package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VersionResponseTest {

    @Test
    @DisplayName("Should create a VersionResponse")
    void shouldCreateAVersionResponse() {

        Long id = 1L;
        Long bootcampId = 2L;
        String bootcampName = "Bootcamp";
        Integer maximumCapacity = 50;
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusDays(10);


        VersionResponse versionResponse = new VersionResponse(id, bootcampName, maximumCapacity, startDate, endDate);

        assertEquals(id, versionResponse.getId());
        assertEquals(bootcampName, versionResponse.getBootcampName());
        assertEquals(maximumCapacity, versionResponse.getMaximumCapacity());
        assertEquals(startDate, versionResponse.getStartDate());
        assertEquals(endDate, versionResponse.getEndDate());
    }
}
