package Modules.GameBusiness.Battle;

import Modules.Main;
import View.View.ShowAccount;

public class Battle {
    public static void doOrder() {
        showMenu();
        String order;
        while (true) {
            order = Main.scanner.nextLine();
            if (order.equalsIgnoreCase("Single player")) {
//todo
            } else if (order.equalsIgnoreCase("Multi player")) {
//todo
            } else if (order.equalsIgnoreCase("back")) {
                ShowAccount.showMenu();
                return;
            } else if (order.equalsIgnoreCase("Help")) {
                showHelpMenu();
            } else {
                showInvalidCommand();
            }
        }
    }

    public static void showMenu() {
        System.out.println("1. Single player\n2. Multi player\n3. Back\n4. Help");
    }

    public static void showHelpMenu() {
        System.out.println("1. Single player : play with AI");
        System.out.println("2. Multi player : play with other player");
        System.out.println("3. Back : go to previous menu");
    }

    public static void showInvalidCommand() {
        System.out.println("invalid command please use help");
    }
}

