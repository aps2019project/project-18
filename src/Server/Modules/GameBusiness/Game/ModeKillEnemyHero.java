package Server.Modules.GameBusiness.Game;

import Server.Modules.GameBusiness.Player.AI;
import Server.Modules.GameBusiness.Player.Human;
import Server.Modules.PlayableThings.cards.Card;
import Server.Modules.PlayableThings.cards.Hero;

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
        playground.getGround()[0][2].setCard(players[0].getHeroCard());
        playground.getGround()[8][2].setCard(players[1].getHeroCard());
        for (int i = 0; i < 3; i++) {
            indexX = new Random().nextInt(9);
            indexY = new Random().nextInt(5);
            while (playground.getGround()[indexX][indexY].isCardOnIt()) {
                indexX = new Random().nextInt(9);
                indexY = new Random().nextInt(5);
            }
            playground.getGround()[indexX][indexY].setItem(getRandomCollectableItem());
        }
    }

    @Override
    public void checkEnd() {
        if (players[0].getHeroCard().getHitPoint() <= 0) {
            winnerPlayer = 2;
            end = true;
        } else if (players[1].getHeroCard().getHitPoint() <= 0) {
            winnerPlayer = 1;
            end = true;
        }
    }

    @Override
    public void showInfo() {
        Card card;
        System.out.println(players[0].getAccount().getUserName() + " mana : " + players[0].getManaPoint());
        System.out.println(players[1].getAccount().getUserName() + " mana : " + players[1].getManaPoint());
        System.out.println("turn : " + players[this.getTurn() % 2].getAccount().getUserName());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                card = playground.getGround()[i][j].getCard();
                if (card instanceof Hero) {
                    System.out.println("Player have hero : " + card.getPlayerUserNameWhoHaveThisCard() + " - Hero HP : " + ((Hero) card).getHitPoint());
                }
            }
        }
        showPlayground();
    }
}
