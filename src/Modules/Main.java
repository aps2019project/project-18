package Modules;

import Modules.GameBusiness.Game.Game;
import View.View.ShowMain;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private static void signIn(Scene scene) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);

        TextField username = new TextField();
        username.relocate(100, 200);
        PasswordField password = new PasswordField();
        password.relocate(100, 300);
        Button apply = new Button("Sign In");
        apply.relocate(100, 400);

        Alert emptyFields = new Alert(Alert.AlertType.WARNING);
        emptyFields.setTitle("Warning");
        emptyFields.setHeaderText("Incorrect username/password");
        emptyFields.setContentText("incorrect username or password, please check your entries");
        apply.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (username.getText().equals("") || password.getText().equals("")) {

                }
            }
        });

        root.getChildren().addAll(username, password, apply);
        Account.signIn(username, password);
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
        scene = new Scene(root, 800, 800, Color.VIOLET);

        Button signIn = new Button("Sign In");
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

        signIn.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                signIn(scene);
            }
        });
        root.getChildren().addAll(exit , signIn , signUp);
    }
}
