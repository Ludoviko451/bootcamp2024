package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception;

import java.time.LocalDate;

public class DateVersionBeforeTodayException extends RuntimeException{

    public DateVersionBeforeTodayException(LocalDate message) {
        super("" + message);
    }
}
