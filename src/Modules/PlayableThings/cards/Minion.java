package Modules.PlayableThings.cards;

import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;

public class Minion extends Force {
    private SpecialPower specialPower;
    private Buff[] mySpecial;
    private int manaPoint;

    public Minion(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint) {
        super(name, description, price, attackPower, hitPoint, attackType, range);
        this.manaPoint = manaPoint;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public void excuteAbility() {
        //todo
    }
}
