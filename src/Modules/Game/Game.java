package Modules.Game;

import Modules.GameBusiness.Player.Player;
import Modules.GameData;
import Modules.PlayableThings.Item.Item;

import java.util.ArrayList;

public abstract class Game {
    protected Player playerOne = new Player();
    protected Player playerTwo = new Player();
    protected int turn;
    protected boolean end;
    protected int winnerPlayer;
    private static ArrayList<Item> collectableItems = new ArrayList<>();

     public void turn(){
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

    abstract public void setPlayground();

    abstract protected void checkEnd();
}
