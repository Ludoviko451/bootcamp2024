package com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request;


import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.RequestConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


///
// Esta clase define un DTO (Data Transfer Object) llamado AddTecnologiaRequest, el cual representa los datos
// de una solicitud HTTP para agregar una nueva tecnología.
//
// Los campos nombre y descripcion son obligatorios y se validan con la anotación @NotBlank para asegurar que no estén en blanco.
//
// El constructor permite inicializar los campos nombre y descripcion al crear una instancia de AddTecnologiaRequest.
//
// Los métodos getNombre y getDescripcion proporcionan acceso a los valores de los campos nombre y descripcion, respectivamente.
//

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
