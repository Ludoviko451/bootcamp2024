package com.bootcamp2024.bootcamp2024.domain.model;

import java.time.LocalDate;

public class Version {
    private final long id;


    private final int maximumCapacity;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final Bootcamp bootcamp;

    public Version(long id, int maximumCapacity, LocalDate startDate, LocalDate endDate, Bootcamp bootcamp) {
        this.id = id;
        this.maximumCapacity = maximumCapacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bootcamp = bootcamp;
    }

    public long getId() {
        return id;
    }


    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Bootcamp getBootcamp() {
        return bootcamp;
    }
}
