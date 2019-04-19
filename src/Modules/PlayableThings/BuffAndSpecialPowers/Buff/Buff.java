package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

public abstract class Buff {
    private boolean positivity;
    protected int numberOfTurns;
    protected int[] effectPoint = new int[2];
    protected int dimension;

    public Buff(){

    }

    public Buff(boolean positivity , int numberOfTurns , int x , int y , int dimension){
        this.positivity = positivity;
        this.numberOfTurns = numberOfTurns;
        effectPoint[0] = x;
        effectPoint[1] = y;
        this.dimension = dimension;
    }

    public boolean getPositivity() {
        return positivity;
    }

    public void doEffect(){
        //todo
    }

}
