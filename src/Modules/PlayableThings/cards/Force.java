package Modules.PlayableThings.cards;

import java.util.ArrayList;

public abstract class Force extends Card {
    protected int attackPower;
    protected int healthPower;
    protected boolean flag;
    protected String attackType;
    protected boolean canMove;
    protected int range;
    protected ArrayList<Buff> buffs;

    void addBuff(Buff buff){
        //todo
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
