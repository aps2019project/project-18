package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.PlayableThings.Item.Flag;

public class ModeCaptureFlag6Turn extends Game {

    public ModeCaptureFlag6Turn(Human playerOne, Human playerTwo) {
        super(playerOne, playerTwo);
        setPlayground();
    }

    public ModeCaptureFlag6Turn(Human playerOne, AI playerTwo) {
        super(playerOne, playerTwo);
        setPlayground();
    }

    @Override
    void setPlayground() {
        playground.getGround()[0][2].setCard(playerOne.getHeroCard());
        playground.getGround()[8][2].setCard(playerTwo.getHeroCard());
        playground.getGround()[4][2].setItem(new Flag());
    }

    @Override
    protected void checkEnd() {
        if (playerOne.getHeroCard().getHitPoint() <= 0) {
            winnerPlayer = 2;
            end = true;
            return;
        }
        if (playerTwo.getHeroCard().getHitPoint() <= 0) {
            winnerPlayer = 1;
            end = true;
            return;
        }
        if (playerOne.getNumberOfTurnPlayerHaveFlag() >= 6) {
            winnerPlayer = 1;
            end = true;
            return;
        }
        if (playerTwo.getNumberOfTurnPlayerHaveFlag() >= 6) {
            winnerPlayer = 2;
            end = true;
        }
    }
}
