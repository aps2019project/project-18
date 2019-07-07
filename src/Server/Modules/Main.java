package Server.Modules;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        while (true) {
            try {
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\asus\\Desktop\\project-18\\src\\Server\\config.txt");
                Scanner scanner = new Scanner(fileInputStream);
                scanner.nextLine();
                ServerSocket ss = new ServerSocket(scanner.nextInt());
                Socket socket = ss.accept();
                Formatter serverOutput = new Formatter(socket.getOutputStream());
                Scanner serverScanner = new Scanner(socket.getInputStream());
                if (serverScanner.nextLine().equals("sign up")) {
                    String username = serverScanner.nextLine();
                    if (!Account.checkExistUserName(username)) {
                        Account.createAccount(username, serverScanner.nextLine());
                        //todo start account thread
                        serverOutput.format("ok\n");
                        serverOutput.flush();
                    } else {
                        serverOutput.format("fuck\n");
                        serverOutput.flush();
                    }
                } else {
                    String username = serverScanner.nextLine();
                    if (Account.checkExistUserName(username)) {
                        if (Account.findAccount(username).checkPassword(serverScanner.nextLine())) {
                            Account.findAccount(username).signIn();
                            //todo start account thread
                            serverOutput.format("ok\n");
                            serverOutput.flush();
                        } else {
                            serverOutput.format("pass\n");
                            serverOutput.flush();
                        }
                    } else {
                        serverOutput.format("user\n");
                        serverOutput.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                try {
                    ServerSocket ss = new ServerSocket(8000);
                } catch (IOException e1) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}