package Modules.GameBusiness.Battle;

import Modules.Account;
import Modules.GameBusiness.Game.Game;
import Modules.GameBusiness.Game.ModeCaptureFlag6Turn;
import Modules.GameBusiness.Game.ModeCaptureHalfFlags;
import Modules.GameBusiness.Game.ModeKillEnemyHero;
import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.Main;
import Modules.Shop;
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

    private static Game gameMode(Account account, AI ai) {
        ShowBattle.showGameModeMenu();
        while (true) {
            String string = Main.scanner.nextLine().trim();
            if (string.equalsIgnoreCase("Kill enemy hero")) {
                return new ModeKillEnemyHero(new Human(account), ai);
            } else if (string.equalsIgnoreCase("Capture flag for 6 turn")) {
                return new ModeCaptureFlag6Turn(new Human(account), ai);
            } else if (string.split(" ")[0].equalsIgnoreCase("Capture more than half flags")) {
                if (string.split(" ").length > 1) {
                    return new ModeCaptureHalfFlags(new Human(account), ai, Integer.parseInt(string.trim().split(" ")[1]));
                } else {
                    return new ModeCaptureHalfFlags(new Human(account), ai);
                }
            } else if (string.equalsIgnoreCase("Help")) {
                ShowBattle.showHelpGameModeMenu();
            } else {
                ShowBattle.showInvalidCommand();
            }
        }
    }

    private static void storyMode(Account account) {
        if (!storyMode(account, levelOne())) {
            return;
        }
        if (!storyMode(account, levelTwo())) {
            return;
        }
        if (!storyMode(account, levelThree())) {
            return;
        }
        System.out.println("Congratulation you win story mode");
    }

    private static boolean storyMode(Account account, AI ai) {
        Game game = gameMode(account, ai);
        game.turn();
        while (true)
            if (game.getWinnerPlayer() instanceof AI) {
                System.out.println("1. Rematch");
                System.out.println("2. Give up");
                while (true) {
                    String s = Main.scanner.nextLine();
                    if (s.equalsIgnoreCase("Rematch")) {
                        game = gameMode(account, ai);
                        game.turn();
                        break;
                    }
                    if (s.equalsIgnoreCase("Give up")) {
                        return false;
                    }
                }
            } else {
                System.out.println("Player win");
                break;
            }
        return true;
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
        ai.setDeck(Shop.getInstance().setCustomDeck());
        gameMode(account, ai);
    }
}
