package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.entities;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TechnologyEntityTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("Fields should not be blank")
    void testTechnologyEntityFieldsNotBlank() {
        TechnologyEntity technology = new TechnologyEntity();
        technology.setId(0L);
        technology.setName("");
        technology.setDescription("");

        var violations = validator.validate(technology);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not be null")
    void testTechnologyEntityFieldsNotNull() {
        TechnologyEntity technology = new TechnologyEntity();
        technology.setId(null);
        technology.setName(null);
        technology.setDescription(null);

        var violations = validator.validate(technology);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not exceed maximum length")
    void testTechnologyEntityFieldsMaxLength() {
        TechnologyEntity technology = new TechnologyEntity();
        technology.setId(0L);
        technology.setName("a".repeat(51));
        technology.setDescription("a".repeat(101));

        var violations = validator.validate(technology);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should be valid")
    void testTechnologyEntityFieldsValid() {
        TechnologyEntity technology = new TechnologyEntity();
        technology.setId(1L);
        technology.setName("Valid Name");
        technology.setDescription("Valid Description");

        var violations = validator.validate(technology);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Getters and Setters should work")
    void testGettersAndSetters() {
        TechnologyEntity technology = new TechnologyEntity();
        technology.setId(1L);
        technology.setName("Valid Name");
        technology.setDescription("Valid Description");

        assertEquals(1L, technology.getId());
        assertEquals("Valid Name", technology.getName());
        assertEquals("Valid Description", technology.getDescription());
    }
}
