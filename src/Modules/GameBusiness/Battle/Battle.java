package Modules.GameBusiness.Battle;

import Modules.Account;
import Modules.Main;
import View.View.ShowAccount;
import View.View.ShowBattle;

public class Battle {
    public static void doOrder(Account account) {
        ShowBattle.showMenu();
        String order;
        while (true) {
            order = Main.scanner.nextLine();
            if (order.equalsIgnoreCase("Single player")) {
                SinglePlayer.doOrder(account);
            } else if (order.equalsIgnoreCase("Multi player")) {
                MultiPlayer.doOrder(account);
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

