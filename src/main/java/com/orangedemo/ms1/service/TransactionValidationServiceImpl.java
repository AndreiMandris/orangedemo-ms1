package com.orangedemo.ms1.service;

import com.orangedemo.ms1.dto.TransactionDto;
import com.orangedemo.ms1.dto.TransactionType;
import com.orangedemo.ms1.exceptions.TransactionNotValidException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionValidationServiceImpl implements TransactionValidationService {

    @Override
    public void validateTransaction(TransactionDto transactionDto) {
        if (!isValidCnp(transactionDto.getCnp())) {
            throw new TransactionNotValidException("CNP is not valid!");
        } else if (!isValidIban(transactionDto.getIban())) {
            throw new TransactionNotValidException("IBAN is not valid!");
        } else if (!isValidName(transactionDto.getName())) {
            throw new TransactionNotValidException("Name is not valid!");
        } else if (!isValidSum(transactionDto.getSum())) {
            throw new TransactionNotValidException("Sum is not valid!");
        } else if (!isValidTransactionType(transactionDto.getType())) {
            throw new TransactionNotValidException("Transaction type is not valid!");
        }
    }

    private boolean isValidName(String name) {
        return name.matches("^[\\p{L}\\s'.-]+$");
    }

    private boolean isValidSum(BigDecimal sum) {
        return sum != null && sum.toString().matches("^[+]?[0-9]*\\.?[0-9]*$");
    }

    private boolean isValidIban(String iban) {
        return iban.matches("^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,9}$");
    }

    private boolean isValidCnp(String cnp) {
        return cnp.matches("^\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b$");
    }

    private boolean isValidTransactionType(TransactionType transactionType) {
        for (TransactionType type : TransactionType.values()) {
            if (type.equals(transactionType)) {
                return true;
            }
        }
        return false;
    }
}
