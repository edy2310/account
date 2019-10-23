package com.edgar.account.Console;

import com.edgar.account.Models.Account;
import com.edgar.account.Factories.RestTemplateFactory;
import com.edgar.account.Factories.ScannerFactory;
import com.edgar.account.Util.URIs;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Scanner;

public class Login {

    public static void showLoginMenu(){
        Scanner sc = ScannerFactory.getScanner();
        Integer account, pin;

        System.out.println("\n--- Login into account. ---");
        System.out.print("Account number: ");
        account = sc.nextInt();

        System.out.print("PIN code: ");
        pin = sc.nextInt();

        Account accountLogged = tryLogin(account, pin);
        if (accountLogged != null)
            Admin.showAdmin(accountLogged);
        else
            System.out.println("A problem occurred while login");
    }

    private static Account tryLogin(int account, int pin){
        RestTemplate rt = RestTemplateFactory.getRestTemplate();
        try{
            URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS + account);
            Account loginAccount = rt.getForObject(uri, Account.class);
            if(loginAccount.getPin() == pin)
                return loginAccount;
            else
                return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
