package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class TechnologyResponseTest {

    @Test
    @DisplayName("Should return a valid TechnologyResponse")
    void shouldReturnATechnologyResponse() {

        Long expectedId = 1L;
        String expectedName = "Java";
        String expectedDescription = "Java is a programming language";
        TechnologyResponse technologyResponse = new TechnologyResponse(expectedId, expectedName, expectedDescription);

         assertThat(technologyResponse.getId()).isEqualTo(expectedId);
         assertThat(technologyResponse.getName()).isEqualTo(expectedName);
         assertThat(technologyResponse.getDescription()).isEqualTo(expectedDescription);
    }
}
