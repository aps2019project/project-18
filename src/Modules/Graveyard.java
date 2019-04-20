package Modules;

import Modules.PlayableThings.cards.Card;
import View.View.Show;

import java.util.ArrayList;

public class Graveyard {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void showGraveyard() {
        new Show().showMinions(cards, null);
        new Show().showSpells(cards, null);
    }
}
