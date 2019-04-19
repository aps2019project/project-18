package View.Request;

import java.util.Scanner;

public abstract class MainRequest {
    private static Scanner scanner = new Scanner(System.in);
    protected String command;

    public void getNewCommand(){
        this.command = scanner.nextLine();
    }

    public String getCommand(){
        return command;
    }
}
