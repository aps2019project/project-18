package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

import Modules.PlayableThings.cards.Force;

import java.util.HashMap;

public abstract class Buff {
    private int numberOfTurns;
    private int executeTime;
    private int dimension;
    private boolean disarm;
    private int attackPower;
    private int hitPoint;
    private boolean fireHouse;
    private boolean poison;
    private int hit;
    private Boolean holy;
    private boolean kill;
    private boolean stun;
    private boolean isContinious;
    private boolean infitinive;
    private boolean deleteNegative;
    private boolean deletePositive;
    private boolean risingAttackWithTurns;
    private HashMap<Force , Integer> attackedPerson = new HashMap<>();
    private boolean dispel;
    private int rangeRising;
    //check


    public void doEffect(){
        //todo
    }

    public void aging(){
        if (!infitinive && isContinious)
            numberOfTurns--;
        if (executeTime > 0)
            executeTime--;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public boolean isInfinitive(){
        return infitinive;
    }

    public boolean isContinious(){
        return isContinious;
    }
}
