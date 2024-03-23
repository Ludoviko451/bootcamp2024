package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;


import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import jakarta.validation.Valid;
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

    @PostMapping("/")
    public ResponseEntity<String> addCapacity(@RequestBody @Valid AddCapacityRequest request) {
        capacityServicePort.saveCapacity(capacityRequestMapper.addRequestToCapacity(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @GetMapping("/")
    public ResponseEntity<List<CapacityResponse>> getAllCapacity(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String sortBy, @RequestParam Boolean tecnologies){
        return ResponseEntity.ok(capacityResponseMapper.toCapacityResponseList(capacityServicePort.getAllCapacity(page, size, sortBy,tecnologies)));
    }
}
