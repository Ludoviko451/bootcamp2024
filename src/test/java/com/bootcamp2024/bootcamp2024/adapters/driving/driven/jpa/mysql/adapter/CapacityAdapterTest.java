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
        Integer page = 1;
        Integer size = 10;
        String sortBy = "desc";
        boolean technologies = true;
        String field = "id";

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);
        List<CapacityEntity> capacityEntityList = new ArrayList<>();
        when(capacityRepository.findAllOrderByTechnologiesCountDesc(pagination)).thenReturn(Page.empty());


        List<Capacity> capacities = capacityAdapter.getAllCapacity(page, size, sortBy, technologies, field);


        assertEquals(0, capacities.size());
    }

    @Test
    void testGetAllCapacity_InvalidField() {
        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        boolean technologies = true;
        String field = "invalidField";

        assertThrows(NotValidFieldForVersionException.class, () -> capacityAdapter.getAllCapacity(page, size, sortBy, technologies, field));
    }

    @Test
    void testGetAllCapacity_NotTechnologies() {

        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        boolean technologies = false;
        String field = "id";

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);
        List<CapacityEntity> capacityEntityList = new ArrayList<>();
        when(capacityRepository.findAll(pagination)).thenReturn(Page.empty());


        List<Capacity> capacities = capacityAdapter.getAllCapacity(page, size, sortBy, technologies, field);


        assertEquals(0, capacities.size());
    }

    @Test
    void testSaveCapacity() {

        Capacity capacity = new Capacity(1L, "Type1", "description", Collections.emptyList());
        CapacityEntity capacityEntity = capacityEntityMapper.toEntity(capacity);


        capacityAdapter.saveCapacity(capacity);


        verify(capacityRepository).save(capacityEntity);
    }

    @Test
    void testFindCapacityByName_Exists() {

        String capacityName = "Test Capacity";
        CapacityEntity capacityEntity = new CapacityEntity(1L, capacityName, "description", Collections.emptyList());
        when(capacityRepository.findByName(capacityName)).thenReturn(capacityEntity);
        Capacity expectedCapacity = new Capacity(1L, capacityName, "description", Collections.emptyList());
        when(capacityEntityMapper.toModel(capacityEntity)).thenReturn(expectedCapacity);


        Optional<Capacity> capacityOptional = capacityAdapter.findCapacityByName(capacityName);


        assertTrue(capacityOptional.isPresent());
        assertEquals(expectedCapacity, capacityOptional.get());
    }

    @Test
    void testFindCapacityByName_NotExists() {

        String capacityName = "Nonexistent Capacity";
        when(capacityRepository.findByName(capacityName)).thenReturn(null);


        Optional<Capacity> capacityOptional = capacityAdapter.findCapacityByName(capacityName);


        assertFalse(capacityOptional.isPresent());
    }

}
