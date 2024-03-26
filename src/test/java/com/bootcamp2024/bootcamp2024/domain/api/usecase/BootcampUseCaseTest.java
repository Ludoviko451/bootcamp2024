package com.bootcamp2024.bootcamp2024.domain.api.usecase;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.CapacitySizeIsNotInTheLimitException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.DuplicateCapacityException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BootcampUseCaseTest {

    @Mock
    private IBootcampPersistencePort bootcampPersistencePort;
    @Mock
    private ICapacityServicePort capacityServicePort;
    private BootcampUseCase bootcampUseCase;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        bootcampUseCase = new BootcampUseCase(capacityServicePort, bootcampPersistencePort);
    }

    @Test
    void shouldSaveBootcampSuccessfully(){

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "Capacidad 1", "Description", Collections.emptyList()),
                new Capacity(2L, "Capacidad 2", "Description", Collections.emptyList())
        );

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp de prueba", "Description", capacityList);

        assertDoesNotThrow(() -> bootcampUseCase.saveBootcamp(bootcamp));

        verify(bootcampPersistencePort, times(1)).saveBootcamp(bootcamp);
    }

    @Test
    void shouldThrowCapacitySizeIsNotInTheLimitExceptionWhenCapacitiesBelowLimit(){

        List<Capacity> capacityList = Collections.emptyList();

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp prueba", "Description", capacityList);

        assertThrows(CapacitySizeIsNotInTheLimitException.class, () -> {
           bootcampUseCase.saveBootcamp(bootcamp);
        });
    }

    @Test
    void shouldThrowCapacitySizeIsNotTheLimitExceptionWhenCapacitiesExceedLimit(){

        List<Capacity> capacityList = new ArrayList<>();

        for (long i = 1L; i <= 5L; i++) {
            capacityList.add(new Capacity(i, "Capacity" + i, "Description", Collections.emptyList()));
        }
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp prueba", "Description", capacityList);

        assertThrows(CapacitySizeIsNotInTheLimitException.class, () ->{
            bootcampUseCase.saveBootcamp(bootcamp);
        });
    }

    @Test
    void shouldThrowDuplicateCapacityExceptionWhenCapacitiesAreNotUnique(){

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "Capacidad", "Description", Collections.emptyList()),
                new Capacity(1L, "Capacidad", "Description", Collections.emptyList())
        );
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp de prueba", "Description", capacityList);

        assertThrows(DuplicateCapacityException.class, () -> {
            bootcampUseCase.saveBootcamp(bootcamp);
        });
    }

    @Test
    void shouldReturnAllBootcamp(){

        List<Capacity> capacityList = Arrays.asList(
                new Capacity(1L, "Capacidad 1", "Description", Collections.emptyList()),
                new Capacity(2L, "Capacidad 2", "Description", Collections.emptyList())
        );

        List<Bootcamp> bootcampList = Arrays.asList(
                new Bootcamp(1L, "Bootcamp 1", "Description", capacityList),
                new Bootcamp(1L, "Bootcamp 2", "Description", capacityList)
        );

        when(bootcampPersistencePort.getAllBootcamp(anyInt(), anyInt(), anyString(), anyBoolean())).thenReturn(bootcampList);

        List<Bootcamp> bootcampResult = bootcampUseCase.getAllBootcamp(0, 2, "", false);

        assertEquals(2, bootcampResult.size());
        assertEquals("Bootcamp 1", bootcampResult.get(0).getName());
        assertEquals("Bootcamp 2", bootcampResult.get(1).getName());
    }

    @Test
    void shouldThrowNoDataFoundExceptionWhenBootcampNoExist(){
        when(bootcampPersistencePort.getAllBootcamp(anyInt(), anyInt(), anyString(), anyBoolean())).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> {
            bootcampUseCase.getAllBootcamp(0, 2, "", true);
        });
    }
}