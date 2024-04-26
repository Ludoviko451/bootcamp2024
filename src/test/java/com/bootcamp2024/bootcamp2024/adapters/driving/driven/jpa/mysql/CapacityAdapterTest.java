package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.CapacityAdapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NotValidFieldForVersionException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.util.ListHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
class CapacityAdapterTest {
    @Mock
    private ICapacityRepository capacityRepository;
    @Mock
    private ICapacityEntityMapper capacityEntityMapper;

    private CapacityAdapter capacityAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        capacityAdapter = new CapacityAdapter(capacityRepository, capacityEntityMapper);
    }

    @Test
    void testGetAllCapacity_ValidField() {
        // Setup
        Integer page = 1;
        Integer size = 10;
        String sortBy = "desc";
        boolean technologies = true;
        String field = "id";

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);
        List<CapacityEntity> capacityEntityList = new ArrayList<>();
        when(capacityRepository.findAllOrderByTechnologiesCountDesc(pagination)).thenReturn(Page.empty());

        // Execute
        List<Capacity> capacities = capacityAdapter.getAllCapacity(page, size, sortBy, technologies, field);

        // Verify
        assertEquals(0, capacities.size());
    }

    @Test
    void testGetAllCapacity_InvalidField() {
        // Setup
        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        boolean technologies = true;
        String field = "invalidField";

        // Verify
        assertThrows(NotValidFieldForVersionException.class, () -> capacityAdapter.getAllCapacity(page, size, sortBy, technologies, field));
    }

    @Test
    void testGetAllCapacity_NotTechnologies() {
        // Setup
        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        boolean technologies = false;
        String field = "id";

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);
        List<CapacityEntity> capacityEntityList = new ArrayList<>();
        when(capacityRepository.findAll(pagination)).thenReturn(Page.empty());

        // Execute
        List<Capacity> capacities = capacityAdapter.getAllCapacity(page, size, sortBy, technologies, field);

        // Verify
        assertEquals(0, capacities.size());
    }

    @Test
    void testSaveCapacity() {
        // Setup
        Capacity capacity = new Capacity(1L, "Type1", "description", Collections.emptyList());
        CapacityEntity capacityEntity = capacityEntityMapper.toEntity(capacity);

        // Execute
        capacityAdapter.saveCapacity(capacity);

        // Verify
        verify(capacityRepository).save(capacityEntity);
    }

    @Test
    void testFindCapacityByName_Exists() {
        // Setup
        String capacityName = "Test Capacity";
        CapacityEntity capacityEntity = new CapacityEntity(1L, capacityName, "description", Collections.emptyList());
        when(capacityRepository.findByName(capacityName)).thenReturn(capacityEntity);
        Capacity expectedCapacity = new Capacity(1L, capacityName, "description", Collections.emptyList());
        when(capacityEntityMapper.toModel(capacityEntity)).thenReturn(expectedCapacity);

        // Execute
        Optional<Capacity> capacityOptional = capacityAdapter.findCapacityByName(capacityName);

        // Verify
        assertTrue(capacityOptional.isPresent());
        assertEquals(expectedCapacity, capacityOptional.get());
    }

    @Test
    void testFindCapacityByName_NotExists() {
        // Setup
        String capacityName = "Nonexistent Capacity";
        when(capacityRepository.findByName(capacityName)).thenReturn(null);

        // Execute
        Optional<Capacity> capacityOptional = capacityAdapter.findCapacityByName(capacityName);

        // Verify
        assertFalse(capacityOptional.isPresent());
    }

}
