package Controller;

import Modules.Main;

public class AccountController {
    private static AccountController ourInstance = new AccountController();

    public static AccountController getInstance() {
        return ourInstance;
    }

    private AccountController() {
    }

    public String getMenuAction() {
        String action = Main.scanner.nextLine();
        return action;
    }
}
