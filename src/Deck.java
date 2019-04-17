import cards.Card;

import java.util.ArrayList;

public class Deck {
    private String name;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Item item = null;

    public Deck(String name) {
        this.name = name;
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
        if (cards.size() < 20 && findCard(card.getId()) == null) {
            cards.add(card);
            System.out.println("card added");
            return;
        } else if (cards.size() == 20) {
            System.out.println("deck is full");
            return;
        } else {
            System.out.println("card already in deck");
            return;
        }
    }

    public void removeCard(Card card) {
        if (findCard(card.getId()) == null) {
            System.out.println("card isn't in deck");
            return;
        } else {
            cards.remove(card);
            System.out.println("card removed from deck");
            return;
        }
    }

    public void addItem(Item item) {
        if (this.item == null) {
            this.item = item;
            System.out.println("item added");
            return;
        } else {
            System.out.println("deck already has an item");
            return;
        }
    }

    public void removeItem(Item item) {
        if (this.item.getId().compareTo(item.getId()) == 0) {
            this.item = null;
            System.out.println("item removed");
            return;
        } else {
            System.out.println("item not in deck");
            return;
        }
    }


}
