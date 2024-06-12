package com.bootcamp2024.bootcamp2024.domain.spi;

import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import java.util.List;
import java.util.Optional;

public interface IBootcampPersistencePort {

    void saveBootcamp(Bootcamp bootcamp);

    List<Bootcamp> getAllBootcamp(Integer page, Integer size, String orderBy, boolean capacities, String field);

    Optional<Bootcamp> findBootcampByName(String name);

    Bootcamp findBootcampById(Long id);
}