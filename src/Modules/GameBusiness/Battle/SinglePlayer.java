package Modules.GameBusiness.Battle;

import Modules.Main;
import View.View.ShowBattle;
import View.View.ShowSinglePlayer;

public class SinglePlayer {
    public static void doOrder() {
        ShowSinglePlayer.showMenu();
        String order;
        while (true) {
            order = Main.scanner.nextLine();
            if (order.equalsIgnoreCase("Story")) {
                storyMode();
            } else if (order.equalsIgnoreCase("Custom")) {
                customGame();
            } else if (order.equalsIgnoreCase("back")) {
                ShowBattle.showMenu();
                return;
            } else if (order.equalsIgnoreCase("Help")) {
                ShowSinglePlayer.showHelpMenu();
            } else {
                ShowSinglePlayer.showInvalidCommand();
            }
        }
    }

    private static void storyMode() {
        System.out.println("Story");
        //todo
    }

    private static void customGame() {
        System.out.println("custom");
        //todo
    }
}
