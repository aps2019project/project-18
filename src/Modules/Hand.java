package Modules;

import Modules.PlayableThings.cards.*;
import View.View.Show;

public class Hand {
    private Deck deck;
    private Card[] hand = new Card[5];
    private Card nextCard;
    private Hero hero;
    private Graveyard graveyard;

    public void showHand() {
        for (int i = 0; i < 5; i++) {
            if (hand[i] instanceof Spell)
                new Show().showSpell((Spell) hand[i]);
            if (hand[i] instanceof Minion)
                new Show().showMinion((Minion) hand[i]);
        }
    }

    public void showNextCard() {
        if (nextCard instanceof Spell)
            new Show().showSpell((Spell) nextCard);
        if (nextCard instanceof Minion)
            new Show().showMinion((Minion) nextCard);
    }

    public void insertCard(String cardId, int manaPoint) {
        // TODO
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }
}
