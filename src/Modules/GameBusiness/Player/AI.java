package Modules.GameBusiness.Player;


import Modules.Account;
import Modules.Deck;
import Modules.Hand;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Force;
import Modules.PlayableThings.cards.Hero;
import Modules.PlayableThings.cards.Minion;
import Modules.PlayableThings.cards.Spell.Spell;

import java.util.ArrayList;

public class AI extends Player {
    int aiLevel = 0;
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
        ArrayList<Force> forces = game.getMyCards();
        attackAndMove(forces);
        while(true) {
            ArrayList<Card> cards = hand.getPutableCards(manaPoint);
            if (cards.size() == 0)
                break;
            else if (cards.size() == 1 && cards.get(0) instanceof Spell)
                break;
            Card card = judgePutCard(cards);
            putCard(card);
        }
        handleNextCard();
        aging();
    }

    private void putCard(Card card) {
        if (game.insertCardNearestToEnemyHero(card)) {
            manaPoint -= card.getManaPoint();
        }
    }

    private Card judgePutCard(ArrayList<Card> cards){
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
        return cards.get(max);
    }

    private void attackAndMove(ArrayList<Force> forces){
        for (Force force : forces){
            if (force.canMove())
                judgeMove(force);
            if (force.canAttack())
                judgeAttack(force);
        }
    }

    private void judgeAttack(Force force){
        Force[] defenders = game.getAttackableMinions(force);
        for (Force force1 : defenders) {
            if (force1 instanceof Hero) {
                game.attack(force, force1.getId());
                game.checkEnd();
                return;
            }
        }
        if (defenders.length == 0)
            return;
        for (Force force1 : defenders){
            if (force1.getHitPoint() <= force.getAttackPower()) {
                game.attack(force, force1.getId());
                game.checkEnd();
                return;
            }
        }
        game.attack(force , defenders[0].getId());
        game.checkEnd();
    }

    public void setDeck(Deck deck){
        hand = new Hand(deck);
    }

    private void judgeMove(Force force) {
        int[][] places = game.getMovablePlaces(force);
        if (places.length == 0)
            return;
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
