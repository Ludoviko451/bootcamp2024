package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.BootcampResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.ParametersConstants;
import com.bootcamp2024.bootcamp2024.domain.api.IBootcampServicePort;
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
@RequestMapping("/bootcamp")
public class BootcampRestControllerAdapter {

    private final IBootcampServicePort bootcampServicePort;
    private  final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampResponseMapper bootcampResponseMapper;

    public BootcampRestControllerAdapter(IBootcampServicePort bootcampServicePort, IBootcampRequestMapper bootcampRequestMapper, IBootcampResponseMapper bootcampResponseMapper) {
        this.bootcampServicePort = bootcampServicePort;
        this.bootcampRequestMapper = bootcampRequestMapper;
        this.bootcampResponseMapper = bootcampResponseMapper;
    }

    @Operation(summary = "Add a Bootcamp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bootcamp created"),
            @ApiResponse(responseCode = "400", description = "Capacities exceed or fall below the limit"),
    })
    @PostMapping("/")
    public ResponseEntity<String> addBootcamp(@RequestBody @Valid AddBootcampRequest request) {
        bootcampServicePort.saveBootcamp(bootcampRequestMapper.addRequestToBootcamp(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get All Bootcamps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bootcamps found"),
            @ApiResponse(responseCode = "404", description = "Bootcamps not found"),
            @ApiResponse(responseCode = "400", description = "Parameter not valid")
    })
    @GetMapping("/")
    public ResponseEntity<List<BootcampResponse>> getAllBootcamp(
            @Parameter(description = "Page number (default: 0)", required = true, example = "0") @RequestParam(defaultValue = ParametersConstants.DEFAULT_PAGE) @Valid @Min(value = 0, message = ParametersConstants.MIN_PAGE_MESSAGE) Integer page,
            @Parameter(description = "Page size (default: 10)", required = true, example = "10") @RequestParam(defaultValue = ParametersConstants.DEFAULT_SIZE) @Valid @Min(value = 1, message = ParametersConstants.MIN_SIZE_MESSAGE) Integer size,
            @Parameter(description = "Sort by asc or desc", required = false, example = "asc") @RequestParam(required = false) String sortBy,
            @Parameter(description = "Sort by capacities (default: false)", required = false, example = "true") @RequestParam(defaultValue = "false") Boolean capacities,
            @Parameter(description = "Field to sort by (default: id)", required = false, example = "id") @RequestParam(defaultValue = ParametersConstants.DEFAULT_FIELD) String field) {
        return ResponseEntity.ok(bootcampResponseMapper.toBootcampResponseList(bootcampServicePort.getAllBootcamp(page, size, sortBy, capacities, field)));
    }
}
