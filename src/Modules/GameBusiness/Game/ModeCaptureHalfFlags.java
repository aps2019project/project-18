package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;

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

    }

    @Override
    protected void checkEnd() {

    }
}
