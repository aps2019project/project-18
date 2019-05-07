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
        if (specialPowers.size() == 0)
            return new Minion( name,  description,  price,  attackPower,  hitPoint,  attackType,  range,  manaPoint ,  specialPowers.get(0));
        else
            return new Minion( name,  description,  price,  attackPower,  hitPoint,  attackType,  range,  manaPoint ,  null);
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

    public void prepareForTurn(boolean isItMyTurn) {
        super.prepareForTurn(isItMyTurn);
        if (isItMyTurn) {
            //Onturn & passive
        }
    }
}
