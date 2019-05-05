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
        Card[] cards = game.getMyCards();
        attackAndMove(cards);
        while(true) {
            Card card = hand.getPutableCard(manaPoint);
            if (card == null)
                break;
            putCard(card);
        }
        handleNextCard();
        aging();
    }

    private void putCard(Card card) {
        game.insertCardNearestToEnemyHero(card);
    }

    private void attackAndMove(Card[] cards){

    }

    public void setDeck(Deck deck){
        hand =new Hand(deck);
    }

}
