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
    public Card getCopyCard() {
        return new Spell(this.name, this.description, this.price, this.manaPoint, this.target);
    }
}
