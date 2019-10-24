package com.edgar.account.Models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import com.edgar.account.Util.Enum.TransactionType;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "TRANSACTION")
public class Transaction {

    public Transaction() {
    }

    public Transaction(TransactionType transactionType, BigInteger amount, String description, Account account) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.account = account;
    }

    public Transaction(TransactionType transactionType, BigInteger amount, String description){
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "DATE")
    @CreationTimestamp
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private TransactionType transactionType;

    @Column(name = "AMOUNT", nullable = false)
    private BigInteger amount;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
