package com.bootcamp2024.bootcamp2024.domain.api.usecase;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.CapacityAlreadyExistsException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.DuplicateTechnologyException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologySizeIsNotInTheLimitException;
import com.bootcamp2024.bootcamp2024.domain.api.ITechnologyServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import com.bootcamp2024.bootcamp2024.domain.spi.ICapacityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapacityUseCaseTest {
    @Mock
    private ICapacityPersistencePort capacityPersistencePort;
    @Mock
    private ITechnologyServicePort technologyServicePort;
    private CapacityUseCase capacityUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        capacityUseCase = new CapacityUseCase(capacityPersistencePort, technologyServicePort);
    }


    @Test
    void getAllCapacity() {
        List<Technology> technologyList = Arrays.asList(
                new Technology(1L, "Java", "Lenguaje de programacion"),
                new Technology(2L, "Python", "Lenguaje de programacion"),
                new Technology(3L, "C#", "Lenguaje de programacion")
        );
        Capacity capacity1 = new Capacity(1L, "capacity1", "description", technologyList);
        Capacity capacity2 = new Capacity(2L, "capacity2", "description", technologyList);

        List<Capacity> capacityList = Arrays.asList(capacity1, capacity2);

        when(capacityPersistencePort.getAllCapacity(anyInt(), anyInt(), anyString(), anyBoolean())).thenReturn(capacityList);

        List<Capacity> capacityResult = capacityUseCase.getAllCapacity(0, 2, "", true);

        assertEquals(2, capacityResult.size());
        assertEquals("capacity1", capacityResult.get(0).getName());
        assertEquals("capacity2", capacityResult.get(1).getName());

    }

    @Test
    void shouldThrowNoDataFoundExceptionWhenCapacityNoExists(){
        when(capacityPersistencePort.getAllCapacity(anyInt(), anyInt(), anyString(), anyBoolean())).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> {
            capacityUseCase.getAllCapacity(0, 2, "", true);
        });
    }


    @Test
    void shouldSaveCapacitySuccessfully() {
        // Crear una capacidad con una lista de tecnologías
        List<Technology> technologyList = Arrays.asList(
                new Technology(1L, "Java", "Lenguaje de programacion"),
                new Technology(2L, "Python", "Lenguaje de programacion"),
                new Technology(3L, "C#", "Lenguaje de programacion")
        );
        Capacity capacity = new Capacity(1L, "Capacidad de prueba", "Descripcion", technologyList);

        // Llamar al método que queremos probar
        assertDoesNotThrow(() -> capacityUseCase.saveCapacity(capacity));

        // Verificar que el método saveCapacity fue llamado una vez con la capacidad esperada
        verify(capacityPersistencePort, times(1)).saveCapacity(capacity);
    }

    @Test
    void shouldThrowTechnologySizeIsNotInTheLimitExceptionWhenTechnologiesBelowLimit(){
        List<Technology> technologyList = Arrays.asList(
                new Technology(1L, "Java", "Lenguaje de programacion"),
                new Technology(2L, "Python", "Lenguaje de programacion")
        );
        Capacity capacity = new Capacity(1L, "Capacidad de prueba", "Descripción", technologyList);

        assertThrows(TechnologySizeIsNotInTheLimitException.class, () -> {
            capacityUseCase.saveCapacity(capacity);
        });
    }

    @Test
    void shouldThrowTechnologySizeIsNotInTheLimitExceptionWhenTechnologiesExceedLimit(){
        List<Technology> technologyList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            technologyList.add(new Technology((long) (i + 1), "Technology " + (i + 1), "Description"));
        }
        Capacity capacity = new Capacity(1L, "Capacidad de prueba", "Descripción", technologyList);

        assertThrows(TechnologySizeIsNotInTheLimitException.class, () -> {
            capacityUseCase.saveCapacity(capacity);
        });
    }

    @Test
    void shouldThrowDuplicateTechnologyExceptionWhenTechnologiesAreNotUnique(){
        List<Technology> technologyList = Arrays.asList(
                new Technology(1L, "Java", "Lenguaje de programacion"),
                new Technology(1L, "Java", "Lenguaje de programacion")
        );
        Capacity capacity = new Capacity(1L, "Capacidad de prueba", "Descripción", technologyList);

        assertThrows(DuplicateTechnologyException.class, () -> {
            capacityUseCase.saveCapacity(capacity);
        });
    }

    @Test
    void shouldReturnCapacities(){

        int page= 0;
        int size = 5;
        String orderBy = "asc";
        boolean technologies = false;

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "capacidad", "description", Collections.emptyList())
        );

        when(capacityPersistencePort.getAllCapacity(page,size,orderBy,technologies)).thenReturn(capacityList);

        List<Capacity> capacityResult = capacityUseCase.getAllCapacity(page, size, orderBy, technologies);

        assertEquals(capacityList, capacityResult);
        verify(capacityPersistencePort, times(1)).getAllCapacity(page,size,orderBy, technologies);
    }
    @Test
    void shouldReturnNoDataFoundExceptionWhenCapacitiesNoExist(){

        int page= 0;
        int size = 5;
        String orderBy = "asc";
        boolean technologies = false;


        when(capacityPersistencePort.getAllCapacity(page,size,orderBy,technologies)).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> {
            capacityUseCase.getAllCapacity(page, size, orderBy, technologies);
        });

        verify(capacityPersistencePort, times(1)).getAllCapacity(page,size,orderBy, technologies);
    }

    @Test
    void shouldThrowsCapacityAlreadyExistsExceptionWhenCapacityExists(){
        List<Technology> technologyList = Arrays.asList(
                new Technology(1L, "Java", "Lenguaje de programacion"),
                new Technology(2L, "Python", "Lenguaje de programacion")
        );
        Capacity capacity = new Capacity(1L, "Capacidad de prueba", "Descripción", technologyList);

        when(capacityPersistencePort.findCapacityByName(capacity.getName())).thenReturn(Optional.of(capacity));

        assertThrows(CapacityAlreadyExistsException.class, () -> {
            capacityUseCase.saveCapacity(capacity);
        });
    }
}