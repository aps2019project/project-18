import cards.Card;
import View.Show;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Deck> decks = new ArrayList<Deck>();
    private Deck mainDeck;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Item> items = new ArrayList<Item>();

    public void show() {
        new Show().showHeroes(cards, true);
        new Show().showMinions(cards, true);
        new Show().showSpells(cards, true);
        new Show().showItems(items, true);
    }


}
