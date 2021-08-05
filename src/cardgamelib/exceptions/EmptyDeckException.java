package cardgamelib.exceptions;

import cardgamelib.storage.Deck;

/**
 * Thrown whenever a {@link Deck} doesn't have the requested number of cards to pick.
 */
public class EmptyDeckException extends RuntimeException {

    public EmptyDeckException(final String message) {
        super(message);
    }
}
