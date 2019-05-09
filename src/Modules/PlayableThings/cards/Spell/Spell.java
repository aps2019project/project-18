package Modules.PlayableThings.cards.Spell;

import Modules.GameBusiness.Game.Game;
import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.cards.Card;
import Modules.PlayableThings.cards.Force;
import Modules.PlayableThings.cards.Hero;
import Modules.Playground;
import View.View.Show;

import java.util.ArrayList;

public class Spell extends Card {
    private Target target;
    private ArrayList<Buff> buffs = new ArrayList<>();
    Integer[][] targets;


    public Spell(String name, String description, int price, int manaPoint, Target target) {
        super(name, description, price, manaPoint);
        this.target = target;
    }

    public Spell(String name, String description, int price, int manaPoint, Target target, ArrayList<Buff> buffs) {
        super(name, description, price, manaPoint);
        this.target = target;
        this.buffs.addAll(buffs);
    }

    public Spell() {

    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public Spell setTarget(Target target) {
        this.target = target;
        return this;
    }

    public Spell addBuff(Buff buff) {
        buffs.add(buff);
        return this;
    }

    public boolean executeBuff(Game game, int x, int y, String userNamePlayerHAveTurn) {
        Integer[][] targets = target.getTargets(game, x, y, userNamePlayerHAveTurn);
        if (targets == null) return false;
        if (target.isGround()) {
            for (Buff buff : buffs) {
                if (buff.getHolyCount() > 0) {
                    for (Integer[] integers : targets) {
                        game.getPlayground().getGround()[integers[0]][integers[1]].addHolyBuff(buff.getNumberOfTurns());
                    }
                }
                if (buff.getPoisonCount() > 0) {
                    for (Integer[] integers : targets) {
                        game.getPlayground().getGround()[integers[0]][integers[1]].addHolyBuff(buff.getNumberOfTurns());
                    }
                }
                if (buff.getFireCount() > 0) {
                    for (Integer[] integers : targets) {
                        game.getPlayground().getGround()[integers[0]][integers[1]].addHolyBuff(buff.getNumberOfTurns());
                    }
                }
            }
        } else {
            for (Buff buff : buffs) {
                for (Integer[] integers : targets) {
                    Force force = (Force) game.getPlayground().getGround()[integers[0]][integers[1]].getCard();
                    if (buff.isBuff()) {
                        force.addBuff(buff.getBuffCopy());
                        force.setAttackPower(force.getAttackPower() + buff.getAttackPower());
                        force.setHitPoint(force.getHitPoint() + buff.getHitPoint());
                    } else {
                        execute(buff, force, userNamePlayerHAveTurn);
                    }
                }
            }
        }
        return true;
    }

    private void execute(Buff buff, Force force, String playerHaveTurn) {
        force.setAttackPower(force.getAttackPower() + buff.getAttackPower());
        if (force.getAttackPower() < 0) force.setAttackPower(0);
        force.setHitPoint(force.getHitPoint() - buff.getHit());
        if (force.getHitPoint() < 0) force.setHitPoint(0);
        if (force.getId().contains(playerHaveTurn)) {
            //own force
            force.diepell(false);
        }
        if (!force.getId().contains(playerHaveTurn)) {
            //enemy force
            force.diepell(true);
        }
        if (buff.isKill()) {
            force.setHitPoint(0);
        }
    }

    public void executeOnAttack(Force force) {
        for (Buff buff : buffs) {
            if (buff.isDeletePositive()) {
                force.diepell(true);
            } else {
                force.addBuff(buff);
            }
        }
    }

    public void execute(Game game, int x, int y) {
        targets = target.getTargets(game, x, y, id.split("_")[0]);
    }

    @Override
    public Card getCopyCard() {
        Spell spell;
        if (target != null)
            spell = new Spell(this.name, this.description, this.price, this.manaPoint, this.target.getCopy(),
                    this.getBuffsCopy());
        else
            spell = new Spell(this.name, this.description, this.price, this.manaPoint, this.target,
                    this.getBuffsCopy());
        spell.setId(this.id);
        return spell;
    }

    public ArrayList<Buff> getBuffsCopy() {
        ArrayList<Buff> result = new ArrayList<>();
        for (Buff buff : buffs)
            result.add(buff.getBuffCopy());
        return result;
    }

    @Override
    public void showCard() {
        Show.showSpellCardInfo(name, manaPoint, price, description);
    }


}
