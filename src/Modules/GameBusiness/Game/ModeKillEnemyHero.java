package Modules.GameBusiness.Game;

import Modules.GameBusiness.Player.AI;
import Modules.GameBusiness.Player.Human;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Hero;

import java.util.Random;

public class ModeKillEnemyHero extends Game {

    public ModeKillEnemyHero(Human playerOne, Human playerTwo) {
        super(playerOne, playerTwo);
        setPlayground();
    }

    public ModeKillEnemyHero(Human playerOne, AI playerTwo) {
        super(playerOne, playerTwo);
        setPlayground();
    }

    @Override
    void setPlayground() {
        int indexX, indexY;
        playground.getGround()[0][2].setCard(playerOne.getHeroCard());
        playground.getGround()[8][2].setCard(playerTwo.getHeroCard());
        for (int i = 0; i < 3; i++) {
            indexX = new Random().nextInt(9);
            indexY = new Random().nextInt(5);
            while (playground.getGround()[indexX][indexY].isCardOnIt()) {
                indexX = new Random().nextInt(9);
                indexY = new Random().nextInt(5);
            }
            playground.getGround()[indexX][indexY].setItem(Game.getRandomCollectableItem());
        }
    }

    @Override
    protected void checkEnd() {
        if (playerOne.getHeroCard().getHitPoint() <= 0) {
            winnerPlayer = 2;
            end = true;
        } else if (playerTwo.getHeroCard().getHitPoint() <= 0) {
            winnerPlayer = 1;
            end = true;
        }
    }

    @Override
    public void showInfo() {
        Card card;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                card = playground.getGround()[i][j].getCard();
                if (card instanceof Hero) {
                    System.out.println("Player have hero : " + card.getPlayerUserNameWhoHaveThisCard() + "Hero HP" + ((Hero) card).getHitPoint());
                }
            }
        }
    }
}
