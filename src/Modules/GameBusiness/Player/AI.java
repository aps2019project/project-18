package Modules.GameBusiness.Player;


import Modules.PlayableThings.cards.Card;

public class AI extends Player {
    Card[] putableCards;
    int[] enemyHero = new int[2];

    @Override
    public void playTurn(int turn) {
        super.playTurn(turn);
        putableCards = hand.getPutableCards(manaPoint);
        putCards();
    }

    private void putCards() {
        for (Card card : putableCards){
            int[][] placees= game.placesCanPut(this);
        }
    }

}
