package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

public class PowerAndWeaknessBuff extends  Buff{
    private int healthPowerIncreaseNumber = 0;
    private int attackPowerIncreaseNumber = 0;

    public PowerAndWeaknessBuff(int healthPowerIncreaseNumber , int attackPowerIncreaseNumber){
        super(healthPowerIncreaseNumber >= 0 && attackPowerIncreaseNumber >= 0);
        this.attackPowerIncreaseNumber = attackPowerIncreaseNumber;
        this.healthPowerIncreaseNumber = healthPowerIncreaseNumber;
    }

    @Override
    public void doEffect() {
        //todo
    }
}
