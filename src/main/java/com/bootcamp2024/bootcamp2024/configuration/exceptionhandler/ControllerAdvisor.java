package com.bootcamp2024.bootcamp2024.configuration.exceptionhandler;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.*;
import com.bootcamp2024.bootcamp2024.configuration.Constants;
import com.bootcamp2024.bootcamp2024.domain.exception.EmptyFieldException;
import com.bootcamp2024.bootcamp2024.domain.exception.NegativeNotAllowedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(NegativeNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> handleNegativeNotAllowedException(NegativeNotAllowedException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(PageAndSizeLessThanZeroException.class)
    public ResponseEntity<ExceptionResponse> handlePageAndSizeCantBeZeroException(PageAndSizeLessThanZeroException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.PAGE_SIZE_CANT_BE_LESS_THAN_ZERO, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }



    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ParameterNotValidForOrderbyException.class)
    public ResponseEntity<ExceptionResponse> handleParameterNotValidForOrderByException(ParameterNotValidForOrderbyException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                String.format("Parameter not valid: %s", exception.getMessage()),
                HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidJwtClaimFormat.class)
    public ResponseEntity<ExceptionResponse> handleInvalidJwtClaimFormat(InvalidJwtClaimFormat exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                String.format("Invalid JWT claim format: %s", exception.getMessage()),
                HttpStatus.UNAUTHORIZED.toString(), LocalDateTime.now()));
    }
}
