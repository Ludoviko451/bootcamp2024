package com.bootcamp2024.bootcamp2024.domain.api.model;


import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VersionTest {

    @Test
    public void testVersionConstructorAndGetters() {
        long id = 1L;
        int maximumCapacity = 30;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(6);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description of Bootcamp 1", List.of(new Capacity(1L, "Capacity 1", "Description 1", List.of())));

        Version version = new Version(id, maximumCapacity, startDate.toString(), endDate.toString(), bootcamp);

        assertEquals(id, version.getId());
        assertEquals(maximumCapacity, version.getMaximumCapacity());
        assertEquals(startDate.toString(), version.getStartDate());
        assertEquals(endDate.toString(), version.getEndDate());
        assertEquals(bootcamp, version.getBootcamp());
    }

    @Test
    public void testVersionWithNullBootcamp() {
        long id = 2L;
        int maximumCapacity = 50;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(3);
        Bootcamp bootcamp = null;

        Version version = new Version(id, maximumCapacity, startDate.toString(), endDate.toString(), bootcamp);

        assertEquals(id, version.getId());
        assertEquals(maximumCapacity, version.getMaximumCapacity());
        assertEquals(startDate.toString(), version.getStartDate());
        assertEquals(endDate.toString(), version.getEndDate());
        assertNull(version.getBootcamp());
    }
}
