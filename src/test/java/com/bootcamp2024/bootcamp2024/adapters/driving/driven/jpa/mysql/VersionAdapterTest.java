package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.VersionAdapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NotValidFieldForVersionException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VersionAdapterTest {
    @Mock
    private IVersionRepository versionRepository;
    @Mock
    private IVersionEntityMapper versionEntityMapper;

    private VersionAdapter versionAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        versionAdapter = new VersionAdapter(versionRepository, versionEntityMapper);
    }

    @Test
    void testSaveVersion_ValidVersion() {

        String startDate = "2024-04-05";
        String endDate = "2024-04-12";
        BootcampEntity bootcamp = new BootcampEntity(1L, "BootcampName", "BootcampDescription", null);
        VersionEntity versionEntity = new VersionEntity(1L, 30, LocalDate.now(), LocalDate.now().plusDays(7), bootcamp);

        // Configurar el comportamiento esperado para versionEntityMapper
        when(versionEntityMapper.toEntity(any(Version.class))).thenReturn(versionEntity);

        // Llamar al método a probar
        versionAdapter.saveVersion(new Version(1L, 30, startDate, endDate, new Bootcamp(1L, "BootcampName", "BootcampDescription", null)));

        // Verificar que versionRepository.save fue llamado una vez con el versionEntity correcto
        verify(versionRepository, times(1)).save(versionEntity);
    }

    @Test
    void testGetAllVersion_WithBootcampIds() {
        List<VersionEntity> versionEntityList = Collections.emptyList();
        Page<VersionEntity> versionPage = new PageImpl<>(versionEntityList);

        when(versionRepository.findAllByBootcampIds(anyList(), any(Pageable.class))).thenReturn(versionPage);

        List<Version> versionList = versionAdapter.getAllVersion(1, 10, "id", "asc", Arrays.asList(1L, 2L));

        // Verificar que versionRepository.findAllByBootcampIds fue llamado una vez con los IDs de bootcamp correctos
        verify(versionRepository, times(1)).findAllByBootcampIds(Arrays.asList(1L, 2L), PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "id")));

    }

    @Test
    void testGetAllVersion_WithoutBootcampIds() {
        List<VersionEntity> versionEntityList = Collections.emptyList();
        Page<VersionEntity> versionPage = new PageImpl<>(versionEntityList);

        when(versionRepository.findAll(any(Pageable.class))).thenReturn(versionPage);

        List<Version> versionList = versionAdapter.getAllVersion(1, 10, "id", "asc", null);

        // Verificar que versionRepository.findAll fue llamado una vez con la paginación correcta
        verify(versionRepository, times(1)).findAll(PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "id")));

    }

    @Test
    void testGetAllVersion_InvalidField() {
        assertThrows(NotValidFieldForVersionException.class,
                () -> versionAdapter.getAllVersion(1, 10, "invalidField", "asc", null));
    }

}
