package Modules.PlayableThings.cards;

import java.util.ArrayList;
import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;

public abstract class Force extends Card {
    protected int attackPower;
    protected int healthPower;
    protected boolean flag;
    protected String attackType;
    protected boolean canMove;
    protected int range;
    protected ArrayList<Buff> buffs;

    void addBuff(Buff buff){
        buffs.add(buff);
    }

    public boolean getFlag(){
        //todo
    }

    public int getRange() {
        return range;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getHealthPower() {
        return healthPower;
    }

    public void checkBuff(){
        //todo
    }
}
