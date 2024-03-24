package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception;

public class TechnologyNotFoundException extends RuntimeException {

    public TechnologyNotFoundException(String technologyName)
    {
        super(technologyName);
    }
}
