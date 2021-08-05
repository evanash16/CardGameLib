package cardgamelib.exceptions;

import cardgamelib.games.Player;
import cardgamelib.storage.Hand;

/**
 * Thrown when attempting to retrieve {@link Hand} from a {@link Player} which has no hands.
 */
public class NoHandsException extends RuntimeException {

    public NoHandsException(final String message) {
        super(message);
    }
}
