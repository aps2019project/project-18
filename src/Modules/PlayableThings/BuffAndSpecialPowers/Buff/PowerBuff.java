package Modules.PlayableThings.BuffAndSpecialPowers.Buff;

public class PowerBuff extends  Buff{
    private int HealthPowerIncreaseNumber = 0;
    private int AttackPowerIncreseNumber = 0;
    private boolean positivity = true;

    @Override
    public void doEffect() {
        //todo
    }

    public boolean isPositivity() {
        return positivity;
    }
}
