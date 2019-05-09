package Modules;

import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Force;

import java.util.ArrayList;

public class House {
    private Force force;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Integer> fireTurns = new ArrayList<>();
    private ArrayList<Integer> poisonTurns = new ArrayList();
    private ArrayList<Integer> holyTurns = new ArrayList();

    public String getCardId() {
        return force.getId();
    }

    public Card getCard() {
        return force;
    }

    public void excuteBuffs() {
        if (force != null) {
            force.setHitPoint(force.getHitPoint() - poisonTurns.size() - holyTurns.size()*2);
        }
    }

    public ArrayList<Item> getItem() {
        return items;
    }

    public int getHolyCount() {
        return holyTurns.size();
    }

    public void setCard(Card card) {
        this.force = (Force)card;
    }

    public void setItem(Item item) {
        this.items.add(item);
    }

    public void removeCard() {
        force = null;
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
        if (force == null) {
            return false;
        }
        return true;
    }

    public void aging() {
        for (int i = fireTurns.size() - 1; i >= 0 ; i--) {
            fireTurns.set(i , fireTurns.get(i)-1);
            if (fireTurns.get(i) == 0)
                fireTurns.remove(i);
        }
        for (int j = poisonTurns.size() - 1; j >= 0 ; j--) {
            poisonTurns.set(j , poisonTurns.get(j)-1);
            if (poisonTurns.get(j) == 0)
                poisonTurns.remove(j);
        }
        for (int k = holyTurns.size() - 1; k >= 0 ; k--) {
            holyTurns.set(k , holyTurns.get(k)-1);
            if (holyTurns.get(k) == 0)
                holyTurns.remove(k);
        }
    }
}
