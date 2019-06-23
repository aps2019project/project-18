package Modules.PlayableThings.cards;


import Modules.PlayableThings.cards.Spell.Spell;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CustomCard {
    FileWriter file;
    PrintWriter writer;
    FileReader fileReader;
    Scanner scanner;

    {
        try {
            file = new FileWriter("cards.txt");
            writer = new PrintWriter(file);
            fileReader = new FileReader("cards.txt");
            scanner = new Scanner(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void craeteCustomCard(String cartDetails) {
        writer.write(cartDetails);
    }

    public Card initializeCards() {
        Card card;
        String name = scanner.nextLine();
        String type = scanner.nextLine();
        switch (type) {
            case "spell":
                return customSpell(name);
            case "minion":
                return customMinion(name);
            case "hero":
                return customHero(name);
        }
        return null;
    }

    private Spell customSpell(String name) {
        int cost = scanner.nextInt();
        int manaPoint = scanner.nextInt();
        return new Spell(name, "", cost, manaPoint, null);
    }

    private Minion customMinion(String name) {
        int ap = scanner.nextInt();
        int hp = scanner.nextInt();
        String attackType = scanner.nextLine();
        int range = scanner.nextInt();
        int cost = scanner.nextInt();
        int manaPoint = scanner.nextInt();
        return new Minion(name, "", cost, ap, hp, attackType, range, manaPoint, null);
    }

    private Hero customHero(String name) {
        int ap = scanner.nextInt();
        int hp = scanner.nextInt();
        String attackType = scanner.nextLine();
        int range = scanner.nextInt();
        int cost = scanner.nextInt();
        return new Hero(name, "", cost, ap, hp, attackType, range, null, 0, 0);
    }
}
