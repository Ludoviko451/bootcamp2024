package com.bootcamp2024.bootcamp2024.adapters.driving.http.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.BootcampResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityBootcampResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyCapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IBootcampResponseMapperImpl;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IBootcampResponseMapperImplTest {

    private final IBootcampResponseMapperImpl mapper = new IBootcampResponseMapperImpl();

    @Test
    @DisplayName("Should map to BootcampResponse")
    void shouldMapToBootcampResponse() {
        Bootcamp bootcamp = new Bootcamp(1L, "bootcamp", "description", Collections.emptyList());

        BootcampResponse bootcampResponse = mapper.toBootcampResponse(bootcamp);

        assertThat(bootcampResponse.getId()).isEqualTo(bootcamp.getId());
        assertThat(bootcampResponse.getName()).isEqualTo(bootcamp.getName());
        assertThat(bootcampResponse.getDescription()).isEqualTo(bootcamp.getDescription());
        assertThat(bootcampResponse.getCapacityList()).isEmpty();
    }

    @Test
    @DisplayName("Should map to BootcampResponseList with single Bootcamp")
    void shouldMapToBootcampResponseListWithSingleBootcamp() {
        Bootcamp bootcamp = new Bootcamp(1L, "bootcamp", "description", Collections.emptyList());

        List<BootcampResponse> bootcampResponseList = mapper.toBootcampResponseList(Collections.singletonList(bootcamp));

        assertThat(bootcampResponseList).hasSize(1);
        BootcampResponse bootcampResponse = bootcampResponseList.get(0);
        assertThat(bootcampResponse.getId()).isEqualTo(bootcamp.getId());
        assertThat(bootcampResponse.getName()).isEqualTo(bootcamp.getName());
        assertThat(bootcampResponse.getDescription()).isEqualTo(bootcamp.getDescription());
        assertThat(bootcampResponse.getCapacityList()).isEmpty();
    }

    @Test
    @DisplayName("Should map to BootcampResponseList with empty list")
    void shouldMapToBootcampResponseListWithEmptyList() {
        List<BootcampResponse> bootcampResponseList = mapper.toBootcampResponseList(Collections.emptyList());

        assertThat(bootcampResponseList).isEmpty();
    }

    @Test
    @DisplayName("Should map Bootcamp with Capacity and Technology to BootcampResponse")
    void shouldMapBootcampWithCapacityAndTechnologyToBootcampResponse() {
        Technology technology = new Technology(1L, "Java", "description");
        Capacity capacity = new Capacity(1L, "Backend Development", "description", Collections.singletonList(technology));
        Bootcamp bootcamp = new Bootcamp(1L, "bootcamp", "description", Collections.singletonList(capacity));

        BootcampResponse bootcampResponse = mapper.toBootcampResponse(bootcamp);

        assertThat(bootcampResponse.getId()).isEqualTo(bootcamp.getId());
        assertThat(bootcampResponse.getName()).isEqualTo(bootcamp.getName());
        assertThat(bootcampResponse.getDescription()).isEqualTo(bootcamp.getDescription());

        List<CapacityBootcampResponse> capacityResponses = bootcampResponse.getCapacityList();
        assertThat(capacityResponses).hasSize(1);

        CapacityBootcampResponse capacityResponse = capacityResponses.get(0);
        assertThat(capacityResponse.getId()).isEqualTo(capacity.getId());
        assertThat(capacityResponse.getName()).isEqualTo(capacity.getName());

        List<TechnologyCapacityResponse> technologyResponses = capacityResponse.getTechnologyList();
        assertThat(technologyResponses).hasSize(1);

        TechnologyCapacityResponse technologyResponse = technologyResponses.get(0);
        assertThat(technologyResponse.getId()).isEqualTo(technology.getId());
        assertThat(technologyResponse.getName()).isEqualTo(technology.getName());
    }
}
