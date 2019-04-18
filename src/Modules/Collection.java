package Modules;

import Modules.cards.Card;
import View.Show;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Deck> decks = new ArrayList<Deck>();
    private Deck mainDeck;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Item> items = new ArrayList<Item>();

    public void menu() {
        // TODO
    }

    public void search(String name) {
        int i = 0;
        for (Card card : cards)
            if (card.getName().equals(name))
                i++;
        for (Item item : items)
            if (item.getName().equals(name))
                i++;
        new Show().showNumberOfCardsOrItems(i);
    }

    private void showDecks() {
        for (Deck deck : decks) {
            deck.showDeck();
        }
    }

    public void show() {
        new Show().showHeroes(cards, true);
        new Show().showMinions(cards, true);
        new Show().showSpells(cards, true);
        new Show().showItems(items, true);
    }

    public void addCard(Card card) {
        cards.add(card);
        new Show().cardAddMessage();
    }

    public void removeCard(Card card) {
        if (findCard(card.getId()) != null) {
            cards.remove(card);
            new Show().cardRemovedMessage();
        } else {
            new Show().cardNotInCollection();
        }
    }

    private Card findCard(String cardId) {
        for (Card card : cards)
            if (card.getId().equals(cardId))
                return card;
        return null;
    }

    public void addItem(Item item) {
        if (items.size() < 3) {
            items.add(item);
            new Show().itemAddMessage();
        } else {
            new Show().collectionItemsIsFullMessage();
        }
    }

    public void removeItem(Item item) {
        if (findItem(item.getId()) != null) {
            items.remove(item);
            new Show().itemRemovedMessage();
        } else {
            new Show().itemNotInCollectionMessage();
        }
    }

    public Item findItem(String itemId) {
        for (Item item : items)
            if (item.getId().equals(itemId))
                return item;
        return null;
    }

    private Deck findDeck(String name) {
        for (Deck deck : decks)
            if (deck.getName().equals(name))
                return deck;
        return null;
    }
}
