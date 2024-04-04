package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AddVersionRequest {


    private final int maximumCapacity;


    private final LocalDate startDate;

    private final LocalDate endDate;

    private final BootcampVersionRequest bootcampVersionRequest;
}
