package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception;

public class CapacityNotFoundException extends RuntimeException {


    public CapacityNotFoundException(String capacityName)
    {
        super(capacityName);
    }
}
