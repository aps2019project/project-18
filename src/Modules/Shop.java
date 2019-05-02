package Modules;

import java.util.ArrayList;

import Modules.PlayableThings.Item.Item;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Hero;
import Modules.PlayableThings.cards.Minion;
import Modules.PlayableThings.cards.Spell;
import View.View.Show;//why? u fucked mvc module should not use view
import View.View.ShowAccount;

public class Shop {
    private static final Shop SHOP = new Shop();
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Card> cards = new ArrayList<Card>();

    private Shop() {
        initializeCards();
        initializeItems();
    }

    public static Shop getInstance() {
        return SHOP;
    }

    public void menu(Account account) {
        Show.showShopMenu();
        String input;
        while (true) {
            input = Main.scanner.nextLine();
            if (input.compareTo("exit") == 0) {
                ShowAccount.showMenu();
                return;
            } else if (input.compareTo("show collection") == 0) {
                account.getCollection().show();
            } else if (input.matches("search collection .+")) {
                account.getCollection().search(input.substring(17));
            } else if (input.matches("search .+")) {
                search(input.substring(7));
            } else if (input.matches("buy .+")) {
                sell(account, input.substring(4));
            } else if (input.matches("sell .+")) {
                account.sellCard(input.substring(5));
            } else if (input.compareTo("show") == 0) {
                show();
            } else if (input.compareTo("help") == 0) {
                showHelp();
            } else {
                System.out.println("Invalid command, please use 'help' to show menu");
            }
        }
    }

    private void search(String name) {
        for (Card card : cards) {
            if (card.getName().compareTo(name) == 0)
                System.out.println(card.getName() + " - " + card.getDescription() + " - " + card.getPrice());
        }
        for (Item item : items) {
            if (item.getName().compareTo(name) == 0)
                System.out.println(item.getName() + " - " + item.getDescription() + " - " + item.getPrice());
        }
    }

    private void sell(Account account, String name) {
        if (findCard(name) == null && findItem(name) == null) {
            System.out.println("this card/item doesn't exist in the shop");
            return;
        } else if (findCard(name) != null) {
            account.buyCard(findCard(name));
            return;
        } else {
            account.buyItem(findItem(name));
            return;
        }
    }

    private Card findCard(String name) {
        for (Card card : cards) {
            if (card.getName().compareTo(name) == 0)
                return card;
        }
        return null;
    }

    private Item findItem(String name) {
        for (Item item : items) {
            if (item.getName().compareTo(name) == 0)
                return item;
        }
        return null;
    }

    private void show() {
        showShopItems();
        showShopHeroes();
        showShopMinions();
        showShopSpells();
    }

    private void showShopMinions() {
        Show.get().showMinions(cards, false);
    }

    private void showShopSpells() {
        Show.get().showSpells(cards, false);
    }

    private void showShopItems() {
        Show.get().showItems(items, false);
    }

    private void showShopHeroes() {
        Show.get().showHeroes(cards, false);
    }

    private void showHelp() {
        Show.get().showShopHelp();
    }

    private void initializeCards() {
        cards.add(new Hero("dive sepid", "Add 1 power buff with 4 AP increase to his self for hole game", 8000, 4, 50, "melee", 0, 2, 1));
        cards.add(new Hero("simorgh", "Stun all enemy force for 1 turn", 9000, 4, 50, "melee", 0, 8, 5));
        cards.add(new Hero("Dragon with 7 head", "Disarm 1 force", 8000, 4, 50, "melee", 0, 1, 0));
        cards.add(new Hero("Rakhsh", "Stun 1 enemy force for 1 turn", 8000, 4, 50, "melee", 0, 2, 1));
        cards.add(new Hero("Zahhak", "On attack poison his target for 3 turn", 10000, 2, 50, "melee", 0, 0, 0));
        cards.add(new Hero("Kaveh", "....???", 8000, 4, 50, "melee", 0, 3, 1));
        cards.add(new Hero("Arash", "Hit 4 HP to all force in hero row", 10000, 2, 30, "ranged", 6, 2, 2));
        cards.add(new Hero("Afsaneh", "Dispel 1 enemy force", 11000, 3, 40, "ranged", 3, 2, 1));
        cards.add(new Hero("Esfandiar", "Have 3 continuous holy buff", 12000, 3, 35, "hybrid", 3, 2, 1));
        cards.add(new Hero("Rostam", "", 8000, 7, 55, "hybrid", 4, 0, 0));
        cards.add(new Spell("Total Disarm", "", 1000, 0, "enemy force"));
        cards.add(new Spell("Area Dispel", "", 1500, 2, "2*2"));
        cards.add(new Spell("Empower", "", 250, 1, "own force"));
        cards.add(new Spell("Fireball", "", 400, 1, "enemy force"));
        cards.add(new Spell("God Strength", "", 450, 2, "own hero"));
        cards.add(new Spell("HellFire", "", 600, 3, "2*2"));
        cards.add(new Spell("Lighting Bolt", "", 1250, 2, "enemy hero"));
        cards.add(new Spell("Poison Lake", "", 900, 5, "3*3"));
        cards.add(new Spell("Madness", "", 650, 0, "own force"));
        cards.add(new Spell("All Disarm", "", 2000, 9, "all enemy force"));
        cards.add(new Spell("All poison", "", 1500, 8, "all enemy force"));
        cards.add(new Spell("Dispel", "", 2100, 0, "one force"));
        cards.add(new Spell("Health with profit", "", 2250, 0, "own force"));
        cards.add(new Spell("Power Up", "", 2500, 2, "own force"));
        cards.add(new Spell("All power", "", 2000, 4, "all own force"));
        cards.add(new Spell("All Attack", "", 1500, 4, "all enemy hero in column"));
        cards.add(new Spell("Weakening", "", 1000, 1, "one enemy minion"));
        cards.add(new Spell("Sacrifice", "", 1600, 2, "one own monion"));
        cards.add(new Spell("Kings Guard", "", 1750, 9, "dasti handle shavad"));
        cards.add(new Spell("Shock", "", 1200, 1, "one enemy hero"));
        cards.add(new Minion("Persian crossbowman", "", 300, 4, 6, "ranged", 7, 2));
        cards.add(new Minion("Persian swordsman", "", 400, 4, 6, "melee", 0, 2));
        cards.add(new Minion("Persian lancer", "", 500, 3, 5, "hybrid", 3, 1));
        cards.add(new Minion("Persian horseman", "", 200, 6, 10, "melee", 0, 4));
        cards.add(new Minion("Persian hero", "", 600, 6, 24, "melee", 0, 9));
        cards.add(new Minion("Persian commander-in-chief", "", 800, 4, 12, "melee", 0, 7));
        cards.add(new Minion("Torani crossbowman", "", 500, 4, 3, "ranged", 5, 1));
        cards.add(new Minion("Torani sling", "", 600, 2, 4, "ranged", 7, 1));
        cards.add(new Minion("Torani lancer", "", 600, 4, 4, "hybrid", 3, 1));
        cards.add(new Minion("Torani spy", "", 700, 6, 6, "melee", 0, 4));
        cards.add(new Minion("Gorz dare Torani", "", 450, 10, 3, "melee", 0, 2));
        cards.add(new Minion("Torani prince", "", 800, 10, 6, "melee", 0, 6));
        cards.add(new Minion("Black demon", "", 300, 10, 14, "hybrid", 7, 9));
        cards.add(new Minion("Demon throw rock", "", 300, 12, 12, "ranged", 7, 9));
        cards.add(new Minion("Eagle", "", 200, 2, 0, "ranged", 3, 2));
        cards.add(new Minion("Demon ride swine", "", 300, 8, 16, "melee", 0, 6));
        cards.add(new Minion("Demon with one eye", "", 500, 11, 12, "hybrid", 3, 7));
        cards.add(new Minion("Poison snake", "", 300, 6, 5, "ranged", 4, 4));
        cards.add(new Minion("Dragon throw fire", "", 250, 5, 9, "ranged", 4, 5));
        cards.add(new Minion("Wild lion", "", 600, 8, 1, "melee", 0, 2));
        cards.add(new Minion("Big snake", "", 500, 7, 14, "ranged", 5, 8));
        cards.add(new Minion("White wolf", "", 400, 2, 8, "melee", 0, 5));
        cards.add(new Minion("Leopard", "", 400, 2, 6, "melee", 0, 4));
        cards.add(new Minion("Wolf", "", 400, 1, 6, "melee", 0, 3));
        cards.add(new Minion("Witch", "", 550, 4, 5, "ranged", 3, 4));
        cards.add(new Minion("Grand witch", "", 550, 6, 6, "ranged", 5, 6));
        cards.add(new Minion("Jen", "", 500, 4, 10, "ranged", 4, 5));
        cards.add(new Minion("Wild swine", "", 500, 14, 10, "melee", 0, 6));
        cards.add(new Minion("Piran", "", 400, 20, 8, "melee", 0, 8));
        cards.add(new Minion("Giv", "", 450, 7, 5, "ranged", 5, 4));
        cards.add(new Minion("Bahman", "", 450, 9, 16, "melee", 0, 8));
        cards.add(new Minion("Ashkbos", "", 400, 8, 14, "melee", 0, 7));
        cards.add(new Minion("Iranj", "", 500, 20, 6, "ranged", 3, 4));
        cards.add(new Minion("Big ogre", "", 600, 8, 30, "hybrid", 2, 9));
        cards.add(new Minion("Ogre with two head", "", 550, 4, 10, "melee", 0, 4));
        cards.add(new Minion("Nane sarma", "", 500, 4, 3, "ranged", 5, 3));
        cards.add(new Minion("Folaf zereh", "", 650, 1, 1, "melee", 3, 0));
        cards.add(new Minion("Siyavash", "", 350, 5, 8, "melee", 0, 4));
        cards.add(new Minion("Shah ghol", "", 600, 4, 10, "melee", 0, 5));
        cards.add(new Minion("Arzhang div", "", 600, 6, 6, "melee", 0, 3));
    }

    private void initializeItems() {
        items.add(new Item("Crown of knowledge", 300, "Add 1 mana in 3 first turn"));
        items.add(new Item("Parisa", 4000, "Active 12 holy buff to own hero"));
        items.add(new Item("Damol's arrow", 30000, "Only for ranged adn hybrid : when attacked on own hero enemy disarm for 1 turn"));
        items.add(new Item("Phoenix'wing", 3500, "if enemy hero is ranged or hybrid decrease 2 AP"));
        items.add(new Item("Terror Hood", 5000, "On attack, add one weakness buff with decrease 2 AP on random force"));
        items.add(new Item("King Wisdom ", 9000, "In all turns add 1 mana"));
        items.add(new Item("Assassination Dagger", 15000, "When put each own force card on field hit 1 HP to enemy hero"));
        items.add(new Item("Poisonous Dagger", 7000, "On own attack add one poison buff for 1 turn to random enemy force"));
        items.add(new Item("Shock Hammer", 15000, "Own hero on attack for 1 turn disarm enemy force"));
        items.add(new Item("Soul Eater", 25000, "On death of each own force add 1 power buff with 1 increase AP too random force"));
        items.add(new Item("B‌aptism'ablution", 20000, "Each minion on spawn give 2 turns holy buff"));
    }
}
