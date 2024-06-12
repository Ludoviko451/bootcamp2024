package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddBootcampRequestTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("Fields should not be blank")
    void testAddBootcampRequestFieldsNotBlank() {
        AddBootcampRequest addBootcampRequest = new AddBootcampRequest("", "", Collections.emptyList());
        var violations = validator.validate(addBootcampRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not be null")
    void testAddBootcampRequestFieldsNotNull() {
        AddBootcampRequest addBootcampRequest = new AddBootcampRequest(null, null, null);
        var violations = validator.validate(addBootcampRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Getters and Setters should be valid")
    void testAddBootcampRequestGettersAndSettersValid() {
        AddBootcampRequest addBootcampRequest = new AddBootcampRequest("Valid Name", "Valid Description", Collections.emptyList());
        assertEquals("Valid Name", addBootcampRequest.getName());
        assertEquals("Valid Description", addBootcampRequest.getDescription());
    }

    @Test
    @DisplayName("Fields should be valid")
    void testAddBootcampRequestFieldsValid() {
        List<CapacityBootcamp> capacityBootcamps = List.of(new CapacityBootcamp(1L, "Valid Description"));
        AddBootcampRequest addBootcampRequest = new AddBootcampRequest("Valid Name", "Valid Description", capacityBootcamps);
        var violations = validator.validate(addBootcampRequest);
        assertTrue(violations.isEmpty());
    }
}
