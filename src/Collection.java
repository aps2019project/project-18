import cards.Card;
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
    }
}
