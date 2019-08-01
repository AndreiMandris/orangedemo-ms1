package com.orangedemo.ms1.controller;

import com.orangedemo.ms1.dto.TransactionDto;
import com.orangedemo.ms1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<TransactionDto> submitTransaction(@RequestBody TransactionDto transactionDto) {
        transactionService.handleTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
