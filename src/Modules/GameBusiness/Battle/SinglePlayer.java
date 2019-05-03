package Modules.GameBusiness.Battle;

import Modules.Account;
import Modules.GameBusiness.Game.ModeCaptureFlag6Turn;
import Modules.GameBusiness.Game.ModeCaptureHalfFlags;
import Modules.GameBusiness.Game.ModeKillEnemyHero;
import Modules.GameBusiness.Player.AI;
import Modules.Main;
import Modules.Shop;
import View.View.Show;
import View.View.ShowBattle;
import View.View.ShowSinglePlayer;

public class SinglePlayer {
    public static void doOrder(Account account) {
        ShowSinglePlayer.showMenu();
        String order;
        while (true) {
            order = Main.scanner.nextLine();
            if (order.equalsIgnoreCase("Story")) {
                storyMode(account);
            } else if (order.equalsIgnoreCase("Custom")) {
                customGame(account);
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

    private static void gameMode(Account account, AI ai) {
        ShowBattle.showGameModeMenu();
        while (true) {
            String string = Main.scanner.nextLine().trim();
            if (string.equalsIgnoreCase("Kill enemy hero")) {
                ModeKillEnemyHero killEnemyHero = new ModeKillEnemyHero(account.getPlayer(), ai);
                killEnemyHero.turn();
                return;
            } else if (string.equalsIgnoreCase("Capture flag for 6 turn")) {
                ModeCaptureFlag6Turn modeCaptureFlag6Turn = new ModeCaptureFlag6Turn(account.getPlayer(), ai);
                modeCaptureFlag6Turn.turn();
                return;
            } else if (string.split(" ")[0].equalsIgnoreCase("Capture more than half flags")) {
                if (string.split(" ").length > 1) {
                    ModeCaptureHalfFlags modeCaptureHalfFlags = new ModeCaptureHalfFlags(account.getPlayer(), ai,
                            Integer.parseInt(string.trim().split(" ")[1]));
                    modeCaptureHalfFlags.turn();
                    return;
                } else {
                    ModeCaptureHalfFlags modeCaptureHalfFlags = new ModeCaptureHalfFlags(account.getPlayer(), ai);
                    modeCaptureHalfFlags.turn();
                    return;
                }
            } else if (string.equalsIgnoreCase("Help")) {
                ShowBattle.showHelpGameModeMenu();
            } else {
                ShowBattle.showInvalidCommand();
            }
        }
    }

    private static void storyMode(Account account) {
        gameMode(account, levelOne());
        //todo if win go to next level
    }

    private static AI levelOne() {
        AI ai = new AI();
        ai.setDeck(Shop.getInstance().getDeckLevelOne());
        return ai;
    }

    private static AI levelTwo() {
        AI ai = new AI();
        ai.setDeck(Shop.getInstance().getDeckLevelTwo());
        return ai;
    }

    private static AI levelThree() {
        AI ai = new AI();
        ai.setDeck(Shop.getInstance().getDeckLevelThree());
        return ai;
    }

    private static void customGame(Account account) {
        AI ai = new AI();
        //todo
        gameMode(account, ai);
    }
}
