package Modules;

import View.ShowMain;

import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;

    public static void main(String[] args) {
        ShowMain.showMenu();
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
            exit();
        } else if (input.equalsIgnoreCase("Help")) {
            ShowMain.showHelp();
        } else {
            ShowMain.showInvalidCommand();
        }
    }

    private static void signIn() {
        ShowMain.showTextForSignIn();
        String userName = scanner.nextLine();
        String password = scanner.nextLine();
        Account.singIn(userName, password);
    }

    private static void signUp() {
        ShowMain.showTextForInsertUserName();
        String userName = scanner.nextLine();
        Account.createAccount(userName);
    }

    public static void exit() {
        exit = true;
    }

}
