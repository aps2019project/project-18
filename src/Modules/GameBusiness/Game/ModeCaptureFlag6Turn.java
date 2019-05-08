package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.PlayableThings.Item.Flag;
import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Force;

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
        playground.getGround()[0][2].setCard(players[0].getHeroCard());
        playground.getGround()[8][2].setCard(players[1].getHeroCard());
        playground.getGround()[4][2].setItem(new Flag());
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
        if (players[0].getNumberOfTurnPlayerHaveFlag() >= 6) {
            winnerPlayer = 1;
            end = true;
            return;
        }
        if (players[1].getNumberOfTurnPlayerHaveFlag() >= 6) {
            winnerPlayer = 2;
            end = true;
        }
    }

    @Override
    public void showInfo() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getItem() == null) continue;
                for (Item item : playground.getGround()[i][j].getItem()) {
                    if (item.getName().equals("flag")) {
                        System.out.println("(" + i + "," + j + ")");
                        return;
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() == null) continue;
                Force force = (Force) playground.getGround()[i][j].getCard();
                if (force.getFlags() == null || force.getFlags().length == 0) continue;
                System.out.println(force.getId() + " (" + i + "," + j + ")");
                return;
            }
        }
    }

}
