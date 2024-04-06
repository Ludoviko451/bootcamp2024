package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception;

public class BootcampNotFoundException extends RuntimeException{
    public BootcampNotFoundException(String message) {
        super(message);
    }

    public BootcampNotFoundException(Long message) {
        super( "" + message);
    }
}
