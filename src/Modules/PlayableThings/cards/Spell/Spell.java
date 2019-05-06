package Modules.PlayableThings.cards.Spell;

import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.cards.Card;
import Modules.Playground;
import View.View.Show;

import java.util.ArrayList;

public class Spell extends Card {
    private Target target;
    private ArrayList<Buff> buffs = new ArrayList<>();


    public Spell(String name, String description, int price, int manaPoint, Target target) {
        super(name, description, price, manaPoint);
        this.target = target;
    }

    public Spell(String name, String description, int price, int manaPoint, Target target , ArrayList<Buff> buffs) {
        super(name, description, price, manaPoint);
        this.target = target;
        this.buffs.addAll(buffs);
    }

    public Spell(){

    }

    public Spell setTarget(Target target) {
        this.target = target;
        return this;
    }

    public Spell addBuff(Buff buff) {
        buffs.add(buff);
        return this;
    }

    public void executeBuff(Playground playground, int x, int y) {
        // TODO
    }

    public void execute() {
        // TODO
    }

    @Override
    public Card getCopyCard() {
        return new Spell(this.name, this.description, this.price, this.manaPoint, this.target , buffs);
    }

    @Override
    public void showCard() {
        Show.showSpellCardInfo(name, manaPoint, price, description);
    }


}
