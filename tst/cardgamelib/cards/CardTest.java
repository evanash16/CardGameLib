package cardgamelib.cards;

import cardgamelib.TestBase;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CardTest extends TestBase {

    @Test
    public void testGetNumericalValue() {
        assertEquals(ACE_OF_CLUBS.getNumericalValue(), 1);
        assertEquals(KING_OF_SPADES.getNumericalValue(), 10);
    }

    @Test
    public void testGetCardIndex() {
        assertEquals(ACE_OF_CLUBS.getCardIndex(), 0);
        assertEquals(KING_OF_SPADES.getCardIndex(), 51);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(ACE_OF_CLUBS.hashCode(), new Card(Value.ACE, Suit.HEARTS).hashCode());
        assertNotEquals(ACE_OF_CLUBS.hashCode(), KING_OF_SPADES.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(ACE_OF_CLUBS.toString(), "A" + Suit.CLUBS.toString());
        assertEquals(KING_OF_SPADES.toString(), "K" + Suit.SPADES.toString());
    }

    @Test
    public void testToStringWithFacedownCard() {
        Card faceDown = new Card(Value.KING, Suit.DIAMONDS, false);
        assertEquals("??", faceDown.toString());
    }
}
