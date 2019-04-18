package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

public class PowerAndWeaknessBuff extends  Buff{
    private int HealthPowerIncreaseNumber = 0;
    private int AttackPowerIncreseNumber = 0;

    public PowerAndWeaknessBuff(){
        super(HealthPowerIncreaseNumber >= 0 && AttackPowerIncreaseNumber >= 0);
    }

    @Override
    public void doEffect() {
        //todo
    }
}
