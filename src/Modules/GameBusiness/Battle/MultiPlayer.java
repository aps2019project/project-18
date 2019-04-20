package Modules.GameBusiness.Battle;

import Modules.Account;
import Modules.Main;
import View.View.ShowBattle;
import View.View.ShowMultiPlayer;

public class MultiPlayer {
    public static void doOrder() {
        new Account().showLeaderboard();
        ShowMultiPlayer.showMenu();
        while (true) {
            String[] order = Main.scanner.nextLine().trim().split(" ");
            if (order[0].equalsIgnoreCase("select") && order[1].equalsIgnoreCase("user")
                    && order.length > 2) {
//todo
            } else if (order[0].equalsIgnoreCase("back") && order.length == 1) {
                ShowBattle.showMenu();
                return;
            } else if (order[0].equalsIgnoreCase("help") && order.length == 1) {
                ShowMultiPlayer.showHelpMenu();
            } else {
                ShowMultiPlayer.showInvalidCommand();
            }
        }
    }


}
