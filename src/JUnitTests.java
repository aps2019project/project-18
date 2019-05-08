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
        if (playground.getGround()[0][0] != null) {
            Assert.assertFalse(false);
        }
        if (playground.getGround()[1][2].getCard() == null) {
            Assert.assertFalse(false);
        }
    }
}
