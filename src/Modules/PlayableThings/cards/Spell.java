package Modules.PlayableThings.cards;

import Modules.Playground;

public class Spell extends Card {
    private String target;

    public Spell(String name, String description, int price, int manaPoint, String target) {
        super(name, description, price , manaPoint);
        this.target = target;
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
