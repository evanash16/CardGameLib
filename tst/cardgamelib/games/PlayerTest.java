package cardgamelib.games;

import cardgamelib.TestBase;
import cardgamelib.exceptions.NoHandsException;
import cardgamelib.storage.Hand;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import static org.testng.Assert.*;

public class PlayerTest extends TestBase {

    private class TestPlayer extends Player {
    }

    @Test
    public void testAddHand() {
        Player player = new TestPlayer();
        Hand newHand = player.addHand();

        assertTrue(newHand.getCards().isEmpty());
    }

    @Test
    public void testAddHandWithPredefinedHand() throws Exception {
        Player player = new TestPlayer();
        Hand hand = new Hand(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));

        player.addHand(hand);

        assertEquals(player.getHand(), hand);
    }

    @Test(expectedExceptions = {NoHandsException.class})
    public void testGetHandFailsWithNoHand() throws Exception {
        Player player = new TestPlayer();
        player.getHand();
    }


    @Test
    public void testGetHands() throws Exception {
        Player player = new TestPlayer();
        player.addHand();
        player.addHand();

        assertEquals(player.getHands().size(), 2);
    }

    @Test(expectedExceptions = {NoHandsException.class})
    public void testGetHandsFailsWithNoHands() throws Exception {
        Player player = new TestPlayer();
        player.getHands();
    }

    @Test
    public void testRemoveHands() throws Exception {
        Player player = new TestPlayer();
        player.addHand();
        player.addHand();

        assertEquals(player.removeHands().size(), 2);
    }

    @Test(expectedExceptions = {NoHandsException.class})
    public void testRemoveHandsFailsWithNoHands() throws Exception {
        Player player = new TestPlayer();
        player.removeHands();
    }

    @Test
    public void testNextHand() throws Exception {
        Player player = new TestPlayer();

        player.addHand();
        assertNull(player.nextHand());

        player.addHand();
        assertNotNull(player.nextHand());
    }

    @Test(expectedExceptions = {NoHandsException.class})
    public void testNextHandFailsWithNoHands() throws Exception {
        Player player = new TestPlayer();
        player.nextHand();
    }

    @Test
    public void testHandLifecycle() throws Exception {
        Player player = new TestPlayer();
        Hand firstHand = player.addHand();
        firstHand.addCards(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));

        Hand secondHand = new Hand(Lists.newArrayList(TWO_OF_DIAMONDS));
        player.addHand(secondHand);

        assertEquals(player.getHand(), firstHand);
        assertEquals(player.nextHand(), secondHand);
        assertNull(player.nextHand());

        assertEquals(player.getHand(), firstHand);
        assertEquals(player.removeHands().size(), 2);
    }
}
