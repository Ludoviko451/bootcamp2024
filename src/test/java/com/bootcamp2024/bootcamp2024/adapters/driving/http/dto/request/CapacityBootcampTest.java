package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CapacityBootcampTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = validatorFactory.getValidator();


    @Test
    @DisplayName("Should Getters and Setters be valid")
    void testCapacityBootcampGettersAndSettersValid() {
        CapacityBootcamp capacityBootcamp = new CapacityBootcamp();
        capacityBootcamp.setId(1L);
        capacityBootcamp.setName("name");
        assertEquals(1L, capacityBootcamp.getId());
        assertEquals("name", capacityBootcamp.getName());
    }

    @Test
    @DisplayName("Fields should not be empty")
    void testCapacityBootcampFieldsNotEmpty() {
        CapacityBootcamp capacityBootcamp = new CapacityBootcamp();
        capacityBootcamp.setId(0L);
        capacityBootcamp.setName("");
        var violations = validator.validate(capacityBootcamp);
        assertFalse(violations.isEmpty());
    }
}
