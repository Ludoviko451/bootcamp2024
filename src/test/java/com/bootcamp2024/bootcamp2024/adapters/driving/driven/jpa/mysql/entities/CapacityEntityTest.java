package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.entities;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CapacityEntityTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = validatorFactory.getValidator();


    @Test
    @DisplayName("Fields should not be blank")
    void testCapacityEntityFieldsNotBlank() {
        CapacityEntity capacity = new CapacityEntity();
        capacity.setId(0L);
        capacity.setName("");
        capacity.setDescription("");

        var violations = validator.validate(capacity);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not exceed maximum length")
    void testCapacityEntityFieldsMaxLength() {
        CapacityEntity capacity = new CapacityEntity();
        capacity.setId(0L);
        capacity.setName("a".repeat(51));
        capacity.setDescription("a".repeat(101));

        var violations = validator.validate(capacity);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should be valid")
    void testCapacityEntityFieldsValid() {
        CapacityEntity capacity = new CapacityEntity();

        capacity.setId(1L);
        capacity.setName("Valid Name");
        capacity.setDescription("Valid Description");
        capacity.setTechnologyList(Collections.emptyList());

        var violations = validator.validate(capacity);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not be null")
    void testCapacityEntityFieldsNotNull() {
        CapacityEntity capacity = new CapacityEntity();
        capacity.setId(null);
        capacity.setName(null);
        capacity.setDescription(null);
        capacity.setTechnologyList(null);

        var violations = validator.validate(capacity);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Getters and Setters should work")
    void testGettersAndSetters() {
        CapacityEntity capacity = new CapacityEntity();
        capacity.setId(1L);
        capacity.setName("Valid Name");
        capacity.setDescription("Valid Description");
        capacity.setTechnologyList(Collections.emptyList());

        assertEquals(1L, capacity.getId());
        assertEquals("Valid Name", capacity.getName());
        assertEquals("Valid Description", capacity.getDescription());
        assertEquals(Collections.emptyList(), capacity.getTechnologyList());
    }
}
