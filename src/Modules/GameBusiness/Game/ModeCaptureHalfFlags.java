package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;

import java.util.Random;

public class ModeCaptureHalfFlags extends Game {
    public ModeCaptureHalfFlags(Human playerOne, Human playerTwo) {
        super(playerOne, playerTwo);
    }

    public ModeCaptureHalfFlags(Human playerOne, AI playerTwo) {
        super(playerOne, playerTwo);
    }

    @Override
    public void setPlayground() {

    }

    @Override
    protected void checkEnd() {

    }
}
