package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyCapacityResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddCapacityRequestTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("Fields should not be blank")
    void testAddCapacityRequestFieldsNotBlank() {
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("", "", Collections.emptyList());
        var violations = validator.validate(addCapacityRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Fields should not be null")
    void testAddCapacityRequestFieldsNotNull() {
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest(null, null, null);
        var violations = validator.validate(addCapacityRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Getters and Setters should be valid")
    void testAddCapacityRequestGettersAndSettersValid() {
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("Valid Name", "Valid Description", Collections.emptyList());
        assertEquals("Valid Name", addCapacityRequest.getName());
        assertEquals("Valid Description", addCapacityRequest.getDescription());
    }

    @Test
    @DisplayName("Fields should be valid")
    void testAddCapacityRequestFieldsValid() {
        List<TechnologyCapacityResponse> technologyCapacityResponse = List.of(new TechnologyCapacityResponse(1L, "Valid Description"));
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("Valid Name", "Valid Description", technologyCapacityResponse);
        var violations = validator.validate(addCapacityRequest);
        assertTrue(violations.isEmpty());
    }
}
