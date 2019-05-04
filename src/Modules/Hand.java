package Modules;

import Modules.PlayableThings.cards.*;
import View.View.Show;

public class Hand {
    private Deck deck;
    private Card[] hand = new Card[5];
    private Card nextCard;
    private Graveyard graveyard;

    Hand (Deck deck) {
        for (int i = 0; i < 5; i++)
            this.hand[i] = deck.getRandomCard();
        this.nextCard = deck.getRandomCard();
    }

    public void showHand() {
        for (int i = 0; i < 5; i++) {
            if (hand[i] instanceof Spell)
                Show.get().showSpell((Spell) hand[i]);
            if (hand[i] instanceof Minion)
                Show.get().showMinion((Minion) hand[i]);
        }
    }

    public void showNextCard() {
        if (nextCard instanceof Spell)
            Show.get().showSpell((Spell) nextCard);
        if (nextCard instanceof Minion)
            Show.get().showMinion((Minion) nextCard);
    }

    public void insertCard(String cardId, int manaPoint) {
        // TODO
    }

    public void showInsertables(int manaPoint) {
        for (Card card : hand) {
            if (card instanceof Minion) {
                if (((Minion) card).getManaPoint() < manaPoint) {
                    Show.get().showCardId(card);
                }
            } else if (card instanceof Spell) {
                if (((Spell) card).getMP() < manaPoint) {
                    Show.get().showCardId(card);
                }
            }
        }
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }
}
