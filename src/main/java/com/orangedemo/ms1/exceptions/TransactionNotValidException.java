package com.orangedemo.ms1.exceptions;

public class TransactionNotValidException extends RuntimeException {

    public TransactionNotValidException(String message) {
        super(message);
    }

    public TransactionNotValidException() {
    }
}
