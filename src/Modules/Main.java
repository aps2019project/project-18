package Modules;

import Modules.GameBusiness.Game.Game;
import View.View.ShowMain;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static Scene scene;
    private static boolean exit = false;
    public static void main(String[] args) {
        Game.initializeItems();
    }

    private static void signIn(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);

        TextField username = new TextField();
        username.relocate(100, 200);
        PasswordField password = new PasswordField();
        password.relocate(100, 300);
        Button apply = new Button("Sign In");
        apply.setFont(Font.font(35));
        apply.relocate(100, 400);

        Alert emptyFields = new Alert(Alert.AlertType.WARNING);
        emptyFields.setTitle("Warning");
        emptyFields.setHeaderText("Empty Fields");
        emptyFields.setContentText("Please enter your username and password");

        Alert usernameNotFound = new Alert(Alert.AlertType.WARNING);
        usernameNotFound.setTitle("Warning");
        usernameNotFound.setHeaderText("Username does not exist");
        usernameNotFound.setContentText("Please enter an existing account or sign up");

        Alert wrongPassword = new Alert(Alert.AlertType.WARNING);
        wrongPassword.setTitle("Warning");
        wrongPassword.setHeaderText("Wrong password");
        wrongPassword.setContentText("Please check your password");

        apply.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (username.getText().equals("") || password.getText().equals("")) {
                    emptyFields.showAndWait();
                } else if (!Account.checkExistUserName(username.getText())) {
                    usernameNotFound.showAndWait();
                } else if (!Account.findAccount(username.getText()).equals(password.getText())) {
                    wrongPassword.showAndWait();
                } else {
                    //todo
                }
            }
        });

        root.getChildren().addAll(username, password, apply);
        // Account.signIn(username, password);
        stage.setTitle("sign in");
        stage.setScene(scene);
        stage.show();
    }

    private static void signUp(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 100, 100, Color.RED);
        TextField userName = new TextField();
        userName.relocate(0, 20);
        PasswordField password = new PasswordField();
        password.relocate(50, 20);
        Button ok = new Button("Ok");
        ok.setFont(Font.font(35));
        ok.relocate(50, 100);
        ok.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                Account.createAccount(userName.getText(), password.getText());
        });
        root.getChildren().addAll(ok, password, userName);
        stage.setTitle("sign up");
        stage.setScene(scene);
        stage.show();
    }

    public static void menu(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);
        try {
            Image backGround = new Image(new FileInputStream("C:\\Users\\asus\\Desktop\\project-18\\src\\Modules\\bg.jpg"));
            ImageView bg = new ImageView(backGround);
            root.getChildren().add(bg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Label title = new Label("Duelyst");
        title.setFont(Font.font(150));
        title.setTextFill(Color.ORANGERED);
        title.relocate(150 , 20);


        Button signIn = new Button("Sign In");
        signIn.setFont(Font.font(35));
        signIn.relocate(305, 250);
        signIn.setStyle("-fx-background-color: #3A81C4");

        Button exit = new Button("Exit");
        exit.setFont(Font.font(35));
        exit.relocate(335, 450);
        exit.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                System.exit(0);
        });
        exit.setStyle("-fx-background-color: #3A81C4");

        Button signUp = new Button("Sign up");
        signUp.setFont(Font.font(35));
        signUp.relocate(300, 350);
        signUp.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                signUp(stage);
        });
        signUp.setStyle("-fx-background-color: #3A81C4");

        signIn.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                signIn(stage);
            }
        });
        root.getChildren().addAll(exit, signIn, signUp , title);
        stage.setTitle("menu");
        stage.setScene(scene);
        stage.show();
    }
}
