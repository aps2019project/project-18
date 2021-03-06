package Server.Modules;

import Client.Main;
import Server.Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Server.Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Server.Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPowerType;
import Server.Modules.PlayableThings.Item.Item;
import Server.Modules.PlayableThings.cards.Card;
import Server.Modules.PlayableThings.cards.CustomCard;
import Server.Modules.PlayableThings.cards.Hero;
import Server.Modules.PlayableThings.cards.Minion;
import Server.Modules.PlayableThings.cards.Spell.Spell;
import Server.Modules.PlayableThings.cards.Spell.Target;
import Server.View.View.Show;
import Server.View.View.ShowAccount;

import java.util.ArrayList;
import java.util.Random;

public class Shop {
    private static final Shop SHOP = new Shop();
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Card> cards = new ArrayList<Card>();

    private Shop() {
        initializeCards();
        initializeItems();
        CustomCard.getInstance().initilizeCustomCards();
    }

    public ArrayList<Card> getHeroes() {
        ArrayList<Card> heroes = new ArrayList<>();
        for (Card card : cards) {
            if (card instanceof Hero)
                heroes.add(card);
        }
        return heroes;
    }

    public ArrayList<Card> getMinions() {
        ArrayList<Card> minions = new ArrayList<>();
        for (Card card : cards) {
            if (card instanceof Minion)
                minions.add(card);
        }
        return minions;
    }

    public ArrayList<Card> getSpells() {
        ArrayList<Card> spells = new ArrayList<>();
        for (Card card : cards) {
            if (card instanceof Spell)
                spells.add(card);
        }
        return spells;
    }

    public ArrayList<Item> getItems() {
        return items;
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
                account.getCollection().search(input.substring(18));
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

    public Card findCard(String name) {
        for (Card card : cards) {
            if (card.getName().compareTo(name) == 0)
                return card;
        }
        return null;
    }

    public Item findItem(String name) {
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

    public void addCard(Card card) {
        cards.add(card);
    }


    public void addSomeCardToCollectionForBeginning(Account account) {
        account.buyItem(items.get(0));
        int random = new Random().nextInt(3) + 1;
        switch (random) {
            case 1:
                account.buyCard(cards.get(0));
                break;
            case 2:
                account.buyCard(cards.get(3));
                break;
            case 3:
                account.buyCard(cards.get(9));
        }
        for (int i = 0; i < 9; i++) {
            random = new Random().nextInt(6) + 1;
            switch (random) {
                case 1:
                    account.buyCard(cards.get(10));
                    break;
                case 2:
                    account.buyCard(cards.get(12));
                    break;
                case 3:
                    account.buyCard(cards.get(13));
                    break;
                case 4:
                    account.buyCard(cards.get(14));
                    break;
                case 5:
                    account.buyCard(cards.get(15));
                    break;
                case 6:
                    account.buyCard(cards.get(18));
            }
        }
        for (int i = 0; i < 11; i++) {
            random = new Random().nextInt(6) + 1;
            switch (random) {
                case 1:
                    account.buyCard(cards.get(30));
                    break;
                case 2:
                    account.buyCard(cards.get(31));
                    break;
                case 3:
                    account.buyCard(cards.get(32));
                    break;
                case 4:
                    account.buyCard(cards.get(33));
                    break;
                case 5:
                    account.buyCard(cards.get(36));
                    break;
                case 6:
                    account.buyCard(cards.get(40));
            }
        }
    }

    public Deck getDeckLevelOne() {
        Deck deck = new Deck("AI");
        //HERO
        setCardToAiDeck(0, 1, deck, 1);
        //SPELLS
        setCardToAiDeck(10, 1, deck, 1);
        setCardToAiDeck(16, 1, deck, 1);
        setCardToAiDeck(19, 1, deck, 1);
        setCardToAiDeck(20, 1, deck, 1);
        setCardToAiDeck(21, 1, deck, 1);
        setCardToAiDeck(27, 1, deck, 1);
        setCardToAiDeck(29, 1, deck, 1);
        //MINION
        setCardToAiDeck(30, 1, deck, 1);
        setCardToAiDeck(38, 1, deck, 1);
        setCardToAiDeck(40, 1, deck, 1);
        setCardToAiDeck(40, 2, deck, 1);
        setCardToAiDeck(42, 1, deck, 1);
        setCardToAiDeck(46, 1, deck, 1);
        setCardToAiDeck(47, 1, deck, 1);
        setCardToAiDeck(50, 1, deck, 1);
        setCardToAiDeck(51, 1, deck, 1);
        setCardToAiDeck(55, 1, deck, 1);
        setCardToAiDeck(67, 1, deck, 1);
        setCardToAiDeck(65, 1, deck, 1);
        setCardToAiDeck(69, 1, deck, 1);
        //ITEM
        Item item;
        item = items.get(0).getItemCopy();
        item.setItemId("AI1" + "_" + item.getName() + "_" + "1");
        deck.addItem(item);
        return deck;
    }

    public Deck getDeckLevelTwo() {
        Deck deck = new Deck("AI");
        //HERO
        setCardToAiDeck(4, 1, deck, 2);
        //SPELLS
        setCardToAiDeck(11, 1, deck, 2);
        setCardToAiDeck(12, 1, deck, 2);
        setCardToAiDeck(14, 1, deck, 2);
        setCardToAiDeck(18, 1, deck, 2);
        setCardToAiDeck(17, 1, deck, 2);
        setCardToAiDeck(22, 1, deck, 2);
        setCardToAiDeck(28, 1, deck, 2);
        //MINION
        setCardToAiDeck(31, 1, deck, 2);
        setCardToAiDeck(32, 1, deck, 2);
        setCardToAiDeck(34, 1, deck, 2);
        setCardToAiDeck(37, 1, deck, 2);
        setCardToAiDeck(41, 1, deck, 2);
        setCardToAiDeck(44, 1, deck, 2);
        setCardToAiDeck(44, 2, deck, 2);
        setCardToAiDeck(48, 1, deck, 2);
        setCardToAiDeck(52, 1, deck, 2);
        setCardToAiDeck(56, 1, deck, 2);
        setCardToAiDeck(59, 1, deck, 2);
        setCardToAiDeck(62, 1, deck, 2);
        setCardToAiDeck(68, 1, deck, 2);
        //ITEM
        Item item;
        item = items.get(9).getItemCopy();
        item.setItemId("AI2" + "_" + item.getName() + "_" + "1");
        deck.addItem(item);
        return deck;
    }

    public Deck getDeckLevelThree() {
        Deck deck = new Deck("AI");
        //HERO
        setCardToAiDeck(6, 1, deck, 2);
        //SPELLS
        setCardToAiDeck(15, 1, deck, 2);
        setCardToAiDeck(19, 1, deck, 2);
        setCardToAiDeck(21, 1, deck, 2);
        setCardToAiDeck(23, 1, deck, 2);
        setCardToAiDeck(24, 1, deck, 2);
        setCardToAiDeck(25, 1, deck, 2);
        setCardToAiDeck(26, 1, deck, 2);
        //MINION
        setCardToAiDeck(35, 1, deck, 2);
        setCardToAiDeck(36, 1, deck, 2);
        setCardToAiDeck(39, 1, deck, 2);
        setCardToAiDeck(43, 1, deck, 2);
        setCardToAiDeck(45, 1, deck, 2);
        setCardToAiDeck(45, 2, deck, 2);
        setCardToAiDeck(49, 2, deck, 2);
        setCardToAiDeck(53, 1, deck, 2);
        setCardToAiDeck(54, 1, deck, 2);
        setCardToAiDeck(57, 1, deck, 2);
        setCardToAiDeck(58, 1, deck, 2);
        setCardToAiDeck(60, 1, deck, 2);
        setCardToAiDeck(63, 1, deck, 2);
        //ITEM
        Item item;
        item = items.get(4).getItemCopy();
        item.setItemId("AI" + "_" + item.getName() + "_" + "1");
        deck.addItem(item);
        return deck;
    }

    public Deck setCustomDeck() {
        Deck deck = new Deck("AI");
        //Item
        int random = new Random().nextInt(4);
        Item item = items.get(random).getItemCopy();
        item.setItemId("AI" + "_" + item.getName() + "_" + "1");
        deck.addItem(item);
        //Hero
        Card card = cards.get(new Random().nextInt(10)).getCopyCard();
        card.setId("AI" + "_" + card.getName() + "_" + 1);
        deck.addCard(card);
        //Spell
        for (int i = 0; i < 11; i++) {
            card = cards.get(new Random().nextInt(20) + 10).getCopyCard();
            deck.addCard(card);
        }
        //Minion
        for (int i = 0; i < 9; i++) {
            deck.addCard(cards.get(new Random().nextInt(40) + 30).getCopyCard());
        }
        for (int i = 0; i < 20; i++) {
            int id = 1;
            for (int j = i + 1; j < 20; j++) {
                if (deck.getCards().get(i).getName().equals(deck.getCards().get(j))) {
                    id++;
                }
            }
            deck.getCards().get(i).setId("AI" + "_" + deck.getCards().get(i).getName() + "_" + id);
        }
        return deck;
    }


    private void setCardToAiDeck(int indexCard, int numberOfThatCard, Deck deck, int i) {
        Card card = cards.get(indexCard).getCopyCard();
        card.setId("AI" + i + "_" + card.getName() + "_" + numberOfThatCard);
        deck.addCard(card);
    }

    private void initializeCards() {
        Spell spell = new Spell();
        Buff buff = new Buff();
        buff.setAttackPower(4);
        buff.setInfitinive(true);
        buff.setBuff(true);
        spell.addBuff(buff);
        Target target = new Target();
        target.setHero(true);
        target.setAlly(true);
        spell.setTarget(target);
        cards.add(new Hero("diveSepid", "Add 1 power buff with 4 AP increase to his self for hole game", 8000, 4, 50, "melee", 0, spell, 2, 1));
        spell = new Spell();
        buff = new Buff();
        buff.setStun(true);
        buff.setNumberOfTurns(1);
        buff.setBuff(true);
        spell.addBuff(buff);
        target = new Target();
        target.setHero(true);
        target.setMinion(true);
        target.setAll(true);
        target.setAlly(false);
        spell.setTarget(target);
        cards.add(new Hero("simorgh", "Stun all enemy force for 1 turn", 9000, 4, 50, "melee", 0, spell, 8, 5));
        spell = new Spell();
        buff = new Buff();
        buff.setDisarm(true);
        buff.setInfitinive(true);
        buff.setBuff(true);
        spell.addBuff(buff);
        target = new Target();
        target.setHero(true);
        target.setMinion(true);
        target.setAlly(false);
        spell.setTarget(target);
        cards.add(new Hero("DragonWith7Head", "Disarm 1 force", 8000, 4, 50, "melee", 0, spell, 1, 0));
        spell = new Spell();
        buff = new Buff();
        buff.setStun(true);
        buff.setNumberOfTurns(1);
        buff.setBuff(true);
        spell.addBuff(buff);
        target = new Target();
        target.setHero(true);
        target.setMinion(true);
        target.setAlly(false);
        spell.setTarget(target);
        cards.add(new Hero("Rakhsh", "Stun 1 enemy force for 1 turn", 8000, 4, 50, "melee", 0, spell, 2, 1));
        SpecialPower specialPower = new SpecialPower();
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setPoison(true);
        buff.setPoisonCount(1);
        buff.setNumberOfTurns(3);
        buff.setBuff(true);
        spell.addBuff(buff);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        cards.add(new Hero("Zahhak", "On attack poison his target for 3 turn", 10000, 2, 50, "melee", 0, null, 0, 0, specialPower));
        spell = new Spell();
        buff = new Buff();
        buff.setHoly(true);
        buff.setHolyCount(1);
        buff.setNumberOfTurns(3);
        buff.setBuff(true);
        spell.addBuff(buff);
        target = new Target();
        target.setGround(true);
        spell.setTarget(target);
        cards.add(new Hero("Kaveh", "....???", 8000, 4, 50, "melee", 0, spell, 3, 1));
        spell = new Spell();
        buff = new Buff();
        buff.setHit(4);
        spell.addBuff(buff);
        target = new Target();
        target.setRow(true);
        target.setHero(true);
        spell.setTarget(target);
        cards.add(new Hero("Arash", "Hit 4 HP to all force in hero row", 10000, 2, 30, "ranged", 6, spell, 2, 2));
        spell = new Spell();
        buff = new Buff();
        buff.setDispel(true);
        spell.addBuff(buff);
        target = new Target();
        target.setAlly(false);
        target.setHero(true);
        target.setMinion(true);
        spell.setTarget(target);
        cards.add(new Hero("Afsaneh", "Dispel 1 enemy force", 11000, 3, 40, "ranged", 3, spell, 2, 1));
        spell = new Spell();
        buff = new Buff();
        buff.setHoly(true);
        buff.setHolyCount(3);
        buff.setContinious(true);
        buff.setBuff(true);
        spell.addBuff(buff);
        target = new Target();
        target.setHero(true);
        target.setAlly(true);
        spell.setTarget(target);
        cards.add(new Hero("Esfandiar", "Have 3 continuous holy buff", 12000, 3, 35, "hybrid", 3, spell, 2, 1));
        cards.add(new Hero("Rostam", "", 8000, 7, 55, "hybrid", 4, null, 0, 0));
        Target targetOneEnemyForce = new Target();
        targetOneEnemyForce.setAlly(false);
        targetOneEnemyForce.setHero(true);
        targetOneEnemyForce.setMinion(true);
        buff = new Buff();
        buff.setDisarm(true);
        buff.setInfitinive(true);
        buff.setBuff(true);
        cards.add(new Spell("TotalDisarm", "Disarm one enemy force for hole game", 1000, 0, targetOneEnemyForce).addBuff(buff));
        Target targetOneMoraba = new Target();
        targetOneEnemyForce.setGround(true);
        targetOneEnemyForce.setDimension(2);
        buff = new Buff();
        buff.setDeleteNegative(true);
        buff.setDeletePositive(true);
        cards.add(new Spell("AreaDispel", "Destroy all enemy force positive buff and all own force negative buff in 2*2 area", 1500, 2, targetOneMoraba).addBuff(buff));
        Target targetOwnForce = new Target();
        targetOneEnemyForce.setAlly(true);
        targetOneEnemyForce.setHero(true);
        targetOneEnemyForce.setMinion(true);
        buff = new Buff();
        buff.setAttackPower(2);
        cards.add(new Spell("Empower", "Add 2 AP to one own force", 250, 1, targetOwnForce).addBuff(buff));
        buff = new Buff();
        buff.setHit(4);
        cards.add(new Spell("Fireball", "Hit 4 HP to one enemy force", 400, 1, targetOneEnemyForce).addBuff(buff));
        Target targetOwnHero = new Target();
        targetOwnHero.setAlly(true);
        targetOwnHero.setHero(true);
        buff = new Buff();
        buff.setAttackPower(4);
        cards.add(new Spell("GodStrength", "Add 4 AP to own hero", 450, 2, targetOwnHero).addBuff(buff));
        buff = new Buff();
        buff.setFireHouse(true);
        buff.setNumberOfTurns(2);
        cards.add(new Spell("HellFire", "For 2 turns make target point fire place", 600, 3, targetOneMoraba).addBuff(buff));
        Target targetEnemyHero = new Target();
        targetOneEnemyForce.setAlly(false);
        targetOneEnemyForce.setHero(true);
        buff = new Buff();
        buff.setHitPoint(8);
        cards.add(new Spell("LightingBolt", "Hit 8 HP to enemy hero", 1250, 2, targetEnemyHero).addBuff(buff));
        targetOneMoraba.setDimension(3);
        buff = new Buff();
        buff.setPoison(true);
        buff.setNumberOfTurns(1);
        buff.setNumberOfTurns(1);
        cards.add(new Spell("PoisonLake", "For 1 turn make target point poison place", 900, 5, targetOneMoraba).addBuff(buff));
        buff = new Buff();
        buff.setNumberOfTurns(3);
        buff.setAttackPower(4);
        buff.setBuff(true);
        Buff buff1 = new Buff();
        buff1.setNumberOfTurns(3);
        buff1.setDisarm(true);
        buff1.setBuff(true);
        cards.add(new Spell("Madness", "For 3 turns add 4 AP to one own force but disarm it", 650, 0, targetOwnForce).addBuff(buff).addBuff(buff1));
        Target targetAllEnemyForce = new Target();
        targetAllEnemyForce.setAlly(false);
        targetAllEnemyForce.setHero(true);
        targetAllEnemyForce.setMinion(true);
        buff = new Buff();
        buff.setDisarm(true);
        buff.setNumberOfTurns(1);
        buff.setBuff(true);
        cards.add(new Spell("AllDisarm", "All enemy force disarm for 1 turn", 2000, 9, targetAllEnemyForce).addBuff(buff));
        buff = new Buff();
        buff.setPoison(true);
        buff.setNumberOfTurns(4);
        buff.setBuff(true);
        cards.add(new Spell("AllPoison", "All enemy force poison for 4 turns", 1500, 8, targetAllEnemyForce).addBuff(buff));

        cards.add(new Spell("Dispel", "Destroy all positive buff one enemy force or all negative buff one own force", 2100, 0, new Target()));
        buff = new Buff();
        buff.setHitPoint(-6);
        buff.setBuff(true);
        buff1 = new Buff();
        buff1.setHoly(true);
        buff1.setHolyCount(2);
        buff1.setNumberOfTurns(3);
        buff.setBuff(true);
        cards.add(new Spell("HealthWithProfit", "Add one weakness buff with decrease 6 HP to one own force but give it 2 holy buff for 3 turns", 2250, 0, targetOwnForce).addBuff(buff).addBuff(buff1));
        buff = new Buff();
        buff.setAttackPower(6);
        buff.setBuff(true);
        cards.add(new Spell("PowerUp", "Add one power buff with increase 6 AP to one own force", 2500, 2, targetOwnForce).addBuff(buff));
        Target targetAllOwnForce = new Target();
        targetAllEnemyForce.setAll(true);
        targetAllEnemyForce.setMinion(true);
        targetAllEnemyForce.setHero(true);
        targetAllEnemyForce.setAlly(true);
        buff = new Buff();
        buff.setAttackPower(2);
        buff.setInfitinive(true);
        buff.setBuff(true);
        cards.add(new Spell("AllPower", "Add one power buff with increase 2 AP to all own force for hole game", 2000, 4, targetAllOwnForce).addBuff(buff));
        Target targetAllEnemyForceInOneColumn = new Target();
        targetAllEnemyForce.setAlly(false);
        targetAllEnemyForce.setColumn(true);
        targetAllEnemyForce.setHero(true);
        targetAllEnemyForce.setMinion(true);
        buff = new Buff();
        buff.setHit(6);
        cards.add(new Spell("AllAttack", "Hit 6 HP to all enemy forces in target column", 1500, 4, targetAllEnemyForceInOneColumn).addBuff(buff));
        Target targetOneEnemyMinion = new Target();
        targetAllEnemyForce.setMinion(true);
        targetAllEnemyForce.setAlly(false);
        buff = new Buff();
        buff.setHitPoint(-4);
        buff.setBuff(true);
        cards.add(new Spell("Weakening", "Add one weakness buff with decrease 4 AP to one enemy minion", 1000, 1, targetOneEnemyMinion).addBuff(buff));
        Target targetOneOwnMinion = new Target();
        targetAllEnemyForce.setMinion(true);
        targetAllEnemyForce.setAlly(true);
        buff = new Buff();
        buff.setHitPoint(-6);
        buff.setBuff(true);
        buff1 = new Buff();
        buff1.setAttackPower(8);
        buff.setBuff(true);
        cards.add(new Spell("Sacrifice", "Add one weakness buff with decrease 6 HP and add one power buff with increase 8 AP to one own minion", 1600, 2, targetOneOwnMinion).addBuff(buff).addBuff(buff1));
        target = new Target();
        target.setAroundHero(true);
        target.setAlly(false);
        target.setMinion(true);
        target.setRandom(true);
        buff = new Buff();
        buff.setKill(true);
        cards.add(new Spell("KingsGuard", "Kill one random enemy minion in own hero side", 1750, 9, null).addBuff(buff));
        buff = new Buff();
        buff.setStun(true);
        buff.setNumberOfTurns(2);
        buff.setBuff(true);
        cards.add(new Spell("Shock", "Stun one enemy force for 2 turn", 1200, 1, targetOneEnemyForce).addBuff(buff));
        cards.add(new Minion("PersianCrossbowman", "", 300, 4, 6, "ranged", 7, 2, null));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setStun(true);
        buff.setNumberOfTurns(1);
        buff.setExecuteTime(1);
        spell.addBuff(buff);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        specialPower.setSpell(spell);
        cards.add(new Minion("PersianSwordsman", "On attack stun target for next turn", 400, 4, 6, "melee", 0, 2, specialPower));
        cards.add(new Minion("PersianLancer", "", 500, 3, 5, "hybrid", 3, 1, null));
        cards.add(new Minion("PersianHorseman", "", 200, 6, 10, "melee", 0, 4, null));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setRisingAttackWithTurns(true);
        spell.addBuff(buff);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        specialPower.setSpell(spell);
        cards.add(new Minion("PersianHero", "be tedad dafaAti ke dar nobat haye ghabl be yek niro hamle karde 5 vahed bishtar be an zarbe mizanad", 600, 6, 24, "melee", 0, 9, specialPower));
        specialPower = new SpecialPower();
        specialPower.setType(SpecialPowerType.COMBO);
        cards.add(new Minion("PersianCommander-in-chief", "", 800, 4, 12, "melee", 0, 7, specialPower));
        cards.add(new Minion("ToraniCrossbowman", "", 500, 4, 3, "ranged", 5, 1, null));
        cards.add(new Minion("ToraniSling", "", 600, 2, 4, "ranged", 7, 1, null));
        cards.add(new Minion("ToraniLancer", "", 600, 4, 4, "hybrid", 3, 1, null));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setDisarm(true);
        buff.setNumberOfTurns(1);
        buff1 = new Buff();
        buff1.setPoison(true);
        buff1.setPoisonCount(1);
        buff1.setNumberOfTurns(4);
        spell.addBuff(buff).addBuff(buff1);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        cards.add(new Minion("ToraniSpy", "Disarm target for 1 turn and poison it for 4 turns", 700, 6, 6, "melee", 0, 4, specialPower));
        cards.add(new Minion("GorzDareTorani", "", 450, 10, 3, "melee", 0, 2, null));
        specialPower = new SpecialPower();
        specialPower.setType(SpecialPowerType.COMBO);
        cards.add(new Minion("ToraniPrince", "", 800, 10, 6, "melee", 0, 6, specialPower));
        cards.add(new Minion("BlackDemon", "", 300, 10, 14, "hybrid", 7, 9, null));
        cards.add(new Minion("DemonThrowRock", "", 300, 12, 12, "ranged", 7, 9, null));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setHitPoint(10);
        spell.addBuff(buff);
        specialPower.setType(SpecialPowerType.PASSIVE);
        cards.add(new Minion("Eagle", "Have 10 power buf with increase 1 HP (Passive)", 200, 2, 0, "ranged", 3, 2, specialPower));
        cards.add(new Minion("DemonRideSwine", "", 300, 8, 16, "melee", 0, 6, null));
        specialPower = new SpecialPower();
        target = new Target();
        spell = new Spell();
        spell.addBuff(new Buff());
        spell.setTarget(target);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_DEATH);
        cards.add(new Minion("DemonWithOneEye", "On death hit 2 HP to all minion in its side", 500, 11, 12, "hybrid", 3, 7, specialPower));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setPoison(true);
        buff.setPoisonCount(3);
        buff.setBuff(true);
        spell.addBuff(buff);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        cards.add(new Minion("PoisonSnake", "Poison target for 3 turns", 300, 6, 5, "ranged", 4, 4, specialPower));
        cards.add(new Minion("DragonThrowFire", "", 250, 5, 9, "ranged", 4, 5, null));
        specialPower = new SpecialPower();
        specialPower.setDontAffectHoly(true);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        cards.add(new Minion("WildLion", "Target holy buff doesn't decrease Wild lion AP", 600, 8, 1, "melee", 0, 2, specialPower));
        specialPower = new SpecialPower();
        target = new Target();
        target.setDistance(2);
        target.setMinion(true);
        target.setAll(true);
        target.setAlly(false);
        target.setAroundIt(true);
        spell = new Spell();
        buff = new Buff();
        buff.setHoly(true);
        buff.setHolyCount(-1);
        spell.setTarget(target);
        spell.addBuff(buff);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_SPAWN);
        cards.add(new Minion("BigSnake", "vaghti big snake spawn mishe minion haye dar khane haye ta 2 vahed fasele be tor daem hamishe 1 vahed bishtar zarbe mikhorand", 500, 7, 14, "ranged", 5, 8, specialPower));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setHit(6);
        buff.setBuff(true);
        buff.setExecuteTime(1);
        buff1 = new Buff();
        buff1.setBuff(true);
        buff1.setHit(4);
        buff1.setExecuteTime(2);
        spell.addBuff(buff).addBuff(buff1);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        cards.add(new Minion("WhiteWolf", "When attack to minion next turn decrease 6 HP and next turn 4 HP", 400, 2, 8, "melee", 0, 5, specialPower));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setHit(8);
        buff.setExecuteTime(1);
        buff.setBuff(true);
        spell.addBuff(buff);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        cards.add(new Minion("Leopard", "When attack to minion next turn decrease 8 HP", 400, 2, 6, "melee", 0, 4, specialPower));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setHit(6);
        buff.setExecuteTime(1);
        buff.setBuff(true);
        spell.addBuff(buff);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        cards.add(new Minion("Wolf", "When attack to minion next turn decrease 6", 400, 1, 6, "melee", 0, 3, specialPower));
        specialPower = new SpecialPower();
        target = new Target();
        target.setIt(true);
        target.setAroundIt(true);
        target.setAlly(true);
        target.setAll(true);
        target.setMinion(true);
        buff = new Buff();
        buff.setAttackPower(2);
        buff.setNumberOfTurns(1);
        buff1 = new Buff();
        buff1.setHitPoint(-1);
        buff1.setNumberOfTurns(1);
        spell = new Spell();
        spell.setTarget(target);
        spell.addBuff(buff).addBuff(buff1);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.PASSIVE);
        cards.add(new Minion("Witch", "For his self and all own minion from his sides add one power buff with increase 2 AP and one weakness buff with decrease 1 HP for 1 turn", 550, 4, 5, "ranged", 3, 4, specialPower));
        specialPower = new SpecialPower();
        target = new Target();
        target.setIt(true);
        target.setAroundIt(true);
        target.setAlly(true);
        target.setAll(true);
        target.setMinion(true);
        buff = new Buff();
        buff.setAttackPower(2);
        buff.setContinious(true);
        buff1 = new Buff();
        buff1.setHoly(true);
        buff1.setHolyCount(1);
        buff1.setContinious(true);
        spell = new Spell();
        spell.setTarget(target);
        spell.addBuff(buff).addBuff(buff1);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.PASSIVE);
        cards.add(new Minion("GrandWitch", "For his self and all own minion from his sides add one continuous power buff with increase 2 AP and one continuous holy buff", 550, 6, 6, "ranged", 5, 6, specialPower));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setAttackPower(1);
        buff.setInfitinive(true);
        buff.setContinious(true);
        target = new Target();
        target.setAlly(true);
        target.setAll(true);
        target.setMinion(true);
        spell.setTarget(target);
        spell.addBuff(buff);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_SPAWN);
        cards.add(new Minion("Jen", "Give all won minions one continuous power buff with increase 1 AP", 500, 4, 10, "ranged", 4, 5, specialPower));
        specialPower = new SpecialPower();
        specialPower.setDontAffectDisarm(true);
        specialPower.setType(SpecialPowerType.ON_DEFENCE);
        cards.add(new Minion("WildSwine", "It won't become disarm", 500, 14, 10, "melee", 0, 6, specialPower));
        specialPower = new SpecialPower();
        specialPower.setDontAffectpoison(true);
        specialPower.setType(SpecialPowerType.ON_DEFENCE);
        cards.add(new Minion("Piran", "He won't become poison", 400, 20, 8, "melee", 0, 8, specialPower));
        specialPower = new SpecialPower();
        specialPower.setDontAffectNegativeK(true);
        specialPower.setType(SpecialPowerType.ON_DEFENCE);
        cards.add(new Minion("Giv", "Doesn't take any negativity", 450, 7, 5, "ranged", 5, 4, specialPower));
        buff = new Buff();
        buff.setHit(16);
        target = new Target();
        target.setMinion(true);
        target.setRandom(true);
        target.setAlly(false);
        target.setCount(1);
        spell.setTarget(target);
        specialPower = new SpecialPower();
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_SPAWN);
        cards.add(new Minion("Bahman", "On respawn decrease 16 HP random enemy minion", 450, 9, 16, "melee", 0, 8, specialPower));
        specialPower = new SpecialPower();
        specialPower.setDontTakeDamageFromWeaker(true);
        specialPower.setType(SpecialPowerType.ON_DEFENCE);
        cards.add(new Minion("Ashkbos", "Force with lower AP can't hit him", 400, 8, 14, "melee", 0, 7, specialPower));
        cards.add(new Minion("Iranj", "", 500, 20, 6, "ranged", 3, 4, null));
        cards.add(new Minion("Big ogre", "", 600, 8, 30, "hybrid", 2, 9, null));
        specialPower = new SpecialPower();
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        target = new Target();
        target.setAlly(false);
        target.setCount(1);
        buff = new Buff();
        buff.setDeletePositive(true);
        spell.setTarget(target);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_ATTACK);
        cards.add(new Minion("OgreWithTwoHead", "When attack to force destroy all target positive buff", 550, 4, 10, "melee", 0, 4, specialPower));
        target = new Target();
        target.setAroundIt(true);
        target.setAlly(false);
        target.setAll(true);
        target.setMinion(true);
        spell = new Spell();
        buff = new Buff();
        buff.setStun(true);
        buff.setNumberOfTurns(1);
        spell.addBuff(buff);
        spell.setTarget(target);
        specialPower = new SpecialPower();
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_SPAWN);
        cards.add(new Minion("NaneSarma", "On spawn Stun all enemy minion on her sides", 500, 4, 3, "ranged", 5, 3, specialPower));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setHoly(true);
        buff.setHolyCount(12);
        buff.setContinious(true);
        spell.addBuff(buff);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.PASSIVE);
        cards.add(new Minion("FoladZereh", "Have 12 continuous holy buff", 650, 1, 1, "melee", 3, 0, specialPower));
        specialPower = new SpecialPower();
        spell = new Spell();
        buff = new Buff();
        buff.setHit(6);
        target = new Target();
        target.setAlly(false);
        target.setHero(true);
        spell.setTarget(target);
        spell.addBuff(buff);
        specialPower.setSpell(spell);
        specialPower.setType(SpecialPowerType.ON_DEATH);
        cards.add(new Minion("Siyavash", "On death hit 6 HP to enemy hero", 350, 5, 8, "melee", 0, 4, specialPower));
        specialPower = new SpecialPower();
        specialPower.setType(SpecialPowerType.COMBO);
        cards.add(new Minion("ShahGhol", "", 600, 4, 10, "melee", 0, 5, specialPower));
        specialPower = new SpecialPower();
        specialPower.setType(SpecialPowerType.COMBO);
        cards.add(new Minion("ArzhangDiv", "", 600, 6, 6, "melee", 0, 3, specialPower));
        Random random = new Random();
        for (Card card : cards) {
            card.setNumber(random.nextInt(10));
        }
    }

    private void initializeItems() {
        items.add(new Item("CrownOfKnowledge", 300, "Add 1 mana in 3 first turn", 1, 3, false, null));
        Spell spell = new Spell();
        Target target = new Target();
        target.setAll(true);
        target.setHero(true);
        Buff buff = new Buff();
        buff.setHolyCount(10);
        buff.setHoly(true);
        spell.setTarget(target);
        spell.addBuff(buff);
        items.add(new Item("Parisa", 4000, "Active 12 holy buff to own hero", 0, 0, false, spell));
        items.add(new Item("Damol'sArrow", 30000, "Only for ranged adn hybrid : when attacked on own hero enemy disarm for 1 turn"));
        items.add(new Item("Phoenix'wing", 3500, "if enemy hero is ranged or hybrid decrease 2 AP"));
        items.add(new Item("TerrorHood", 5000, "On attack, add one weakness buff with decrease 2 AP on random force"));
        items.add(new Item("KingWisdom ", 9000, "In all turns add 1 mana", 1, 0, true, null));
        items.add(new Item("AssassinationDagger", 15000, "When put each own force card on field hit 1 HP to enemy hero"));
        items.add(new Item("PoisonousDagger", 7000, "On own attack add one poison buff for 1 turn to random enemy force"));
        items.add(new Item("ShockHammer", 15000, "Own hero on attack for 1 turn disarm enemy force"));
        items.add(new Item("SoulEater", 25000, "On death of each own force add 1 power buff with 1 increase AP too random force"));
        items.add(new Item("B‌aptism'ablution", 20000, "Each minion on spawn give 2 turns holy buff"));
        Random random = new Random();
        for (Item item : items) {
            item.setNumber(random.nextInt(10));
        }
    }
}
