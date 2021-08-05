package cardgamelib.exceptions;

import cardgamelib.cards.Card;

/**
 * Thrown whenever a {@link Card} being discarded or collected is still in play.
 */
public class CardsInPlayException extends RuntimeException {

    public CardsInPlayException(final String message) {
        super(message);
    }
}
