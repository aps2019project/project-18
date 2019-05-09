import Modules.Account;
import Modules.GameBusiness.Game.Game;
import Modules.GameBusiness.Game.ModeCaptureFlag6Turn;
import Modules.GameBusiness.Game.ModeKillEnemyHero;
import Modules.GameBusiness.Player.Human;
import Modules.PlayableThings.BuffAndSpecialPowers.Buff.Buff;
import Modules.PlayableThings.cards.Card;
import Modules.Playground;
import Modules.Shop;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(JUnit4ClassRunner.class)
public class JUnitTests {
    @Test
    public void checkMoveInClassPlayground() {
        Playground playground = new Playground();
        Card card = Shop.getInstance().getDeckLevelOne().getHero();
        playground.getGround()[0][0].setCard(card);
        playground.move(0, 0, 1, 2);
        if (playground.getGround()[0][0].getCard() != null) {
            Assert.fail();
        }
        if (playground.getGround()[1][2].getCard() == null) {
            Assert.fail();
        }
    }

    @Test
    public void checkIsCardOnTheGroundInClassPlayground() {
        Playground playground = new Playground();
        Card card = Shop.getInstance().getDeckLevelOne().getHero();
        playground.getGround()[0][3].setCard(card);
        if (playground.isCardOnGround(card.getId())[0] != 0 || playground.isCardOnGround(card.getId())[1] != 3) {
            Assert.fail();
        }
        if (playground.isCardOnGround("dadadd") != null) {
            Assert.fail();
        }
    }

    @Test
    public void checkEndGameModeKillEnemyHero() {
        Game.initializeItems();
        Account.createAccount("AI1", "Aa!12345");
        Account.findAccount("AI1").getCollection().setMainDeck(Shop.getInstance().getDeckLevelOne());
        Account.createAccount("AI2", "Aa!12345");
        Account.findAccount("AI2").getCollection().setMainDeck(Shop.getInstance().getDeckLevelTwo());
        Human human = new Human(Account.findAccount("AI1"));
        Human opponent = new Human(Account.findAccount("AI2"));
        ModeKillEnemyHero modeKillEnemyHero = new ModeKillEnemyHero(human, opponent);
        human.setGame(modeKillEnemyHero);
        opponent.setGame(modeKillEnemyHero);
        if (modeKillEnemyHero.isEnd()) {
            Assert.fail();
        }
        modeKillEnemyHero.getPlayers()[0].getHeroCard().setHitPoint(0);
        modeKillEnemyHero.checkEnd();
        if (!modeKillEnemyHero.isEnd()) {
            Assert.fail();
        }
        if (modeKillEnemyHero.getWinnerPlayer() == modeKillEnemyHero.getPlayers()[0]) {
            Assert.fail();
        }
    }

    @Test
    public void checkEndModeCaptureFlagFor6Turns() {
        Game.initializeItems();
        Account.createAccount("AI1", "Aa!12345");
        Account.findAccount("AI1").getCollection().setMainDeck(Shop.getInstance().getDeckLevelOne());
        Account.createAccount("AI2", "Aa!12345");
        Account.findAccount("AI2").getCollection().setMainDeck(Shop.getInstance().getDeckLevelTwo());
        Human opponent = new Human(Account.findAccount("AI1"));
        Human human = new Human(Account.findAccount("AI2"));
        ModeCaptureFlag6Turn modeCaptureFlag6Turn = new ModeCaptureFlag6Turn(human, opponent);
        opponent.setGame(modeCaptureFlag6Turn);
        human.setGame(modeCaptureFlag6Turn);
        human.setNumberOfTurnPlayerHaveFlag(7);
        if (modeCaptureFlag6Turn.isEnd()) {
            Assert.fail();
        }
        modeCaptureFlag6Turn.checkEnd();
        if (!modeCaptureFlag6Turn.isEnd()) {
            Assert.fail();
        }
    }

    @Test
    public void getAttackAbleCards() {
        Game.initializeItems();
        Account.createAccount("AI1", "Aa!12345");
        Account.findAccount("AI1").getCollection().setMainDeck(Shop.getInstance().getDeckLevelOne());
        Account.createAccount("AI2", "Aa!12345");
        Account.findAccount("AI2").getCollection().setMainDeck(Shop.getInstance().getDeckLevelTwo());
        Human human = new Human(Account.findAccount("AI1"));
        Human opponent = new Human(Account.findAccount("AI2"));
        ModeKillEnemyHero modeKillEnemyHero = new ModeKillEnemyHero(human, opponent);
        human.setGame(modeKillEnemyHero);
        opponent.setGame(modeKillEnemyHero);
        if (modeKillEnemyHero.getAttackableMinions(human.getHeroCard()) != null) {
            Assert.fail();
        }
        modeKillEnemyHero.getPlayground().move(0, 2, 8, 1);
        if (modeKillEnemyHero.getAttackableMinions(human.getHeroCard()) == null) {
            Assert.fail();
        }
    }

    @Test
    public void moveAblePlaces() {
        Game.initializeItems();
        Account.createAccount("AI1", "Aa!12345");
        Account.findAccount("AI1").getCollection().setMainDeck(Shop.getInstance().getDeckLevelOne());
        Account.createAccount("AI2", "Aa!12345");
        Account.findAccount("AI2").getCollection().setMainDeck(Shop.getInstance().getDeckLevelTwo());
        Human human = new Human(Account.findAccount("AI1"));
        Human opponent = new Human(Account.findAccount("AI2"));
        ModeKillEnemyHero modeKillEnemyHero = new ModeKillEnemyHero(human, opponent);
        human.setGame(modeKillEnemyHero);
        opponent.setGame(modeKillEnemyHero);
        modeKillEnemyHero.getPlayground().move(8, 2, 0, 1);
        int[][] places = modeKillEnemyHero.getMovablePlaces(human.getHeroCard());
        for (int i = 0; i < places.length; i++) {
            if (places[i][0] == 0 && places[i][1] == 0) {
                Assert.fail();
            }
        }
    }

    @Test
    public void canAttack() {
        Game.initializeItems();
        Account.createAccount("AI1", "Aa!12345");
        Account.findAccount("AI1").getCollection().setMainDeck(Shop.getInstance().getDeckLevelOne());
        Account.createAccount("AI2", "Aa!12345");
        Account.findAccount("AI2").getCollection().setMainDeck(Shop.getInstance().getDeckLevelTwo());
        Human human = new Human(Account.findAccount("AI1"));
        Human opponent = new Human(Account.findAccount("AI2"));
        ModeKillEnemyHero modeKillEnemyHero = new ModeKillEnemyHero(human, opponent);
        human.setGame(modeKillEnemyHero);
        opponent.setGame(modeKillEnemyHero);
        if (modeKillEnemyHero.canAttack(human.getHeroCard().getId(), opponent.getHeroCard().getId())) {
            Assert.fail();
        }
        modeKillEnemyHero.getPlayground().move(8, 2, 0, 1);
        if (!modeKillEnemyHero.canAttack(human.getHeroCard().getId(), opponent.getHeroCard().getId())) {
            Assert.fail();
        }
    }

    @Test
    public void holyCheck() {
        Game.initializeItems();
        Account.createAccount("AI1", "Aa!12345");
        Account.findAccount("AI1").getCollection().setMainDeck(Shop.getInstance().getDeckLevelOne());
        Account.createAccount("AI2", "Aa!12345");
        Account.findAccount("AI2").getCollection().setMainDeck(Shop.getInstance().getDeckLevelTwo());
        Human human = new Human(Account.findAccount("AI1"));
        Human opponent = new Human(Account.findAccount("AI2"));
        ModeKillEnemyHero modeKillEnemyHero = new ModeKillEnemyHero(human, opponent);
        modeKillEnemyHero.getPlayground().move(8 ,2 , 5 , 2);
        modeKillEnemyHero.getPlayground().move(0 ,2 , 4 , 2);
        human.setGame(modeKillEnemyHero);
        opponent.setGame(modeKillEnemyHero);
        Buff buff = new Buff();
        buff.setHoly(true);
        buff.setHolyCount(2);
        opponent.getHeroCard().addBuff(buff);
        int hitPointOpponent = opponent.getHeroCard().getHitPoint();
        int hitPointHuman = human.getHeroCard().getHitPoint();
        human.getHeroCard().setCan();
        opponent.getHeroCard().setCan();
        modeKillEnemyHero.attack(human.getHeroCard() , opponent.getHeroCard().getId());
        if (hitPointOpponent - opponent.getHeroCard().getHitPoint() != human.getHeroCard().getAttackPower() - 2 && hitPointHuman - human.getHeroCard().getHitPoint() != opponent.getHeroCard().getAttackPower())
            Assert.fail();
    }
}
