package Client;

import Server.Modules.GameBusiness.Game.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

        GraphicView.getBackGround(root);
        Label title = GraphicView.getTitle("Sign in", 60);
        title.relocate(280, 100);
        TextField username = new TextField();
        username.relocate(300, 200);
        PasswordField password = new PasswordField();
        password.relocate(300, 250);
        Button apply = new Button("Sign In");
        apply.setFont(Font.font(35));
        apply.relocate(300, 300);
        Button back = new Button("Back");
        back.setFont(Font.font(35));
        back.relocate(315, 400);

        back.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                menu(stage);
            }
        });

        apply.setStyle("-fx-background-color: #3A81C4");
        back.setStyle("-fx-background-color: #3A81C4");

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
                } else {
                    GraphicView.connect();
                    GraphicView.write("sign in", true);
                    GraphicView.write(username.getText(), true);
                    GraphicView.write(password.getText(), true);
                    String line = GraphicView.read();
                    if (line.equals("ok")) {
                        GraphicView.setId(username.getText());
                        AcountMenu.goToAccountMenu(stage);
                    } else if (line.equals("user")) {
                        usernameNotFound.showAndWait();
                    } else {
                        wrongPassword.showAndWait();
                    }
                }
            }
        });

        root.getChildren().addAll(username, password, apply, back, title);
        stage.setTitle("sign in");
        stage.setScene(scene);
        stage.show();
    }

    private static void signUp(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.RED);

        GraphicView.getBackGround(root);

        Label title = GraphicView.getTitle("Sign up", 60);
        title.relocate(280, 100);
        Alert duplicateUsername = new Alert(Alert.AlertType.WARNING);
        duplicateUsername.setTitle("Warning");
        duplicateUsername.setHeaderText("Username exists");
        duplicateUsername.setContentText("Please choose a different username");
        Alert emptyFields = new Alert(Alert.AlertType.WARNING);
        emptyFields.setTitle("Warning");
        emptyFields.setHeaderText("Empty Fields");
        emptyFields.setContentText("Please enter your username and password");

        TextField userName = new TextField();
        userName.relocate(310, 200);
        PasswordField password = new PasswordField();
        password.relocate(310, 230);
        Button ok = new Button("Sign up");
        ok.setFont(Font.font(35));
        ok.relocate(300, 260);
        ok.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                if (userName.getText().equals("") || password.getText().equals("")) {
                    emptyFields.showAndWait();
                } else {
                    GraphicView.connect();
                    GraphicView.write("sign up", true);
                    GraphicView.write(userName.getText(), true);
                    GraphicView.write(password.getText(), true);
                    String line = GraphicView.read();
                    if (line.equals("ok")) {
                        GraphicView.setId(userName.getText());
                        AcountMenu.goToAccountMenu(stage);
                    } else {
                        duplicateUsername.showAndWait();
                    }
                }

        });
        Button back = new Button("Back");
        back.setFont(Font.font(35));
        back.relocate(320, 360);
        ok.setStyle("-fx-background-color: #3A81C4");
        back.setStyle("-fx-background-color: #3A81C4");

        back.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                menu(stage);
            }
        });
        root.getChildren().addAll(back, ok, password, userName, title);
        stage.setTitle("sign up");
        stage.setScene(scene);
        stage.show();
    }

    public static void menu(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);
        GraphicView.getBackGround(root);

        Label title = GraphicView.getTitle("Duelyst", 150);
        title.relocate(170, 20);


        Button signIn = new Button("Sign In");
        signIn.setFont(Font.font(35));
        signIn.relocate(325, 250);
        signIn.setStyle("-fx-background-color: #3A81C4");

        Button exit = new Button("Exit");
        exit.setFont(Font.font(35));
        exit.relocate(352, 450);
        exit.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                System.exit(0);
        });
        exit.setStyle("-fx-background-color: #3A81C4");

        Button signUp = new Button("Sign up");
        signUp.setFont(Font.font(35));
        signUp.relocate(320, 350);
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
        root.getChildren().addAll(exit, signIn, signUp, title);
        stage.setTitle("menu");
        stage.setScene(scene);
        stage.show();
    }
}
