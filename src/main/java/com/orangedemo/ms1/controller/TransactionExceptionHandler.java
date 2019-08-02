package com.orangedemo.ms1.controller;

import com.orangedemo.ms1.exceptions.TransactionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(TransactionNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidTransaction(TransactionNotValidException e) {
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException() {
        return "Service has encountered an error.";
    }
}
