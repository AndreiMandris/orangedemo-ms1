package com.orangedemo.ms1.service;

import com.orangedemo.ms1.dto.TransactionDto;
import com.orangedemo.ms1.dto.TransactionType;
import com.orangedemo.ms1.exceptions.TransactionNotValidException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionValidatorServiceImpl implements TransactionValidatorService {

    private static final String VALID_NAME_REGEX = "^[\\p{L}\\s'.-]+$";
    private static final String VALID_IBAN_REGEX = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,9}$";
    private static final String VALID_SUM_REGEX = "^[+]?[0-9]*\\.?[0-9]*$";
    private static final String VALID_CNP_REGEX = "^\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b$";

    @Override
    public void validateTransaction(TransactionDto transactionDto) {
        StringBuffer exceptionMessage = new StringBuffer();
        if (!isValidCnp(transactionDto.getCnp())) {
            exceptionMessage.append("CNP is not valid!");
        }
        if (!isValidIban(transactionDto.getIban())) {
            exceptionMessage.append("\nIBAN is not valid!");
        }
        if (!isValidName(transactionDto.getName())) {
            exceptionMessage.append("\nName is not valid!");
        }
        if (!isValidSum(transactionDto.getSum())) {
            exceptionMessage.append("\nSum is not valid!");
        }
        if (!isValidTransactionType(transactionDto.getType())) {
            exceptionMessage.append("\nTransaction type is not valid!");
        }
        if (!exceptionMessage.toString().isEmpty()) {
            throw new TransactionNotValidException(exceptionMessage.toString());
        }
    }

    private boolean isValidName(String name) {
        return name.matches(VALID_NAME_REGEX);
    }

    private boolean isValidSum(BigDecimal sum) {
        return sum != null && sum.toString().matches(VALID_SUM_REGEX);
    }

    private boolean isValidIban(String iban) {
        return iban.matches(VALID_IBAN_REGEX);
    }

    private boolean isValidCnp(String cnp) {
        return cnp.matches(VALID_CNP_REGEX);
    }

    private boolean isValidTransactionType(String transactionType) {
        for (TransactionType type : TransactionType.values()) {
            if (type.name().equals(transactionType)) {
                return true;
            }
        }
        return false;
    }
}
