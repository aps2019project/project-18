package Modules;

import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;
import View.View.Show;
import View.View.ShowAccount;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Deck> decks = new ArrayList<Deck>();
    private Deck mainDeck;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private int helpToShowOrNotShow = 0;

    public void menu() {
        Show.get().showCollectionMenu();
        String input;
        while (true) {
            input = Main.scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                ShowAccount.showMenu();
                return;
            } else if (input.equalsIgnoreCase("show")) {
                show();
            } else if (input.matches("search \\w+")) {
                search(input.split(" ")[1]);
            } else if (input.matches("create deck \\w+")) {
                createDeck(input.split(" ")[2]);
            } else if (input.matches("delete deck \\w+")) {
                deleteDeck(findDeck(input.split(" ")[2]));
            } else if (input.matches("add \\w+ to deck \\w+")) {
                if (findCard(input.split(" ")[1]) == null && findItem(input.split(" ")[1]) == null) {
                    Show.get().itemOrCardNotInCollectionMessage();
                } else if (findDeck(input.split(" ")[4]) == null) {
                    Show.get().deckDoesNotExistMessage();
                } else if (findCard(input.split(" ")[1]) != null) {
                    findDeck(input.split(" ")[4]).addCard(findCard(input.split(" ")[1]));
                } else if (findItem(input.split(" ")[1]) != null) {
                    findDeck(input.split(" ")[4]).addItem(findItem(input.split(" ")[1]));
                }
            } else if (input.matches("remove \\w+ from deck \\w+")) {
                if (findDeck(input.split(" ")[4]) == null) {
                    Show.get().deckDoesNotExistMessage();
                } else if (findCard(input.split(" ")[1]) != null) {
                    findDeck(input.split(" ")[4]).removeCard(findCard(input.split(" ")[1]));
                } else if (findItem(input.split(" ")[1]) != null) {
                    findDeck(input.split(" ")[4]).removeItem(findItem(input.split(" ")[1]));
                } else {
                    Show.get().itemOrCardNotInCollectionMessage();
                }
            } else if (input.matches("validate deck \\w+")) {
                if (findDeck(input.split(" ")[2]) == null) {
                    Show.get().deckDoesNotExistMessage();
                } else {
                    if (findDeck(input.split(" ")[2]).checkValidity())
                        Show.get().deckValidMessage();
                    else
                        Show.get().deckInvalidMessage();
                }
            } else if (input.matches("select deck \\w+")) {
                setMainDeck(input.split(" ")[2]);
            } else if (input.equalsIgnoreCase("show all decks")) {
                showDecks();
            } else if (input.matches("show deck \\w+")) {
                if (findDeck(input.split(" ")[2]) == null) {
                    Show.get().deckDoesNotExistMessage();
                } else {
                    findDeck(input.split(" ")[2]).showDeck();
                }
            } else if (input.equalsIgnoreCase("help")) {
                Show.get().showCollectionHelp();
            }
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Card> getCards() {
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
        if (helpToShowOrNotShow <= 20) {
            helpToShowOrNotShow++;
            Show.get().cardAddMessage();
        }
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
            if (helpToShowOrNotShow <= 20) {
                helpToShowOrNotShow++;
                Show.get().itemAddMessage();
            }
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

    private Item findItem(String itemId) {
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
