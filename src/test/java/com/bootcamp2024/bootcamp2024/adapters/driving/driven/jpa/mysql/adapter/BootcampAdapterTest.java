package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.adapter;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.BootcampAdapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NotValidFieldForVersionException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.util.ListHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BootcampAdapterTest {

    @Mock
    private IBootcampRepository bootcampRepository;
    @Mock
    private IBootcampEntityMapper bootcampEntityMapper;

    private BootcampAdapter bootcampAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bootcampAdapter = new BootcampAdapter(bootcampRepository, bootcampEntityMapper);
    }

    @Test
    void testGetAllBootcamp_ValidField() {

        Integer page = 1;
        Integer size = 10;
        String sortBy = "desc";
        boolean capacities = true;
        String field = "id";

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);
        List<BootcampEntity> bootcampEntityList = new ArrayList<>();
        when(bootcampRepository.findAllOrderByCapacitiesCountDesc(pagination)).thenReturn(new PageImpl<>(bootcampEntityList));


        List<Bootcamp> bootcamps = bootcampAdapter.getAllBootcamp(page, size, sortBy, capacities, field);


        assertEquals(0, bootcamps.size());
    }

    @Test
    void testGetAllBootcamp_InvalidField() {

        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        boolean capacities = true;
        String field = "invalidField";


        assertThrows(NotValidFieldForVersionException.class, () -> bootcampAdapter.getAllBootcamp(page, size, sortBy, capacities, field));
    }

    @Test
    void testGetAllBootcamp_SortByCapacitiesAsc() {

        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        boolean capacities = true;
        String field = "id";

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);
        List<BootcampEntity> bootcampEntityList = new ArrayList<>();
        when(bootcampRepository.findAllOrderByCapacitiesCountAsc(pagination)).thenReturn(new PageImpl<>(bootcampEntityList));


        List<Bootcamp> bootcamps = bootcampAdapter.getAllBootcamp(page, size, sortBy, capacities, field);

        assertEquals(0, bootcamps.size());
    }

    @Test
    void testGetAllBootcamp_SortByCapacitiesDesc() {

        Integer page = 1;
        Integer size = 10;
        String sortBy = "desc";
        boolean capacities = true;
        String field = "id";

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);
        List<BootcampEntity> bootcampEntityList = new ArrayList<>();
        when(bootcampRepository.findAllOrderByCapacitiesCountDesc(pagination)).thenReturn(new PageImpl<>(bootcampEntityList));


        List<Bootcamp> bootcamps = bootcampAdapter.getAllBootcamp(page, size, sortBy, capacities, field);



        assertEquals(0, bootcamps.size());
    }

    @Test
    void testGetAllBootcamp_NotSortByCapacities() {

        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        boolean capacities = false;
        String field = "id";

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);
        List<BootcampEntity> bootcampEntityList = new ArrayList<>();
        when(bootcampRepository.findAll(pagination)).thenReturn(new PageImpl<>(bootcampEntityList));


        List<Bootcamp> bootcamps = bootcampAdapter.getAllBootcamp(page, size, sortBy, capacities, field);


        assertEquals(0, bootcamps.size());
    }

    @Test
    void testSaveBootcamp() {

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp", "Description", Collections.emptyList());
        BootcampEntity bootcampEntity = bootcampEntityMapper.toEntity(bootcamp);


        bootcampAdapter.saveBootcamp(bootcamp);


        verify(bootcampRepository).save(bootcampEntity);
    }


    @Test
    void testFindBootcampByName_Exists() {

        Long id = 1L;
        String bootcampName = "Test Bootcamp";
        String description = "Description";
        BootcampEntity bootcampEntity = new BootcampEntity(id, bootcampName, description, Collections.emptyList());
        when(bootcampRepository.findByName(bootcampName)).thenReturn(bootcampEntity);
        Bootcamp expectedBootcamp = new Bootcamp(id, bootcampName, description, Collections.emptyList());
        when(bootcampEntityMapper.toModel(bootcampEntity)).thenReturn(expectedBootcamp);


        Optional<Bootcamp> bootcampOptional = bootcampAdapter.findBootcampByName(bootcampName);


        assertTrue(bootcampOptional.isPresent());
        assertEquals(expectedBootcamp, bootcampOptional.get());
    }

    @Test
    void testFindBootcampByName_NotExists() {

        String bootcampName = "Nonexistent Bootcamp";
        when(bootcampRepository.findByName(bootcampName)).thenReturn(null);

        Optional<Bootcamp> bootcampOptional = bootcampAdapter.findBootcampByName(bootcampName);


        assertFalse(bootcampOptional.isPresent());
    }

    @Test
    void testFindBootcampById_Exists() {

        Long id = 1L;
        String bootcampName = "Test Bootcamp";
        String description = "Description";

        BootcampEntity bootcampEntity = new BootcampEntity(id, bootcampName, description, Collections.emptyList());
        when(bootcampRepository.findById(id)).thenReturn(Optional.of(bootcampEntity));
        Bootcamp expectedBootcamp = new Bootcamp(id, bootcampName, description, Collections.emptyList());
        when(bootcampEntityMapper.toModel(bootcampEntity)).thenReturn(expectedBootcamp);


        Bootcamp bootcamp = bootcampAdapter.findBootcampById(id);


        assertNotNull(bootcamp);
        assertEquals(expectedBootcamp, bootcamp);
    }

    @Test
    void testFindBootcampById_NotExists() {

        long bootcampId = 1L;
        when(bootcampRepository.findById(bootcampId)).thenReturn(Optional.empty());


        Bootcamp bootcamp = bootcampAdapter.findBootcampById(bootcampId);


        assertNull(bootcamp);
    }
}
