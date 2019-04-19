package Modules;

import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;
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

    public ArrayList<Card> getCards(){
        return cards;
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
        if (findItem(item.getItemId()) != null) {
            items.remove(item);
            new Show().itemRemovedMessage();
        } else {
            new Show().itemNotInCollectionMessage();
        }
    }

    public Item findItem(String itemId) {
        for (Item item : items)
            if (item.getItemId().equals(itemId))
                return item;
        return null;
    }

    private Deck findDeck(String name) {
        for (Deck deck : decks)
            if (deck.getName().equals(name))
                return deck;
        return null;
    }

    public int getItemsSize() {
        return items.size();
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    private void createDeck(String name) {
        if (findDeck(name) != null) {
            new Show().createDeckErrorMessage();
            return;
        }
        decks.add(new Deck(name));
        new Show().createDeckMessage();
    }

    private void deleteDeck(Deck deck) {
        if (findDeck(deck.getName()) == null) {
            new Show().deleteDeckErrorMessage();
            return;
        }
        decks.remove(deck);
        new Show().deleteDeckMessage();
    }

    private void setMainDeck(String name) {
        if (findDeck(name) != null && findDeck(name).checkValidity()) {
            mainDeck = findDeck(name);
            new Show().setMainDeckMessage(name);
            return;
        }
        new Show().setMainDeckErrorMessage();
    }
}
