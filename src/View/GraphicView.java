package View;

import Modules.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicView extends Application {
    private Scene scene;
    public void start(Stage primaryStage) {
        Main.menu(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Duelist");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
