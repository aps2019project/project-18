package Modules;

import Modules.GameBusiness.Game.Game;
import View.View.ShowMain;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import netscape.javascript.JSObject;

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

    public static void menu(Scene scene) {
        Group root = new Group();
        scene = new Scene(root, Color.VIOLET);

        Button signIn = new Button();
        signIn.setText("Sign In");
        signIn.relocate(100, 100);

    }
}
