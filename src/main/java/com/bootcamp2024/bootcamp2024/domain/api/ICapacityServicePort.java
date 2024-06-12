package com.bootcamp2024.bootcamp2024.domain.api;

import com.bootcamp2024.bootcamp2024.domain.model.Capacity;

import java.util.List;

public interface ICapacityServicePort {

    List<Capacity> getAllCapacity(Integer page, Integer size, String orderBy, boolean technologies, String field);
    void saveCapacity(Capacity capacity);

    Capacity findCapacityByName(String name);
}