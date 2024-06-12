package com.bootcamp2024.bootcamp2024.domain.api.model;

import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TechnologyTest {

    @Test
    void testTechnologyConstructorAndGetters() {
        Long id = 1L;
        String name = "Java";
        String description = "Java programming language";

        Technology technology = new Technology(id, name, description);

        assertEquals(id, technology.getId());
        assertEquals(name, technology.getName());
        assertEquals(description, technology.getDescription());
    }
}
