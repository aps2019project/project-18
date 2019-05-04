package Modules.GameBusiness.Player;


import Modules.Deck;
import Modules.Hand;
import Modules.PlayableThings.cards.Card;

public class AI extends Player {
    Card[] putableCards;
    int[] enemyHero = new int[2];

    @Override
    public void playTurn(int turn) {
        super.playTurn(turn);
        enemyHero = game.getEnemyHeroPlace();
        putableCards = hand.getPutableCards(manaPoint);
        putCards();
        handleNextCard();
    }

    private void putCards() {
        for (Card card : putableCards){
            int[][] placees= game.placesCanPut(this);
        }
    }

    public void setDeck(Deck deck){
        hand =new Hand(deck);
    }

}
