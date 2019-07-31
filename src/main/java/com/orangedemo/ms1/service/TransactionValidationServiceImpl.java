package com.orangedemo.ms1.service;

import com.orangedemo.ms1.dto.TransactionDto;
import com.orangedemo.ms1.exceptions.TransactionNotValidException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionValidationServiceImpl implements TransactionValidationService {

    @Override
    public void validateTransaction(TransactionDto transactionDto) {
        boolean isValidTransaction = isCnpValid(transactionDto.getCnp()) && isIbanValid(transactionDto.getIban())
                && isNameValid(transactionDto.getName()) && isSumValid(transactionDto.getSum());

        if (!isValidTransaction) {
            throw new TransactionNotValidException();
        }
    }

    private boolean isNameValid(String name) {
        return name.matches("^[\\p{L}\\s'.-]+$");
    }

    private boolean isSumValid(BigDecimal sum) {
        return sum.toString().matches("^[-+]?[0-9]*\\.?[0-9]*$");
    }

    private boolean isIbanValid(String iban) {
        return iban.matches("^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,9}$");
    }

    private boolean isCnpValid(String cnp) {
        return cnp.matches("^\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b$");
    }
}
