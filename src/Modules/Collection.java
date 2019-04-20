package Modules;

import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;
import View.View.Show;
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
        Show.get().showNumberOfCardsOrItems(i);
    }

    private void showDecks() {
        for (Deck deck : decks) {
            deck.showDeck();
        }
    }

    public void show() {
         Show.get().showHeroes(cards, true);
         Show.get().showMinions(cards, true);
         Show.get().showSpells(cards, true);
         Show.get().showItems(items, true);
    }

    public void addCard(Card card) {
        cards.add(card);
         Show.get().cardAddMessage();
    }

    public void removeCard(Card card) {
        if (findCard(card.getId()) != null) {
            cards.remove(card);
             Show.get().cardRemovedMessage();
        } else {
             Show.get().cardNotInCollection();
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
            Show.get().itemAddMessage();
        } else {
            Show.get().collectionItemsIsFullMessage();
        }
    }

    public void removeItem(Item item) {
        if (findItem(item.getItemId()) != null) {
            items.remove(item);
            Show.get().itemRemovedMessage();
        } else {
            Show.get().itemNotInCollectionMessage();
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
            Show.get().createDeckErrorMessage();
            return;
        }
        decks.add(new Deck(name));
        Show.get().createDeckMessage();
    }

    private void deleteDeck(Deck deck) {
        if (findDeck(deck.getName()) == null) {
            Show.get().deleteDeckErrorMessage();
            return;
        }
        decks.remove(deck);
        Show.get().deleteDeckMessage();
    }

    private void setMainDeck(String name) {
        if (findDeck(name) != null && findDeck(name).checkValidity()) {
            mainDeck = findDeck(name);
            Show.get().setMainDeckMessage(name);
            return;
        }
        Show.get().setMainDeckErrorMessage();
    }
}
