package Modules.PlayableThings.cards;

import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPowerType;
import View.View.Show;


public class Minion extends Force {

    public Minion(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint , SpecialPower specialPower) {
        super(name, description, price, attackPower, hitPoint, attackType, range , manaPoint , specialPower);
    }

    public void excuteAbility() {
        //todo
    }

    @Override
    public Card getCopyCard() {
        Minion minion;
        if (specialPowers.size() > 0)
            minion = new Minion( name,  description,  price,  attackPower,  hitPoint,  attackType,  range,  manaPoint ,  specialPowers.get(0));
        else
            minion = new Minion( name,  description,  price,  attackPower,  hitPoint,  attackType,  range,  manaPoint ,  null);
        minion.setId(this.id);
        return minion;
    }

    public void showCard() {
        Show.showMinionCardInfo(name, hitPoint, attackPower, manaPoint, range, hasComboAttack() ,price, description );
    }

    public boolean hasComboAttack(){
        for (SpecialPower specialPower : specialPowers) {
            if (specialPower.getType() == SpecialPowerType.COMBO)
                return true;
        }
        return false;
    }
}
