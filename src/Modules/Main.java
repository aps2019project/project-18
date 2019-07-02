package Modules;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
        emptyFields.setHeaderText("Incorrect username/password");
        emptyFields.setContentText("incorrect username or password, please check your entries");
        apply.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (username.getText().equals("") || password.getText().equals("")) {

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

        Label title = new Label("")

        Button signIn = new Button("Sign In");
        signIn.setFont(Font.font(35));
        signIn.relocate(305, 200);
        signIn.setStyle("-fx-background-color: #3A81C4");

        Button exit = new Button("Exit");
        exit.setFont(Font.font(35));
        exit.relocate(335, 400);
        exit.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                System.exit(0);
        });
        exit.setStyle("-fx-background-color: #3A81C4");

        Button signUp = new Button("Sign up");
        signUp.setFont(Font.font(35));
        signUp.relocate(300, 300);
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
        root.getChildren().addAll(exit, signIn, signUp);
        stage.setTitle("menu");
        stage.setScene(scene);
        stage.show();
    }
}
