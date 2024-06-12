package com.bootcamp2024.bootcamp2024.adapters.driving.http.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IVersionRequestMapperImpl;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IVersionRequestMapperImplTest {

    private final IVersionRequestMapperImpl mapper = new IVersionRequestMapperImpl();

    @Test
    @DisplayName("Should map to IVersionRequest")
    void shouldMapToIVersionRequest() {
        String startDate = LocalDate.now().plusDays(3).toString();
        String endDate = LocalDate.now().plusDays(7).toString();
        AddVersionRequest addVersionRequest = new AddVersionRequest(30,startDate, endDate, 1L);

        Version version = mapper.addRequestToVersion(addVersionRequest);

        assertEquals(version.getStartDate(), addVersionRequest.getStartDate());
        assertEquals(version.getEndDate(), addVersionRequest.getEndDate());
        assertEquals(version.getMaximumCapacity(), addVersionRequest.getMaximumCapacity());
        assertEquals(version.getBootcamp().getId(), addVersionRequest.getBootcampId());
    }
}
