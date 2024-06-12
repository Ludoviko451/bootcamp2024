package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.entities;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.VersionEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VersionEntityTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("Fields should be valid")
    void testVersionEntityFieldsValid() {

        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        VersionEntity version = new VersionEntity();
        version.setId(1L);
        version.setMaximumCapacity(300);
        version.setStartDate(startDate);
        version.setEndDate(endDate);
        version.setBootcamp(new BootcampEntity());

        var violations = validator.validate(version);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Getters and Setters should be valid")
    void testVersionEntityGettersAndSettersValid() {
        VersionEntity version = new VersionEntity();
        BootcampEntity bootcamp = new BootcampEntity();
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        version.setId(1L);
        version.setMaximumCapacity(300);
        version.setStartDate(startDate);
        version.setEndDate(endDate);
        version.setBootcamp(bootcamp);
        assertEquals(1L, version.getId());
        assertEquals(300, version.getMaximumCapacity());
        assertEquals(startDate, version.getStartDate());
        assertEquals(endDate, version.getEndDate());
        assertEquals(bootcamp, version.getBootcamp());
    }
}
