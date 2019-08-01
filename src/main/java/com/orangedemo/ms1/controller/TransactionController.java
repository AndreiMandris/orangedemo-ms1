package com.orangedemo.ms1.controller;

import com.google.gson.Gson;
import com.orangedemo.ms1.dto.TransactionDto;
import com.orangedemo.ms1.service.TransactionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionValidationService transactionValidationService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Gson gson;

    @PostMapping("/send")
    public ResponseEntity<TransactionDto> submitTransaction(@RequestBody TransactionDto transactionDto){
        transactionValidationService.validateTransaction(transactionDto);
        String transactionJson = gson.toJson(transactionDto);
        jmsTemplate.convertAndSend(queue, transactionJson);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
