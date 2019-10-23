package com.edgar.account.Factories;

import java.util.Scanner;

public class ScannerFactory {
    public static Scanner sc;

    private ScannerFactory(){}

    public static Scanner getScanner(){
        if (sc != null)
            return sc;
        else{
            sc = new Scanner(System.in);
            return sc;
        }
    }
}
