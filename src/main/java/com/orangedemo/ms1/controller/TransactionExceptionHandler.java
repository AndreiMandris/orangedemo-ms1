package com.orangedemo.ms1.controller;

import com.orangedemo.ms1.exceptions.TransactionNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionExceptionHandler {
    @ExceptionHandler
    public String handleInvalidTransaction(TransactionNotValidException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    public String handleException(Exception e) {
        return "Service has encountered an error.";
    }
}
