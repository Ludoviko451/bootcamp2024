package com.bootcamp2024.bootcamp2024.domain.api;



import com.bootcamp2024.bootcamp2024.domain.model.Technology;

import java.util.List;

public interface ITechnologyServicePort {

    void deleteTechnology(Long id);
    void saveTechnology(Technology technology);

    Technology updateTechnology(Technology technology);

    List<Technology> getAllTechnology(Integer page, Integer size, String sortBy, String field);

    Technology findTechnologyByName(String name);
}
