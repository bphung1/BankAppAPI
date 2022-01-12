package com.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction {

        private int transactionId;
        private int userId;
        private BigDecimal transactionAmount;
        private int transactionfrom;
        private int transactionto;
        private Timestamp transactionTimeStamp;

    public Transaction() {
    }


    public Transaction(int transactionId, int userId, BigDecimal transactionAmount, int transactionfrom, int transactionto, Timestamp transactionTimeStamp) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.transactionAmount = transactionAmount;
        this.transactionfrom = transactionfrom;
        this.transactionto = transactionto;
        this.transactionTimeStamp = transactionTimeStamp;
    }

    public Timestamp getTransactionTimeStamp() {
        return transactionTimeStamp;
    }

    public void setTransactionTimeStamp(Timestamp transactionTimeStamp) {
        this.transactionTimeStamp = transactionTimeStamp;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getTransactionfrom() {
        return transactionfrom;
    }

    public void setTransactionfrom(int transactionfrom) {
        this.transactionfrom = transactionfrom;
    }

    public int getTransactionto() {
        return transactionto;
    }

    public void setTransactionto(int transactionto) {
        this.transactionto = transactionto;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", userId=" + userId +
                ", transactionAmount=" + transactionAmount +
                ", transactionfrom=" + transactionfrom +
                ", transactionto=" + transactionto +
                '}';
    }
}
