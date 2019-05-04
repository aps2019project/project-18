package Modules;

import Modules.PlayableThings.cards.*;
import Modules.PlayableThings.cards.Spell.Spell;
import View.View.Show;

public class Hand {
    private Deck deck;
    private Card[] hand = new Card[5];
    private Card nextCard;
    private Graveyard graveyard;

    public Hand (Deck deck) {
        this.deck = deck;
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

    public Card insertCard(String cardId, int manaPoint) {
        for (int i = 0; i < 5; i++) {
            if (hand[i].getId().equals(cardId)) {
                if (hand[i].getManaPoint() <= manaPoint)
                    return hand[i];
                else
                    Show.get().notEnoughManaMessage();
            }
        }
        Show.get().cardNotInHandMessage();
        return null;
    }

    public void deleteCard(Card card) {
        for (int i = 0; i < 5; i++) {
            if (hand[i] == card) {
                hand[i] = null;
                break;
            }
        }
    }

    public void showInsertables(int manaPoint) {
        for (Card card : hand) {
            if (card.getManaPoint() < manaPoint) {
                Show.get().showCardId(card);
            }
        }
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }

    public void handleNextCard() {
        for (int i = 0; i < 5; i++)
            if (this.hand[i] == null) {
                this.hand[i] = this.nextCard;
                this.nextCard = deck.getRandomCard();
            }
    }
}
