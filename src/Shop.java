import java.util.ArrayList;

import cards.Card;

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

    private void showHelp() {
        System.out.println("1. show collection : see your collection");
        System.out.println("2. show : see cards and items in shop");
        System.out.println("3. search collection [ card name | item name ] : search your collection");
        System.out.println("4. search [ card name | item name ] : search the shop");
        System.out.println("5. buy [ card name | item name ] : buy card or item");
        System.out.println("6. sell [ card name | item name ] : sell card or item");
        System.out.println("7. exit : close the shop");
    }


}
