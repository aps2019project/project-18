package Modules;

import View.View.Show;
import Modules.PlayableThings.cards.*;
import Modules.PlayableThings.Item.*;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private String name;
    private Hero hero;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Item item = null;

    public Deck(String name) {
        this.name = name;
    }

    private Deck(String name, Hero hero, ArrayList<Card> cards, Item item) {
        this.name = name;
        this.hero = hero;
        this.cards = cards;
        this.item = item;
    }

    public Card findCard(String cardId) {
        for (Card card : cards) {
            if (card.getId().compareTo(cardId) == 0) {
                return card;
            }
        }
        return null;
    }

    public void addCard(Card card) {
        if (card instanceof Hero) {
            if (hero == null) {
                hero = (Hero) card;
                Show.get().cardAddMessage();
                return;
            } else {
                Show.get().deckHasAHeroMessage();
                return;
            }
        } else {
            if (cards.size() < 20 && findCard(card.getId()) == null) {
                cards.add(card);
                Show.get().cardAddMessage();
                return;
            } else if (cards.size() == 20) {
                Show.get().fullDeckMessage();
                return;
            } else {
                Show.get().cardInDeckMessage();
                return;
            }
        }
    }

    public void removeCard(Card card) {
        if (findCard(card.getId()) == null) {
            Show.get().cardNotInDeckMessage();
            return;
        } else {
            cards.remove(card);
            Show.get().cardRemovedMessage();
            return;
        }
    }

    public void addItem(Item item) {
        if (this.item == null) {
            this.item = item;
            Show.get().itemAddMessage();
            return;
        } else {
            Show.get().deckHasAnItemMessage();
            return;
        }
    }

    public void removeItem(Item item) {
        if (this.item.getItemId().compareTo(item.getItemId()) == 0) {
            this.item = null;
            Show.get().itemRemovedMessage();
            return;
        } else {
            Show.get().itemNotInDeckMessage();
            return;
        }
    }

    public boolean checkValidity() {
        if (hero == null || cards.size() < 20)
            return false;
        return true;
    }

    public void showDeck() {
        Show.get().showDeckName(this.name);
        if (hero != null) {
            Show.get().showHero(hero, null);
        }
        if (cards.size() > 0) {
            Show.get().showMinions(cards, null);
            Show.get().showSpells(cards, null);
        }
        if (item != null) {
            Show.get().showItem(item, null);
        }
    }

    public Card getRandomCard() {
        Random random = new Random();
        int i = random.nextInt(cards.size());
        Card card = cards.get(i);
        cards.remove(i);
        return card;
    }

    public Deck getDeckCopy() {
        return new Deck(this.name, this.hero, this.cards, this.item);
    }

    public String getName() {
        return this.name;
    }

    public Hero getHero() {
        return hero;
    }

    public boolean equals(Deck deck) {
        if (this.name.equals(deck.getName()))
            return true;
        return false;
    }
}
