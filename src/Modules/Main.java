package Modules;

import Modules.GameBusiness.Game.Game;
import View.View.ShowMain;

import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;
    public static void main(String[] args) {
        Game.initializeItems();
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
}
