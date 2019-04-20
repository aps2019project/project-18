package Modules.GameBusiness.Battle;

import Modules.Main;
import View.View.ShowAccount;
import View.View.ShowBattle;

public class Battle {
    public static void doOrder() {
        ShowBattle.showMenu();
        String order;
        while (true) {
            order = Main.scanner.nextLine();
            if (order.equalsIgnoreCase("Single player")) {
                SinglePlayer.doOrder();
            } else if (order.equalsIgnoreCase("Multi player")) {
                MultiPlayer.doOrder();
            } else if (order.equalsIgnoreCase("back")) {
                ShowAccount.showMenu();
                return;
            } else if (order.equalsIgnoreCase("Help")) {
                ShowBattle.showHelpMenu();
            } else {
                ShowBattle.showInvalidCommand();
            }
        }
    }


}

