package View.View;

import Modules.PlayableThings.cards.*;
import Modules.PlayableThings.Item.*;

import java.util.ArrayList;

public class Show {
    final static Show show = new Show();
    private Show(){

    }

    public static Show get(){
        return show;
    }
    public void showItems(ArrayList<Item> items, Boolean sell) {
        int i = 1;
        System.out.println("Items : ");
        for (Item item : items) {
            System.out.print("        " + i + ". Name : " + item.getName() + " - Desc : " + item.getDescription());
            if (sell) {
                System.out.println(" - Sell Cost : " + item.getPrice() / 10);
            } else if (!sell) {
                System.out.println(" - Buy Cost : " + item.getPrice());
            } else if (sell == null) {
                System.out.println();
            }
            i++;
        }
    }

    public void showHeroes(ArrayList<Card> cards, Boolean sell) {
        int i = 1;
        System.out.println("Heroes : ");
        for (Card card : cards) {
            if (card instanceof Hero) {
                System.out.print("        " + i + ". Name : " + card.getName() + " - AP : " + ((Hero) card).getAttackPower() +
                        " - HP : " + ((Hero) card).getHitPoint() + " - Special Power : " + card.getDescription());
                if (sell) {
                    System.out.println(" - Sell Cost : " + card.getPrice() / 10);
                } else if (!sell) {
                    System.out.println("- Buy Cost : " + card.getPrice());
                } else if (sell == null) {
                    System.out.println();
                }
                i++;
            }
        }
    }

    public void showSpells(ArrayList<Card> cards, Boolean sell) {
        int i = 1;
        System.out.println("Spells : ");
        for (Card card : cards) {
            if (card instanceof Spell) {
                System.out.println("        " + i + ". Name : " + card.getName() + " - MP : " + ((Spell) card).getMP()
                        + " - Desc" + card.getDescription());

                if (sell == null) {
                    System.out.println();
                } else if (sell) {
                    System.out.println(" - Sell Cost : " + card.getPrice() / 10);
                } else if (!sell) {
                    System.out.println(" - Buy Cost : " + card.getPrice());
                }
                i++;
            }
        }
    }

    public void showSpell(Spell spell) {
        System.out.println("spell :    Name : " + spell.getName() + " - MP : " + spell.getMP()
                + " - Desc" + spell.getDescription());
    }

    public void showMinions(ArrayList<Card> cards, Boolean sell) {
        int i = 1;
        System.out.println("Minions : ");
        for (Card card : cards) {
            if (card instanceof Minion) {
                System.out.println("        " + i + ". Name : " + card.getName() + " - Class : " +
                        ((Minion) card).getAttackType() + " - AP : " + ((Minion) card).getAttackPower() + " - HP : " +
                        ((Minion) card).getHitPoint() + " - MP : " + ((Minion) card).getManaPoint() + " - Special Power : " +
                        card.getDescription());
                if (sell == null) {
                    System.out.println();
                } else if (!sell) {
                    System.out.println(" - Buy Cost : " + card.getPrice());
                } else if (sell) {
                    System.out.println(" - Sell Cost : " + card.getPrice() / 10);
                }
                i++;
            }
        }
    }

    public void showMinion(Minion minion) {
        System.out.println("minion :    Name : " + minion.getName() + " - Class : " +
                minion.getAttackType() + " - AP : " + minion.getAttackPower() + " - HP : " +
                minion.getHitPoint() + " - MP : " + minion.getManaPoint() + " - Special Power : " +
                minion.getDescription());


    }

    public void showHero(Hero hero, Boolean sell) {
        System.out.println("Hero :");
        System.out.print("        " + ". Name : " + hero.getName() + " - AP : " + hero.getAttackPower() +
                " - HP : " + hero.getHitPoint() + " - Special Power : " + hero.getDescription());
        if (sell) {
            System.out.println(" - Sell Cost : " + hero.getPrice() / 10);
        } else if (!sell) {
            System.out.println(" - Buy Cost : " + hero.getPrice());
        } else {
            System.out.println();
        }
    }

    public void showItem(Item item, Boolean sell) {
        System.out.println("Item :");
        System.out.print("        " + ". Name : " + item.getName() + " - Desc : " + item.getDescription());
        if (sell) {
            System.out.println(" - Sell Cost : " + item.getPrice() / 10);
        } else if (!sell) {
            System.out.println(" - Buy Cost : " + item.getPrice());
        } else {
            System.out.println();
        }
    }

    public void showNumberOfCardsOrItems(int number) {
        if (number > 0)
            System.out.println("you have " + number + " of this card/item");
        else
            System.out.println("card/item not in collection");
    }

    public static void showShopMenu(){
        System.out.print("1. show collection\n2. show\n3. search collection [ card name | item name ]\n" +
                "4. search [ card name | item name ]\n5. buy [ card name | item name ]\n" +
                "6. sell [ card name | item name ]\n7. exit : close the shop");
    }

    public void showShopHelp() {
        System.out.println("1. show collection : see your collection");
        System.out.println("2. show : see cards and items in shop");
        System.out.println("3. search collection [ card name | item name ] : search your collection");
        System.out.println("4. search [ card name | item name ] : search the shop");
        System.out.println("5. buy [ card name | item name ] : buy card or item");
        System.out.println("6. sell [ card name | item name ] : sell card or item");
        System.out.println("7. exit : close the shop");
    }

    public void cardAddMessage() {
        System.out.println("card added");
    }

    public void deckHasAHeroMessage() {
        System.out.println("deck already has a hero");
    }

    public void fullDeckMessage() {
        System.out.println("deck is full");
    }

    public void cardInDeckMessage() {
        System.out.println("card already in deck");
    }

    public void cardNotInDeckMessage() {
        System.out.println("card not in deck");
    }

    public void cardRemovedMessage() {
        System.out.println("card removed");
    }

    public void deckHasAnItemMessage() {
        System.out.println("deck already has an item");
    }

    public void itemAddMessage() {
        System.out.println("item added");
    }

    public void itemRemovedMessage() {
        System.out.println("item removed");
    }

    public void itemNotInDeckMessage() {
        System.out.println("item not in deck");
    }

    public void showDeckName(String name) {
        System.out.println(name + " :");
    }

    public void cardNotInCollection() {
        System.out.println("card not in collection");
    }

    public void collectionItemsIsFullMessage() {
        System.out.println("you can't add any more items to your collection");
    }

    public void itemNotInCollectionMessage() {
        System.out.println("item not in collection");
    }

    public void createDeckMessage() {
        System.out.println("deck created");
    }

    public void createDeckErrorMessage() {
        System.out.println("a deck with this name already exists");
    }

    public void deleteDeckErrorMessage() {
        System.out.println("There is no such a deck");
    }

    public void deleteDeckMessage() {
        System.out.println("deck deleted");
    }

    public void setMainDeckMessage(String name) {
        System.out.println("deck " + name + " is now main deck");
    }

    public void setMainDeckErrorMessage() {
        System.out.println("deck is not valid or does not exist");
    }

}
