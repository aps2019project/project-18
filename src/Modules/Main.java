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
            exit = true;
        } else if (input.equalsIgnoreCase("Help")) {
            ShowMain.showHelp();
        } else {
            ShowMain.showInvalidCommand();
        }
    }

    private static void signIn() {
        ShowMain.showTextForSignIn();
        Account.singIn(scanner.nextLine(), scanner.nextLine());
    }

    private static void signUp() {
        ShowMain.showTextForInsertUserName();
        Account.createAccount(scanner.nextLine());
    }


}
