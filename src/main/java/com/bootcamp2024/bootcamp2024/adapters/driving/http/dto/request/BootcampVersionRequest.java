package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BootcampVersionRequest {


    private final Long id;

    private final String name;
}
