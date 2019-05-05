package Modules.PlayableThings.cards;

import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPowerType;
import Modules.PlayableThings.cards.Spell.Spell;
import View.View.Show;

import java.util.ArrayList;

public class Minion extends Force {
    private ArrayList<SpecialPower> specialPowers = new ArrayList<>();

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

    public void showCard() {
        Show.showMinionCardInfo(name, hitPoint, attackPower, manaPoint, range, price, description);
    }

    public boolean hasComboAttack(){
        for (SpecialPower specialPower : specialPowers) {
            if (specialPower.getType() == SpecialPowerType.COMBO)
                return true;
        }
        return false;
    }

    public Spell die() {
        for (SpecialPower specialPower : specialPowers) {
            if (specialPower.getType() == SpecialPowerType.ON_DEATH)
                return specialPower.getSpell();
        }
        return null;
    }

    public Spell insert() {
        for (SpecialPower specialPower : specialPowers) {
            //on spawn
            if (specialPower.getType() == SpecialPowerType.ON_SPAWN)
                return specialPower.getSpell();
        }
        return null;
    }

    @Override
    public boolean getCanAttck() {
        return super.getCanAttck();
    }

    //check syntax
    public void attack(Force force) {
        if (canAttack) {
            //check on attack
            super.attack(force);
        }
        else
            System.out.println("you cant attack");
    }

    public void defend(Force force) {
        //check on defence
        super.defend(force);
    }

    public void prepareForTurn(boolean isItMyTurn) {
        super.prepareForTurn(isItMyTurn);
        if (isItMyTurn) {
            //Onturn & passive
        }
    }
}
