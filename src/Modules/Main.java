package Modules;

import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;

    public static void main(String[] args) {
        showMenu();
        String input;
        while (true) {
            input = scanner.nextLine();
            doOrder(input);
            if (exit == true) {
                break;
            }
        }
    }

    private static void doOrder(String input) {
        if (input.equalsIgnoreCase("sign in")) {
            signIn();
        } else if (input.equalsIgnoreCase("sign up")) {
            signUp();
        } else if (input.equalsIgnoreCase("Exit")) {
            exit = true;
        } else if (input.equalsIgnoreCase("Help")) {
            showHelp();
        } else {
            showInvalidCommand();
        }
    }

    private static void signIn() {
        showTextForSignIn();
        Account.singIn(scanner.nextLine(), scanner.nextLine());
    }

    private static void signUp() {
        showTextForInsertUserName();
        Account.createAccount(scanner.nextLine());
    }

    private static void showHelp() {
        System.out.println("1. sign in : login to your account");
        System.out.println("2. sign up : create account");
        System.out.println("3. Exit : you can close the game");
    }

    public static void showMenu(){
        System.out.println("1. sign in\n2. sign up\n3. Exit");
    }

    private static void showInvalidCommand() {
        System.out.println("Invalid command, please use 'help' to show menu");
    }

    private static void showTextForInsertUserName(){
        System.out.println("please insert user name");
    }

    private static void showTextForSignIn(){
        System.out.println("please insert user name and password");
    }
}
