package Modules;

import Modules.PlayableThings.cards.*;
import Modules.PlayableThings.cards.Spell.Spell;
import View.View.Show;

import java.util.ArrayList;

public class Graveyard {
    private ArrayList<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void showGraveyard() {
        Show.get().showMinions(cards, null);
        Show.get().showSpells(cards, null);
    }

    public void showCard(String cardId) {
        for (Card card : cards) {
            if (card.getId().equals(cardId)) {
                if (card instanceof Minion) {
                    Show.get().showMinion((Minion) card);
                } else {
                    Show.get().showSpell((Spell) card);
                }
            }
        }
    }
}
