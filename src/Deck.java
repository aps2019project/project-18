import cards.Card;
import cards.Spell;

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
                System.out.println("card added");
                return;
            } else {
                System.out.println("deck already has a hero");
                return;
            }
        } else {
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

    public boolean checkValidity() {
        if (hero == null || cards.size() < 20)
            return false;
        return true;
    }

    public void showDeck() {
        if (hero != null) {
            System.out.println("Hero :");
            System.out.println("        " + ". Name : " + hero.getName() + " - AP : " + hero.getAP() +
                    " - HP : " + hero.getHP() + " - Special Power : " + hero.getDescription() +
                    "Buy Cost : " + hero.getPrice());
        }
        if (cards.size() > 0)
            System.out.println("Cards : ");
        int i = 1;
        for (Card card : cards) {
            if (card instanceof Minion) {
                System.out.println("        " + i + ". Name : " + card.getName() + " - Class : " +
                        ((Minion) card).getAttackType() + " - AP : " + ((Minion) card).getAP() + " - HP : " +
                        ((Minion) card).getHP() + " - MP : " + ((Minion) card).getMP() + " - Special Power : " +
                        card.getDescription() + "Buy Cost : " + card.getPrice());
                i++;
            }
            if (card instanceof Spell) {
                System.out.println("        " + i + ". Name : " + card.getName() + " - MP : " + ((Spell) card).getMP()
                        + " - Desc" + card.getDescription() + "Buy Cost : " + card.getPrice());
                i++;
            }
        }
        if (item != null) {
            System.out.println("Item :");
                System.out.println("        " + ". Name : " + item.getName() + " - Desc : " + item.getDesc() +
                        " - Buy Cost : " + item.getPrice());
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
}
