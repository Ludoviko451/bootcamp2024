package com.bootcamp2024.bootcamp2024.domain.model;

public class Version {
    private final long id;


    private final int maximumCapacity;

    private final String startDate;

    private final String endDate;

    private final Bootcamp bootcamp;

    public Version(long id, int maximumCapacity, String startDate, String endDate, Bootcamp bootcamp) {
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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Bootcamp getBootcamp() {
        return bootcamp;
    }
}
