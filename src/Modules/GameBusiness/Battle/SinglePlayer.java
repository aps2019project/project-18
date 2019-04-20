package Modules.GameBusiness.Battle;

import Modules.Main;

public class SinglePlayer {
    public static void doOrder() {
        showMenu();
        String order;
        while (true) {

            order = Main.scanner.nextLine();
            if (order.equalsIgnoreCase("Story")) {
//todo
            } else if (order.equalsIgnoreCase("Custom")) {
//todo
            } else if (order.equalsIgnoreCase("back")) {
                Battle.showMenu();
                return;
            } else if (order.equalsIgnoreCase("Help")) {
                showHelpMenu();
            } else {
                showInvalidCommand();
            }
        }
    }

    public static void showMenu() {
        System.out.print("1. Story\n2. Custom\n3. Back\n4. Help");
    }

    public static void showHelpMenu() {
        System.out.println("1. Story : contains 3 level");
        System.out.println("2. Custom : you can select hero that want play against it");
        System.out.println(("3. Back : go to previous menu"));
    }

    public static void showInvalidCommand() {
        System.out.println("invalid command please use help");
    }
}
