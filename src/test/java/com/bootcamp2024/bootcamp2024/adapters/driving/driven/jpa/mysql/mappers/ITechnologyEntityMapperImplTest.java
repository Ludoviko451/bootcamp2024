package com.bootcamp2024.bootcamp2024.adapters.driving.driven.jpa.mysql.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapperImpl;
import org.junit.jupiter.api.Test;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;

class ITechnologyEntityMapperImplTest {

    private ITechnologyEntityMapperImpl mapper = new ITechnologyEntityMapperImpl();


    @Test
    void testToModel() {

        TechnologyEntity technologyEntity = new TechnologyEntity();
        technologyEntity.setId(1L);
        technologyEntity.setName("Java");
        technologyEntity.setDescription("A programming language");


        Technology technology = mapper.toModel(technologyEntity);


        assertEquals(technologyEntity.getId(), technology.getId());
        assertEquals(technologyEntity.getName(), technology.getName());
        assertEquals(technologyEntity.getDescription(), technology.getDescription());
    }

    @Test
    void testToEntity() {

        Technology technology = new Technology(1L, "Java", "A programming language");


        TechnologyEntity technologyEntity = mapper.toEntity(technology);


        assertEquals(technology.getId(), technologyEntity.getId());
        assertEquals(technology.getName(), technologyEntity.getName());
        assertEquals(technology.getDescription(), technologyEntity.getDescription());
    }

    @Test
    void testToModelList() {

        List<TechnologyEntity> technologyEntities = Arrays.asList(
                new TechnologyEntity(1L, "Java", "A programming language"),
                new TechnologyEntity(2L, "Python", "Another programming language")
        );

        List<Technology> technologies = mapper.toModelList(technologyEntities);


        assertEquals(technologyEntities.size(), technologies.size());
        for (int i = 0; i < technologyEntities.size(); i++) {
            assertEquals(technologyEntities.get(i).getId(), technologies.get(i).getId());
            assertEquals(technologyEntities.get(i).getName(), technologies.get(i).getName());
            assertEquals(technologyEntities.get(i).getDescription(), technologies.get(i).getDescription());
        }
    }
}
