package Modules.PlayableThings.cards;

import java.util.ArrayList;
import java.util.Iterator;

import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPowerType;
import Modules.PlayableThings.Item.Flag;
import Modules.PlayableThings.cards.Spell.Spell;


public abstract class Force extends Card {
    protected int attackPower;
    protected int hitPoint;
    protected ArrayList<Flag> flags;
    protected String attackType;
    protected boolean canMove = false;
    protected boolean canAttack = false;
    protected int range;
    protected ArrayList<Buff> buffs = new ArrayList<>();
    protected ArrayList<SpecialPower> specialPowers = new ArrayList<>();

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public void addBuff(Buff buff) {
        for (SpecialPower specialPower : specialPowers){
            if (specialPower.getType() == SpecialPowerType.ON_DEFENCE) {
                if (specialPower.isDontAffectNegativeK() && buff.isNegative())
                    return;
                else if (specialPower.isDontAffectDisarm() && buff.isDisarm())
                    return;
                else if (specialPower.isDontAffectpoison() && buff.isPoison())
                    return;
            }
        }
        buffs.add(buff);
    }

    public Force(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint) {
        super(name, description, price, manaPoint);
        this.attackPower = attackPower;
        this.hitPoint = hitPoint;
        this.attackType = attackType;
        this.range = range;
    }

    public Force(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint , SpecialPower specialPower) {
        super(name, description, price, manaPoint);
        this.attackPower = attackPower;
        this.hitPoint = hitPoint;
        this.attackType = attackType;
        this.range = range;
        specialPowers.add(specialPower);
    }

    public Force(String name, String description, int price, int attackPower, int hitPoint, String attackType, int range, int manaPoint , Buff buff) {
        super(name, description, price, manaPoint);
        this.attackPower = attackPower;
        this.hitPoint = hitPoint;
        this.attackType = attackType;
        this.range = range;
        buffs.add(buff);
    }

    public Force(int attackPower, int hitPoint, String attackType, int range) {
        this.attackPower = attackPower;
        this.hitPoint = hitPoint;
        this.attackType = attackType;
        this.range = range;
    }

    public Spell die() {
        for (SpecialPower specialPower : specialPowers) {
            if (specialPower.getType() == SpecialPowerType.ON_DEATH)
                return specialPower.getSpell();
        }
        return null;
    }

    public Spell insert(Flag flag) {
        takeFlag(flag);
        for (SpecialPower specialPower : specialPowers) {
            //on spawn
            if (specialPower.getType() == SpecialPowerType.ON_SPAWN)
                return specialPower.getSpell();
        }
        return null;
    }

    public boolean getCanAttack(){
        return canAttack;
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
            if (buff.getNumberOfTurns() == 0 && !buff.isContinious() && !buff.isInfinitive() && buff.getExecuteTime() == 0)
                iterator.remove();
        }
    }

    public void agging() {
        for (Buff buff : buffs) {
            buff.aging();
        }
        checkBuffs();
    }

    public void takeFlag(Flag flag) {
        if (flag != null)
            flags.add(flag);
    }

    public void moved() {
        canMove = false;
    }

    public boolean canMove(){
        for (Buff buff : buffs) {
            if (buff.isStun())
                return false;
        }
        return canMove;
    }

    public boolean canAttack(){
        if (!canAttack)
            return false;
        for (Buff buff : buffs) {
            if (buff.isStun())
                return false;
        }
        return true;
    }

    public void attack(Force force , boolean haveCounterAttack , boolean canCounterAttack){
        int defence = 0;
        for (Buff buff : buffs){
            if (buff.isStun())
                return;
        }for (Buff buff : force.buffs){
            if (buff.getHoly())
                defence += buff.getHolyCount();
        }
        if (canAttack) {
            canAttack = false;
            canMove = false;
            for (SpecialPower specialPower: specialPowers){
                if (specialPower.getType() == SpecialPowerType.ON_ATTACK) {
                    if (specialPower.getSpell() != null) {
                        //specialPower.getSpell().execute(force);
                    }
                    if (specialPower.isDontAffectHoly())
                        defence = 0;
                    for (Buff buff : specialPower.getSpell().getBuffs())
                        if (buff.isRisingAttackWithTurns())
                            defence -= buff.isAttacked(force)*5;
                }
            }
            for (SpecialPower specialPower : force.specialPowers) {
                if (specialPower.getType() == SpecialPowerType.ON_DEFENCE) {
                    if (specialPower.isDontTakeDamageFromWeaker()) {
                        if (force.getAttackPower() > attackPower)
                            return;
                    }
                    else if (specialPower.isDontAffectNegativeK())
                        return;
                }
            }
            force.hitPoint -= (attackPower - defence);
            if (force.hitPoint < 0)
                force.hitPoint = 0;
            if (haveCounterAttack && canCounterAttack)
                force.counterAttack(this);
        }
        else
            System.out.println("This card has attacked");
    }

    public void defend(Force force){
        int counter = 0;
        for (SpecialPower specialPower : specialPowers){
            if (specialPower.getType() == SpecialPowerType.ON_DEFENCE && specialPower.isDontTakeDamageFromWeaker())
                if (attackPower > force.attackPower)
                    return;
        }
        for (Buff buff : buffs){
            if (buff.getHoly())
                counter += buff.getHolyCount();
        }
        hitPoint -= (force.getAttackPower() -  counter);
    }

    public void counterAttack(Force force){
        for (Buff buff : buffs){
            if ((buff.isDisarm() || buff.isStun()))
                return;
        }
        force.defend(this);
    }

    public Flag[] getFlags(){
        return (Flag[])flags.toArray();
    }

    public void prepareForTurn(boolean isItMyTurn){
        //todo check some buffs
        canAttack = true;
        canMove = true;
    }

}
