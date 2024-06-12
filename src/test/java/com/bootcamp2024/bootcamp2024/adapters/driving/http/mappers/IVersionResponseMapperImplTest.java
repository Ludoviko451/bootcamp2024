package com.bootcamp2024.bootcamp2024.adapters.driving.http.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.VersionResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IVersionResponseMapperImpl;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IVersionResponseMapperImplTest {

    private final IVersionResponseMapperImpl mapper = new IVersionResponseMapperImpl();

    @Test
    @DisplayName("Should map Version to VersionResponse")
    void shouldMapVersionToVersionResponse() {
        LocalDate startDate = LocalDate.now().plusDays(3);
        String startDateString = startDate.toString();
        LocalDate endDate = LocalDate.now().plusDays(7);
        String endDateString = endDate.toString();
        Bootcamp bootcamp = new Bootcamp(1L, "bootcamp", "description", Collections.emptyList());

        Version version = new Version(1L, 30, startDateString, endDateString, bootcamp);
        VersionResponse versionResponse = mapper.toVersionResponse(version);

        assertThat(versionResponse).isNotNull();
        assertEquals(versionResponse.getId(), version.getId());
        assertEquals(versionResponse.getMaximumCapacity(), version.getMaximumCapacity());
        assertEquals(versionResponse.getStartDate(), startDate);
        assertEquals(versionResponse.getEndDate(), endDate);
        assertEquals(versionResponse.getBootcampName(), version.getBootcamp().getName());
    }

    @Test
    @DisplayName("Should map empty version list to version response list")
    void shouldMapEmptyVersionListToVersionResponseList() {
        List<VersionResponse> versionResponseList = mapper.toVersionResponseList(Collections.emptyList());

        assertThat(versionResponseList).isEmpty();
    }

    @Test
    @DisplayName("Should map version list to version response list")
    void shouldMapVersionListToVersionResponseList() {
        LocalDate startDate1 = LocalDate.now().plusDays(3);
        String startDateString1 = startDate1.toString();
        LocalDate endDate1 = LocalDate.now().plusDays(7);
        String endDateString1 = endDate1.toString();

        LocalDate startDate2 = LocalDate.now().plusDays(10);
        String startDateString2 = startDate2.toString();
        LocalDate endDate2 = LocalDate.now().plusDays(14);
        String endDateString2 = endDate2.toString();

        Bootcamp bootcamp = new Bootcamp(1L, "bootcamp", "description", Collections.emptyList());

        Version version1 = new Version(1L, 30, startDateString1, endDateString1, bootcamp);
        Version version2 = new Version(2L, 25, startDateString2, endDateString2, bootcamp);
        List<Version> versions = List.of(version1, version2);

        List<VersionResponse> versionResponseList = mapper.toVersionResponseList(versions);

        assertThat(versionResponseList).hasSize(2);

        VersionResponse versionResponse1 = versionResponseList.get(0);
        VersionResponse versionResponse2 = versionResponseList.get(1);

        assertEquals(version1.getId(), versionResponse1.getId());
        assertEquals(version1.getMaximumCapacity(), versionResponse1.getMaximumCapacity());
        assertEquals(version1.getStartDate(), startDateString1);
        assertEquals(version1.getEndDate(), endDateString1);
        assertEquals(version1.getBootcamp().getName(), versionResponse1.getBootcampName());

        assertEquals(version2.getId(), versionResponse2.getId());
        assertEquals(version2.getMaximumCapacity(), versionResponse2.getMaximumCapacity());
        assertEquals(version2.getStartDate(), startDateString2);
        assertEquals(version2.getEndDate(), endDateString2);
        assertEquals(version2.getBootcamp().getName(), versionResponse2.getBootcampName());
    }

    @Test
    @DisplayName("Should map version with null dates to version response")
    void shouldMapVersionWithNullDatesToVersionResponse() {
        Bootcamp bootcamp = new Bootcamp(1L, "bootcamp", "description", Collections.emptyList());
        Version version = new Version(1L, 30, null, null, bootcamp);
        VersionResponse versionResponse = mapper.toVersionResponse(version);

        assertThat(versionResponse).isNotNull();
        assertEquals(versionResponse.getId(), version.getId());
        assertEquals(versionResponse.getMaximumCapacity(), version.getMaximumCapacity());
        assertNull(versionResponse.getStartDate());
        assertNull(versionResponse.getEndDate());
        assertEquals(versionResponse.getBootcampName(), version.getBootcamp().getName());
    }

    @Test
    @DisplayName("Should map version with null bootcamp to version response")
    void shouldMapVersionWithNullBootcampToVersionResponse() {
        LocalDate startDate = LocalDate.now().plusDays(3);
        String startDateString = startDate.toString();
        LocalDate endDate = LocalDate.now().plusDays(7);
        String endDateString = endDate.toString();

        Version version = new Version(1L, 30, startDateString, endDateString, null);
        VersionResponse versionResponse = mapper.toVersionResponse(version);

        assertThat(versionResponse).isNotNull();
        assertEquals(versionResponse.getId(), version.getId());
        assertEquals(versionResponse.getMaximumCapacity(), version.getMaximumCapacity());
        assertEquals(versionResponse.getStartDate(), startDate);
        assertEquals(versionResponse.getEndDate(), endDate);
        assertNull(versionResponse.getBootcampName());
    }

    @Test
    @DisplayName("Should map null version to null version response")
    void shouldMapNullVersionToNullVersionResponse() {
        VersionResponse versionResponse = mapper.toVersionResponse(null);

        assertNull(versionResponse);
    }
}
