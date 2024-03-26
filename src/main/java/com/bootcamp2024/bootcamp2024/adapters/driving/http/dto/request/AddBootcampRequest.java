package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AddBootcampRequest {

    @NotBlank(message = "El bootcamp debe tener un nombre")
    private final String name;

    @NotBlank(message = "El bootcamp debe tener una descripcion")
    private final String description;

    @NotEmpty(message = "La lista de capacidades no puede estar vacia")
    private final List<CapacityBootcamp> capacityList;
}
