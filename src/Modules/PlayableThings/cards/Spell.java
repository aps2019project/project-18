package Modules.PlayableThings.cards;

import Modules.Playground;

public class Spell extends Card {
    private int manaPoint;
    private String target;

    public Spell(String name, String description, int price, int manaPoint, String target) {
        super(name, description, price);
        this.manaPoint = manaPoint;
        this.target = target;
    }

    public int getMP() {
        return manaPoint;
    }

    public void executeBuff(Playground playground, int x, int y) {
        // TODO
    }

    public void execute() {
        // TODO
    }

    @Override
    public Card getCopyCard(Card card) {
        Spell spell = (Spell) card;
        Card copyCard = new Spell(spell.name, spell.description, spell.price, spell.manaPoint, spell.target);
        return copyCard;
    }
}
