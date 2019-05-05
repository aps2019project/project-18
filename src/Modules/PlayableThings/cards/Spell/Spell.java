package Modules.PlayableThings.cards.Spell;

import Modules.PlayableThings.cards.Card;
import Modules.Playground;

public class Spell extends Card {
    private Target target;

    public Spell(String name, String description, int price, int manaPoint, Target target) {
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
