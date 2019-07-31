package com.orangedemo.ms1.service;

import com.orangedemo.ms1.dto.TransactionDto;

public interface TransactionValidationService {
    void validateTransaction(TransactionDto transactionDto);
}
