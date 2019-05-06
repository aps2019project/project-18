package Modules.PlayableThings.cards.Spell;

import Modules.GameBusiness.Game.Game;
import Modules.GameBusiness.Player.Player;
import Modules.PlayableThings.cards.Hero;
import Modules.Playground;

import java.util.Random;

public class Target {
    private boolean ground;
    private int dimension;
    private int distance;
    private boolean all;
    private Boolean ally;
    private int count = 1;
    private boolean hero;
    private boolean minion;
    private boolean aroundHero;
    private boolean hybride = true;
    private boolean ranged = true;
    private boolean melee = true;
    private boolean row;
    private boolean column;
    private boolean game = true;
    private boolean graveyard;
    private boolean aroundIt;
    private boolean it;
    private boolean hand;
    private boolean random;

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public Boolean getAlly() {
        return ally;
    }

    public boolean isGround() {
        return ground;
    }

    public int getDimension() {
        return dimension;
    }

    public int getDistance() {
        return distance;
    }

    public boolean isAll() {
        return all;
    }

    public boolean isHero() {
        return hero;
    }

    public boolean isMinion() {
        return minion;
    }

    public boolean isAroundHero() {
        return aroundHero;
    }

    public boolean isHybride() {
        return hybride;
    }

    public boolean isRanged() {
        return ranged;
    }

    public boolean isMelee() {
        return melee;
    }

    public boolean isRow() {
        return row;
    }

    public boolean isColumn() {
        return column;
    }

    public boolean isGame() {
        return game;
    }

    public boolean isGraveyard() {
        return graveyard;
    }

    public boolean isAroundIt() {
        return aroundIt;
    }

    public boolean isIt() {
        return it;
    }

    public boolean isHand() {
        return hand;
    }

    public void setGround(boolean ground) {
        this.ground = ground;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public void setAlly(Boolean ally) {
        this.ally = ally;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setHero(boolean hero) {
        this.hero = hero;
    }

    public void setMinion(boolean minion) {
        this.minion = minion;
    }

    public void setAroundHero(boolean aroundHero) {
        this.aroundHero = aroundHero;
    }

    public void setHybride(boolean hybride) {
        this.hybride = hybride;
    }

    public void setRanged(boolean ranged) {
        this.ranged = ranged;
    }

    public void setMelee(boolean melee) {
        this.melee = melee;
    }

    public void setRow(boolean row) {
        this.row = row;
    }

    public void setColumn(boolean column) {
        this.column = column;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public void setGraveyard(boolean graveyard) {
        this.graveyard = graveyard;
    }

    public void setAroundIt(boolean aroundIt) {
        this.aroundIt = aroundIt;
    }

    public void setIt(boolean it) {
        this.it = it;
    }

    public void setHand(boolean hand) {
        this.hand = hand;
    }

    public int getCount() {
        return count;
    }

    public Integer[][] getTargets(Game game, int x, int y, String userNamePlayerHaveTurn) {
        Playground playground = game.getPlayground();
        if (minion && hero && !ally) {
            return oneEnemyForce(playground, x, y, userNamePlayerHaveTurn);
        } else if (ground) {
            return ground(x, y);
        } else if (minion && hero && ally) {
            return oneOwnForce(playground, x, y, userNamePlayerHaveTurn);
        } else if (hero && ally) {
            return ownHero(playground, userNamePlayerHaveTurn);
        } else if (hero && !ally) {
            return enemyHero(playground, userNamePlayerHaveTurn);
        } else if (minion && hero && !ally && all) {
            return allEnemyForce(playground, userNamePlayerHaveTurn);
        } else if (minion && hero && ally == null) {
            return oneOwnOrEnemyForce(playground, x, y);
        } else if (minion && hero && ally && all) {
            return allOwnForce(playground, userNamePlayerHaveTurn);
        } else if (column && minion && hero && !ally) {
            return allEnemyInOneColumn(playground, x, y, userNamePlayerHaveTurn);
        } else if (minion && !ally) {
            return oneEnemyMinion(playground, x, y, userNamePlayerHaveTurn);
        } else if (minion && ally) {
            return oneOwnMinion(playground, x, y, userNamePlayerHaveTurn);
        } else if (minion && !ally && random && aroundHero) {
            return oneRandomMinionAroundOwnHero(game, playground, userNamePlayerHaveTurn);
        }
        return null;
    }

    private Integer[][] oneEnemyForce(Playground playground, int x, int y, String id) {
        if (playground.getGround()[x][y].getCard() != null) {
            if (playground.getGround()[x][y].getCard().getId().contains(id)) return null;
            Integer[][] integers = new Integer[1][2];
            integers[0][0] = x;
            integers[0][1] = y;
            return integers;
        }
        return null;
    }

    private Integer[][] ground(int x, int y) {
        Integer[][] integers = new Integer[dimension][2];
        int index = 0;
        for (int i = x; i < x + dimension && i < 9; i++) {
            for (int j = y; j < y + dimension && j < 5; j++) {
                integers[index][0] = i;
                integers[index][1] = j;
                index++;
            }
        }
        return integers;
    }


    private Integer[][] oneOwnForce(Playground playground, int x, int y, String id) {
        if (playground.getGround()[x][y].getCard() != null) {
            if (!playground.getGround()[x][y].getCard().getId().contains(id)) {
                return null;
            }
            Integer[][] integers = new Integer[1][2];
            integers[0][0] = x;
            integers[0][1] = y;
            return integers;
        }
        return null;
    }

    private Integer[][] ownHero(Playground playground, String id) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() != null && playground.getGround()[i][j].getCard() instanceof Hero) {
                    if (!playground.getGround()[i][j].getCard().getId().contains(id)) continue;
                    Integer[][] integers = new Integer[1][2];
                    integers[0][0] = i;
                    integers[0][1] = j;
                    return integers;
                }
            }
        }
        return null;
    }

    private Integer[][] enemyHero(Playground playground, String id) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard() != null && playground.getGround()[i][j].getCard() instanceof Hero) {
                    if (playground.getGround()[i][j].getCard().getId().contains(id)) continue;
                    Integer[][] integers = new Integer[1][2];
                    integers[0][0] = i;
                    integers[0][1] = j;
                    return integers;
                }
            }
        }
        return null;
    }

    private Integer[][] allEnemyForce(Playground playground, String id) {
        Integer[][] integers = new Integer[15][2];
        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (!playground.getGround()[i][j].getCard().getId().contains(id)) {
                    integers[index][0] = i;
                    integers[index][1] = j;
                    index++;
                }
            }
        }
        return integers;
    }

    private Integer[][] allOwnForce(Playground playground, String id) {
        Integer[][] integers = new Integer[15][2];
        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (playground.getGround()[i][j].getCard().getId().contains(id)) {
                    integers[index][0] = i;
                    integers[index][1] = j;
                    index++;
                }
            }
        }
        return integers;
    }

    private Integer[][] oneOwnOrEnemyForce(Playground playground, int x, int y) {
        if (playground.getGround()[x][y].getCard() != null) {
            Integer[][] integers = new Integer[1][2];
            integers[0][0] = x;
            integers[0][1] = y;
            return integers;
        }
        return null;
    }

    private Integer[][] allEnemyInOneColumn(Playground playground, int x, int y, String id) {
        if (x < 0 || x > 9) return null;
        Integer[][] integers = new Integer[5][2];
        int index = 0;
        for (int j = 0; j < 5; j++) {
            if (playground.getGround()[x][j].getCard() == null) continue;
            if (!playground.getGround()[x][j].getCard().getId().contains(id)) {
                integers[index][0] = x;
                integers[index][1] = y;
                index++;
            }
        }
        return integers;
    }

    private Integer[][] oneEnemyMinion(Playground playground, int x, int y, String id) {
        if (playground.getGround()[x][y].getCard() != null) {
            if (playground.getGround()[x][y].getCard() instanceof Hero) return null;
            if (!playground.getGround()[x][y].getCard().getId().contains(id)) {
                Integer[][] integers = new Integer[1][2];
                integers[0][0] = x;
                integers[0][1] = y;
                return integers;
            }
        }
        return null;
    }

    private Integer[][] oneOwnMinion(Playground playground, int x, int y, String id) {
        if (playground.getGround()[x][y].getCard() != null) {
            if (playground.getGround()[x][y].getCard() instanceof Hero) return null;
            if (playground.getGround()[x][y].getCard().getId().contains(id)) {
                Integer[][] integers = new Integer[1][2];
                integers[0][0] = x;
                integers[0][1] = y;
                return integers;
            }
        }
        return null;
    }

    private Integer[][] oneRandomMinionAroundOwnHero(Game game, Playground playground, String id) {
        int[] a = game.getPosition(game.getEnemyHero().getId());
        int[][] b = new int[8][2];
        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Math.abs(a[0] - i) > 1) continue;
                if (Math.abs(a[1] - j) > 1) continue;
                if (playground.getGround()[i][j].getCard() == null) continue;
                if (playground.getGround()[i][j].getCard() instanceof Hero) continue;
                if (playground.getGround()[i][j].getCard().getId().contains(id)) continue;
                b[index][0] = i;
                b[index][1] = j;
                index++;
            }
        }
        int random = new Random().nextInt(index);
        Integer[][] c = new Integer[1][2];
        c[0][0] = b[random][0];
        c[0][1] = b[random][1];
        return c;
    }
}
