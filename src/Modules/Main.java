package Modules;

import Modules.GameBusiness.Game.Game;
import View.View.ShowMain;
import netscape.javascript.JSObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;
    public static void main(String[] args) {
        Game.initializeItems();
        ShowMain.showMenu();
        String input;
        while (true) {
            input = scanner.nextLine();
            doOrder(input);
            if (exit) {
                break;
            }
        }
    }

    private static void doOrder(String input) {
        Account.createAccount("AI1", "Aa!12345");
        Account.findAccount("AI1").getCollection().setMainDeck(Shop.getInstance().getDeckLevelOne());
        Account.createAccount("AI2", "Aa!12345");
        Account.findAccount("AI2").getCollection().setMainDeck(Shop.getInstance().getDeckLevelTwo());

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
        Account.signIn(userName, password);
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
