package com.edgar.account.Console;

import com.edgar.account.Models.Account;
import com.edgar.account.Factories.RestTemplateFactory;
import com.edgar.account.Factories.ScannerFactory;
import com.edgar.account.Util.URIs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.util.Scanner;

public class Register {

    public static void showRegisterMenu(){
        Scanner sc = ScannerFactory.getScanner();
        String firstName, lastName, ssn;
        Integer pin;

        System.out.println("--- Register new account. ---");
        System.out.println("* Mandatory");

        System.out.print("*First name: ");
        firstName = sc.nextLine();

        System.out.print("*Last name: ");
        lastName = sc.nextLine();

        System.out.print("SSN: ");
        ssn = sc.nextLine();

        do{
            System.out.print("*Pin (4 digits): ");
            pin = sc.nextInt();
            if(pin < 1000 || pin > 9999)
                System.out.println("Please enter a 4 digit pin.");
        }while(pin < 1000 || pin > 9999);

        Account createdAccount = createAccount(new Account(firstName, lastName, pin, ssn, BigInteger.ZERO));
        if(createdAccount != null){
            System.out.println("Account created!");
            System.out.println("Your account number is: " + createdAccount.getId());
            System.out.println("Your PIN is: " + createdAccount.getPin());
        }
        else
            System.out.println("A problem occurred while creating the account");

    }

    private static Account createAccount(Account newAccount){
        RestTemplate rt = RestTemplateFactory.getRestTemplate();
        try{
            URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS);
            ResponseEntity postAccount = rt.postForEntity(uri, newAccount, Account.class);
            return (Account) postAccount.getBody();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
