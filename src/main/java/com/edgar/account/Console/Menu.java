package com.edgar.account.Console;

public class Menu {
    public static void showMainMenu(){
        System.out.println("\nWelcome to the best bank app");
        System.out.println("Menu:");
        System.out.println("1.- Login");
        System.out.println("2.- Create account");
        System.out.println("0.- Exit");
    }

    public static void decisionMenu(int decision){
        switch(decision){
            case 0:
                System.out.println("Shutting down");
                System.exit(0);
                break;
            case 1:
                Login.showLoginMenu();
                break;
            case 2:
                Register.showRegisterMenu();
                break;
            default:
                System.out.println("\nOption not available. Please choose another option.");
        }
    }
}
