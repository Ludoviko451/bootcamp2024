package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTechnologyRequestTest {


    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("Fields should not be blank")
    void testAddTechnologyRequestFieldsNotBlank() {
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest("", "");
        var violations = validator.validate(addTechnologyRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not be null")
    void testAddTechnologyRequestFieldsNotNull() {
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest(null, null);
        var violations = validator.validate(addTechnologyRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not exceed maximum length")
    void testAddTechnologyRequestFieldsMaxLength() {
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest("a".repeat(51), "a".repeat(101));
        var violations = validator.validate(addTechnologyRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should be valid")
    void testAddTechnologyRequestFieldsValid() {
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest("Valid Name", "Valid Description");
        var violations = validator.validate(addTechnologyRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Getters and Setters should be valid")
    void testAddTechnologyRequestGettersAndSettersValid() {
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest("Valid Name", "Valid Description");
        assertEquals("Valid Name", addTechnologyRequest.getName());
        assertEquals("Valid Description", addTechnologyRequest.getDescription());
    }

}
