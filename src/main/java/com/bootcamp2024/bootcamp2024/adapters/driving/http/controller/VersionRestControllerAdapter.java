package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.VersionResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IVersionRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IVersionResponseMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.ParametersConstants;
import com.bootcamp2024.bootcamp2024.domain.api.IVersionServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.apache.coyote.Response;
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
    @Operation(summary = "Add a version to bootcamp")
    @PostMapping("/")
    public ResponseEntity<String> addVersion(@RequestBody @Valid AddVersionRequest request){

        versionServicePort.saveVersion(versionRequestMapper.addRequestToVersion(request));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all versions")
    @GetMapping("/")
    public ResponseEntity<List<VersionResponse>> getAllVersions(@RequestParam(defaultValue = ParametersConstants.DEFAULT_PAGE) @Valid @Min(value = 0, message =  ParametersConstants.MIN_PAGE_MESSAGE) Integer page, @RequestParam(defaultValue = ParametersConstants.DEFAULT_SIZE) @Valid @Min(value = 1, message =  ParametersConstants.MIN_SIZE_MESSAGE) Integer size, @RequestParam(defaultValue = ParametersConstants.DEFAULT_FIELD)String field,@RequestParam(required = false) String sortBy, @RequestParam(required = false) List<Long> bootcampIds){

        return ResponseEntity.ok(versionResponseMapper.toVersionResponseList(versionServicePort.getAllVersion(page, size, field, sortBy, bootcampIds)));
    }
}
