package com.bootcamp2024.bootcamp2024.domain.api;

import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampServicePort {

    void saveBootcamp(Bootcamp bootcamp);

    List<Bootcamp> getAllBootcamp(Integer page, Integer size, String orderBy, boolean capacities, String field);

    Bootcamp findBootcampById(Long id);
}
