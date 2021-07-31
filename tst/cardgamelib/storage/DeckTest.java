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
    public void testShuffleMultipleTimes() throws Exception {
        Deck deck = new Deck();
        deck.shuffle(5);
        deck.pick();
    }

    @Test(expectedExceptions = {CardsInPlayException.class})
    public void testShuffleWithCardsInPlay() throws Exception {
        Deck deck = new Deck();
        deck.pick();
        deck.shuffle();
    }

    @Test(expectedExceptions = {CardsInPlayException.class})
    public void testShuffleMultipleTimesWithCardsInPlay() throws Exception {
        Deck deck = new Deck();
        deck.pick();
        deck.shuffle(5);
    }

    @Test
    public void testPick() throws Exception {
        Deck deck = new Deck();
        assertEquals(deck.pick(), ACE_OF_CLUBS);
    }

    @Test
    public void testPickMultipleCards() throws Exception {
        Deck deck = new Deck();
        List<Card> cards = deck.pick(5);

        assertEquals(cards.size(), 5);
        assertEquals(cards.get(0), ACE_OF_CLUBS);
    }

    @Test(expectedExceptions = {EmptyDeckException.class})
    public void testPickFailsWithEmptyDeck() throws Exception {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.pick();
        }
        deck.pick();
    }

    @Test(expectedExceptions = {EmptyDeckException.class})
    public void testPickMultipleCardsFailsWithEmptyDeck() throws Exception {
        Deck deck = new Deck();
        deck.pick(53);

        // make sure that subsequent pick succeeds
        assertEquals(deck.pick(52).size(), 52);
    }

    @Test
    public void testDiscard() throws Exception {
        Deck deck = new Deck();
        Card picked = deck.pick();

        deck.discard(picked);

        // shouldn't fail
        deck.shuffle();
    }

    @Test (expectedExceptions = {CardNotDealtException.class})
    public void testDiscardFailsWithNonDealtCard() throws Exception {
        Deck deck = new Deck();
        deck.discard(ACE_OF_CLUBS);
    }

    @Test
    public void testDiscardMultipleCards() throws Exception {
        Deck deck = new Deck();
        List<Card> picked = deck.pick(5);

        deck.discard(picked);

        // shouldn't fail
        deck.shuffle();
    }

    @Test (expectedExceptions = {CardNotDealtException.class})
    public void testDiscardMultipleCardsFailsWithNonDealtCard() throws Exception {
        Deck deck = new Deck();
        deck.discard(Lists.newArrayList(ACE_OF_CLUBS, KING_OF_SPADES));
    }

    @Test
    public void testCardLifecycle() throws Exception {
        Deck deck = new Deck();
        List<Card> picked = deck.pick(32);

        assertEquals(20, deck.getRemainingCardCount());

        deck.discard(picked);
        deck.shuffle();

        assertEquals(52, deck.getRemainingCardCount());
    }
}
