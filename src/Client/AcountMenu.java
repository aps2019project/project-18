package Client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        chat.relocate(310, 500);

        shop.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("shop", true);
                goToShopMenu(stage);
            }
        });

        battle.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("battle", true);
                goToBattleMenu(stage);
            }
        });

        collection.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("collection", true);
                goToCollectionMenu(stage);
            }
        });

        logOut.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("log out", true);
                Main.menu(stage);
            }
        });

        leaderBoard.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("leaderboard", true);
                goToLeaderBoardMenu(stage);
            }
        });

        exit.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("log out", true);
                System.exit(0);
            }
        });

        chat.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("chat" , true);
                goChating(stage);
            }
        });

        root.getChildren().addAll(collection , shop , battle , leaderBoard , exit , logOut , chat);

        stage.setTitle("Account Menu");
        stage.setScene(scene);
        stage.show();
    }

    private static void getCards(ArrayList<String> cards) {
        while (true) {
            String s = GraphicView.read();
            if (s.equals("end"))
                break;
            cards.add(s);
        }
    }

    private static void goToShopMenu(Stage stage) {
        String money;
        ArrayList<String> heroes = new ArrayList<>();
        ArrayList<String> spells = new ArrayList<>();
        ArrayList<String> minions = new ArrayList<>();
        ArrayList<String> items = new ArrayList<>();
        Group root = new Group();

        Alert cardDoesNotExist = new Alert(Alert.AlertType.WARNING);
        cardDoesNotExist.setTitle("Warning");
        cardDoesNotExist.setHeaderText("Card does not exist");
        cardDoesNotExist.setContentText("Sorry, the shop has run out of this card");

        Alert notEnoughMoney = new Alert(Alert.AlertType.WARNING);
        notEnoughMoney.setTitle("Warning");
        notEnoughMoney.setHeaderText("Not enough money");
        notEnoughMoney.setContentText("You don't have enough money to buy this card");

        money = GraphicView.read();
        getCards(heroes);
        getCards(spells);
        getCards(minions);
        getCards(items);

        GraphicView.getBackGround(root);
        root.getChildren().addAll();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(new Label("your money is : " + money));
        for (String s : heroes) {
            Button button = new Button(s);
            vBox.getChildren().addAll(button);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String price = s.split("/")[2].replace(" price : ", "");
                    String number = s.split("/")[1].trim().replace("number : ", "");
                    if (Integer.parseInt(number) == 0)
                        cardDoesNotExist.showAndWait();
                    else if (Integer.parseInt(price) > Integer.parseInt(money))
                        notEnoughMoney.showAndWait();
                    else {
                        GraphicView.write(s.split("/")[0].trim(), true);
                        vBox.getChildren().removeAll();
                        goToShopMenu(stage);
                    }
                }
            });
        }
        for (String s : spells) {
            Button button = new Button(s);
            vBox.getChildren().addAll(button);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String price = s.split("/")[2].replace(" price : ", "");
                    String number = s.split("/")[1].trim().replace("number : ", "");
                    if (Integer.parseInt(number) == 0)
                        cardDoesNotExist.showAndWait();
                    else if (Integer.parseInt(price) > Integer.parseInt(money))
                        notEnoughMoney.showAndWait();
                    else {
                        GraphicView.write(s.split("/")[0].trim(), true);
                        vBox.getChildren().removeAll();
                        goToShopMenu(stage);
                    }
                }
            });
        }
        for (String s : minions) {
            Button button = new Button(s);
            vBox.getChildren().addAll(button);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String price = s.split("/")[2].replace(" price : ", "");
                    String number = s.split("/")[1].trim().replace("number : ", "");
                    if (Integer.parseInt(number) == 0)
                        cardDoesNotExist.showAndWait();
                    else if (Integer.parseInt(price) > Integer.parseInt(money))
                        notEnoughMoney.showAndWait();
                    else {
                        GraphicView.write(s.split("/")[0].trim(), true);
                        vBox.getChildren().removeAll();
                        goToShopMenu(stage);
                    }
                }
            });
        }
        for (String s : items) {
            Button button = new Button(s);
            vBox.getChildren().addAll(button);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String price = s.split("/")[2].replace(" price : ", "");
                    String number = s.split("/")[1].trim().replace("number : ", "");
                    if (Integer.parseInt(number) == 0)
                        cardDoesNotExist.showAndWait();
                    else if (Integer.parseInt(price) > Integer.parseInt(money))
                        notEnoughMoney.showAndWait();
                    else {
                        GraphicView.write(s.split("/")[0].trim(), true);
                        vBox.getChildren().removeAll();
                        goToShopMenu(stage);
                    }
                }
            });
        }
        Button back = GraphicView.getButton("back");
        back.relocate(650 , 0);
        back.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("end" , true);
                goToAccountMenu(stage);
            }
        });
        root.getChildren().addAll(vBox , back);
        ScrollPane scrollPane = new ScrollPane();


        scrollPane.setContent(root);
        vBox.setFillWidth(true);
        scene = new Scene(scrollPane, 800, 800);

        stage.setTitle("Shop");
        stage.setScene(scene);
        stage.show();
    }

    private static void goToCollectionMenu(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);

        GraphicView.getBackGround(root);

        setBack(root , stage);

        root.getChildren().addAll();

        stage.setTitle("Collection");
        stage.setScene(scene);
        stage.show();
    }

    private static void goToBattleMenu(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, 800, 800, Color.VIOLET);

        setBack(root , stage);

        root.getChildren().addAll();

        stage.setTitle("Battle");
        stage.setScene(scene);
        stage.show();
    }

    private static void goToLeaderBoardMenu(Stage stage) {
        Group root = new Group();

        GraphicView.getBackGround(root);

        Label title = GraphicView.getTitle("Leader board", 50);
        title.relocate(280, 0);
        String line = GraphicView.read();
        VBox vBox = new VBox();
        vBox.getChildren().add(title);
        while (!line.equals("end")) {
            String[] parts = line.split(" ");
            Label label = new Label(parts[0] + "." + parts[1] + "   " + parts[2]);
            label.setFont(Font.font(30));

            if (parts[3].equals("on")) {
                label.setTextFill(Color.GREEN);
            }else {
                label.setTextFill(Color.RED);
            }
            vBox.getChildren().addAll(label);
            line = GraphicView.read();
        }

        root.getChildren().addAll(vBox);

        setBack(root , stage);

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

        updateChat(root);

        Button back = GraphicView.getButton("Back");
        back.relocate(650 , 700);
        back.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GraphicView.write("end" , true);
                goToAccountMenu(stage);
            }
        });

        Button update = GraphicView.getButton("update");
        update.relocate(650 , 600);
        update.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                updateChat(root);
            }
        });

        TextField message = new TextField();
        message.setOnAction(event -> {
            GraphicView.write(message.getText() , false);
            message.setText("");
            updateChat(root);
        });
        message.relocate(0 , 750);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(root);
        root.getChildren().addAll(vBox , back , message);
        stage.setScene(new Scene(scrollPane , 800 , 800));
        stage.setTitle("Chat");
        stage.show();
    }


    private static void setBack(Group root , Stage stage) {
        Button back = GraphicView.getButton("Back");
        back.relocate(650 , 750);
        back.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                goToAccountMenu(stage);
            }
        });
        root.getChildren().add(back);
    }

    private static void updateChat(Group root) {
        GraphicView.write("update" , true);
        String line = GraphicView.read();
        int number = 0;
        VBox vBox = new VBox();
        while (!line.equals("end")) {
            Label label = new Label(line);
            label.setFont(Font.font(20));
            label.setStyle("-fx-background-color: white");
            vBox.getChildren().add(label);
            number++;
            System.err.println(line);
            line = GraphicView.read();
            System.err.println("fuck");
        }
        root.getChildren().addAll(vBox);
    }
}
