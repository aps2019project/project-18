package Modules.PlayableThings.cards;

import Modules.PlayableThings.cards.Spell.Spell;

public class Hero extends Force {
    private int spellCoolDown;
    private int spellMana;
    private int usedSpellCoolDown = 0;
    private Spell spell;

    public Hero(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int spellCoolDown, int spellMana) {
        super(name, description, price, attackPower, hitPoint, attackType, range , 0);
        this.spellCoolDown = spellCoolDown;
        this.spellMana = spellMana;
    }

    public void excuteAbility() {
        if (usedSpellCoolDown == 0) {
            usedSpellCoolDown = spellCoolDown;
            //todo
        }
    }

    public void decreaseCoolDown() {
        if (usedSpellCoolDown > 0)
            usedSpellCoolDown--;
    }

    @Override
    public Card getCopyCard() {
        return new Hero(this.name, this.description, this.price, this.attackPower, this.hitPoint,
                this.attackType, this.range, this.spellCoolDown, this.spellMana);
    }
}
