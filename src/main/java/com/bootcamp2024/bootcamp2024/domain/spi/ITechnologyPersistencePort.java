package com.bootcamp2024.bootcamp2024.domain.spi;


import com.bootcamp2024.bootcamp2024.domain.model.Technology;

import java.util.List;
import java.util.Optional;


public interface ITechnologyPersistencePort {

    void saveTechnology(Technology technology);

    void deleteTechnology(Long id);

    Optional<Technology> findByName(String name);
    List<Technology> getAllTechnology(Integer page, Integer size, String sortBy, String field);

    Technology updateTechnology(Technology technology);

}
