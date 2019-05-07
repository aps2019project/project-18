package Modules.PlayableThings.cards;

import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Modules.PlayableThings.cards.Spell.Spell;
import View.View.Show;

public class Hero extends Force {
    private int spellCoolDown;
    private int spellMana;
    private int usedSpellCoolDown = 0;
    private Spell spell;

    public Hero(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, Spell spell , int spellCoolDown, int spellMana) {
        super(name, description, price, attackPower, hitPoint, attackType, range, 0);
        this.spell = spell;
        this.spellCoolDown = spellCoolDown;
        this.spellMana = spellMana;
    }

    public Hero(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, Spell spell , int spellCoolDown, int spellMana , SpecialPower specialPower) {
        super(name, description, price, attackPower, hitPoint, attackType, range, 0 , specialPower);
        this.spell = spell;
        this.spellCoolDown = spellCoolDown;
        this.spellMana = spellMana;
    }

    public Hero(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range) {
        super(name, description, price, attackPower, hitPoint, attackType, range, 0);
        spell = null;
    }

    public Hero(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint , SpecialPower specialPower) {
        super(name, description, price, attackPower, hitPoint, attackType, range, manaPoint , specialPower);
        spell = null;
    }

    public Hero(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint , Buff buff) {
        super( name, description, price, attackPower, hitPoint, attackType, range, manaPoint , buff);
        spell = null;
    }

    public void excuteAbility() {
        if (usedSpellCoolDown == 0) {
            usedSpellCoolDown = spellCoolDown;
            //todo
        }
    }

    @Override
    public void agging() {
        if (usedSpellCoolDown > 0)
            usedSpellCoolDown--;
        super.agging();
    }

    @Override
    public Card getCopyCard() {
        if (specialPowers.size() == 1)
            return new Hero( name,  description,  price,  attackPower,  hitPoint,  attackType,  range,  (Spell) spell.getCopyCard() ,  spellCoolDown,  spellMana ,  specialPowers.get(0));
        else
            return new Hero( name,  description,  price,  attackPower,  hitPoint,  attackType,  range,  (Spell) spell.getCopyCard() ,  spellCoolDown,  spellMana ,  null);
    }

    @Override
    public void showCard() {
        Show.showHeroCardInfo(name, price, description);
    }

}
