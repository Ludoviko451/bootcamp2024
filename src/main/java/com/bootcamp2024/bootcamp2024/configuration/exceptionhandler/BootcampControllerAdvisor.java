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
public class BootcampControllerAdvisor {

    @ExceptionHandler(CapacitySizeIsNotInTheLimitException.class)
    public ResponseEntity<ExceptionResponse> handleCapacitySizeIsNotInTheLimitException(CapacitySizeIsNotInTheLimitException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.CAPACITY_SIZE_IS_NOT_IN_THE_LIMIT, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CapacityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCapacityNotFoundException(CapacityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                String.format(Constants.CAPACITY_NOT_FOUND_MESSAGE, exception.getMessage()),
                HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(DuplicateCapacityException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateCapacityException(DuplicateCapacityException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.DUPLICATE_CAPACITY_EXCEPTION, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BootcampAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleBootcampAlreadyExistsException(BootcampAlreadyExistsException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.BOOTCAMP_ALREADY_EXISTS, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }


}
