package com.productCatalougeService.productCatalougeService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {
    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<String> handleExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
