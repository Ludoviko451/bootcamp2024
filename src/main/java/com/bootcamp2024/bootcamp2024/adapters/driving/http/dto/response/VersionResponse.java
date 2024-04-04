package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response;

import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class VersionResponse {

    private final long id;

    private final String bootcampName;

    private final int maximumCapacity;

    private final LocalDate startDate;

    private final LocalDate endDate;


}
