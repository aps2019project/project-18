package Modules.PlayableThings;

import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;

import java.util.ArrayList;

public class House {
    private Card card;
    private Item item;
    private ArrayList<Integer> fireTurns = new ArrayList<>();
    private ArrayList<Integer> poisonTurns = new ArrayList();
    private ArrayList<Integer> holyTurns = new ArrayList();

    public String getCardId() {
        return card.getId();
    }

    public Card getCard() {
        return card;
    }

    public Item getItem() {
        return item;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void removeCard() {
        card = null;
    }

    public void removeItem() {
        item = null;
    }

    public void addFireBuff(int numberOfFireTurn) {
        fireTurns.add(numberOfFireTurn);
    }

    public void addPoisonBuff(int numberOfPoisonTurn) {
        poisonTurns.add(numberOfPoisonTurn);
    }

    public void addHolyBuff(int numberOfHolyTurn) {
        holyTurns.add(numberOfHolyTurn);
    }
}
