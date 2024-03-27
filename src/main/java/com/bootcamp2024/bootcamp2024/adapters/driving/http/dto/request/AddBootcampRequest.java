package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.RequestConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AddBootcampRequest {

    @NotBlank(message = RequestConstants.NAME_IS_MANDATORY)
    @Size(max = RequestConstants.NAME_MAX_SIZE, message = RequestConstants.NAME_MAX_SIZE_MESSAGE)
    private final String name;

    @NotBlank(message = RequestConstants.DESCRIPTION_IS_MANDATORY)
    @Size(max = RequestConstants.DESCRIPTION_MAX_SIZE, message = RequestConstants.DESCRIPTION_MAX_SIZE_MESSAGE)
    private final String description;

    @NotEmpty(message = RequestConstants.CAPACITIES_LIST_IS_MANDATORY)
    private final List<CapacityBootcamp> capacityList;
}
