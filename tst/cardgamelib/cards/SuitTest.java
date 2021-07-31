package cardgamelib.cards;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SuitTest {

    @Test
    public void testToString() {
        assertEquals("♣", Suit.CLUBS.toString());
        assertEquals("♦", Suit.DIAMONDS.toString());
        assertEquals("♥", Suit.HEARTS.toString());
        assertEquals("♠", Suit.SPADES.toString());
    }
}
