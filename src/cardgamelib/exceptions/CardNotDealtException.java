package cardgamelib.exceptions;

import cardgamelib.cards.Card;

/**
 * Thrown whenever a {@link Card} being discarded or collected hasn't been dealt.
 */
public class CardNotDealtException extends RuntimeException {

    public CardNotDealtException(String message) {
        super(message);
    }
}
