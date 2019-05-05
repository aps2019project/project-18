package Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower;

import Modules.PlayableThings.cards.Spell.Spell;

public class SpecialPower {
    SpecialPowerType type;
    Spell spell;
    private boolean dontAffectHoly;
    private boolean dontAffectDisarm;
    private boolean dontAffectpoison;
    private boolean dontAffectNegative;

    public SpecialPowerType getType() {
        return type;
    }

    public void excute(){
        //todo
    }

    public Spell getSpell() {
        return spell;
    }
}
