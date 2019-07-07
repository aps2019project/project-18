package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GraphicView extends Application {
    private Scene scene;
    private static Socket socket;

    public void start(Stage primaryStage) {
        Main.menu(primaryStage);
    }

    public static void main(String[] args) {
        connect();
        launch(args);
    }

    private static void connect() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\asus\\Desktop\\project-18\\config.txt");
            Scanner scanner = new Scanner(fileInputStream);
            socket = new Socket(scanner.nextLine() , scanner.nextInt());
        } catch (FileNotFoundException e) {
            try {
                socket = new Socket("127.0.0.1" , 8000);
            } catch (IOException e1) {

            }
        } catch (UnknownHostException e) {
            System.out.println("server not found");
        } catch (IOException e) {
            System.out.println("server not found");
        }
    }
}
