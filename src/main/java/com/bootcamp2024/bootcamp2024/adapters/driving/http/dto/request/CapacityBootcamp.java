package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.RequestConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapacityBootcamp {

    private Long id;
    @NotBlank(message = RequestConstants.NAME_IS_MANDATORY)
    private String name;

}
