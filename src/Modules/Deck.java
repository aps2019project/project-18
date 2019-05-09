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

    public Item getItem() {
        return item;
    }

    public Card findCard(String cardId) {
        if (hero.getId().compareTo(cardId) == 0)
            return hero;
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
            if (cards.size() < 20) {
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

    public void removeCard(Card card, boolean sell) {
        if (findCard(card.getId()) == null) {
            if (!sell)
                Show.get().cardNotInDeckMessage();
            return;
        } else {
            if (card instanceof Hero)
                this.hero = null;
            else
                cards.remove(card);
            if (!sell)
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

    public void removeItem(Item item, boolean sell) {
        if (this.item.getItemId().compareTo(item.getItemId()) == 0) {
            this.item = null;
            if (!sell)
                Show.get().itemRemovedMessage();
            return;
        } else {
            if (!sell)
                Show.get().itemNotInDeckMessage();
            return;
        }
    }

    public boolean checkValidity() {
        return !(hero == null || cards.size() < 20);
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
        return new Deck(this.name, (Hero) this.hero.getCopyCard(), this.getCardsCopy(), this.item.getItemCopy());
    }

    private ArrayList<Card> getCardsCopy() {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cards)
            result.add(card.getCopyCard());
        return result;
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

    public ArrayList<Card> getCards() {
        return cards;
    }
}
