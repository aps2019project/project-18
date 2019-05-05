package Modules.GameBusiness.Player;


import Modules.Deck;
import Modules.Hand;
import Modules.PlayableThings.cards.Card;

public class AI extends Player {
    int[] enemyHero = new int[2];

    @Override
    public void playTurn(int turn) {
        super.playTurn(turn);
        enemyHero = game.getEnemyHeroPlace();
        Card card = hand.getPutableCard(manaPoint);
        putCard(card);
        handleNextCard();
        aging();
    }

    private void putCard(Card card) {
        game.insertCardNearestToEnemyHero(card);
    }

    public void setDeck(Deck deck){
        hand =new Hand(deck);
    }

}
