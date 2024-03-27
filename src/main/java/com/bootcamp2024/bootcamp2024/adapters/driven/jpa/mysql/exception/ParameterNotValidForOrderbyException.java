package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception;

public class ParameterNotValidForOrderbyException extends  RuntimeException {
    public ParameterNotValidForOrderbyException(String parameterNotValid) {
        super(parameterNotValid);
    }
}
