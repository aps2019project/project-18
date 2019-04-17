import cards.Card;

import java.util.ArrayList;

public class Deck {
    private String name;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Item item = null;

    public Deck(String name) {
        this.name = name;
    }


}
