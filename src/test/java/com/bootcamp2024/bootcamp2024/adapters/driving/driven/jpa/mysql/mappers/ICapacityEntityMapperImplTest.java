package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapperImpl;
import org.junit.jupiter.api.Test;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;

class ICapacityEntityMapperImplTest {

    private ICapacityEntityMapperImpl mapper = new ICapacityEntityMapperImpl();



    @Test
    void testToModel() {

        CapacityEntity capacityEntity = new CapacityEntity();
        capacityEntity.setId(1L);
        capacityEntity.setName("Capacity 1");
        capacityEntity.setDescription("Description 1");
        TechnologyEntity technologyEntity = new TechnologyEntity();
        technologyEntity.setId(1L);
        technologyEntity.setName("Java");
        technologyEntity.setDescription("A programming language");
        capacityEntity.setTechnologyList(Arrays.asList(technologyEntity));


        Capacity capacity = mapper.toModel(capacityEntity);

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
        // Mock de Capacity
        Technology technology = new Technology(1L, "Java", "A programming language");
        Capacity capacity = new Capacity(1L, "Capacity 1", "Description 1", Arrays.asList(technology));


        CapacityEntity capacityEntity = mapper.toEntity(capacity);

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

        List<CapacityEntity> capacityEntities = Arrays.asList(capacityEntity);


        List<Capacity> capacities = mapper.toModelList(capacityEntities);


        assertEquals(capacityEntities.size(), capacities.size());
        Capacity capacity = capacities.get(0);
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
