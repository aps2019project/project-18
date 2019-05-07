package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

import Modules.PlayableThings.cards.Force;

import java.util.HashMap;

public class Buff {
    private int numberOfTurns = 0;
    private int executeTime = 0;
    private int dimension = 0;
    private boolean disarm;
    private int disarmCount = 0;
    private int attackPower = 0;
    private int hitPoint = 0;
    private boolean fireHouse;
    private int fireCount = 0;
    private boolean poison;
    private int poisonCount = 0;
    private int hit = 0;
    private boolean holy;
    private int holyCount = 0;
    private boolean kill;
    private boolean stun;
    private boolean isContinious;
    private boolean infitinive;
    private boolean deleteNegative;
    private boolean deletePositive;
    private boolean risingAttackWithTurns;
    private HashMap<Force, Integer> attackedPerson = new HashMap<>();
    private boolean dispel;
    private int rangeRising = 0;
    private boolean buff;

    public boolean isBuff() {
        return buff;
    }

    public void setBuff(boolean buff) {
        this.buff = buff;
    }

    public int getDisarmCount() {
        return disarmCount;
    }

    public int getFireCount() {
        return fireCount;
    }

    public int getPoisonCount() {
        return poisonCount;
    }

    public int getHolyCount() {
        return holyCount;
    }

    public void setDisarmCount(int disarmCount) {
        this.disarmCount = disarmCount;
    }

    public void setFireCount(int fireCount) {
        this.fireCount = fireCount;
    }

    public void setPoisonCount(int poisonCount) {
        this.poisonCount = poisonCount;
    }

    public void setHolyCount(int holyCount) {
        this.holyCount = holyCount;
    }

    public void doEffect() {
        //todo
    }

    public void aging() {
        if (!infitinive && isContinious && executeTime == 0)
            numberOfTurns--;
        if (executeTime > 0)
            executeTime--;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public boolean isInfinitive() {
        return infitinive;
    }

    public boolean isContinious() {
        return isContinious;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public void setExecuteTime(int executeTime) {
        this.executeTime = executeTime;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setDisarm(boolean disarm) {
        this.disarm = disarm;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public void setFireHouse(boolean fireHouse) {
        this.fireHouse = fireHouse;
    }

    public void setPoison(boolean poison) {
        this.poison = poison;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void setHoly(Boolean holy) {
        this.holy = holy;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public void setContinious(boolean continious) {
        isContinious = continious;
    }

    public void setInfitinive(boolean infitinive) {
        this.infitinive = infitinive;
    }

    public void setDeleteNegative(boolean deleteNegative) {
        this.deleteNegative = deleteNegative;
    }

    public void setDeletePositive(boolean deletePositive) {
        this.deletePositive = deletePositive;
    }

    public void setRisingAttackWithTurns(boolean risingAttackWithTurns) {
        this.risingAttackWithTurns = risingAttackWithTurns;
    }

    public void setAttackedPerson(HashMap<Force, Integer> attackedPerson) {
        this.attackedPerson = attackedPerson;
    }

    public void setDispel(boolean dispel) {
        this.dispel = dispel;
    }

    public void setRangeRising(int rangeRising) {
        this.rangeRising = rangeRising;
    }

    public int getExecuteTime() {
        return executeTime;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isDisarm() {
        return disarm;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public boolean isFireHouse() {
        return fireHouse;
    }

    public boolean isPoison() {
        return poison;
    }

    public int getHit() {
        return hit;
    }

    public Boolean getHoly() {
        return holy;
    }

    public boolean isKill() {
        return kill;
    }

    public boolean isStun() {
        return stun;
    }

    public boolean isInfitinive() {
        return infitinive;
    }

    public boolean isDeleteNegative() {
        return deleteNegative;
    }

    public boolean isDeletePositive() {
        return deletePositive;
    }

    public boolean isRisingAttackWithTurns() {
        return risingAttackWithTurns;
    }

    public HashMap<Force, Integer> getAttackedPerson() {
        return attackedPerson;
    }

    public boolean isDispel() {
        return dispel;
    }

    public int getRangeRising() {
        return rangeRising;
    }

    public int isAttacked(Force force){
        if (risingAttackWithTurns)
        if (attackedPerson.containsKey(force)){
            int value = attackedPerson.get(force);
            attackedPerson.put(force , attackedPerson.get(force)+1);
            return value;
        }return 0;
    }
}
