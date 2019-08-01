package com.orangedemo.ms1.controller;

import com.orangedemo.ms1.exceptions.TransactionNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(TransactionNotValidException.class)
    public String handleInvalidTransaction(HttpServletResponse httpServletResponse, TransactionNotValidException e) {
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return "Service has encountered an error.";
    }
}
