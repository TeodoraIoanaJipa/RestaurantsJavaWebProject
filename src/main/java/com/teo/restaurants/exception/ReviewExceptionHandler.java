package com.teo.restaurants.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReviewExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInvalidModel(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body("Ooopsy. Field '" + e.getFieldError().getField() +
                        "''s value = " + e.getFieldError().getRejectedValue() +" is not valid." +
                        " The value " + e.getFieldError().getDefaultMessage());
    }
}
