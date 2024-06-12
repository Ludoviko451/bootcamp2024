package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.adapter;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NotValidFieldForVersionException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import com.bootcamp2024.bootcamp2024.domain.util.ListHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TechnologyAdapterTest {
    @Mock
    private ITechnologyRepository technologyRepository;
    @Mock
    private ITechnologyEntityMapper technologyEntityMapper;

    private TechnologyAdapter technologyAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        technologyAdapter = new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }


    @Test
    void testSaveTechnology() {

        Technology technology = new Technology(1L, "Java", "Description");
        TechnologyEntity technologyEntity = new TechnologyEntity(1L, "Java", "Description");
        when(technologyEntityMapper.toEntity(technology)).thenReturn(technologyEntity);


        technologyAdapter.saveTechnology(technology);


        verify(technologyRepository).save(technologyEntity);
    }

    @Test
    void testDeleteTechnology_ExistingId() {

        Long id = 1L;
        when(technologyRepository.findById(id)).thenReturn(Optional.of(new TechnologyEntity()));


        technologyAdapter.deleteTechnology(id);


        verify(technologyRepository).deleteById(id);
    }

    @Test
    void testDeleteTechnology_NonExistingId() {

        Long id = 1L;
        when(technologyRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(ElementNotFoundException.class, () -> technologyAdapter.deleteTechnology(id));
    }

    @Test
    void testFindByName_ExistingTechnology() {

        String technologyName = "Java";
        TechnologyEntity technologyEntity = new TechnologyEntity();
        when(technologyRepository.findByName(technologyName)).thenReturn(technologyEntity);
        Technology expectedTechnology = new Technology(1L, "Java", "Description");
        when(technologyEntityMapper.toModel(technologyEntity)).thenReturn(expectedTechnology);


        Optional<Technology> technologyOptional = technologyAdapter.findByName(technologyName);


        assertTrue(technologyOptional.isPresent());
        assertEquals(expectedTechnology, technologyOptional.get());
    }

    @Test
    void testFindByName_NonExistingTechnology() {

        String technologyName = "Nonexistent Technology";
        when(technologyRepository.findByName(technologyName)).thenReturn(null);


        Optional<Technology> technologyOptional = technologyAdapter.findByName(technologyName);


        assertFalse(technologyOptional.isPresent());
    }

    @Test
    void testGetAllTechnology_ValidField() {

        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        String field = "id";
        Pageable pageable = ListHelper.createPageable(page, size, sortBy, field);
        List<TechnologyEntity> technologyEntities = Collections.emptyList();
        when(technologyRepository.findAll(pageable)).thenReturn(new PageImpl<>(technologyEntities));
        when(technologyEntityMapper.toModelList(technologyEntities)).thenReturn(Collections.emptyList());


        List<Technology> result = technologyAdapter.getAllTechnology(page, size, sortBy, field);


        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetAllTechnology_InvalidField() {

        Integer page = 1;
        Integer size = 10;
        String sortBy = "asc";
        String field = "invalidField";


        assertThrows(NotValidFieldForVersionException.class, () -> technologyAdapter.getAllTechnology(page, size, sortBy, field));
    }

    @Test
    void testUpdateTechnology_ExistingId() {

        Technology technology = new Technology(1L, "Java", "Description");
        TechnologyEntity technologyEntity = new TechnologyEntity(1L, "Java", "Description");
        when(technologyEntityMapper.toEntity(technology)).thenReturn(technologyEntity);
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(technologyEntity));

        Technology updatedTechnology = new Technology(1L, "Python", "Description");
        TechnologyEntity updatedTechnologyEntity = new TechnologyEntity(1L, "Python", "Description");
        when(technologyEntityMapper.toEntity(updatedTechnology)).thenReturn(updatedTechnologyEntity);


        technologyAdapter.updateTechnology(updatedTechnology);


        verify(technologyRepository).save(updatedTechnologyEntity);
    }


    @Test
    void testUpdateTechnology_NonExistingId() {

        Technology technology = new Technology(1L, "Java", "Description");
        when(technologyRepository.findById(technology.getId())).thenReturn(Optional.empty());


        assertThrows(ElementNotFoundException.class, () -> technologyAdapter.updateTechnology(technology));
    }
}
