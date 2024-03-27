package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;


import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.ParametersConstants;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import io.swagger.v3.oas.annotations.Operation;
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
    @PostMapping("/")
    public ResponseEntity<String> addCapacity(@RequestBody @Valid AddCapacityRequest request) {
        capacityServicePort.saveCapacity(capacityRequestMapper.addRequestToCapacity(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Get All Capacities")
    @GetMapping("/")
    public ResponseEntity<List<CapacityResponse>> getAllCapacity(@RequestParam(defaultValue = ParametersConstants.DEFAULT_PAGE) @Valid @Min(value = 0, message =  ParametersConstants.MIN_PAGE_MESSAGE) Integer page, @RequestParam(defaultValue = ParametersConstants.DEFAULT_SIZE) @Valid @Min(value = 1, message =  ParametersConstants.MIN_SIZE_MESSAGE)Integer size, @RequestParam(required = false) String sortBy, @RequestParam Boolean tecnologies){
        return ResponseEntity.ok(capacityResponseMapper.toCapacityResponseList(capacityServicePort.getAllCapacity(page, size, sortBy,tecnologies)));
    }
}
