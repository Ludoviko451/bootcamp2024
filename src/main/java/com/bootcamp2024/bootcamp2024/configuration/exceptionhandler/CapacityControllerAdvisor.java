package com.bootcamp2024.bootcamp2024.configuration.exceptionhandler;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.*;
import com.bootcamp2024.bootcamp2024.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class CapacityControllerAdvisor {

    @ExceptionHandler(TechnologySizeIsNotInTheLimitException.class)
    public ResponseEntity<ExceptionResponse> handleTechnologyIdsIsNotInTheLimitException(TechnologySizeIsNotInTheLimitException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.TECHNOLOGY_PASS_THE_LIMIT_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicateTechnologyException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateTechnologyIdsException(DuplicateTechnologyException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.DUPLICATE_IDS_TECHNOLOGY_IDS_EXCEPTION, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(TechnologyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTechnologyNotFoundException(TechnologyNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                String.format(Constants.TECHNOLOGY_NOT_FOUND_MESSAGE, exception.getMessage()),
                HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(CapacityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCapacityAlreadyExistsException(CapacityAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                String.format(Constants.CAPACITY_ALREADY_EXISTS, exception.getMessage()),
                HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }
}
