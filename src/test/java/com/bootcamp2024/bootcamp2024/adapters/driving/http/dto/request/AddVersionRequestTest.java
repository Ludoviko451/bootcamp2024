package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddVersionRequestTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("Should Getters be valid")
    void testAddVersionRequestGettersAndSettersValid() {
        String startDate = LocalDate.now().plusDays(1).toString();
        String endDate = LocalDate.now().plusDays(5).toString();
        AddVersionRequest addVersionRequest = new AddVersionRequest(300, startDate, endDate, 1L);
        assertEquals(1L, addVersionRequest.getBootcampId());
        assertEquals(300, addVersionRequest.getMaximumCapacity());
        assertEquals(startDate, addVersionRequest.getStartDate());
        assertEquals(endDate, addVersionRequest.getEndDate());
    }

    @Test
    @DisplayName("Fields should not be null")
    void testAddVersionRequestFieldsNotNull() {
        AddVersionRequest addVersionRequest = new AddVersionRequest(0, null, null, null);
        var violations = validator.validate(addVersionRequest);
        assertFalse(violations.isEmpty());
    }


}
