package cardgamelib.games;

import cardgamelib.TestBase;
import cardgamelib.exceptions.CardNotDealtException;
import cardgamelib.storage.Hand;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class DealerTest extends TestBase {

    @Test
    public void testDeal() throws Exception {
        Dealer dealer = new TestDealer();
        Hand hand = dealer.deal(5);

        assertEquals(hand.getCards().size(), 5);
    }

    @Test
    public void testDealIntoHand() throws Exception {
        Dealer dealer = new TestDealer();
        Hand existingHand = new Hand(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));
        dealer.deal(5, existingHand);

        assertEquals(existingHand.getCards().size(), 7);
    }

    @Test
    public void testCollect() throws Exception {
        Dealer dealer = new TestDealer();
        Hand hand = dealer.deal(5);

        dealer.collect(hand);
        assertEquals(hand.getCards().size(), 0);

        // should not fail
        dealer.shuffle();
    }

    @Test
    public void testCollectWithMultipleHands() throws Exception {
        Dealer dealer = new TestDealer();
        Hand firstHand = dealer.deal(5);
        Hand secondHand = dealer.deal(5);

        dealer.collect(Lists.newArrayList(firstHand, secondHand));
        assertEquals(firstHand.getCards().size(), 0);
        assertEquals(secondHand.getCards().size(), 0);

        // should not fail
        dealer.shuffle();
    }

    @Test(expectedExceptions = {CardNotDealtException.class})
    public void testCollectWithMultipleHandsFailsWithUndealtCards() throws Exception {
        Dealer dealer = new TestDealer();
        Hand firstHand = new Hand(Lists.newArrayList(KING_OF_SPADES, TWO_OF_DIAMONDS));

        // can't have been dealt without shuffling
        Hand secondHand = dealer.deal(2);

        dealer.collect(Lists.newArrayList(firstHand, secondHand));
        assertNotEquals(firstHand.getCards().size(), 0);
        assertEquals(secondHand.getCards().size(), 0);
    }


    @Test
    public void testDeckLifecycle() throws Exception {
        Dealer dealer = new TestDealer();
        dealer.shuffle(5);

        List<Hand> hands = Lists.newArrayList(dealer.deal(5), dealer.deal(4));
        dealer.collect(hands);

        hands = Lists.newArrayList(dealer.deal(3), dealer.deal(2));
        dealer.collect(hands);

        dealer.shuffle();
    }
}
