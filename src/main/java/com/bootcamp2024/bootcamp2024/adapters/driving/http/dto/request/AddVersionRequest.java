package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AddVersionRequest {


    @NotNull
    private final int maximumCapacity;

    @NotNull
    private final String startDate;

    @NotNull
    private final String endDate;

    @Valid
    @NotNull
    private final Long bootcampId;
}
