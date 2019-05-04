package View.View;

import Modules.PlayableThings.cards.*;
import Modules.PlayableThings.Item.*;
import Modules.PlayableThings.cards.Spell.Spell;

import java.util.ArrayList;

public class Show {
    final static Show show = new Show();

    private Show() {

    }

    public static Show get() {
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

    public static void showShopMenu() {
        System.out.print("1. show collection\n2. show\n3. search collection [ card name | item name ]\n" +
                "4. search [ card name | item name ]\n5. buy [ card name | item name ]\n" +
                "6. sell [ card name | item name ]\n7. exit");
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

    public void deckDoesNotExistMessage() {
        System.out.println("deck does not exist");
    }

    public void itemOrCardNotInCollectionMessage() {
        System.out.println("item/card not in collection");
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

    public static void showTargetThatForceCanAttackTo(int x, int y) {
        System.out.println("{ " + x + " ," + y + " }");
    }

    public static void showTargetThatForceCanMoveTo(int x, int y) {
        System.out.println("{ " + x + " ," + y + " }");
    }

    public void deckValidMessage() {
        System.out.println("deck is valid");
    }

    public void deckInvalidMessage() {
        System.out.println("deck is invalid");
    }

    public void showCollectionMenu() {
        System.out.println("1. show");
        System.out.println("2. search [ card name | item name ]");
        System.out.println("3. create deck [ deck name ]");
        System.out.println("4. delete deck [ deck name ]");
        System.out.println("5. add [ card id | item id ] to deck [ deck name ]");
        System.out.println("6. remove [ card id | item id ] from deck [ deck name ]");
        System.out.println("7. validate deck [ deck name ]");
        System.out.println("8. select deck [ deck name ]");
        System.out.println("9. show all decks");
        System.out.println("10. show deck [ deck name ]");
    }

    public void showCollectionHelp() {
        System.out.println("1. show : see cards and items in your collection");
        System.out.println("2. search [ card name | item name ] : see the number of a specific card/item you own");
        System.out.println("3. create deck [ deck name ] : create a new deck");
        System.out.println("4. delete deck [ deck name ] : delete a deck");
        System.out.println("5. add [ card id | item id ] to deck [ deck name ] : add a new card/item to a deck");
        System.out.println("6. remove [ card id | item id ] from deck [ deck name ] : remove a card/item from a deck");
        System.out.println("7. validate deck [ deck name ] : check if a deck is valid or not");
        System.out.println("8. select deck [ deck name ] : set a deck as your main deck");
        System.out.println("9. show all decks : see all of your decks");
        System.out.println("10. show deck [ deck name ] : see a specific deck");
    }

    public void showCardId(Card card) {
        System.out.println(card.getId());
    }

    public void notEnoughManaMessage() {
        System.out.println("not enough mana");
    }

    public void cardNotInHandMessage() {
        System.out.println("card not in hand");
    }
}
