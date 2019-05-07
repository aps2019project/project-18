package Modules.GameBusiness.Player;


import Modules.Account;
import Modules.Deck;
import Modules.Hand;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Force;
import Modules.PlayableThings.cards.Minion;
import Modules.PlayableThings.cards.Spell.Spell;

import java.util.ArrayList;

public class AI extends Player {
    int aiLevel;
//i(omid) need this for handling prize

    public void setAiLevel(int aiLevel) {
        this.aiLevel = aiLevel;
    }

    public int getAiLevel() {
        return aiLevel;
    }

    int[] enemyHero;

    public AI(){
        account =new Account();
        account.setUserName("AI");
    }
    @Override
    public void playTurn(int turn) {
        super.playTurn(turn);
        enemyHero = game.getPosition(hand.getHero().getId());
        Force[] forces = game.getMyCards();
        attackAndMove(forces);
        while(true) {
            Card[] cards = hand.getPutableCards(manaPoint);
            if (cards.length == 0)
                break;
            Card card = judgePutCard(cards);
            putCard(card);
        }
        handleNextCard();
        aging();
    }

    private void putCard(Card card) {
        if (game.insertCardNearestToEnemyHero(card))
            manaPoint -= card.getManaPoint();
    }

    private Card judgePutCard(Card[] cards){
        ArrayList<Integer> points = new ArrayList<>();
        for (Card card : cards){
            if (card instanceof Spell) {
                points.add(-333);
                //return card;
            }
            else {
                Minion minion = (Minion) card;
                int point = minion.getAttackPower() * minion.getHitPoint() + minion.getRange() * minion.getRange() + minion.getRange() - minion.getManaPoint();
                points.add(point);
            }
        }
        int max = 0;
        for (int i = 0 ; i < points.size() ; i++){
            if (points.get(i) > points.get(max))
                max = i;
        }
        return cards[max];
    }

    private void attackAndMove(Force[] forces){
        for (Force force : forces){
            if (force.canMove())
                judgeMove(force);
            if (force.canAttack())
                judgeAttack(force);
        }
    }

    private void judgeAttack(Force force){

    }

    public void setDeck(Deck deck){
        hand =new Hand(deck);
    }

    private void judgeMove(Force force) {
        int[][] places = game.getMovablePlaces(force);
        if (places.length == 0)
            return;;
        int[] destination = judgeDestination(places);
        move(force , ((Integer)(destination[0] + 1)).toString() + " " + ((Integer)(destination[1] + 1)).toString());
    }

    private int[] judgeDestination(int[][] places){
        int[] destination = places[0];
        if (places.length == 1)
            return destination;
        for (int i = 1; i < places.length ; i++){
            if (Math.abs(enemyHero[0] - places[i][0]) + Math.abs(enemyHero[1] - places[i][1]) < Math.abs(enemyHero[0] - destination[0]) + Math.abs(enemyHero[1] -destination[1]))
                destination = places[i];
        }
        return destination;
    }

}
