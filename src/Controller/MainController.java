package Controller;

import Modules.Main;

public class MainController {
    private static MainController ourInstance = new MainController();

    public static MainController getInstance() {
        return ourInstance;
    }

    public String getMenuAction() {
        String action = Main.scanner.nextLine();
        return action;
    }

    public String getUserNameForSignIn() {
        String userName = Main.scanner.nextLine();
        return userName;
    }

    public String getPassword() {
        String password = Main.scanner.nextLine();
        return password;
    }

    public String getUserNameForSignUp() {
        String userName = Main.scanner.nextLine();
        return userName;
    }
}
