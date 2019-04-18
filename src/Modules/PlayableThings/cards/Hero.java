package Modules.PlayableThings.cards;

public class Hero extends Force{
    private  int spellCoolDown;
    private int usedSpellCoolDown = 0;
    private Spell spell;

    public void excuteAbility(){
        if (usedSpellCoolDown == 0){
            usedSpellCoolDown = spellCoolDown;
            //todo
        }
    }

    public void decreaseCoolDown(){
        if (usedSpellCoolDown > 0)
            usedSpellCoolDown--;
    }
}
