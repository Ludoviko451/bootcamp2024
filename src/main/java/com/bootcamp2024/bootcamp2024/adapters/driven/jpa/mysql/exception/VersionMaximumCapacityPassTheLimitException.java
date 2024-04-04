package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception;

public class VersionMaximumCapacityPassTheLimitException extends RuntimeException{
    public VersionMaximumCapacityPassTheLimitException(int message) {
        super("" + message);
    }
}
