package cardgamelib.games;

import cardgamelib.TestBase;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

public class GameTest extends TestBase {

    private class TestGame extends Game {
        public TestGame(Dealer dealer, List<Player> players) {
            super(dealer, players);
        }

        @Override
        void play() {
            // do nothing
        }

        @Override
        void score() {
            // do nothing
        }
    }

    @Test
    public void testReset() throws Exception {
        Dealer dealer = new TestDealer();
        Player playerTwo = new TestPlayer();
        playerTwo.addHand(dealer.deal(2));

        Game game = new TestGame(dealer, Lists.newArrayList(new TestPlayer(), playerTwo, new TestPlayer()));
        game.reset();
    }
}
