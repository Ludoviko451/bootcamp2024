package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.VersionResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IVersionRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IVersionResponseMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.ParametersConstants;
import com.bootcamp2024.bootcamp2024.domain.api.IVersionServicePort;
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
@RequestMapping("/version")
public class VersionRestControllerAdapter {

    private final IVersionServicePort versionServicePort;
    private final IVersionRequestMapper versionRequestMapper;
    private final IVersionResponseMapper versionResponseMapper;


    public VersionRestControllerAdapter(IVersionServicePort versionServicePort, IVersionRequestMapper versionRequestMapper, IVersionResponseMapper versionResponseMapper) {
        this.versionServicePort = versionServicePort;
        this.versionRequestMapper = versionRequestMapper;
        this.versionResponseMapper = versionResponseMapper;
    }
    @Operation(summary = "Add a Version to Bootcamp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Version created"),
            @ApiResponse(responseCode = "400", description = "Invalid format of date"),
            @ApiResponse(responseCode = "400", description = "Maximum capacity exceed or fall below the limit"),
            @ApiResponse(responseCode = "400", description = "Bootcamp not exists"),
    })
    @PostMapping("/")
    public ResponseEntity<String> addVersion(@RequestBody @Valid AddVersionRequest request) {
        versionServicePort.saveVersion(versionRequestMapper.addRequestToVersion(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get All Versions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Versions found"),
            @ApiResponse(responseCode = "404", description = "Versions not found"),
            @ApiResponse(responseCode = "400", description = "Parameter not valid"),
            @ApiResponse(responseCode = "400", description = "Bootcamp not exists"),
    })
    @GetMapping("/")
    public ResponseEntity<List<VersionResponse>> getAllVersions(
            @Parameter(description = "Page number (default: 0)", required = true, example = "0") @RequestParam(defaultValue = ParametersConstants.DEFAULT_PAGE) @Valid @Min(value = 0, message = ParametersConstants.MIN_PAGE_MESSAGE) Integer page,
            @Parameter(description = "Page size (default: 10)", required = true, example = "10") @RequestParam(defaultValue = ParametersConstants.DEFAULT_SIZE) @Valid @Min(value = 1, message = ParametersConstants.MIN_SIZE_MESSAGE) Integer size,
            @Parameter(description = "Field to sort by (default: id)", required = false, example = "id") @RequestParam(defaultValue = ParametersConstants.DEFAULT_FIELD) String field,
            @Parameter(description = "Sort order (default: ASC)", required = false, example = "asc") @RequestParam(required = false) String sortBy,
            @Parameter(description = "List of bootcamp IDs for filter", required = false) @RequestParam(required = false) List<Long> bootcampIds) {
        return ResponseEntity.ok(versionResponseMapper.toVersionResponseList(versionServicePort.getAllVersion(page, size, field, sortBy, bootcampIds)));
    }
}
