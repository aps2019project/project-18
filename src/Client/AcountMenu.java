package Client;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AcountMenu {
    private static Scene scene;
    public static void goToAccountMenu(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);

        GraphicView.getBackGround(root);

        Button collection = GraphicView.getButton("Collection");
        collection.relocate(298, 100);
        Button shop = GraphicView.getButton("Shop");
        shop.relocate(338, 200);
        Button battle = GraphicView.getButton("Battle");
        battle.relocate(335, 300);
        Button leaderBoard = GraphicView.getButton("LeaderBoard");
        leaderBoard.relocate(280, 400);
        Button exit = GraphicView.getButton("exit");
        exit.relocate(353, 700);
        Button logOut = GraphicView.getButton("Log out");
        logOut.relocate(320, 600);
        Button chat = GraphicView.getButton("chat room");
        logOut.relocate(320, 500);

        shop.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("shop" , true);
                goToShopMenu(stage);
            }
        });

        battle.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("battle" , true);
                goToBattleMenu(stage);
            }
        });

        collection.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("collection" , true);
                goToCollectionMenu(stage);
            }
        });

        logOut.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("log out" , true);
                Main.menu(stage);
            }
        });

        leaderBoard.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("leaderboard" , true);
                goToLeaderBoardMenu(stage);
            }
        });

        exit.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("log out" , true);
                System.exit(0);
            }
        });

        exit.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("chat" , true);
                goChating(stage);
            }
        });

        root.getChildren().addAll(collection , shop , battle , leaderBoard , exit , logOut);

        stage.setTitle("Account Menu");
        stage.setScene(scene);
        stage.show();
    }

    private static void goToShopMenu(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);

        GraphicView.getBackGround(root);

        root.getChildren().addAll();

        stage.setTitle("Shop");
        stage.setScene(scene);
        stage.show();
    }

    private static void goToCollectionMenu(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);

        GraphicView.getBackGround(root);

        root.getChildren().addAll();

        stage.setTitle("Collection");
        stage.setScene(scene);
        stage.show();
    }

    private static void goToBattleMenu(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);

        GraphicView.getBackGround(root);

        root.getChildren().addAll();

        stage.setTitle("Battle");
        stage.setScene(scene);
        stage.show();
    }

    private static void goToLeaderBoardMenu(Stage stage) {
        Group root = new Group();


        GraphicView.getBackGround(root);

        Label title = GraphicView.getTitle("Leader board" , 50);
        title.relocate(280 , 0);
        String line = GraphicView.read();

        while (!line.equals("end")) {
            String[] parts = line.split(" ");
            Label number = new Label(parts[0] + ".");
            number.relocate(0 , 100 * Integer.parseInt(parts[0]));
            number.setFont(Font.font(30));
            Label name = new Label(parts[1]);
            name.relocate(70 , 100 * Integer.parseInt(parts[0]));
            name.setFont(Font.font(30));
            Label wins = new Label(parts[2]);
            wins.relocate(200 , 100 * Integer.parseInt(parts[0]));
            wins.setFont(Font.font(30));
            root.getChildren().addAll(number , name , wins);

        }

        root.getChildren().addAll(title);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scene = new Scene(scrollPane, 800, 800);
        stage.setTitle("Leader Board");
        stage.setScene(scene);
        stage.show();
    }

    private static void goChating (Stage stage) {
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vBox);
        Group root = new Group();

        GraphicView.getBackGround(root);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                String line = GraphicView.read();

                while (!line.equals("end")) {

                }
            }
        };
        animationTimer.start();

        TextField message = new TextField();
        message.setOnAction(event -> {
            GraphicView.write(message.getText() , false);
        });
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(vBox);
        root.getChildren().add(scrollPane);
        stage.setScene(new Scene(root , 800 , 800));
        stage.setTitle("Chat");
        stage.show();
    }
}
