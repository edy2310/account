package com.edgar.account.Models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Entity(name = "ACCOUNT")
@SequenceGenerator(name="seq", initialValue = 1001)
public class Account {

    public Account() {
    }

    public Account(String firstName, String lastName, Integer pin, String SSN, BigInteger balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.SSN = SSN;
        this.balance = balance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PIN", nullable = false, precision = 4)
    private Integer pin;

    @Column(name = "SSN")
    private String SSN;

    @Column(name = "BALANCE")
    private BigInteger balance;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pin=" + pin +
                ", SSN='" + SSN + '\'' +
                ", balance=" + balance +
                '}';
    }
}
