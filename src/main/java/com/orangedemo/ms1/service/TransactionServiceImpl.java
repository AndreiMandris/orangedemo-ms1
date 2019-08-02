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

    @Autowired
    private TransactionValidatorService transactionValidatorService;

    @Override
    public void handleTransaction(TransactionDto transactionDto) {
        transactionValidatorService.validateTransaction(transactionDto);
        sendTransactionToQueue(transactionDto);
    }

    private void sendTransactionToQueue(TransactionDto transactionDto) {
        String transactionJson = gson.toJson(transactionDto);
        jmsTemplate.convertAndSend(queue, transactionJson);
    }
}
