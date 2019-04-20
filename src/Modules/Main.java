package Modules;

import Controller.MainController;
import View.ShowMain;

import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;

    public static void main(String[] args) {
        ShowMain.showMenu();
        String input = MainController.getInstance().getMenuAction();
        while (true) {
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
        String userName = MainController.getInstance().getUserNameForSignIn();
        String password = MainController.getInstance().getPassword();
        ShowMain.showTextForSignIn();
        Account.singIn(userName, password);
    }

    private static void signUp() {
        String userName = MainController.getInstance().getUserNameForSignUp();
        ShowMain.showTextForInsertUserName();
        Account.createAccount(userName);
    }


}
