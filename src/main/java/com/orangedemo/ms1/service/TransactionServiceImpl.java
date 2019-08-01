package com.orangedemo.ms1.service;

import com.google.gson.Gson;
import com.orangedemo.ms1.dto.TransactionDto;
import com.orangedemo.ms1.dto.TransactionType;
import com.orangedemo.ms1.exceptions.TransactionNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import java.math.BigDecimal;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Gson gson;

    @Override
    public void handleTransaction(TransactionDto transactionDto) {
        validateTransaction(transactionDto);
        sendTransactionToQueue(transactionDto);
    }

    private void sendTransactionToQueue(TransactionDto transactionDto) {
        String transactionJson = gson.toJson(transactionDto);
        jmsTemplate.convertAndSend(queue, transactionJson);
    }

    private void validateTransaction(TransactionDto transactionDto) {
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

    private boolean isValidTransactionType(String transactionType) {
        for (TransactionType type : TransactionType.values()) {
            if (type.name().equals(transactionType)) {
                return true;
            }
        }
        return false;
    }
}
