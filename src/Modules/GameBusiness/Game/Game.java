package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.GameBusiness.Player.Player;
import Modules.GameData;
import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.*;
import Modules.PlayableThings.cards.Spell.Spell;
import Modules.Playground;
import View.View.Show;

import java.util.ArrayList;
import java.util.Random;

public abstract class Game {
    Player[] players = new Player [2];
    private int turn = 0;
    boolean end;
    int winnerPlayer;
    Playground playground = new Playground();
    private static ArrayList<Item> collectableItems = new ArrayList<>();
    private static ArrayList<Integer[]> targetPositionCanAttackTo;

    Game(Player playerOne, Player playerTwo) {
        this.players[0] = playerOne;
        this.players[1] = playerTwo;
    }

    public void turn() {
        if (end) {
            doWhatNeedDoAfterGameEnd();
            return;
        }
        players[turn % 2].playTurn(turn);
        doWhatNeedDoAfterEachTurn();
    }

    private void doWhatNeedDoAfterEachTurn() {
        turn++;
        checkEnd();
    }

    public void comboAttack(Force force, String command) {
        String[] splittedCommand = command.split(" ");
        if (((Minion) force).hasCombo()) {
            if (!getEnemyPlayer().checkCard(splittedCommand[0])) {
                System.out.println("selected card does not belong to enemy");
                return;
            }
            for (int i = 1; i < command.split(" ").length; i++) {

            }
        }
    }

    public Force getForce(String id) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard().getId().equals(id)) {
                    return (Force) playground.getGround()[i][j].getCard();
                }
            }
        }
        return null;
    }

    public boolean insertCard(Card card, int x, int y) {
        if (x - 1 >= 0 && x - 1 < 9 && y - 1 >= 0 && y - 1 < 9) {
            if (playground.getGround()[x - 1][y - 1] == null) {
                if (card instanceof Spell) {
                    Spell spell = (Spell) card;
                    if (canExecuteSpell) {
                        spell.execute();
                        return true;
                    } else {
                        System.out.println("invalid target");
                    }
                } else {
                    Minion minion = (Minion) card;
                    if (canPlaceMinion(x, y, card)) {
                        playground.getGround()[x - 1][y - 1].setCard(minion);
                        return true;
                    } else {
                        System.out.println("minion can place near own forces");
                    }
                }
            } else {
                System.out.println("target place is already have card on it");
            }
        } else {
            System.out.println("target out of play ground");
        }
        return false;
    }

    private boolean canPlaceMinion(int x, int y, Card card) {
        String player;
        if (players[0].checkCard(card.getId())) {
            player = players[0].getAccount().getUserName();
        } else {
            player = players[1].getAccount().getUserName();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Math.abs(i - x) <= 1 && Math.abs(j - y) <= 1) {
                    if (playground.getGround()[x][y].getCard() == null) continue;
                    if (!playground.getGround()[x][y].getCard().getId().contains(player)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private void doWhatNeedDoAfterGameEnd() {
        saveData();
    }

    private void saveData() {
        String playerOneName = players[0].getAccount().getUserName();
        String playerTwoName = players[1].getAccount().getUserName();
        String matchResultPlayerOne, matchResultPlayerTwo;
        if (winnerPlayer == 1) {
            matchResultPlayerOne = "win";
            matchResultPlayerTwo = "lose";
        } else {
            matchResultPlayerOne = "lose";
            matchResultPlayerTwo = "win";
        }
        players[0].getAccount().saveGameData(new GameData(playerTwoName, matchResultPlayerOne));
        players[1].getAccount().saveGameData(new GameData(playerOneName, matchResultPlayerTwo));
    }

    static Item getRandomCollectableItem() {
        return collectableItems.get(new Random().nextInt(collectableItems.size()));
    }

    private void setRandomItemInPlayground() {
        int indexX, indexY;
        indexX = new Random().nextInt(9);
        indexY = new Random().nextInt(5);
        while (playground.getGround()[indexX][indexY].isCardOnIt()) {
            indexX = new Random().nextInt(9);
            indexY = new Random().nextInt(5);
        }
        playground.getGround()[indexX][indexY].setItem(getRandomCollectableItem());
    }

    private void setItemInRandomTurn() {
        if (new Random().nextInt() % 6 == 0) {
            setRandomItemInPlayground();
        }
    }

    abstract void setPlayground();

    abstract protected void checkEnd();

    private Force getPlayerForce(int x, int y) {
        String userNamePlayerWhoHaveTurn;
        Player player = players[turn % 2]
        //player should have card to move it
        if (player.checkCard(playground.getGround()[x][y].getCard().getId())) {
            if (playground.getGround()[x][y].getCard() instanceof Force) {
                return (Force) playground.getGround()[x][y].getCard();
            }
        }
        return null;
    }

    private boolean canAttack(Force force, int x, int y) {
        if (force.getAttackType().equals("melee")) {
            return canAttackMeleeForce(x, y);
        }
        if (force.getAttackType().equals("range")) {
            return canAttackRangeForce(force, x, y);
        }
        if (force.getAttackType().equals("hybrid")) {
            return canAttackHybridForce(force, x, y);
        }
        return false;
    }

    private boolean canAttackRangeForce(Force force, int x, int y) {
        targetPositionCanAttackTo = new ArrayList<>();
        if (canAttackMeleeForce(x, y)) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Math.abs(x - i) + Math.abs(y - j) <= force.getRange()) {
                    if (getEnemyForce(i, j) != null) {
                        targetPositionCanAttackTo.add(new Integer[]{i, j});
                    }
                }
            }
        }
        return targetPositionCanAttackTo.size() != 0;
    }

    private boolean canAttackMeleeForce(int x, int y) {
        targetPositionCanAttackTo = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if ((x - i == 1 || i - x == 1) && (y - j == 1 || j - y == 1)) {
                    if (getEnemyForce(i, j) != null) {
                        targetPositionCanAttackTo.add(new Integer[]{i, j});
                    }
                }
            }
        }
        return targetPositionCanAttackTo.size() != 0;
    }

    private boolean canAttackHybridForce(Force force, int x, int y) {
        return canAttackMeleeForce(x, y) || canAttackRangeForce(force, x, y);
    }

    private Force getEnemyForce(int x, int y) {
        if (playground.getGround()[x][y].getCard() instanceof Force) {
            //that means this force is for enemy
            if (getPlayerForce(x, y) == null) {
                return (Force) playground.getGround()[x][y].getCard();
            }
        }
        return null;
    }

    public void showOpponentMinion() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (getEnemyForce(i, j) == null) continue;
                //todo show card now
            }
        }
    }

    public void showMyMinions() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (getEnemyForce(i, j) != null) continue;
                //todo show card now
            }
        }
    }

    private Player getEnemyPlayer() {
        return players[(turn + 1) % 2];
    }

    public Hero getEnemyHero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard().getId().contains
                        (getEnemyPlayer().getAccount().getUserName())) {
                    continue;
                }
                if (playground.getGround()[i][j].getCard() instanceof Hero) {
                    return (Hero) playground.getGround()[i][j].getCard();
                }
            }
        }
        return null;
    }

    public void showMoveAbleCards() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (getPlayerForce(i, j) != null) {
                    if (getPlayerForce(i, j).getCanMove()) {
                        //show card
                    }
                }
            }
        }
    }

    public void showAttackAbleCards() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (getPlayerForce(i, j) == null) continue;
                if (!getPlayerForce(i, j).getCanAttack()) continue;
                if (canAttack(getPlayerForce(i, j), i, j)) {
                    //show card
                }
            }
        }
    }

    public void showMovablePlaces(String id) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() == null) continue;
                if (playground.getGround()[i][j].getCard().getId().equals(id)) {
                    showAllPlaceCanForceMoveTo(getForce(id), i, j);
                    return;
                }
            }
        }
    }

    public void useItem(Item item) {
        item.execute();//todo handle daghigh
    }

    private void showAllPlaceCanForceMoveTo(Force force, int i, int j) {
        if (!force.getCanMove()) {
            return;
        }
        if (i - 1 >= 0 && playground.getGround()[i - 1][j].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i - 1, j);
        }
        if (j - 1 >= 0 && playground.getGround()[i][j - 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i, j - 1);
        }
        if (j + 1 < 9 && playground.getGround()[i][j + 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i, j + 1);
        }
        if (j + 1 < 9 && playground.getGround()[i + 1][j].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i + 1, j);
        }
        if (i - 1 >= 0 && playground.getGround()[i - 1][j].getCard() == null &&
                i - 2 >= 0 && playground.getGround()[i - 2][j].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i - 2, j);
        }
        if (j - 1 >= 0 && playground.getGround()[i][j - 1].getCard() == null &&
                j - 2 >= 0 && playground.getGround()[i][j - 2].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i, j - 2);
        }
        if (i + 1 < 9 && playground.getGround()[i + 1][j].getCard() == null &&
                i + 2 < 9 && playground.getGround()[i + 2][j].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i + 2, j);
        }
        if (j + 1 < 9 && playground.getGround()[i][j + 1].getCard() == null &&
                j + 2 < 9 && playground.getGround()[i][j + 2].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i, j + 2);
        }
        if (j - 1 >= 0 && i - 1 >= 0 && (playground.getGround()[i][j - 1].getCard() == null ||
                playground.getGround()[i - 1][j].getCard() == null) &&
                playground.getGround()[i - 1][j - 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i - 1, j - 1);
        }
        if (j + 1 < 9 && i + 1 < 9 && (playground.getGround()[i][j + 1].getCard() == null ||
                playground.getGround()[i + 1][j].getCard() == null) &&
                playground.getGround()[i + 1][j + 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i + 1, j + 1);
        }
        if (j - 1 >= 0 && i + 1 < 9 && (playground.getGround()[i][j - 1].getCard() == null ||
                playground.getGround()[i + 1][j].getCard() == null) &&
                playground.getGround()[i + 1][j - 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i + 1, j - 1);
        }
        if (j + 1 < 9 && i - 1 >= 0 && (playground.getGround()[i][j + 1].getCard() == null ||
                playground.getGround()[i - 1][j].getCard() == null) &&
                playground.getGround()[i - 1][j + 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i - 1, j + 1);
        }
    }

    abstract public void showInfo();

    public void showTargetThatForceCanAttackTo(Force force, int i, int j) {
        if (!canAttack(force, i, j)) return;
        int x, y;
        for (Integer[] integers : targetPositionCanAttackTo) {
            x = integers[0];
            y = integers[1];
            Show.showTargetThatForceCanAttackTo(x, y);
        }
    }

    public static void initializeItems() {
        collectableItems.add(new Item("Antidote", 0, "Increase 6 HP random force"));
        collectableItems.add(new Item("Fucking arrow", 0, "Increase one random ranged or hybrid force 2 AP"));
        collectableItems.add(new Item("Eٍlixir", 0, "Increase 3 HP and add one power buff with 3 increase AP for random minion"));
        collectableItems.add(new Item("Mana'alectuary", 0, "Increase 3 mana for next turn"));
        collectableItems.add(new Item("Brazen-bodied'alectuary", 0, "Add 10 holy buff to random own force for 2 turn"));
        collectableItems.add(new Item("Death'curse", 0, "Add ability to random minion : ability is hit 8 HP to nearest force on death"));
        collectableItems.add(new Item("Random damage", 0, "Hit 2 HP to random force"));
        collectableItems.add(new Item("Blades of agility", 0, "Increase 6 AP to random force"));
        collectableItems.add(new Item("Chinese sword", 0, "Increase 5 AP to all melee force"));
    }
}
