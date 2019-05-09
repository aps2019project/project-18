package Modules.PlayableThings.cards;

import Modules.House;
import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPower;
import Modules.PlayableThings.BuffAndSpecialPowers.SpecialPower.SpecialPowerType;
import Modules.PlayableThings.Item.Flag;
import Modules.PlayableThings.cards.Spell.Spell;

import java.util.ArrayList;
import java.util.Iterator;


public abstract class Force extends Card {
    protected int attackPower;
    protected int hitPoint;
    protected ArrayList<Flag> flags = new ArrayList<>();
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
        if (specialPower != null)
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
            if (buff.getNumberOfTurns() == 0 && !buff.isContinious() && !buff.isInfinitive() && buff.getExecuteTime() == 0) {
                attackPower -= buff.getAttackPower();
                hitPoint -= buff.getHitPoint();
                iterator.remove();
            }
            if (buff.isPoison()) {
               hitPoint -= buff.getPoisonCount();
            }
        }
    }

    public void agging() {
        for (int i = buffs.size() - 1 ;i >= 0 ; i--) {
            boolean canNotExcute = false;
            boolean canExcute = false;
            Buff buff = buffs.get(i);
            if (buff.getExecuteTime() > 0)
                canNotExcute = true;
            buff.aging();
            if (buff.getExecuteTime() == 0) {
                canExcute = true;
            }
            if (canExcute && canNotExcute) {
                if (!buff.isBuff()) {
                    attackPower += buff.getAttackPower();
                    hitPoint += (buff.getHitPoint() - buff.getHit());
                    buffs.remove(buff);
                }
                else {
                    attackPower += buff.getAttackPower();
                    hitPoint += buff.getHitPoint();
                }
            }
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

    public void attack(Force force , boolean haveCounterAttack , boolean canCounterAttack ,House defenderPlace){
        int defence = 0;
        for (Buff buff : buffs){
            if (buff.isStun())
                return;
        }for (Buff buff : force.buffs){
            if (buff.getHoly() && buff.getExecuteTime() == 0)
                defence += buff.getHolyCount();
        }
        defence += defenderPlace.getHolyCount();
        if (canAttack) {
            canAttack = false;
            canMove = false;
            for (SpecialPower specialPower: specialPowers){
                if (specialPower.getType() == SpecialPowerType.ON_ATTACK) {
                    if (specialPower.getSpell() != null) {
                        specialPower.getSpell().executeOnAttack(force);
                    }
                    if (specialPower.isDontAffectHoly())
                        defence = 0;
                    for (Buff buff : specialPower.getSpell().getBuffs())
                        if (buff.isRisingAttackWithTurns() && buff.getExecuteTime() == 0)
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
                force.counterAttack(this , defenderPlace);
        }
        else
            System.out.println("This card has attacked");
    }

    public void defend(Force force, House defenderPlace){
        int counter = 0;
        for (SpecialPower specialPower : specialPowers){
            if (specialPower.getType() == SpecialPowerType.ON_DEFENCE && specialPower.isDontTakeDamageFromWeaker())
                if (attackPower > force.attackPower)
                    return;
        }
        for (Buff buff : buffs){
            if (buff.getHoly() && buff.getExecuteTime() == 0)
                counter += buff.getHolyCount();
        }
        counter  += defenderPlace.getHolyCount();
        hitPoint -= (force.getAttackPower() -  counter);
    }

    public void dispell(boolean positive){
        for (Buff buff : buffs) {
            if (buff.isPositive() == true && positive == true){
                attackPower -= buff.getAttackPower();
                hitPoint -= buff.getHitPoint();
                if (buff.isContinious()) {
                    buff.setExecuteTime(1);
                    attackPower -= buff.getAttackPower();
                    hitPoint -= buff.getHitPoint();
                }
                else
                    buffs.remove(buff);
            }else if (buff.isNegative() == true && positive == false){
                attackPower -= buff.getAttackPower();
                hitPoint -= buff.getHitPoint();
                if (buff.isContinious()) {
                    buff.setExecuteTime(1);
                    attackPower -= buff.getAttackPower();
                    hitPoint -= buff.getHitPoint();
                }
                else
                    buffs.remove(buff);
            }
        }
    }

    public void counterAttack(Force force , House defenderPlace){
        for (Buff buff : buffs){
            if ((buff.isDisarm() || buff.isStun()) && buff.getExecuteTime() == 0)
                return;
        }
        force.defend(this, defenderPlace);
    }

    public ArrayList<Flag> getFlags(){
        return flags;
    }

    public void prepareForTurn(boolean isItMyTurn){
        for (SpecialPower specialPower : specialPowers) {
            if (specialPower.getType() == SpecialPowerType.PASSIVE) {
                //todo
            }
            if (specialPower.getType() == SpecialPowerType.ON_TURN && isItMyTurn){
                //todo
            }
        }
        canAttack = true;
        canMove = true;
    }

    public void setCan(){
        canAttack = true;
        canMove = true;
    }

}
