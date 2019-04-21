package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.PlayableThings.Item.Flag;

import java.util.Random;

public class ModeCaptureHalfFlags extends Game {
    private int numberOfFlags = 7;

    public ModeCaptureHalfFlags(Human playerOne, Human playerTwo) {
        super(playerOne, playerTwo);
    }

    public ModeCaptureHalfFlags(Human playerOne, AI playerTwo) {
        super(playerOne, playerTwo);
    }

    public ModeCaptureHalfFlags(Human playerOne, Human playerTwo, int numberOfFlags) {
        super(playerOne, playerTwo);
        this.numberOfFlags = numberOfFlags;
    }

    public ModeCaptureHalfFlags(Human playerOne, AI playerTwo, int numberOfFlags) {
        super(playerOne, playerTwo);
        this.numberOfFlags = numberOfFlags;
    }

    @Override
    public void setPlayground() {
        int indexX, indexY;
        playground.getGround()[0][3].setCard(playerOne.getHeroCard());
        playground.getGround()[8][3].setCard(playerTwo.getHeroCard());
        for (int i = 0; i < numberOfFlags; i++) {
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

    }
}
