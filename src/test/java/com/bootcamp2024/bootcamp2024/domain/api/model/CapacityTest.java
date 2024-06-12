package com.bootcamp2024.bootcamp2024.domain.api.model;

import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CapacityTest {

    @Test
    public void testCapacityConstructorAndGetters() {
        Long id = 1L;
        String name = "Capacity 1";
        String description = "Description of Capacity 1";
        List<Technology> technologyList = List.of(new Technology(1L, "Tech 1", "Description 1"),
                new Technology(2L, "Tech 2", "Description 2"));

        Capacity capacity = new Capacity(id, name, description, technologyList);

        assertEquals(id, capacity.getId());
        assertEquals(name, capacity.getName());
        assertEquals(description, capacity.getDescription());
        assertEquals(technologyList, capacity.getTechnologyList());
    }

    @Test
    public void testCapacityEmptyTechnologyList() {
        Long id = 2L;
        String name = "Empty Tech Capacity";
        String description = "No technologies";
        List<Technology> technologyList = List.of();

        Capacity capacity = new Capacity(id, name, description, technologyList);

        assertEquals(id, capacity.getId());
        assertEquals(name, capacity.getName());
        assertEquals(description, capacity.getDescription());
        assertTrue(capacity.getTechnologyList().isEmpty());
    }
}
