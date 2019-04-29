package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

import Modules.PlayableThings.cards.Force;

public abstract class Buff {
    private int numberOfTurns;
    private int executeTime;
    private int[] effectPoint = new int[2];
    private int dimension;
    private Boolean disarm;
    private int attackpower;
    private int healthPower;
    private Boolean fireHouse;
    private Boolean poisonHouse;
    private int hit;
    private Boolean holy;
    private Boolean kill;
    private Boolean stun;
    private boolean isContinious;
    private Boolean deleteNegative;
    private Boolean deletePositive;
    private Force enemy;
    private int turnsOfAttack;
    //todo target

    public void doEffect(){
        //todo
    }
}
