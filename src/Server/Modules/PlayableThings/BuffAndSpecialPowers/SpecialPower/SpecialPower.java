package Server.Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower;

import Server.Modules.PlayableThings.cards.Spell.Spell;

public class SpecialPower {
    SpecialPowerType type;
    Spell spell;
    private boolean dontAffectHoly;
    private boolean dontAffectDisarm;
    private boolean dontAffectpoison;
    private boolean dontTakeDamageFromWeaker;
    private boolean dontAffectNegativeK;

    public boolean isDontTakeDamageFromWeaker() {
        return dontTakeDamageFromWeaker;
    }

    public void setDontTakeDamageFromWeaker(boolean dontTakeDamageFromWeaker) {
        this.dontTakeDamageFromWeaker = dontTakeDamageFromWeaker;
    }

    public boolean isDontAffectHoly() {
        return dontAffectHoly;
    }

    public boolean isDontAffectDisarm() {
        return dontAffectDisarm;
    }

    public boolean isDontAffectpoison() {
        return dontAffectpoison;
    }

    public boolean isDontAffectNegativeK() {
        return dontAffectNegativeK;
    }

    public void setType(SpecialPowerType type) {
        this.type = type;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }


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
