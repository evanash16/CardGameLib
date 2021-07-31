package cardgamelib.cards;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ValueTest {

    @Test
    public void testToString() {
        assertEquals("A", Value.ACE.toString());
        assertEquals("J", Value.JACK.toString());
        assertEquals("Q", Value.QUEEN.toString());
        assertEquals("K", Value.KING.toString());
        assertEquals("2", Value.TWO.toString());
    }

    @Test
    public void testGetNumericalValue() {
        assertEquals(1, Value.ACE.getNumericalValue());
        assertEquals(10, Value.JACK.getNumericalValue());
        assertEquals(10, Value.QUEEN.getNumericalValue());
        assertEquals(10, Value.KING.getNumericalValue());
        assertEquals(2, Value.TWO.getNumericalValue());
    }
}
