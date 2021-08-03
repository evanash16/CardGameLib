package cardgamelib.storage;

import cardgamelib.TestBase;
import cardgamelib.cards.Card;
import cardgamelib.exceptions.CardNotDealtException;
import cardgamelib.exceptions.CardsInPlayException;
import cardgamelib.exceptions.EmptyDeckException;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class DeckTest extends TestBase {

    @Test
    public void testShuffleMultipleTimes() {
        Deck deck = new Deck();
        deck.shuffle(5);
        deck.pick();
    }

    @Test(expectedExceptions = {CardsInPlayException.class})
    public void testShuffleWithCardsInPlay() {
        Deck deck = new Deck();
        deck.pick();
        deck.shuffle();
    }

    @Test(expectedExceptions = {CardsInPlayException.class})
    public void testShuffleMultipleTimesWithCardsInPlay() {
        Deck deck = new Deck();
        deck.pick();
        deck.shuffle(5);
    }

    @Test
    public void testPick() {
        Deck deck = new Deck();
        assertEquals(deck.pick(), ACE_OF_CLUBS);
    }

    @Test
    public void testPickMultipleCards() {
        Deck deck = new Deck();
        List<Card> cards = deck.pick(5);

        assertEquals(cards.size(), 5);
        assertEquals(cards.get(0), ACE_OF_CLUBS);
    }

    @Test(expectedExceptions = {EmptyDeckException.class})
    public void testPickFailsWithEmptyDeck() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.pick();
        }
        deck.pick();
    }

    @Test(expectedExceptions = {EmptyDeckException.class})
    public void testPickMultipleCardsFailsWithEmptyDeck() {
        Deck deck = new Deck();
        deck.pick(53);

        // make sure that subsequent pick succeeds
        assertEquals(deck.pick(52).size(), 52);
    }

    @Test
    public void testDiscard() {
        Deck deck = new Deck();
        Card picked = deck.pick();

        deck.discard(picked);

        // shouldn't fail
        deck.shuffle();
    }

    @Test (expectedExceptions = {CardNotDealtException.class})
    public void testDiscardFailsWithNonDealtCard() {
        Deck deck = new Deck();
        deck.discard(ACE_OF_CLUBS);
    }

    @Test
    public void testDiscardMultipleCards() {
        Deck deck = new Deck();
        List<Card> picked = deck.pick(5);

        deck.discard(picked);

        // shouldn't fail
        deck.shuffle();
    }

    @Test (expectedExceptions = {CardNotDealtException.class})
    public void testDiscardMultipleCardsFailsWithNonDealtCard() {
        Deck deck = new Deck();
        deck.discard(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));
    }

    @Test
    public void testCardLifecycle() {
        Deck deck = new Deck(5);
        assertEquals(5 * 52, deck.getRemainingCardCount());

        List<Card> picked = deck.pick((4 * 52) + 32);
        assertEquals(20, deck.getRemainingCardCount());
        deck.discard(picked);
        deck.shuffle();

        assertEquals(5 * 52, deck.getRemainingCardCount());
    }
}
