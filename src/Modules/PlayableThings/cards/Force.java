package Modules.PlayableThings.cards;

import java.util.ArrayList;

import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;

public abstract class Force extends Card {
    protected int attackPower;
    protected int hitPoint;
    protected boolean flag;
    protected String attackType;
    protected boolean canMove;
    protected boolean canAttack;
    protected int range;
    protected ArrayList<Buff> buffs = new ArrayList<>();

    void addBuff(Buff buff) {
        buffs.add(buff);
    }

    /*public boolean getFlag(){
        //todo
    }*/

    public boolean getCanAttack() {
        return getCanAttack();
    }

    public boolean getCanMove() {
        return canMove;
    }

    public String getAttackType() {
        return attackType;
    }

    public int getRange() {
        return range;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void checkBuff() {
        //todo
    }
}
