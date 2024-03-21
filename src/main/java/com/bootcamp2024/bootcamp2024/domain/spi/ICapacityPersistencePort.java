package com.bootcamp2024.bootcamp2024.domain.spi;

import com.bootcamp2024.bootcamp2024.domain.model.Capacity;

import java.util.List;

public interface ICapacityPersistencePort {
    List<Capacity> getAllCapacity(Integer page, Integer size, String orderBy, int technologies);
    void saveCapacity(Capacity capacity, List<Long> technologyIds);
//    void addTechnologyToCapacity(Long capacityId, List<Long> technologyIds);
}
