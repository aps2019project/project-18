package View;

import Modules.cards.Card;
import Modules.cards.Spell;

import java.util.ArrayList;

public class Show {
    public void showItems(ArrayList<Item> items, Boolean sell) {
        int i = 1;
        System.out.println("Items : ");
        for (Item item : items) {
            System.out.print("        " + i + ". Name : " + item.getName() + " - Desc : " + item.getDesc());
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
                System.out.print("        " + i + ". Name : " + card.getName() + " - AP : " + ((Hero) card).getAP() +
                        " - HP : " + ((Hero) card).getHP() + " - Special Power : " + card.getDescription());
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

    public void showMinions(ArrayList<Card> cards, Boolean sell) {
        int i = 1;
        System.out.println("Minions : ");
        for (Card card : cards) {
            if (card instanceof Minion) {
                System.out.println("        " + i + ". Name : " + card.getName() + " - Class : " +
                        ((Minion) card).getAttackType() + " - AP : " + ((Minion) card).getAP() + " - HP : " +
                        ((Minion) card).getHP() + " - MP : " + ((Minion) card).getMP() + " - Special Power : " +
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

    public void showHero(Hero hero, Boolean sell) {
        System.out.println("Hero :");
        System.out.print("        " + ". Name : " + hero.getName() + " - AP : " + hero.getAP() +
                " - HP : " + hero.getHP() + " - Special Power : " + hero.getDescription());
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
        System.out.print("        " + ". Name : " + item.getName() + " - Desc : " + item.getDesc());
        if (sell) {
            System.out.println(" - Sell Cost : " + item.getPrice() / 10);
        } else if (!sell) {
            System.out.println(" - Buy Cost : " + item.getPrice());
        } else {
            System.out.println();
        }
    }

    public void showText(String text) {
        System.out.println(text);
    }
}
