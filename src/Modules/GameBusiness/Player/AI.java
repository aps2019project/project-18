package Modules.GameBusiness.Player;


import Modules.Deck;
import Modules.Hand;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Minion;
import Modules.PlayableThings.cards.Spell.Spell;

import java.util.ArrayList;

public class AI extends Player {
    int[] enemyHero = new int[2];

    @Override
    public void playTurn(int turn) {
        super.playTurn(turn);
        enemyHero = game.getEnemyHeroPlace();
        Card[] cards = game.getMyCards();
        attackAndMove(cards);
        while(true) {
            cards = hand.getPutableCards(manaPoint);
            if (cards.length == 0)
                break;
            Card card = judgePutCard(cards);
            putCard(card);
        }
        handleNextCard();
        aging();
    }

    private void putCard(Card card) {
        game.insertCardNearestToEnemyHero(card);
    }

    private Card judgePutCard(Card[] cards){
        ArrayList<Integer> points = new ArrayList<>();
        for (Card card : cards){
            if (card instanceof Spell)
                return card;
            Minion minion = (Minion)card;
            int point = minion.getAttackPower() * minion.getHitPoint() + minion.getRange() * minion.getRange() + minion.getRange() - minion.getManaPoint();
            points.add(point);
        }
        int max = 0;
        for (int i = 0 ; i < points.size() ; i++){
            if (points.get(i) > points.get(max))
                max = i;
        }
        return cards[max];
    }

    private void attackAndMove(Card[] cards){

    }

    public void setDeck(Deck deck){
        hand =new Hand(deck);
    }

}
