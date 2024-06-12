package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IVersionEntityMapperImpl;
import org.junit.jupiter.api.Test;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Version;

class IVersionEntityMapperImplTest {

    private IVersionEntityMapperImpl mapper = new IVersionEntityMapperImpl();


    @Test
    void testToModel() {
        // Mock de VersionEntity
        VersionEntity versionEntity = new VersionEntity();
        versionEntity.setId(1L);
        versionEntity.setMaximumCapacity(100);
        versionEntity.setStartDate(LocalDate.of(2024, 6, 1));
        versionEntity.setEndDate(LocalDate.of(2024, 6, 30));
        BootcampEntity bootcampEntity = new BootcampEntity();
        bootcampEntity.setId(1L);
        bootcampEntity.setName("Bootcamp 1");
        bootcampEntity.setDescription("Description 1");
        versionEntity.setBootcamp(bootcampEntity);

        Version version = mapper.toModel(versionEntity);

        assertEquals(versionEntity.getId(), version.getId());
        assertEquals(versionEntity.getMaximumCapacity(), version.getMaximumCapacity());
        assertEquals("2024-06-01", version.getStartDate());
        assertEquals("2024-06-30", version.getEndDate());
        Bootcamp bootcamp = version.getBootcamp();
        assertEquals(bootcampEntity.getId(), bootcamp.getId());
        assertEquals(bootcampEntity.getName(), bootcamp.getName());
        assertEquals(bootcampEntity.getDescription(), bootcamp.getDescription());
    }

    @Test
    void testToEntity() {

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description 1", null);
        Version version = new Version(1L, 100, "2024-06-01", "2024-06-30", bootcamp);

        VersionEntity versionEntity = mapper.toEntity(version);

        assertEquals(version.getId(), versionEntity.getId());
        assertEquals(version.getMaximumCapacity(), versionEntity.getMaximumCapacity());
        assertEquals(LocalDate.of(2024, 6, 1), versionEntity.getStartDate());
        assertEquals(LocalDate.of(2024, 6, 30), versionEntity.getEndDate());
        BootcampEntity bootcampEntity = versionEntity.getBootcamp();
        assertEquals(bootcamp.getId(), bootcampEntity.getId());
        assertEquals(bootcamp.getName(), bootcampEntity.getName());
        assertEquals(bootcamp.getDescription(), bootcampEntity.getDescription());
    }

    @Test
    void testToModelList() {

        BootcampEntity bootcampEntity = new BootcampEntity();
        bootcampEntity.setId(1L);
        bootcampEntity.setName("Bootcamp 1");
        bootcampEntity.setDescription("Description 1");
        VersionEntity versionEntity = new VersionEntity();
        versionEntity.setId(1L);
        versionEntity.setMaximumCapacity(100);
        versionEntity.setStartDate(LocalDate.of(2024, 6, 1));
        versionEntity.setEndDate(LocalDate.of(2024, 6, 30));
        versionEntity.setBootcamp(bootcampEntity);

        List<VersionEntity> versionEntities = Arrays.asList(versionEntity);

        List<Version> versions = mapper.toModelList(versionEntities);


        assertEquals(versionEntities.size(), versions.size());
        Version version = versions.get(0);
        assertEquals(versionEntity.getId(), version.getId());
        assertEquals(versionEntity.getMaximumCapacity(), version.getMaximumCapacity());
        assertEquals("2024-06-01", version.getStartDate());
        assertEquals("2024-06-30", version.getEndDate());
        Bootcamp bootcamp = version.getBootcamp();
        assertEquals(bootcampEntity.getId(), bootcamp.getId());
        assertEquals(bootcampEntity.getName(), bootcamp.getName());
        assertEquals(bootcampEntity.getDescription(), bootcamp.getDescription());
    }
}
