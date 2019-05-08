package Modules;

import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;

import java.util.ArrayList;

public class House {
    private Card card;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Integer> fireTurns = new ArrayList<>();
    private ArrayList<Integer> poisonTurns = new ArrayList();
    private ArrayList<Integer> holyTurns = new ArrayList();

    public String getCardId() {
        return card.getId();
    }

    public Card getCard() {
        return card;
    }

    public ArrayList<Item> getItem() {
        return items;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setItem(Item item) {
        this.items.add(item);
    }

    public void removeCard() {
        card = null;
    }

    public void removeItems() {
        items = new ArrayList<>();
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

    public boolean isCardOnIt() {
        if (card == null && items.size() == 0) {
            return false;
        }
        return true;
    }
}
