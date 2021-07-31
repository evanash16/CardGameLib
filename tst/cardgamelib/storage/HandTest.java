package cardgamelib.storage;

import cardgamelib.TestBase;
import cardgamelib.cards.Card;
import cardgamelib.cards.Suit;
import cardgamelib.cards.Value;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class HandTest extends TestBase {

    @Test
    public void testCreateWithPredefinedList() {
        List<Card> cards = Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES);

        Hand hand = new Hand(cards);
        assertEquals(hand.getCards(), cards);
        cards.forEach((card) -> assertTrue(hand.isPresent(card)));
    }

    @Test
    public void testSetFaceUp() {
        Hand hand = new Hand(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));

        hand.setFaceUp(true);
        hand.getCards().forEach((card) -> assertTrue(card.isFaceUp()));

        hand.setFaceUp(false);
        hand.getCards().forEach((card) -> assertFalse(card.isFaceUp()));
    }

    @Test
    public void testAddCards() {
        Hand hand = new Hand();
        hand.addCards(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));

        assertEquals(hand.getCards(), Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));
    }

    @Test
    public void testMoveCard() {
        Hand hand = new Hand();
        hand.addCards(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));

        Hand newHand = hand.moveCard(ACE_OF_CLUBS);
        assertEquals(hand.getCards(), Lists.newArrayList(KING_OF_SPADES));
        assertEquals(newHand.getCards(), Lists.newArrayList(ACE_OF_CLUBS));
    }

    @Test (expectedExceptions = {IllegalArgumentException.class})
    public void testMoveCardFailsWithMissingCards() {
        Hand hand = new Hand();
        hand.addCard(KING_OF_SPADES);

        hand.moveCard(ACE_OF_CLUBS);
    }

    @Test
    public void testMoveCards() {
        Hand hand = new Hand();
        List<Card> movedCards = Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES);

        Card additionalCard = new Card(Value.TWO, Suit.DIAMONDS);
        hand.addCards(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES, additionalCard));

        Hand newHand = hand.moveCards( movedCards);
        assertEquals(hand.getCards(), Lists.newArrayList(additionalCard));
        assertEquals(newHand.getCards(),  movedCards);
    }

    @Test (expectedExceptions = {IllegalArgumentException.class})
    public void testMoveCardsFailsWithMissingCards() {
        Hand hand = new Hand();
        hand.addCard(ACE_OF_CLUBS);

        hand.moveCards(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));
    }

    @Test
    public void testRemoveCards() {
        Hand hand = new Hand();
        hand.addCards(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));

        assertEquals(hand.removeCards(), Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));
        assertEquals(hand.getCards(), Lists.newArrayList());
    }

    @Test
    public void testIsPresent() {
        Hand hand = new Hand();
        hand.addCards(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));

        assertTrue(hand.isPresent(ACE_OF_CLUBS));
        assertTrue(hand.isPresent(KING_OF_SPADES));
    }

    @Test
    public void testToString() {
        Hand hand = new Hand(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));
        String expected = String.format("%s %s", ACE_OF_CLUBS, KING_OF_SPADES);

        assertEquals(hand.toString(), expected);
    }
}
