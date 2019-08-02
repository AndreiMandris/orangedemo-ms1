package com.orangedemo.ms1.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private String iban;
    private String cnp;
    private String name;
    private String desc;
    private BigDecimal sum;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "type=" + type +
                ", iban='" + iban + '\'' +
                ", cnp='" + cnp + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", sum=" + sum +
                '}';
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.cnp = this.cnp;
            transactionDto.iban = this.iban;
            transactionDto.desc = this.desc;
            transactionDto.type = this.type;
            transactionDto.name = this.name;
            transactionDto.sum = this.sum;
            return transactionDto;
        }
    }
}

