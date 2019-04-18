package Modules;

import java.util.ArrayList;

import Modules.cards.Card;
import View.Show;

public class Shop {
    private static final Shop SHOP = new Shop();
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Card> cards = new ArrayList<Card>();

    private Shop() {
    }

    public static Shop getInstance() {
        return SHOP;
    }

    private void menu(Account account) {
        String input;
        while (true) {
            input = Main.scanner.nextLine();
            if (input.compareTo("exit") == 0) {
                return;
            } else if (input.compareTo("show collection") == 0) {
                account.getCollection.show();
            } else if (input.matches("search collection \\w+")) {
                account.getCollection.search(input.split(" ")[2]);
            } else if (input.matches("search \\w+")) {
                search(input.split(" ")[1]);
            } else if (input.matches("buy \\w+")) {
                sell(account, input.split(" ")[1]);
            } else if (input.matches("sell \\w+")) {
                account.sell(input.split(" ")[1]);
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
        } else if (findCard(name) != null){
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
        new Show().showMinions(cards, false);
    }

    private void showShopSpells() {
        new Show().showSpells(cards, false);
    }

    private void showShopItems() {
        new Show().showItems(items, false);
    }

    private void showShopHeroes() {
        new Show().showHeroes(cards, false);
    }

    private void showHelp() {
        System.out.println("1. show collection : see your collection");
        System.out.println("2. show : see Modules.cards and items in shop");
        System.out.println("3. search collection [ card name | item name ] : search your collection");
        System.out.println("4. search [ card name | item name ] : search the shop");
        System.out.println("5. buy [ card name | item name ] : buy card or item");
        System.out.println("6. sell [ card name | item name ] : sell card or item");
        System.out.println("7. exit : close the shop");
    }
}
