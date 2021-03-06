package Server;

import Server.Modules.Main;
import Server.Modules.PlayableThings.Item.Item;
import Server.Modules.PlayableThings.cards.Card;
import Server.Modules.Shop;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Server.Modules.Account;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ServerGrraphic extends Application {
    public static void main(String[] args) {
        Thread t = new Thread(new Main());
        t.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.VIOLET);

        ServerGrraphic.getBackGround(root);

        Button online = new Button("online players");
        online.setFont(Font.font(60));
        online.setStyle("-fx-background-color: #3A81C4");
        online.relocate(200 , 100);
        Button shop = new Button("shop");
        shop.setStyle("-fx-background-color: #3A81C4");
        shop.setFont(Font.font(60));
        shop.relocate(300 , 200);

        shop.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                goToShop(primaryStage);
            }
        });

        online.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                goToOnlinePlayer(primaryStage);
            }
        });

        root.getChildren().addAll(online , shop);

        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void goToShop(Stage stage) {
        Group root = new Group();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        vBox.setFillWidth(true);
        Scene scene = new Scene(scrollPane, 800, 800, Color.VIOLET);

        ServerGrraphic.getBackGround(root);

        Button back = new Button("back");
        back.setStyle("-fx-background-color: #3A81C4");
        back.setFont(Font.font(35));
        back.relocate(600 , 500);

        for (Card card : Shop.getInstance().getHeroes()) {
            Label label = new Label(card.getName() + " / number : " + card.getNumber());
            label.setFont(Font.font(30));
            label.setTextFill(Color.ORANGERED);
            vBox.getChildren().addAll(label);
        }
        for (Card card : Shop.getInstance().getMinions()) {
            Label label = new Label(card.getName() + " / number : " + card.getNumber());
            label.setFont(Font.font(30));
            label.setTextFill(Color.ORANGERED);
            vBox.getChildren().addAll(label);
        }
        for (Card card : Shop.getInstance().getSpells()) {
            Label label = new Label(card.getName() + " / number : " + card.getNumber());
            label.setFont(Font.font(30));
            label.setTextFill(Color.ORANGERED);
            vBox.getChildren().addAll(label);
        }
        for (Item item : Shop.getInstance().getItems()) {
            Label label = new Label(item.getName() + " / number : " + item.getNumber());
            label.setFont(Font.font(30));
            label.setTextFill(Color.ORANGERED);
            vBox.getChildren().addAll(label);
        }

        back.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                start(stage);
            }
        });

        Button update = new Button("update");
        update.setStyle("-fx-background-color: #3A81C4");
        update.setFont(Font.font(35));
        update.relocate(600 , 200);

        update.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                goToShop(stage);
            }
        });

        root.getChildren().addAll(back, vBox, update);
        scrollPane.setContent(root);

        stage.setTitle("shop");
        stage.setScene(scene);
        stage.show();
    }

    private  void goToOnlinePlayer(Stage stage) {
        Group root = new Group();
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();

        ServerGrraphic.getBackGround(root);
        int number = 0;
        for (Account account : Account.getAccounts()) {
            if (!account.getOnline())
                continue;
            Label name = new Label(account.getUserName());
            name.setFont(Font.font(30));
            name.setTextFill(Color.RED);
            vBox.getChildren().add(name);
            number++;
        }

        scrollPane.setContent(root);

        Button back = new Button("back");
        back.setStyle("-fx-background-color: #3A81C4");
        back.setFont(Font.font(35));
        back.relocate(600 , 500);

        back.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                start(stage);
            }
        });

        Button update = new Button("update");
        update.setStyle("-fx-background-color: #3A81C4");
        update.setFont(Font.font(35));
        update.relocate(600 , 200);

        update.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                goToOnlinePlayer(stage);
            }
        });

        root.getChildren().addAll(back, vBox, update);

        Scene scene = new Scene(scrollPane, 800, 800, Color.VIOLET);
        stage.setTitle("online players");
        stage.setScene(scene);
        stage.show();

    }

    public static void getBackGround(Group group) {
        try {
            Image backGround = new Image(new FileInputStream("C:\\Users\\asus\\Desktop\\project-18\\src\\Client\\bg.jpg"));
            ImageView bg = new ImageView(backGround);
            group.getChildren().add(bg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
