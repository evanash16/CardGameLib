package cardgamelib;

import cardgamelib.cards.Card;
import cardgamelib.cards.Suit;
import cardgamelib.cards.Value;
import cardgamelib.games.Dealer;
import cardgamelib.games.Player;

public class TestBase {

    public class TestDealer extends Dealer {
    }

    public class TestPlayer extends Player {
    }

    protected static final Card ACE_OF_CLUBS = new Card(Value.ACE, Suit.CLUBS);
    protected static final Card KING_OF_SPADES = new Card(Value.KING, Suit.SPADES);
    protected static final Card TWO_OF_DIAMONDS = new Card(Value.TWO, Suit.DIAMONDS);
}
