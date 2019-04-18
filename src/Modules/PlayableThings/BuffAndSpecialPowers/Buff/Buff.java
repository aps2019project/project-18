package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

public abstract class Buff {
    private boolean positivity;
    protected int numberOfTurns;
    protected int[] effectPoint = new int[2];
    protected int dimenision;

    public Buff(){

    }

    public Buff(boolean positivity , int numberOfTurns , int x , int y , int dimenision){
        this.positivity = positivity;
        this.numberOfTurns = numberOfTurns;
        effectPoint[0] = x;
        effectPoint[1] = y;
        this.dimenision = dimenision;
    }

    public boolean isPositivity() {
        return positivity;
    }

    public void doEffect(){
        //todo
    }

}
