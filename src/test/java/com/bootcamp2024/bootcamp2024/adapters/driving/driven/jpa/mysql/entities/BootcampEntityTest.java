package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.entities;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BootcampEntityTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("Fields should not be blank")
    void testBootcampEntityFieldsNotBlank() {
        BootcampEntity bootcamp = new BootcampEntity();
        bootcamp.setId(0L);
        bootcamp.setName("");
        bootcamp.setDescription("");

        var violations = validator.validate(bootcamp);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should be valid")
    void testBootcampEntityFieldsValid() {
        BootcampEntity bootcamp = new BootcampEntity();

        bootcamp.setId(1L);
        bootcamp.setName("Valid Name");
        bootcamp.setDescription("Valid Description");
        bootcamp.setCapacityList(Collections.emptyList());

        var violations = validator.validate(bootcamp);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not exceed maximum length")
    void testBootcampEntityFieldsMaxLength() {
        BootcampEntity bootcamp = new BootcampEntity();
        bootcamp.setId(0L);
        bootcamp.setName("a".repeat(51));
        bootcamp.setDescription("a".repeat(101));

        var violations = validator.validate(bootcamp);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not be null")
    void testBootcampEntityFieldsNotNull() {
        BootcampEntity bootcamp = new BootcampEntity();
        bootcamp.setId(null);
        bootcamp.setName(null);
        bootcamp.setDescription(null);
        bootcamp.setCapacityList(null);

        var violations = validator.validate(bootcamp);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Getters and Setters should work")
    void testGettersAndSetters() {
        BootcampEntity bootcamp = new BootcampEntity();
        bootcamp.setId(1L);
        bootcamp.setName("Valid Name");
        bootcamp.setDescription("Valid Description");
        bootcamp.setCapacityList(Collections.emptyList());

        assertEquals(1L, bootcamp.getId());
        assertEquals("Valid Name", bootcamp.getName());
        assertEquals("Valid Description", bootcamp.getDescription());
        assertEquals(Collections.emptyList(), bootcamp.getCapacityList());
    }
}
