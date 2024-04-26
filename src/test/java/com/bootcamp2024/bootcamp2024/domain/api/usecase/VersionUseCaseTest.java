package com.bootcamp2024.bootcamp2024.domain.api.usecase;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.*;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import com.bootcamp2024.bootcamp2024.domain.spi.IVersionPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VersionUseCaseTest {

    @Mock
    private IVersionPersistencePort versionPersistencePort;

    @Mock
    private IBootcampPersistencePort bootcampPersistencePort;

    private VersionUseCase versionUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        versionUseCase = new VersionUseCase(versionPersistencePort, bootcampPersistencePort);
    }

    @Test
    void testSaveVersion_ValidVersion() {
        List<Capacity> capacityList = Collections.emptyList();
        String startDate = LocalDate.now().plusDays(1).toString();;
        String endDate = LocalDate.now().plusDays(6).toString();;

        Bootcamp bootcamp = new Bootcamp(1L, "BootcampName", "BootcampDescription", capacityList);
        Version version = new Version(1L, 30, startDate, endDate, bootcamp);

        // Configurar el comportamiento esperado para bootcampPersistencePort
        when(bootcampPersistencePort.findBootcampById(bootcamp.getId())).thenReturn(bootcamp);

        // Llamar al método a probar
        versionUseCase.saveVersion(version);

        // Verificar que versionPersistencePort.saveVersion fue llamado una vez
        verify(versionPersistencePort, times(1)).saveVersion(version);
    }

    @Test
    void testExistBootcamp_BootcampNotFound() {
        when(bootcampPersistencePort.findBootcampByName(anyString())).thenReturn(Optional.empty());

        assertThrows(BootcampNotFoundException.class, () -> versionUseCase.existBootcamp( 1L));
    }

    @Test
    void testExistBootcamp_BootcampFound() {
        Bootcamp bootcamp = new Bootcamp(1L, "BootcampName", "BootcampDescription", Collections.emptyList());
        when(bootcampPersistencePort.findBootcampById(bootcamp.getId())).thenReturn(bootcamp);

        versionUseCase.existBootcamp( 1L);

        verify(bootcampPersistencePort, times(1)).findBootcampById(bootcamp.getId());
    }

    @Test
    void testCheckBootcamp_BootcampNotFound() {
        List<Long> bootcampIds = List.of(1L, 2l, 3L);
        assertThrows(BootcampNotFoundException.class, () -> versionUseCase.checkBootcamps(bootcampIds));
    }

    @Test
    void testCheckBootcamp_BootcampFound() {
        List<Long> bootcampIds = List.of(1L, 2l, 3L);
        when(bootcampPersistencePort.findBootcampById(1L)).thenReturn(new Bootcamp(1L, "BootcampName", "BootcampDescription", Collections.emptyList()));
        when(bootcampPersistencePort.findBootcampById(2L)).thenReturn(new Bootcamp(2L, "BootcampName", "BootcampDescription", Collections.emptyList()));
        when(bootcampPersistencePort.findBootcampById(3L)).thenReturn(new Bootcamp(3L, "BootcampName", "BootcampDescription", Collections.emptyList()));
        versionUseCase.checkBootcamps(bootcampIds);
    }

    @Test
    void testGetAllVersion_ValidInput() {
        List<Long> bootcampIds = List.of(1L);
        when(bootcampPersistencePort.findBootcampById(1L)).thenReturn(new Bootcamp(1L, "BootcampName", "BootcampDescription", Collections.emptyList()));
        when(versionPersistencePort.getAllVersion(1, 10, "id", "asc", bootcampIds))
                .thenReturn(Collections.emptyList());
        List<Version> versions = versionUseCase.getAllVersion(1, 10, "id", "asc", bootcampIds);
        assertNotNull(versions);
        assertEquals(0, versions.size());
    }

    @Test
    void testGetAllVersion_NegativePageValue() {
        List<Long> bootcampIds = List.of(1L, 2L, 3L);
        assertThrows(PageAndSizeLessThanZeroException.class,
                () -> versionUseCase.getAllVersion(-1, 10, "id", "asc", bootcampIds));
    }

    @Test
    void testGetAllVersion_NegativeSizeValue() {
        List<Long> bootcampIds = List.of(1L, 2L, 3L);
        assertThrows(PageAndSizeLessThanZeroException.class,
                () -> versionUseCase.getAllVersion(1, -10, "id", "asc", bootcampIds));
    }

    @Test
    void testSaveVersion_InvalidMaxCapacity() {
        List<Capacity> capacityList = Collections.emptyList();
        String startDate = "2024-04-05";
        String endDate = "2024-04-12";

        Bootcamp bootcamp = new Bootcamp(1L, "BootcampName", "BootcampDescription", capacityList);
        Version version = new Version(1L, -10, startDate, endDate, bootcamp);

        assertThrows(VersionMaximumCapacityPassTheLimitException.class,
                () -> versionUseCase.saveVersion(version));
    }

    @Test
    void testSaveVersion_EndDateBeforeStartDate() {
        String startDate = "2024-04-05";
        String endDate = "2024-03-12";

        List<Capacity> capacityList = Collections.emptyList();
        Bootcamp bootcamp = new Bootcamp(1L, "BootcampName", "BootcampDescription", capacityList);
        Version version = new Version(1L, 30, startDate, endDate, bootcamp);

        assertThrows(VersionEndDateIsBeforeStartDateException.class,
                () -> versionUseCase.saveVersion(version));
    }

    @Test
    void testSaveVersion_StartDateBeforeToday() {
        String startDate = "2024-03-20";
        String endDate = "2024-03-25";

        List<Capacity> capacityList = Collections.emptyList();
        Bootcamp bootcamp = new Bootcamp(1L, "BootcampName", "BootcampDescription", capacityList);
        Version version = new Version(1L, 30, startDate, endDate, bootcamp);

        assertThrows(DateVersionBeforeTodayException.class,
                () -> versionUseCase.saveVersion(version));
    }


    @Test
    void testSaveVersion_BootcampNotFound() {

        String startDate = LocalDate.now().plusDays(1).toString();
        String endDate = "2024-05-05";
        List<Capacity> capacityList = Collections.emptyList();
        Bootcamp bootcamp = new Bootcamp(1L, "BootcampName", "BootcampDescription", capacityList);
        Version version = new Version(1L, 30, startDate, endDate, bootcamp);

        when(bootcampPersistencePort.findBootcampByName("BootcampName")).thenReturn(Optional.empty());

        assertThrows(BootcampNotFoundException.class,
                () -> versionUseCase.saveVersion(version));
    }
}