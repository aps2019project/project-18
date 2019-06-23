package Modules;

import Modules.GameBusiness.Game.Game;
import View.View.ShowMain;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

    private static void signUp(Scene scene) {
        Group root = new Group();
        scene = new Scene(root , 100 , 100 , Color.RED)
        TextField userName = new TextField();
        userName.relocate(0 , 20);
        PasswordField password = new PasswordField();
        password.relocate(50 , 20);
        Button ok = new Button("Ok");
        ok.relocate(50 ,100);
        ok.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                Account.createAccount(userName.getText() , password.getText());
        });
        root.getChildren().addAll(ok , password , userName);
    }

    public static void menu(Scene scene) {
        Group root = new Group();
        scene = new Scene(root, Color.VIOLET);

        Button signIn = new Button();
        signIn.setText("Sign In");
        signIn.relocate(100, 100);

        Button exit = new Button("Exit");
        exit.relocate(100 , 500);
        exit.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                System.exit(0);
        });

        Button signUp = new Button("Sign up");
        signUp.relocate(100 , 300);
        signUp.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                signUp(scene);
        });

        root.getChildren().addAll(exit , signIn , signUp);
    }
}
