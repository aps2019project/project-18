package Modules.PlayableThings.cards;

import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;

public class Minion extends Force {
    private SpecialPower specialPower;
    private Buff[] mySpecial;

    public Minion(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint) {
        super(name, description, price, attackPower, hitPoint, attackType, range , manaPoint);
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public void excuteAbility() {
        //todo
    }

    @Override
    public Card getCopyCard() {
        return new Minion(this.name, this.description, this.price, this.attackPower,
                this.hitPoint, this.attackType, this.range, this.manaPoint);
    }
}
