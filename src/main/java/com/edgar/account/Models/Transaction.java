package com.edgar.account.Models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import com.edgar.account.Util.Enum.TransactionType;

@Entity(name = "TRANSACTION")
public class Transaction {

    public Transaction() {
    }

    public Transaction(Date date, TransactionType transactionType, BigInteger amount, String description) {
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "DATE")
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TYPE")
    private TransactionType transactionType;

    @Column(name = "AMOUNT")
    private BigInteger amount;

    @Column(name = "DESCRIPTION")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
