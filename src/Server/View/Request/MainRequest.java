package Server.View.Request;

import Client.Main;

import java.util.Scanner;

public abstract class MainRequest {
    private static Scanner scanner = Main.scanner;
    protected String command;

    public void getNewCommand(){
        this.command = scanner.nextLine();
    }

    public String getCommand(){
        return command;
    }

    public abstract void show();
}
