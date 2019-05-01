package Modules.PlayableThings.cards;

import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;

public class Minion extends  Force {
    private SpecialPower specialPower;
    private Buff[] mySpecial;
    private int manaPoint;

    public int getManaPoint() {
        return manaPoint;
    }

    public void excuteAbility(){
        //todo
    }
}
