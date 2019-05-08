package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.PlayableThings.Item.Flag;

import java.util.Random;

public class ModeCaptureHalfFlags extends Game {
    private int numberOfFlags = 7;

    public ModeCaptureHalfFlags(Human playerOne, Human playerTwo) {
        super(playerOne, playerTwo);
        setPlayground();
    }

    public ModeCaptureHalfFlags(Human playerOne, AI playerTwo) {
        super(playerOne, playerTwo);
        setPlayground();
    }

    public ModeCaptureHalfFlags(Human playerOne, Human playerTwo, int numberOfFlags) {
        super(playerOne, playerTwo);
        this.numberOfFlags = numberOfFlags;
        setPlayground();
    }

    public ModeCaptureHalfFlags(Human playerOne, AI playerTwo, int numberOfFlags) {
        super(playerOne, playerTwo);
        this.numberOfFlags = numberOfFlags;
        setPlayground();
    }

    @Override
    void setPlayground() {
        int indexX, indexY;
        playground.getGround()[0][2].setCard(players[0].getHeroCard());
        playground.getGround()[8][2].setCard(players[1].getHeroCard());
        for (int i = 0; i < numberOfFlags && i < 43; i++) {
            indexX = new Random().nextInt(9);
            indexY = new Random().nextInt(5);
            while (playground.getGround()[indexX][indexY].isCardOnIt()) {
                indexX = new Random().nextInt(9);
                indexY = new Random().nextInt(5);
            }
            playground.getGround()[indexX][indexY].setItem(new Flag());
        }
    }

    @Override
    protected void checkEnd() {
        if (players[0].getHeroCard().getHitPoint() <= 0) {
            winnerPlayer = 2;
            end = true;
            return;
        }
        if (players[1].getHeroCard().getHitPoint() <= 0) {
            winnerPlayer = 1;
            end = true;
            return;
        }
        if (players[0].getNumberOfFlag() >= numberOfFlags / 2) {
            winnerPlayer = 1;
            end = true;
            return;
        }
        if (players[1].getNumberOfFlag() >= numberOfFlags / 2) {
            winnerPlayer = 2;
            end = true;
        }
    }

    public void showInfo(){
        //todo
    }
}
