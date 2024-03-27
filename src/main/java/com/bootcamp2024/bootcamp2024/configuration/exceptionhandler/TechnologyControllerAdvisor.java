package com.bootcamp2024.bootcamp2024.configuration.exceptionhandler;


import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.DuplicateTechnologyException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyNotFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologySizeIsNotInTheLimitException;
import com.bootcamp2024.bootcamp2024.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class TechnologyControllerAdvisor {

    @ExceptionHandler(TechnologyAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleTechnologyAlreadyExistsException(TechnologyAlreadyExistsException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
}
