package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;

class IBootcampEntityMapperImplTest {

    private IBootcampEntityMapperImpl mapper = new IBootcampEntityMapperImpl();


    @Test
    void testToModel() {

        BootcampEntity bootcampEntity = new BootcampEntity();
        bootcampEntity.setId(1L);
        bootcampEntity.setName("Bootcamp 1");
        bootcampEntity.setDescription("Description 1");
        TechnologyEntity technologyEntity = new TechnologyEntity();
        technologyEntity.setId(1L);
        technologyEntity.setName("Java");
        technologyEntity.setDescription("A programming language");
        CapacityEntity capacityEntity = new CapacityEntity();
        capacityEntity.setId(1L);
        capacityEntity.setName("Capacity 1");
        capacityEntity.setDescription("Description 1");
        capacityEntity.setTechnologyList(List.of(technologyEntity));
        bootcampEntity.setCapacityList(List.of(capacityEntity));


        Bootcamp bootcamp = mapper.toModel(bootcampEntity);


        assertEquals(bootcampEntity.getId(), bootcamp.getId());
        assertEquals(bootcampEntity.getName(), bootcamp.getName());
        assertEquals(bootcampEntity.getDescription(), bootcamp.getDescription());
        assertEquals(1, bootcamp.getCapacityList().size());
        Capacity capacity = bootcamp.getCapacityList().get(0);
        assertEquals(capacityEntity.getId(), capacity.getId());
        assertEquals(capacityEntity.getName(), capacity.getName());
        assertEquals(capacityEntity.getDescription(), capacity.getDescription());
        assertEquals(1, capacity.getTechnologyList().size());
        Technology technology = capacity.getTechnologyList().get(0);
        assertEquals(technologyEntity.getId(), technology.getId());
        assertEquals(technologyEntity.getName(), technology.getName());
        assertEquals(technologyEntity.getDescription(), technology.getDescription());
    }

    @Test
    void testToEntity() {

        Technology technology = new Technology(1L, "Java", "A programming language");
        Capacity capacity = new Capacity(1L, "Capacity 1", "Description 1", Arrays.asList(technology));
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description 1", Arrays.asList(capacity));


        BootcampEntity bootcampEntity = mapper.toEntity(bootcamp);


        assertEquals(bootcamp.getId(), bootcampEntity.getId());
        assertEquals(bootcamp.getName(), bootcampEntity.getName());
        assertEquals(bootcamp.getDescription(), bootcampEntity.getDescription());
        assertEquals(1, bootcampEntity.getCapacityList().size());
        CapacityEntity capacityEntity = bootcampEntity.getCapacityList().get(0);
        assertEquals(capacity.getId(), capacityEntity.getId());
        assertEquals(capacity.getName(), capacityEntity.getName());
        assertEquals(capacity.getDescription(), capacityEntity.getDescription());
        assertEquals(1, capacityEntity.getTechnologyList().size());
        TechnologyEntity technologyEntity = capacityEntity.getTechnologyList().get(0);
        assertEquals(technology.getId(), technologyEntity.getId());
        assertEquals(technology.getName(), technologyEntity.getName());
        assertEquals(technology.getDescription(), technologyEntity.getDescription());
    }

    @Test
    void testToModelList() {

        TechnologyEntity technologyEntity = new TechnologyEntity();
        technologyEntity.setId(1L);
        technologyEntity.setName("Java");
        technologyEntity.setDescription("A programming language");
        CapacityEntity capacityEntity = new CapacityEntity();
        capacityEntity.setId(1L);
        capacityEntity.setName("Capacity 1");
        capacityEntity.setDescription("Description 1");
        capacityEntity.setTechnologyList(Arrays.asList(technologyEntity));
        BootcampEntity bootcampEntity = new BootcampEntity();
        bootcampEntity.setId(1L);
        bootcampEntity.setName("Bootcamp 1");
        bootcampEntity.setDescription("Description 1");
        bootcampEntity.setCapacityList(Arrays.asList(capacityEntity));

        List<BootcampEntity> bootcampEntities = Arrays.asList(bootcampEntity);


        List<Bootcamp> bootcamps = mapper.toModelList(bootcampEntities);


        assertEquals(bootcampEntities.size(), bootcamps.size());
        Bootcamp bootcamp = bootcamps.get(0);
        assertEquals(bootcampEntity.getId(), bootcamp.getId());
        assertEquals(bootcampEntity.getName(), bootcamp.getName());
        assertEquals(bootcampEntity.getDescription(), bootcamp.getDescription());
        assertEquals(1, bootcamp.getCapacityList().size());
        Capacity capacity = bootcamp.getCapacityList().get(0);
        assertEquals(capacityEntity.getId(), capacity.getId());
        assertEquals(capacityEntity.getName(), capacity.getName());
        assertEquals(capacityEntity.getDescription(), capacity.getDescription());
        assertEquals(1, capacity.getTechnologyList().size());
        Technology technology = capacity.getTechnologyList().get(0);
        assertEquals(technologyEntity.getId(), technology.getId());
        assertEquals(technologyEntity.getName(), technology.getName());
        assertEquals(technologyEntity.getDescription(), technology.getDescription());
    }
}
