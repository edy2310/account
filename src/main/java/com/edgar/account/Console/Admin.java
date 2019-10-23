package com.edgar.account.Console;

import com.edgar.account.Models.Account;
import com.edgar.account.Models.Transaction;
import com.edgar.account.Util.Enum.TransactionType;
import com.edgar.account.Factories.RestTemplateFactory;
import com.edgar.account.Factories.ScannerFactory;
import com.edgar.account.Util.URIs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.util.Scanner;

public class Admin {
    private static Scanner sc = ScannerFactory.getScanner();
    private static RestTemplate rt = RestTemplateFactory.getRestTemplate();

    public static void showAdmin(Account account){

        int decision;

        System.out.println("\n--- Welcome to your admin panel---");
        System.out.println("Menu: ");
        System.out.println("1.- Make a Deposit");
        System.out.println("2.- Make a withdrawal");
        System.out.println("3.- Consult balance");
        System.out.println("0.- Close your session");

        do{
            System.out.print("Decision: ");
            decision = sc.nextInt();
            switch (decision){
                case 0:
                    System.out.println("Log out.");
                    break;
                case 1:
                    makeDeposit(account);
                    break;
                case 2:
                    makeWithdrawal(account);
                    break;
                case 3:
                    consultBalance(account);
                    break;
                default:
                    System.out.println("\nOption not available. Please choose another option.");
                    decision = -1;
                    break;
            }
        }while(decision == -1);

    }

    private static void makeDeposit(Account account){
        System.out.print("Amount to deposit: ");
        int amount = sc.nextInt();
        account.setBalance(account.getBalance().add(BigInteger.valueOf(amount)));
        updateAccount(account);
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, BigInteger.valueOf(amount), "Deposit done from system", account);
        createTransaction(transaction);
        showAdmin(account);
    }

    private static void makeWithdrawal(Account account){
        System.out.print("Amount to withdraw: ");
        int amount = sc.nextInt();
        if(amount == -1)
            showAdmin(account);

        if(account.getBalance().subtract(BigInteger.valueOf(amount)).compareTo(BigInteger.ZERO) == -1){
            System.out.println("Not enough funds. Try only with available balance.");
            System.out.println("Enter '-1' to exit");
            makeWithdrawal(account);
        }
        else{
            account.setBalance(account.getBalance().subtract(BigInteger.valueOf(amount)));
            updateAccount(account);
            Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, BigInteger.valueOf(amount).multiply(BigInteger.valueOf(-1)), "Withdraw done from system", account);
            createTransaction(transaction);
            showAdmin(account);
        }

    }

    private static void consultBalance(Account account){
        try{
            URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS + URIs.BALANCE + account.getId());
            BigInteger currentBalance = rt.getForObject(uri, BigInteger.class);
            System.out.println("Your current balance is: " + currentBalance);
            showAdmin(account);
        }catch (Exception e){
            System.out.println(e);
            showAdmin(account);
        }
    }

    private static void updateAccount(Account account){
        try{
            URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS + account.getId());
            rt.put(uri, account);
            System.out.println("Successful process");
        }catch (Exception e){
            System.out.println("Not successful process");
            System.out.println(e);
            showAdmin(account);
        }
    }

    private static void createTransaction(Transaction transaction){
        try{
            URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.TRANSACTIONS);
            ResponseEntity transactionEntity =  rt.postForEntity(uri, transaction, Transaction.class);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
