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

    }
}
