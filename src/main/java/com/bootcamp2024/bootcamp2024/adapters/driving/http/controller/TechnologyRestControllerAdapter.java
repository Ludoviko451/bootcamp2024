package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;



import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.ParametersConstants;
import com.bootcamp2024.bootcamp2024.domain.api.ITechnologyServicePort;
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
@RequestMapping("/technology")


public class TechnologyRestControllerAdapter {

    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;

    public TechnologyRestControllerAdapter(ITechnologyServicePort technologyServicePort, ITechnologyRequestMapper technologyRequestMapper, ITechnologyResponseMapper technologyResponseMapper) {
        this.technologyServicePort = technologyServicePort;
        this.technologyRequestMapper = technologyRequestMapper;
        this.technologyResponseMapper = technologyResponseMapper;
    }

    @Operation(summary = "Add a Technology")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Technology created"),
            @ApiResponse(responseCode = "400", description = "Technology already exists"),
    })
    @PostMapping("/")
    public ResponseEntity<String> addTechnology(@RequestBody @Valid AddTechnologyRequest request) {
        technologyServicePort.saveTechnology(technologyRequestMapper.addRequestToTechnology(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get All Technologies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Technologies found"),
            @ApiResponse(responseCode = "404", description = "Technologies not found"),
            @ApiResponse(responseCode = "400", description = "Parameter not valid")
    })
    @GetMapping("/")
    public ResponseEntity<List<TechnologyResponse>> getAllTechnology(
            @Parameter(description = "Page number (default: 0)", required = false, example = "0") @RequestParam(defaultValue = ParametersConstants.DEFAULT_PAGE) @Valid @Min(value = 0, message = ParametersConstants.MIN_PAGE_MESSAGE) Integer page,
            @Parameter(description = "Page size (default: 10)", required = false, example = "10") @RequestParam(defaultValue = ParametersConstants.DEFAULT_SIZE) @Valid @Min(value = 1, message = ParametersConstants.MIN_SIZE_MESSAGE) Integer size,
            @Parameter(description = "Sort by asc or desc", required = false, example = "asc") @RequestParam(required = false) String sortBy,
            @Parameter(description = "Field to sort by (default: id)", required = false, example = "id") @RequestParam(defaultValue = ParametersConstants.DEFAULT_FIELD) String field) {
        return ResponseEntity.ok(technologyResponseMapper.toTechnologyResponseList(technologyServicePort.getAllTechnology(page, size, sortBy, field)));
    }


//    @PutMapping("/")
//    public ResponseEntity<TechnologyResponse> updateTechnology(@RequestBody UpdateTechnologyRequest request) {
//        return ResponseEntity.ok(technologyResponseMapper.toTechnologyResponse(
//                technologyServicePort.updateTechnology(technologyRequestMapper.updateRequestToTechnology(request))
//        ));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
//        technologyServicePort.deleteTechnology(id);
//        return ResponseEntity.noContent().build();
//    }
}
