package com.orangedemo.ms1.service;

import com.orangedemo.ms1.dto.TransactionDto;

public interface TransactionService {
    void handleTransaction(TransactionDto transactionDto);
}
