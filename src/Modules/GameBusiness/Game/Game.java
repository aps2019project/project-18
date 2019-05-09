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
    Player[] players = new Player[2];
    private int turn = 0;
    boolean end;
    int winnerPlayer;
    private boolean cancel = false;
    Playground playground = new Playground();
    private static ArrayList<Item> collectableItems = new ArrayList<>();
    private static ArrayList<Integer[]> targetPositionCanAttackTo;

    Game(Player playerOne, Player playerTwo) {
        this.players[0] = playerOne;
        this.players[1] = playerTwo;
    }

    public Player getWinnerPlayer() {
        return players[winnerPlayer - 1];
    }

    public Playground getPlayground() {
        return playground;
    }

    public boolean isEnd() {
        return end;
    }

    public Force[] getAttackableMinions(Force force) {
        ArrayList<Force> result = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() != null &&
                        getEnemyPlayer().checkCard(playground.getGround()[i][j].getCardId()) &&
                        canAttack(force.getId(), playground.getGround()[i][j].getCardId())) {
                    result.add((Force) playground.getGround()[i][j].getCard());
                }
            }
        return (Force[]) result.toArray();
    }

    public void showPlayground() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("(" + (i + 1) + "," + (j + 1) + ") : card - ");
                if (playground.getGround()[i][j].getCard() == null) {
                    System.out.print("null");
                } else {
                    System.out.print(playground.getGround()[i][j].getCardId());
                }
                System.out.print(" , items - ");
                for (Item item : playground.getGround()[i][j].getItem())
                    System.out.print(item.getName() + " ");
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public boolean checkDeath(Force force) {
        if (force.getHitPoint() <= 0)
            return true;
        return false;
    }

    public void turn() {
        prepare();
        if (end) {
            doWhatNeedDoAfterGameEnd();
            return;
        }
        players[turn % 2].playTurn(turn);
        doWhatNeedDoAfterEachTurn();
        turn();
    }

    private void doWhatNeedDoAfterEachTurn() {
        if (cancel && !end) {
            winnerPlayer = (turn + 1) % 2 + 1;
            doWhatNeedDoAfterGameEnd();
        }
        aging();
        turn++;
    }

    private void aging() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() != null)
                    ((Force) playground.getGround()[i][j].getCard()).agging();
            }
        playground.aging();
    }

    private void prepare() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() != null)
                    ((Force) playground.getGround()[i][j].getCard()).prepareForTurn(players[turn % 2].checkCard(playground.getGround()[i][j].getCardId()));
            }
    }

    public int[][] getMovablePlaces(Force force) {
        int[][] movablePlaces = new int[2][13];
        int counter = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                movablePlaces[0][counter] = getPosition(force)[0] + i;
                movablePlaces[1][counter] = getPosition(force)[1] + j;
                counter++;
            }
        }
        movablePlaces[0][counter] = getPosition(force)[0] + 2;
        movablePlaces[1][counter] = getPosition(force)[1];
        counter++;
        movablePlaces[0][counter] = getPosition(force)[0];
        movablePlaces[1][counter] = getPosition(force)[1] + 2;
        counter++;
        movablePlaces[0][counter] = getPosition(force)[0] - 2;
        movablePlaces[1][counter] = getPosition(force)[1];
        counter++;
        movablePlaces[0][counter] = getPosition(force)[0];
        movablePlaces[1][counter] = getPosition(force)[1] - 2;
        int[][] finalResult = new int[2][];
        counter = 0;
        for (int i = 0; i < 9; i++) {
            if (movablePlaces[0][i] >= 0 && movablePlaces[0][i] <= 9 && movablePlaces[1][i] >= 0 &&
                    movablePlaces[1][i] <= 5 && (movablePlaces[0][i] != getPosition(force)[0] ||
                    movablePlaces[1][i] != getPosition(force)[1]) &&
                    playground.getGround()[movablePlaces[0][i]][movablePlaces[1][i]].getCard() == null) {
                finalResult[0][counter] = movablePlaces[0][i];
                finalResult[1][counter] = movablePlaces[1][i];
                counter++;
            }
        }
        if (movablePlaces[0][9] >= 0 && movablePlaces[0][9] <= 9 && movablePlaces[1][9] >= 0 && movablePlaces[1][9] <= 5
                && playground.getGround()[movablePlaces[0][9]][movablePlaces[1][9]] == null &&
                playground.getGround()[movablePlaces[0][9] - 1][movablePlaces[1][9]] == null) {
            finalResult[0][counter] = movablePlaces[0][9];
            finalResult[1][counter] = movablePlaces[1][9];
            counter++;
        }
        if (movablePlaces[0][10] >= 0 && movablePlaces[0][10] <= 9 && movablePlaces[1][10] >= 0 &&
                movablePlaces[1][10] <= 5 && playground.getGround()[movablePlaces[0][10]][movablePlaces[1][10]] == null
                && playground.getGround()[movablePlaces[0][10]][movablePlaces[1][10] - 1] == null) {
            finalResult[0][counter] = movablePlaces[0][10];
            finalResult[1][counter] = movablePlaces[1][10];
            counter++;
        }
        if (movablePlaces[0][11] >= 0 && movablePlaces[0][11] <= 9 && movablePlaces[1][11] >= 0 &&
                movablePlaces[1][11] <= 5 && playground.getGround()[movablePlaces[0][11]][movablePlaces[1][11]] == null
                && playground.getGround()[movablePlaces[0][11] + 1][movablePlaces[1][11]] == null) {
            finalResult[0][counter] = movablePlaces[0][11];
            finalResult[1][counter] = movablePlaces[1][11];
            counter++;
        }
        if (movablePlaces[0][12] >= 0 && movablePlaces[0][12] <= 9 && movablePlaces[1][12] >= 0 &&
                movablePlaces[1][12] <= 5 && playground.getGround()[movablePlaces[0][12]][movablePlaces[1][12]] == null
                && playground.getGround()[movablePlaces[0][12]][movablePlaces[1][12] + 1] == null) {
            finalResult[0][counter] = movablePlaces[0][12];
            finalResult[1][counter] = movablePlaces[1][12];
            counter++;
        }
        return finalResult;
    }

    public boolean insertCardNearestToEnemyHero(Card card) {
        int[] nearestPosition;
        nearestPosition = getInsertablePlaces()[0];
        for (int[] position : getInsertablePlaces()) {
            if (distance(position, getPosition(getEnemyHero())) <
                    distance(nearestPosition, getPosition(getEnemyHero()))) {
                nearestPosition = position;
            }
        }
        return insertCard(card, nearestPosition[0], nearestPosition[1]);
    }

    private int[][] getInsertablePlaces() {
        int[][] result = new int[2][];
        int counter = 0;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() != null &&
                        getMyPlayer().checkCard(playground.getGround()[i][j].getCardId())) {
                    for (int k = -1; k < 2; k++)
                        for (int l = -1; l < 2; l++) {
                            if (checkPlaceValidity(i + k, j + l) &&
                                    playground.getGround()[i + k][j + l].getCard() == null) {
                                result[0][counter] = i + k;
                                result[1][counter] = j + l;
                                counter++;
                            }
                        }
                }
            }
        return result;
    }

    private boolean checkPlaceValidity(int x, int y) {
        return !(x > 9 || x < 0 || y > 5 || y < 0);
    }

    private int distance(int[] position1, int[] position2) {
        return Math.abs(position1[0] - position2[0]) + Math.abs(position1[1] - position2[1]);
    }

    public void attack(Force force, String defenderId) {
        if (canAttack(force.getId(), defenderId)) {
            force.attack(getForce(defenderId), true, canAttack(defenderId, force.getId()));
            if (checkDeath(force)) {
                death(force);
            }
            if (checkDeath(getForce(defenderId))) {
                death(getForce(defenderId));
            }
        } else {
            System.out.println("card is not able to attack");
        }
    }

    public void death(Force force) {
        force.die();
        Item[] items = force.getFlags();
        players[(turn + 1) % 2].loseFlag(items.length);
        for (Item item : items) {
            playground.getGround()[getPosition(force)[0]][getPosition(force)[1]].setItem(item);
        }
        players[(turn + 1) % 2].die(force);
        playground.getGround()[getPosition(force)[0]][getPosition(force)[1]].removeCard();
    }

    public ArrayList<Item> move(Force force, int x, int y) {
        if (!checkPlaceValidity(x - 1, y - 1)) {
            System.out.println("house number out of bound");
            return null;
        }
        if (playground.getGround()[x - 1][y - 1].getCard() != null) {
            System.out.println("destination house is full");
            return null;
        } else if (x - 1 == getPosition(force)[0] && ((getPosition(force)[1] - y + 1 == 2 &&
                playground.getGround()[x - 1][y].getCard() != null) || (getPosition(force)[1] - y + 1 == -2 &&
                playground.getGround()[x - 1][y - 2].getCard() != null))) {
            System.out.println("there is an obstacle in the way, card can not move!");
            return null;
        } else if (y - 1 == getPosition(force)[1] && ((getPosition(force)[0] - x + 1 == 2 &&
                playground.getGround()[x][y - 1].getCard() != null) || (getPosition(force)[0] - x + 1 == -2 &&
                playground.getGround()[x - 2][y - 1].getCard() != null))) {
            System.out.println("there is an obstacle in the way, card can not move!");
            return null;
        } else if (Math.abs(getPosition(force)[0] - x + 1) + Math.abs(getPosition(force)[1] - y + 1) > 2) {
            System.out.println("destination too far");
            return null;
        } else {
            playground.move(getPosition(force)[0], getPosition(force)[1], x - 1, y - 1);
            force.moved();
            System.out.println("card moved");
            ArrayList<Item> items = playground.getGround()[x - 1][y - 1].getItem();
            playground.getGround()[x - 1][y - 1].removeItems();
            return items;
        }
    }

    public void comboAttack(Force force, String command) {
        String[] splittedCommand = command.split(" ");
        Force enemyForce = getForce(splittedCommand[0]);
        if (!canAttack(force.getId(), splittedCommand[0])) {
            System.out.println("card can not attack");
            return;
        } else if (((Minion) force).hasComboAttack()) {
            attack(force, splittedCommand[0]);
            if (!getEnemyPlayer().checkCard(splittedCommand[0])) {
                System.out.println("target card does not belong to enemy");
                return;
            }
            if (getForce(splittedCommand[0]) == null) {
                System.out.println("target card is not on the ground");
                return;
            }
            for (int i = 1; i < splittedCommand.length; i++) {
                if (!getMyPlayer().checkCard(splittedCommand[i])) {
                    System.out.println("one of the selected cards is not your card");
                    return;
                }
                if (getForce(splittedCommand[i]) == null) {
                    System.out.println("one of the selected cards is not on the ground");
                    return;
                }
                if (!canAttack(splittedCommand[i], splittedCommand[0]) || !getForce(splittedCommand[i]).getCanAttack()) {
                    System.out.println("one of the selected cards can not attack target card");
                    return;
                }
            }
            for (int i = 1; i < splittedCommand.length; i++) {
                getForce(splittedCommand[i]).attack(enemyForce, false, false);
            }
        } else {
            attack(force, splittedCommand[0]);
        }
    }

    public boolean canAttack(String attackerId, String defenderId) {
        if (getForce(attackerId).getAttackType().equals("melee")) {
            if (distance(attackerId, defenderId) == 1 || (distance(attackerId, defenderId) == 2 &&
                    (getPosition(attackerId)[0] != getPosition(defenderId)[0] ||
                            getPosition(attackerId)[1] != getPosition(defenderId)[1]))) {
                return true;
            } else
                return false;
        } else if (getForce(attackerId).getAttackType().equals("ranged")) {
            if (distance(attackerId, defenderId) > 1 &&
                    distance(attackerId, defenderId) <= getForce(attackerId).getRange())
                return true;
            else
                return false;
        } else {
            if (distance(attackerId, defenderId) <= getForce(attackerId).getRange())
                return true;
            else
                return false;
        }
    }

    public int[] getPosition(String cardId) {
        int[] result = new int[2];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 5; j++)
                if (playground.getGround()[i][j].getCard() != null && playground.getGround()[i][j].getCard().getId().equals(cardId)) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
        return result;
    }

    private int[] getPosition(Force force) {
        return getPosition(force.getId());
    }

    private int distance(String firstCardId, String secondCardId) {
        int[] firstCardPosition, secondCardPosition;
        firstCardPosition = getPosition(firstCardId);
        secondCardPosition = getPosition(secondCardId);
        return Math.abs(firstCardPosition[0] - secondCardPosition[0]) + Math.abs(firstCardPosition[1] - secondCardPosition[1]);
    }

    public Force getForce(String id) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() != null && playground.getGround()[i][j].getCardId().equals(id)) {
                    return (Force) playground.getGround()[i][j].getCard();
                }
            }
        }
        return null;
    }

    public Force[] getMyCards() {
        ArrayList<Force> cards = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j] != null &&
                        players[turn % 2].checkCard(playground.getGround()[i][j].getCardId()))
                    cards.add((Force) playground.getGround()[i][j].getCard());
            }
        return (Force[]) cards.toArray();
    }

    public void cancelGame() {
        cancel = true;
    }

    public boolean insertCard(Card card, int x, int y) {
        if (x - 1 >= 0 && x - 1 < 9 && y - 1 >= 0 && y - 1 < 9) {
            if (playground.getGround()[x - 1][y - 1] == null) {
                if (card instanceof Spell) {
                    Spell spell = (Spell) card;
                    /*if (canExecuteSpell()) {
                        //todo
                        spell.execute();
                        players[turn % 2].die(spell);
                        return true;
                    }*/
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
        handlePrize();
    }

    private void handlePrize() {
        if (players[winnerPlayer - 1] instanceof AI) return;
        if (players[winnerPlayer % 2] instanceof Human) {
            players[winnerPlayer - 1].getAccount().winReward(100);
            return;
        }
        AI ai = (AI) players[winnerPlayer % 2];
        switch (ai.getAiLevel()) {
            case 0:
            case 2:
                players[winnerPlayer - 1].getAccount().winReward(1000);
                return;
            case 1:
                players[winnerPlayer - 1].getAccount().winReward(500);
                return;
            case 3:
                players[winnerPlayer - 1].getAccount().winReward(1500);
        }
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

    abstract public void checkEnd();

    private Force getPlayerForce(int x, int y) {
        Player player = players[turn % 2];
        //player should have card to move it
        if (playground.getGround()[x][y].getCard() != null && player.checkCard(playground.getGround()[x][y].getCard().getId())) {
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
                showCardInfo(getEnemyForce(i, j).getId());
            }
        }
    }

    public void showMyMinions() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (getEnemyForce(i, j) == null) continue;
                showCardInfo(getEnemyForce(i, j).getId());
            }
        }
    }

    private Player getEnemyPlayer() {
        return players[(turn + 1) % 2];
    }

    private Player getMyPlayer() {
        return players[turn % 2];
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
                        showCardInfo(getPlayerForce(i, j).getId());
                    }
                }
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public void showAttackAbleCards() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (getPlayerForce(i, j) == null) continue;
                if (!getPlayerForce(i, j).getCanAttack()) continue;
                if (canAttack(getPlayerForce(i, j), i, j)) {
                    showCardInfo(getPlayerForce(i, j).getId());
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

    public void useItem(Item item, int x, int y) {
        item.execute();//todo handle daghigh
    }

    public void showCardInfo(String id) {
        playground.getCard(id).showCard();
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
        if (j + 1 < 5 && playground.getGround()[i][j + 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i, j + 1);
        }
        if (j + 1 < 5 && playground.getGround()[i + 1][j].getCard() == null) {
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
        if (j + 1 < 5 && playground.getGround()[i][j + 1].getCard() == null &&
                j + 2 < 5 && playground.getGround()[i][j + 2].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i, j + 2);
        }
        if (j - 1 >= 0 && i - 1 >= 0 && (playground.getGround()[i][j - 1].getCard() == null ||
                playground.getGround()[i - 1][j].getCard() == null) &&
                playground.getGround()[i - 1][j - 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i - 1, j - 1);
        }
        if (j + 1 < 5 && i + 1 < 9 && (playground.getGround()[i][j + 1].getCard() == null ||
                playground.getGround()[i + 1][j].getCard() == null) &&
                playground.getGround()[i + 1][j + 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i + 1, j + 1);
        }
        if (j - 1 >= 0 && i + 1 < 9 && (playground.getGround()[i][j - 1].getCard() == null ||
                playground.getGround()[i + 1][j].getCard() == null) &&
                playground.getGround()[i + 1][j - 1].getCard() == null) {
            Show.showTargetThatForceCanMoveTo(i + 1, j - 1);
        }
        if (j + 1 < 5 && i - 1 >= 0 && (playground.getGround()[i][j + 1].getCard() == null ||
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
        collectableItems.add(new Item("FuckingArrow", 0, "Increase one random ranged or hybrid force 2 AP"));
        collectableItems.add(new Item("Eٍlixir", 0, "Increase 3 HP and add one power buff with 3 increase AP for random minion"));
        collectableItems.add(new Item("Mana'alectuary", 0, "Increase 3 mana for next turn"));
        collectableItems.add(new Item("Brazen-bodied'alectuary", 0, "Add 10 holy buff to random own force for 2 turn"));
        collectableItems.add(new Item("Death'curse", 0, "Add ability to random minion : ability is hit 8 HP to nearest force on death"));
        collectableItems.add(new Item("RandomDamage", 0, "Hit 2 HP to random force"));
        collectableItems.add(new Item("BladesOfAgility", 0, "Increase 6 AP to random force"));
        collectableItems.add(new Item("ChineseSword", 0, "Increase 5 AP to all melee force"));
    }

    public int getTurn() {
        return turn;
    }
}
