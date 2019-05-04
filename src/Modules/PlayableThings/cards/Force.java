package Modules.PlayableThings.cards;

import java.util.ArrayList;
import java.util.Iterator;

import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.Item.Flag;


public abstract class Force extends Card {
    protected int attackPower;
    protected int hitPoint;
    protected ArrayList<Flag> flags;
    protected String attackType;
    protected boolean canMove = false;
    protected boolean canAttack = false;
    protected int range;
    protected ArrayList<Buff> buffs = new ArrayList<>();

    void addBuff(Buff buff) {
        buffs.add(buff);
    }

    public Force(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint) {
        super(name, description, price, manaPoint);
        this.attackPower = attackPower;
        this.hitPoint = hitPoint;
        this.attackType = attackType;
        this.range = range;
    }

    public Force(int attackPower, int hitPoint, String attackType, int range) {
        this.attackPower = attackPower;
        this.hitPoint = hitPoint;
        this.attackType = attackType;
        this.range = range;
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

    public void checkBuffs() {
        //dorosteh
        Iterator<Buff> iterator = buffs.iterator();
        while (iterator.hasNext()) {
            Buff buff = iterator.next();
            if (buff.getNumberOfTurns() == 0)
                iterator.remove();
        }
    }

    public void agging() {
        for (Buff buff : buffs) {
            buff.aging();
        }
        checkBuffs();
        canAttack = true;
        canMove = true;
    }

    public void takeFlag(Flag flag) {
        flags.add(flag);
    }

    public void moved() {
        canMove = false;
    }

    public void attack(Force force){
        //check on attck an buffs
        if (canAttack) {
            force.defend(this);
            canAttack = false;
            canMove = false;
        }
        else
            System.out.println("This card has attacked");
    }

    private void defend(Force force){
        //check on defence and buffs
        hitPoint -= force.getAttackPower();
    }

    public void counterAttack(Force force){
        //check conditions
        force.defend(this);
    }
}
