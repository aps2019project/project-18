package Modules.GameBusiness.Player;


import Modules.Deck;
import Modules.PlayableThings.cards.Card;

public class AI extends Player {
    Card[] putableCards;
    int[] enemyHero = new int[2];

    @Override
    public void playTurn(int turn) {
        enemyHero = game.getEnemyHeroPlace();
        super.playTurn(turn);
        putableCards = hand.getPutableCards(manaPoint);
        putCards();
    }

    private void putCards() {
        for (Card card : putableCards){
            int[][] placees= game.placesCanPut(this);
        }
    }

    public void setDeck(Deck deck){
        hand.setDeck();
    }

}
