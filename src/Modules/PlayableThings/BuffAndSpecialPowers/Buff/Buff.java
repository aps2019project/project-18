package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

public abstract class Buff {
    private boolean positivity;
    protected int numberOfTurns;
    protected int[] effectPoint = new int[2];
    protected int dimenision;

    public Buff(){

    }

    public Buff(boolean positivity){
        this.positivity = positivity;
    }

    public boolean isPositivity() {
        return positivity;
    }

    public void doEffect(){
        //todo
    }

}
