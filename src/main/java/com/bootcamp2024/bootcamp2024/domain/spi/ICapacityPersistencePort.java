package com.bootcamp2024.bootcamp2024.domain.spi;

import com.bootcamp2024.bootcamp2024.domain.model.Capacity;

import java.util.List;
import java.util.Optional;

public interface ICapacityPersistencePort {
    List<Capacity> getAllCapacity(Integer page, Integer size, String orderBy, boolean technologies, String field);
    void saveCapacity(Capacity capacity);
    Optional<Capacity> findCapacityByName(String name);
}
