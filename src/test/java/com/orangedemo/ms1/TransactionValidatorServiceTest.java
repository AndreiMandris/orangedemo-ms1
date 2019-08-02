package com.orangedemo.ms1;

import com.orangedemo.ms1.dto.TransactionDto;
import com.orangedemo.ms1.dto.TransactionType;
import com.orangedemo.ms1.exceptions.TransactionNotValidException;
import com.orangedemo.ms1.service.TransactionValidatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionValidatorServiceTest {

    @Autowired
    TransactionValidatorService transactionValidatorService;

    TransactionDto validTransaction;

    @Before
    public void prepareTransaction(){
        validTransaction = new TransactionDto();
        validTransaction.setCnp("1901223211419");
        validTransaction.setIban("RO09BCYP0000001234567890");
        validTransaction.setDesc("restaurant payment");
        validTransaction.setName("Florin Gheorghe");
        validTransaction.setType(TransactionType.IBAN_TO_IBAN.toString());
        validTransaction.setSum(new BigDecimal(400));
        transactionValidatorService.validateTransaction(validTransaction);
    }

    @Test(expected = TransactionNotValidException.class)
    public void transactionHasInvalidIban() {
        TransactionDto invalidTransaction = (TransactionDto) validTransaction.clone();
        invalidTransaction.setIban("232RO1321");
        transactionValidatorService.validateTransaction(invalidTransaction);
    }

    @Test(expected = TransactionNotValidException.class)
    public void transactionHasInvalidCnp() {
        TransactionDto invalidTransaction = (TransactionDto) validTransaction.clone();
        invalidTransaction.setCnp("2321321");
        transactionValidatorService.validateTransaction(invalidTransaction);
    }

    @Test(expected = TransactionNotValidException.class)
    public void transactionHasInvalidName() {
        TransactionDto invalidTransaction = (TransactionDto) validTransaction.clone();
        invalidTransaction.setName("@ndrei");
        transactionValidatorService.validateTransaction(invalidTransaction);
    }

    @Test(expected = TransactionNotValidException.class)
    public void transactionHasInvalidSum() {
        TransactionDto invalidTransaction = (TransactionDto) validTransaction.clone();
        invalidTransaction.setSum(null);
        transactionValidatorService.validateTransaction(invalidTransaction);
    }

    @Test(expected = TransactionNotValidException.class)
    public void transactionHasInvalidType() {
        TransactionDto invalidTransaction = (TransactionDto) validTransaction.clone();
        invalidTransaction.setType("IBAN_TO_ETC");
        transactionValidatorService.validateTransaction(invalidTransaction);
    }
}