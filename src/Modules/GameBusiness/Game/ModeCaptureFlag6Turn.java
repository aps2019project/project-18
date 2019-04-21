package Modules.GameBusiness.Game;

import Modules.PlayableThings.Item.Flag;

public class ModeCaptureFlag6Turn extends Game {
    @Override
    public void setPlayground() {
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
