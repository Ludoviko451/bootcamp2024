package com.bootcamp2024.bootcamp2024.domain.model;

import java.util.List;

public class Bootcamp {

    public Bootcamp(long id, String name, String description, List<Capacity> capacityList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacityList = capacityList;
    }

    private final long id;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Capacity> getCapacityList() {
        return capacityList;
    }

    private final String name;
    private final String description;
    private final List<Capacity> capacityList;

}
