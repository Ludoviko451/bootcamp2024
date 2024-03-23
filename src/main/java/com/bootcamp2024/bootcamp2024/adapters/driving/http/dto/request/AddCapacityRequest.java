package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;


import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyCapacityResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
public class AddCapacityRequest {

    @NotBlank(message = "La capacidad debe tener un nombre")
    private final String name;

    @NotBlank(message = "La capacidad debe tener una descripcion")
    private final String description;

    @NotEmpty(message = "La lista de ids de tecnologia no puede estar vacia")
    private final List<TechnologyCapacityResponse> technologyList;
}
