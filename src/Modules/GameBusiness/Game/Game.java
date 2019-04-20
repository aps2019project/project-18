package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.GameBusiness.Player.Player;
import Modules.GameData;
import Modules.PlayableThings.Item.Item;
import Modules.Playground;

import java.util.ArrayList;
import java.util.Random;

public abstract class Game {
    protected Player playerOne;
    protected Player playerTwo;
    protected int turn;
    protected boolean end;
    protected int winnerPlayer;
    protected Playground playground = new Playground();
    private static ArrayList<Item> collectableItems = new ArrayList<>();

    public Game(Human playerOne, Human playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Game(Human playerOne, AI playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void turn() {
        //todo
    }

    public void saveData() {
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

    public static Item getRandomCollectableItem(){
        return collectableItems.get(new Random().nextInt(collectableItems.size()));
    }
    abstract public void setPlayground();

    abstract protected void checkEnd();
}
