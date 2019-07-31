package com.orangedemo.ms1.dto;

import java.io.Serializable;

public enum TransactionType implements Serializable {

    IBAN_TO_IBAN, IBAN_TO_WALLET, WALLET_TO_IBAN, WALLET_TO_WALLET;

    private static final long serialVersionUID = 42L;
}
