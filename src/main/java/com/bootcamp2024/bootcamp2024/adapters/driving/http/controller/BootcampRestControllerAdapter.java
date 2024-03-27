package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;

import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.BootcampResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.utils.ParametersConstants;
import com.bootcamp2024.bootcamp2024.domain.api.IBootcampServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    @PostMapping("/")
    public ResponseEntity<String> addBootcamp(@RequestBody @Valid AddBootcampRequest request) {
        bootcampServicePort.saveBootcamp(bootcampRequestMapper.addRequestToBootcamp(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get All Bootcamp")
    @GetMapping("/")
    public ResponseEntity<List<BootcampResponse>> getAllBootcamp(@RequestParam(defaultValue = ParametersConstants.DEFAULT_PAGE) @Valid @Min(value = 0, message =  ParametersConstants.MIN_PAGE_MESSAGE) Integer page, @RequestParam(defaultValue = ParametersConstants.DEFAULT_SIZE) @Valid @Min(value = 1, message =  ParametersConstants.MIN_SIZE_MESSAGE) Integer size, @RequestParam(required = false) String sortBy, @RequestParam Boolean capacities){
        return ResponseEntity.ok(bootcampResponseMapper.toBootcampResponseList(bootcampServicePort.getAllBootcamp(page, size, sortBy, capacities)));
    }
}
