package com.bootcamp2024.bootcamp2024.domain.api.model;

import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BootcampTest {

    @Test
    public void testBootcampConstructorAndGetters() {
        long id = 1L;
        String name = "Bootcamp 2024";
        String description = "Bootcamp description";
        List<Capacity> capacityList = List.of(new Capacity(1L, "Capacity 1", "Description",Collections.emptyList()), new Capacity(2L, "Capacity 2", "Description", Collections.emptyList()));

        Bootcamp bootcamp = new Bootcamp(id, name, description, capacityList);

        assertEquals(id, bootcamp.getId());
        assertEquals(name, bootcamp.getName());
        assertEquals(description, bootcamp.getDescription());
        assertEquals(capacityList, bootcamp.getCapacityList());
    }

    @Test
    public void testBootcampEmptyCapacityList() {
        long id = 2L;
        String name = "Empty Capacity Bootcamp";
        String description = "No capacities";
        List<Capacity> capacityList = List.of();

        Bootcamp bootcamp = new Bootcamp(id, name, description, capacityList);

        assertEquals(id, bootcamp.getId());
        assertEquals(name, bootcamp.getName());
        assertEquals(description, bootcamp.getDescription());
        assertTrue(bootcamp.getCapacityList().isEmpty());
    }
}
