package Modules.GameBusiness.Battle;

import Modules.Account;
import Modules.GameBusiness.Game.ModeCaptureFlag6Turn;
import Modules.GameBusiness.Game.ModeCaptureHalfFlags;
import Modules.GameBusiness.Game.ModeKillEnemyHero;
import Modules.GameBusiness.Player.Human;
import Modules.Main;
import View.View.Show;
import View.View.ShowBattle;
import View.View.ShowMultiPlayer;

public class MultiPlayer {
    public static void doOrder(Account account) {
        Account.showOpponents(account.getUserName());
        ShowMultiPlayer.showMenu();
        while (true) {
            String[] order = Main.scanner.nextLine().trim().split(" ");
            if (order[0].equalsIgnoreCase("select") && order[1].equalsIgnoreCase("user")
                    && order.length > 2) {
                selectUser(getUser(order), account);
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

    private static void selectUser(String userName, Account account) {
        Account enemyAccount = Account.findAccount(userName);
        if (enemyAccount != null) {
            if (enemyAccount.getCollection().getMainDeck() != null && enemyAccount.getCollection().getMainDeck().checkValidity() && enemyAccount.getCollection().getMainDeck().checkValidity()) {
                gameModeMenu(account, enemyAccount);
            } else {
                System.out.println("Enemy main deck is not valid");
            }
        } else {
            System.out.println("User name did is not exist");
        }
    }

    private static void gameModeMenu(Account account, Account enemyAccount) {
        ShowBattle.showGameModeMenu();
        boolean whileFlag = true;
        while (whileFlag) {
            String string = Main.scanner.nextLine().trim();
            if (string.equalsIgnoreCase("Kill enemy hero")) {
                ModeKillEnemyHero modeKillEnemyHero = new ModeKillEnemyHero(new Human(account), new Human(enemyAccount));
                modeKillEnemyHero.turn();
                whileFlag = false;
            } else if (string.equalsIgnoreCase("Capture flag for 6 turn")) {
                ModeCaptureFlag6Turn modeCaptureFlag6Turn = new ModeCaptureFlag6Turn(new Human(account), new Human(enemyAccount));
                modeCaptureFlag6Turn.turn();
                whileFlag = false;
            } else if (string.split(" ")[0].equalsIgnoreCase("Capture more than half flags")) {
                if (string.split(" ").length > 1) {
                    ModeCaptureHalfFlags modeCaptureHalfFlags = new ModeCaptureHalfFlags(new Human(account), new Human(enemyAccount),
                            Integer.parseInt(string.trim().split(" ")[1]));
                    modeCaptureHalfFlags.turn();
                    whileFlag = false;
                } else {
                    ModeCaptureHalfFlags modeCaptureHalfFlags = new ModeCaptureHalfFlags(new Human(account), new Human(enemyAccount));
                    modeCaptureHalfFlags.turn();
                    whileFlag = false;
                }
            } else if (string.equalsIgnoreCase("Help")) {
                ShowBattle.showHelpGameModeMenu();
            } else {
                ShowBattle.showInvalidCommand();
            }
        }
    }

    private static String getUser(String[] strings) {
        String userName = strings[2];
        for (int i = 3; i < strings.length; i++) {
            userName += " ";
            userName += strings[i];
        }
        return userName;
    }

}
