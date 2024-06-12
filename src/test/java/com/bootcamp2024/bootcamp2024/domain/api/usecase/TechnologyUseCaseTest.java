package com.bootcamp2024.bootcamp2024.domain.api.usecase;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyNotFoundException;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import com.bootcamp2024.bootcamp2024.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

 class TechnologyUseCaseTest {

    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    private TechnologyUseCase technologyUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        technologyUseCase = new TechnologyUseCase(technologyPersistencePort);
    }

    @Test
    void shouldSaveTecnologiaSuccessfully() {

        when(technologyPersistencePort.findByName(anyString())).thenReturn(Optional.empty());


        Technology technology = new Technology(1L, "Java", "Descripción de Java");

        technologyUseCase.saveTechnology(technology);

        verify(technologyPersistencePort, times(1)).saveTechnology(technology);
    }

     @Test
     void shouldThrowExceptionWhenTecnologiaExists() {

         Technology technology = new Technology(1L, "Java", "Lenguaje de programacion");


         when(technologyPersistencePort.findByName(technology.getName())).thenReturn(Optional.of(technology));


         assertThrows(TechnologyAlreadyExistsException.class, () -> {
             technologyUseCase.saveTechnology(technology);
         });
     }


     @Test
     void shouldReturnAllTechnologies() {
         Technology technology1 = new Technology(1L, "Java", "Lenguaje de programacion");
         Technology technology2 = new Technology(2L, "Python", "Lenguaje de programacion");

         List<Technology> technologyList = Arrays.asList(technology1, technology2);


         when(technologyPersistencePort.getAllTechnology(anyInt(), anyInt(), anyString(), anyString())).thenReturn(technologyList);

         List<Technology> resultado = technologyUseCase.getAllTechnology(0, 2, "", "id");

         assertEquals(2, resultado.size());
         assertEquals("Java", resultado.get(0).getName());
         assertEquals("Python", resultado.get(1).getName());
    }

     @Test
     void shouldThrowNoDataFoundExceptionWhenNoTechnologiesExist() {
         int page = 0;
         int size = 10;
         String orderBy = "";
         String field = "id";

         when(technologyPersistencePort.getAllTechnology(page, size, orderBy, field)).thenReturn(Collections.emptyList());

         assertThrows(NoDataFoundException.class, () -> technologyUseCase.getAllTechnology(page, size, orderBy, field));
     }

     @Test
     void shouldReturnTechnology(){

        Technology technology = new Technology(1L, "C#", "Lenguaje de programacion");
        when(technologyPersistencePort.findByName("C#")).thenReturn(Optional.of(technology));

        Technology technologyFinded = technologyUseCase.findTechnologyByName("C#");

        assertEquals(technology, technologyFinded);
     }

     @Test
     void shouldThrowTechnologyNotFoundExceptionWhenTechnologyNotExist(){
        when(technologyPersistencePort.findByName("C#")).thenReturn(Optional.empty());

        assertThrows(TechnologyNotFoundException.class, () -> technologyUseCase.findTechnologyByName("C#"));
     }
   }

