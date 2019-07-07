package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Scanner;

public class GraphicView extends Application {
    private Scene scene;
    private static Socket socket;
    private static Scanner scanner;
    private static Formatter formatter;
    private static String id;

    public static void setId(String id1) {
        id = id1;
    }

    public void start(Stage primaryStage) {
        Main.menu(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void connect() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\asus\\Desktop\\project-18\\src\\Client\\config.txt");
            Scanner scanner = new Scanner(fileInputStream);
            socket = new Socket(scanner.nextLine() , scanner.nextInt());
            formatter = new Formatter(socket.getOutputStream());
            scanner = new Scanner(socket.getInputStream());
        } catch (FileNotFoundException e) {
            try {
                socket = new Socket("127.0.0.1" , 8000);
                formatter = new Formatter(socket.getOutputStream());
                scanner = new Scanner(socket.getInputStream());
            } catch (IOException e1) {

            }
        } catch (UnknownHostException e) {
            System.out.println("server not found");
        } catch (IOException e) {
            System.out.println("server not found");
        }
    }

    synchronized public static void write(String line , boolean start) {
        if (start)
            formatter.format("%s\n" , line);
        else
            formatter.format("%s%s\n" , id, line);
        formatter.flush();
    }

    synchronized public static String read() {
        return scanner.nextLine();
    }
}
