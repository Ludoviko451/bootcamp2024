package com.bootcamp2024.bootcamp2024.adapters.driving.http.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.CapacityBootcamp;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IBootcampRequestMapperImpl;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IBootcampRequestMapperImplTest {

    private final IBootcampRequestMapperImpl mapper = new IBootcampRequestMapperImpl();

    @Test
    @DisplayName("Should map to Bootcamp")
    void shouldMapToBootcamp() {
        AddBootcampRequest bootcampRequest = new AddBootcampRequest("bootcamp", "description", Collections.emptyList());

        Bootcamp bootcamp = mapper.addRequestToBootcamp(bootcampRequest);

        assertThat(bootcamp.getName()).isEqualTo(bootcampRequest.getName());
        assertThat(bootcamp.getDescription()).isEqualTo(bootcampRequest.getDescription());
        assertThat(bootcamp.getCapacityList()).isEqualTo(bootcampRequest.getCapacityList());
    }

    @Test
    @DisplayName("Should map to Bootcamp with capacities")
    void shouldMapToBootcampWithCapacities() {
        CapacityBootcamp capacityBootcamp = new CapacityBootcamp(1L, "Capacity1");
        List<CapacityBootcamp> capacityBootcampList = Collections.singletonList(capacityBootcamp);
        AddBootcampRequest bootcampRequest = new AddBootcampRequest("bootcamp", "description", capacityBootcampList);

        Bootcamp bootcamp = mapper.addRequestToBootcamp(bootcampRequest);

        assertThat(bootcamp.getName()).isEqualTo(bootcampRequest.getName());
        assertThat(bootcamp.getDescription()).isEqualTo(bootcampRequest.getDescription());


        Capacity capacity = bootcamp.getCapacityList().get(0);
        assertThat(capacity.getId()).isEqualTo(capacityBootcamp.getId());
        assertThat(capacity.getName()).isEqualTo(capacityBootcamp.getName());
    }

    @Test
    @DisplayName("Should map null AddBootcampRequest to null Bootcamp")
    void shouldMapNullAddBootcampRequestToNullBootcamp() {
        Bootcamp bootcamp = mapper.addRequestToBootcamp(null);

        assertNull(bootcamp);
    }

}
