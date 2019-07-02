package View;

import Modules.GameBusiness.Game.Game;
import Modules.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicView extends Application {
    private Scene scene;

    public void start(Stage primaryStage) {
        Main.menu(primaryStage);
    }

    public static void main(String[] args) {
        Game.initializeItems();
        launch(args);
    }
}
