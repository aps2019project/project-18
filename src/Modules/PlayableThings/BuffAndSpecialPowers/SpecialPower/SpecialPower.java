package Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower;

import Modules.PlayableThings.cards.Spell.Spell;

public class SpecialPower {
    SpecialPowerType type;
    Spell spell;
    private boolean dontAffectHoly;
    private boolean dontAffectDisarm;
    private boolean dontAffectpoison;
    private boolean dontAffectNegativeK;

    public void setDontAffectHoly(boolean dontAffectHoly) {
        this.dontAffectHoly = dontAffectHoly;
    }

    public void setDontAffectDisarm(boolean dontAffectDisarm) {
        this.dontAffectDisarm = dontAffectDisarm;
    }

    public void setDontAffectpoison(boolean dontAffectpoison) {
        this.dontAffectpoison = dontAffectpoison;
    }

    public void setDontAffectNegativeK(boolean dontAffectNegativeK) {
        this.dontAffectNegativeK = dontAffectNegativeK;
    }


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
