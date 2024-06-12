package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BootcampResponseTest {

    @Test
    @DisplayName("Should create a BootcampResponse")
    void shouldCreateABootcampResponse() {

        Long id = 1L;
        String name = "Bootcamp";
        String description = "Description";
        CapacityBootcampResponse capacityOne = new CapacityBootcampResponse(1L, "Capacity1", Collections.emptyList());
        CapacityBootcampResponse capacityTwo = new CapacityBootcampResponse(1L, "Capacity1", Collections.emptyList());

        List<CapacityBootcampResponse> bootcampResponseList = List.of(capacityOne, capacityTwo);
        BootcampResponse bootcampResponse = new BootcampResponse(id, name, description, bootcampResponseList);

        assertThat(bootcampResponse.getId()).isEqualTo(id);
        assertThat(bootcampResponse.getName()).isEqualTo(name);
        assertThat(bootcampResponse.getDescription()).isEqualTo(description);
        assertThat(bootcampResponse.getCapacityList()).isEqualTo(bootcampResponseList);


    }
}
