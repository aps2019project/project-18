package Modules.GameBusiness.Battle;

import Modules.Account;
import Modules.Main;
import View.View.ShowBattle;

public class MultiPlayer {
    public static void doOrder() {
        new Account().showLeaderboard();
        showMenu();
        while (true) {
            String[] order = Main.scanner.nextLine().trim().split(" ");
            if (order[0].equalsIgnoreCase("select") && order[1].equalsIgnoreCase("user")
                    && order.length > 2) {
//todo
            } else if (order[0].equalsIgnoreCase("back") && order.length == 1) {
                ShowBattle.showMenu();
                return;
            } else if (order[0].equalsIgnoreCase("help") && order.length == 1) {
                showHelpMenu();
            } else {
                showInvalidCommand();
            }
        }
    }

    public static void showMenu() {
        System.out.println("1. Select user [user name]");
        System.out.println("2. Back");
        System.out.println("3. Help");
    }

    public static void showHelpMenu() {
        System.out.println("1. Select user [user name] : select enemy user name");
        System.out.println("2. Back : go to previous menu");
    }

    public static void showInvalidCommand() {
        System.out.println("invalid command please use help");
    }
}
