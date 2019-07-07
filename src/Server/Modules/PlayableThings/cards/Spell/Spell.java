package Server.Modules.PlayableThings.cards.Spell;

import Server.Modules.GameBusiness.Game.Game;
import Server.Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Server.Modules.PlayableThings.cards.Card;
import Server.Modules.PlayableThings.cards.Force;
import Server.Modules.PlayableThings.cards.Hero;
import Server.Modules.Playground;
import Server.View.View.Show;

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
        if (targets == null) {
            System.out.println("can't insert card");
            return false;
        }
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
                        if (force.getAttackPower() < 0)
                            force.setAttackPower(0);
                        if (force.getHitPoint() < 0)
                            force.setHitPoint(0);
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
            force.dispell(false);
        }
        if (!force.getId().contains(playerHaveTurn)) {
            //enemy force
            force.dispell(true);
        }
        if (buff.isKill()) {
            force.setHitPoint(0);
        }
    }

    public void executeOnAttack(Force force) {
        for (Buff buff : buffs) {
            if (buff.isDeletePositive()) {
                force.dispell(true);
            } else {
                force.addBuff(buff);
            }
        }
    }

    public void executeOnDeath(Force force, Game game, int x, int y) {
        for (Buff buff : buffs) {
            //only works for 2 on death minion
            if (this.target.isHero() && !this.target.getAlly()) {
                game.getEnemyHero().setHitPoint(game.getEnemyHero().getHitPoint() - buff.getHit());
            } else {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (Math.abs(x - i) <= 1 && Math.abs(y - j) <= 1) {
                            Card card = game.getPlayground().getGround()[i][j].getCard();
                            if (card == null) continue;
                            if (card instanceof Hero) continue;
                            Force force1 = (Force) card;
                            force1.setHitPoint(force1.getHitPoint() - 2);
                        }
                    }
                }
            }
        }
    }

    public void executePasive(Playground playground, Force force, int x, int y, String playerHaveTurn) {
        if (target.isAroundIt())
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Math.abs(x - i) > 1 || Math.abs(y - j) > 1) continue;
                    if (playground.getGround()[i][j].getCard() == null) continue;
                    if (playground.getGround()[i][j].getCard() instanceof Hero) continue;
                    if (!playground.getGround()[i][j].getCard().getId().contains(playerHaveTurn)) continue;
                    Force force1 = (Force) playground.getGround()[i][j].getCard();
                    for (Buff buff : buffs) {
                        force1.addBuff(buff);
                    }
                }
            }
        for (Buff buff : buffs) {
            force.addBuff(buff);
        }
    }

    public void executeOnRespawn(Game game, int x, int y, String userNamePlayerHAveTurn) {
        Integer[][] targets = target.getTargets(game, x, y, userNamePlayerHAveTurn);
        for (Buff buff : buffs) {
            if (targets == null)
                return;
            for (Integer[] integers : targets) {
                Force force = (Force) game.getPlayground().getGround()[integers[0]][integers[1]].getCard();
                force.addBuff(buff.getBuffCopy());
                force.setAttackPower(force.getAttackPower() + buff.getAttackPower());
                force.setHitPoint(force.getHitPoint() + buff.getHitPoint());
                if (force.getAttackPower() < 0)
                    force.setAttackPower(0);
                if (force.getHitPoint() < 0)
                    force.setHitPoint(0);
                force.setHitPoint(force.getHitPoint() - buff.getHit());
                if (force.getHitPoint() < 0)
                    force.setHitPoint(0);
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
