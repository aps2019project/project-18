package Client;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
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
        exit.relocate(353, 600);
        Button logOut = GraphicView.getButton("Log out");
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
        scene = new Scene(root, 800, 800, Color.VIOLET);

        GraphicView.getBackGround(root);

        root.getChildren().addAll();

        stage.setTitle("Leader Board");
        stage.setScene(scene);
        stage.show();
    }
}
