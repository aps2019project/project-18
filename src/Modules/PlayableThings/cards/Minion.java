package Modules.PlayableThings.cards;

import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPowerType;
import Modules.PlayableThings.cards.Spell.Spell;

public class Minion extends Force {
    private SpecialPower specialPower;

    public Minion(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint) {
        super(name, description, price, attackPower, hitPoint, attackType, range , manaPoint);
    }

    public void excuteAbility() {
        //todo
    }

    @Override
    public Card getCopyCard() {
        return new Minion(this.name, this.description, this.price, this.attackPower,
                this.hitPoint, this.attackType, this.range, this.manaPoint);
    }

    public boolean hasComboattack(){
        if (specialPower.getType() == SpecialPowerType.COMBO)
            return true;
        return false;
    }

    public Spell die(){
        if (specialPower.getType() == SpecialPowerType.ON_DEATH)
            return specialPower.getSpell();
        return null;
    }

    public Spell insert(){
        if (specialPower.getType() == SpecialPowerType.ON_SPAWN)
            return specialPower.getSpell();
        return  null;
    }
//check syntax
    public void attack(Force force){
        //check on attack
        super.attack(force);
    }

    public void defend(Force force){
        //check on defence
        super.defend(force);
    }

}
