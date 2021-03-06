package Server.Modules;

import Server.ServerGrraphic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Main implements Runnable{
    public void run() {
        ServerSocket ss = null;
        while (true) {
            try {
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\asus\\Desktop\\project-18\\src\\Server\\config.txt");
                Scanner scanner = new Scanner(fileInputStream);
                scanner.nextLine();
                ss = new ServerSocket(scanner.nextInt());
            } catch (FileNotFoundException e) {
                try {
                    ss = new ServerSocket(8000);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Socket socket = null;
            try {
                socket = ss.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Formatter serverOutput = null;
            try {
                serverOutput = new Formatter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scanner serverScanner = null;
            try {
                serverScanner = new Scanner(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (serverScanner.nextLine().equals("sign up")) {
                String username = serverScanner.nextLine();
                if (!Account.checkExistUserName(username)) {
                    try {
                        Account.createAccount(username, serverScanner.nextLine(), socket.getInputStream(), socket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Thread thread = new Thread(Account.findAccount(username));
                    thread.start();
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
                        try {
                            Account.findAccount(username).signIn(socket.getInputStream(), socket.getOutputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Thread thread = new Thread(Account.findAccount(username));
                        thread.start();
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
        }
    }
}