package com.edgar.account;

import com.edgar.account.Console.Menu;
import com.edgar.account.Factories.ScannerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Override
    public void run(String...args){
        int decision;
        do{
            Scanner sc = ScannerFactory.getScanner();
            Menu.showMainMenu();
            System.out.print("Decision: ");
            decision = sc.nextInt();
            sc.nextLine();
            Menu.decisionMenu(decision);
        }while(true);
    }

}
