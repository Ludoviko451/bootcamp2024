package com.bootcamp2024.bootcamp2024.adapters.driving.http.controller;



import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.request.UpdateTechnologyRequest;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.dto.response.TechnologyResponse;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.bootcamp2024.bootcamp2024.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.bootcamp2024.bootcamp2024.domain.api.ITechnologyServicePort;
import jakarta.validation.Valid;
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

    @PostMapping("/")
    public ResponseEntity<String> addTechnology(@RequestBody @Valid AddTechnologyRequest request) {
        technologyServicePort.saveTechnology(technologyRequestMapper.addRequestToTechnology(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<TechnologyResponse>> getAllTechnology(
            @RequestParam Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(technologyResponseMapper.toTechnologyResponseList(technologyServicePort.getAllTechnology(page, size, sortBy)));
    }

    @PutMapping("/")
    public ResponseEntity<TechnologyResponse> updateTechnology(@RequestBody UpdateTechnologyRequest request) {
        return ResponseEntity.ok(technologyResponseMapper.toTechnologyResponse(
                technologyServicePort.updateTechnology(technologyRequestMapper.updateRequestToTechnology(request))
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        technologyServicePort.deleteTechnology(id);
        return ResponseEntity.noContent().build();
    }
}
