package com.bootcamp2024.bootcamp2024.configuration.exceptionhandler;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.*;
import com.bootcamp2024.bootcamp2024.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@ControllerAdvice
public class VersionControllerAdvisor {

    @ExceptionHandler(VersionEndDateIsBeforeStartDateException.class)
    public ResponseEntity<ExceptionResponse> handleVersionStartDateIsBeforeEndDateException(VersionEndDateIsBeforeStartDateException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.VERSION_STARTDATE_IS_BEFORE_ENDDATE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotValidFieldForVersionException.class)
    public ResponseEntity<ExceptionResponse> handleNotValidFieldForVersionException(NotValidFieldForVersionException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.VERSION_NOT_VALID_FIELD_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(VersionMaximumCapacityPassTheLimitException.class)
    public ResponseEntity<ExceptionResponse> handleVersionMaximumCapacityPassTheLimitException(VersionMaximumCapacityPassTheLimitException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.VERSION_MAXIMUM_CAPACITY_PASS_THE_LIMIT, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DateVersionBeforeTodayException.class)
    public ResponseEntity<ExceptionResponse> handleDateVersionBeforeTodayException(DateVersionBeforeTodayException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.VERSION_DATE_BEFORE_TODAY, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(VersionDateParseException.class)
    public ResponseEntity<ExceptionResponse> handleVersionDateParseException(VersionDateParseException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.VERSION_DATE_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
}
