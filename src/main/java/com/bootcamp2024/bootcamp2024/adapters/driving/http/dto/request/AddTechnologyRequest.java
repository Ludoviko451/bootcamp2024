package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;


import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.RequestConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddTechnologyRequest {

    @NotBlank(message = RequestConstants.NAME_IS_MANDATORY)
    @Size(max = RequestConstants.NAME_MAX_SIZE, message = RequestConstants.NAME_MAX_SIZE_MESSAGE)
    private final String name;

    @NotBlank(message = RequestConstants.DESCRIPTION_IS_MANDATORY)
    @Size(max = RequestConstants.DESCRIPTION_MAX_SIZE, message = RequestConstants.DESCRIPTION_MAX_SIZE_MESSAGE)
    private final String description;


}
