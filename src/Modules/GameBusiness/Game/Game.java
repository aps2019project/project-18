package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.GameBusiness.Player.Player;
import Modules.GameData;
import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Force;
import Modules.Playground;

import java.util.ArrayList;
import java.util.Random;

public abstract class Game {
    Player playerOne;
    Player playerTwo;
    private int turn = 0;
    boolean end;
    int winnerPlayer;
    Playground playground = new Playground();
    private static ArrayList<Item> collectableItems = new ArrayList<>();

    Game(Human playerOne, Human playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    Game(Human playerOne, AI playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void turn() {
        if (end) {
            doWhatNeedDoAfterGameEnd();
            return;
        }
        if (turn % 2 == 0) {
            playerOne.playTurn();
        } else {
            playerTwo.playTurn();
        }
        doWhatNeedDoAfterEachTurn();
    }

    private void doWhatNeedDoAfterEachTurn() {
        turn++;
        checkEnd();
    }

    private void doWhatNeedDoAfterGameEnd() {
        saveData();
    }

    private void saveData() {
        String playerOneName = playerOne.getAccount().getUserName();
        String playerTwoName = playerTwo.getAccount().getUserName();
        String matchResultPlayerOne, matchResultPlayerTwo;
        if (winnerPlayer == 1) {
            matchResultPlayerOne = "win";
            matchResultPlayerTwo = "lose";
        } else {
            matchResultPlayerOne = "lose";
            matchResultPlayerTwo = "win";
        }
        playerOne.getAccount().saveGameData(new GameData(playerTwoName, matchResultPlayerOne));
        playerTwo.getAccount().saveGameData(new GameData(playerOneName, matchResultPlayerTwo));
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

    abstract public void setPlayground();

    abstract protected void checkEnd();

    private Force getPlayerForce(int x, int y) {
        String userNamePlayerWhoHaveTurn;
        if (turn % 2 == 0) {
            userNamePlayerWhoHaveTurn = playerOne.getAccount().getUserName();
        } else {
            userNamePlayerWhoHaveTurn = playerTwo.getAccount().getUserName();
        }
        //player should have card to move it
        if (playground.getGround()[x][y].getCard().getId().contains(userNamePlayerWhoHaveTurn)) {
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
        if (canAttackMeleeForce(x, y)) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Math.abs(x - i) + Math.abs(y - j) <= force.getRange()) {
                    if (getEnemyForce(i, j) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canAttackMeleeForce(int x, int y) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if ((x - i == 1 || i - x == 1) && (y - j == 1 || j - y == 1)) {
                    if (getEnemyForce(i, j) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canAttackHybridForce(Force force, int x, int y) {
        if (canAttackMeleeForce(x, y) || canAttackRangeForce(force, x, y)) {
            return true;
        }
        return false;
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

    public void ShowAttackAbleCards() {
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
}
