package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;


import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.ParametersConstants;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/capacity")

public class CapacityRestControllerAdapter {

    public CapacityRestControllerAdapter(ICapacityServicePort capacityServicePort, ICapacityRequestMapper capacityRequestMapper, ICapacityResponseMapper capacityResponseMapper) {
        this.capacityServicePort = capacityServicePort;
        this.capacityRequestMapper = capacityRequestMapper;
        this.capacityResponseMapper = capacityResponseMapper;
    }

    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;
    private final ICapacityResponseMapper capacityResponseMapper;

    @Operation(summary = "Add a Capacity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Capacity created"),
            @ApiResponse(responseCode = "400", description = "Capacities exceed or fall below the limit"),
            @ApiResponse(responseCode = "400", description = "Duplicate technology"),
    })
    @PostMapping("/")
    public ResponseEntity<String> addCapacity(@RequestBody @Valid AddCapacityRequest request) {
        capacityServicePort.saveCapacity(capacityRequestMapper.addRequestToCapacity(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get All Capacities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Capacities found"),
            @ApiResponse(responseCode = "404", description = "Capacities not found"),
            @ApiResponse(responseCode = "400", description = "Parameter not valid")
    })
    @GetMapping("/")
    public ResponseEntity<List<CapacityResponse>> getAllCapacity(
            @Parameter(description = "Page number (default: 0)", required = true, example = "0") @RequestParam(defaultValue = ParametersConstants.DEFAULT_PAGE) @Valid @Min(value = 0, message = ParametersConstants.MIN_PAGE_MESSAGE) Integer page,
            @Parameter(description = "Page size (default: 10)", required = true, example = "10") @RequestParam(defaultValue = ParametersConstants.DEFAULT_SIZE) @Valid @Min(value = 1, message = ParametersConstants.MIN_SIZE_MESSAGE) Integer size,
            @Parameter(description = "Sort by asc or desc", required = false, example = "asc") @RequestParam(required = false) String sortBy,
            @Parameter(description = "Sort by technologies (default: false)", required = false, example = "true") @RequestParam(defaultValue = "false") Boolean technologies,
            @Parameter(description = "Field to sort by (default: id)", required = false, example = "id") @RequestParam(defaultValue = ParametersConstants.DEFAULT_FIELD) String field) {
        return ResponseEntity.ok(capacityResponseMapper.toCapacityResponseList(capacityServicePort.getAllCapacity(page, size, sortBy, technologies, field)));
    }
}

