package cardgamelib.cards;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a playing card. There are 52 possible cards (13 values, 4 suits).
 */
public class Card {

    @Setter
    @Getter
    private boolean faceUp;

    @Getter
    private Value value;

    @Getter
    private Suit suit;

    public Card(final Value value, final Suit suit) {
        this(value, suit, true);
    }

    public Card(final Value value, final Suit suit, final boolean faceUp) {
        this.faceUp = faceUp;
        this.value = value;
        this.suit = suit;
    }

    /**
     * Returns the numerical value of the {@link Card}.
     *
     * <p>
     * <pre>
     *     // returns 1
     *     ACE_OF_CLUBS.getNumericalValue();
     * </pre>
     * </p>
     *
     * @return {@code int} - the numerical value of the {@link Card}
     */
    public int getNumericalValue() {
        return this.value.getNumericalValue();
    }

    /**
     * Returns the index of the {@link Card}.
     *
     * <p>
     * <pre>
     *    // returns 0
     *    ACE_OF_CLUBS.getCardIndex();
     * </pre>
     * </p>
     *
     * @return {@code int} - the index of the {@link Card}
     */
    public int getCardIndex() {
        return (this.suit.ordinal() * Value.values().length) + this.value.ordinal();
    }

    /**
     * Returns the string representation of the {@link Card}.
     * If the card is facedown, this function returns {@code "??"}.
     *
     * <p>
     * <pre>
     *    // returns "A♠"
     *    ACE_OF_SPADES.toString();
     * </pre>
     * </p>
     *
     * @return {@code String} - the string representation of the {@link Card}
     */
    @Override
    public String toString() {
        if (!this.faceUp) {
            return "??";
        }
        return String.format("%s%s", this.value.toString(), this.suit.toString());
    }

    /**
     * Returns a unique hash for a given {@link Card} (ex. card index).
     *
     * @return {@code int} - a unique hash for a given {@link Card}
     */
    @Override
    public int hashCode() {
        return this.getCardIndex();
    }

    /**
     * Returns {@code true} if other is a {@link Card}, and shares the same attributes ({@code value} and {@code suit}).
     *
     * @param other another {@code Object} to check equality to
     * @return {@code Object} - a boolean evaluation of "{@code other} is equivalent to the {@link Card}"
     */
    @Override
    public boolean equals(final Object other) {
        return other instanceof Card &&
                ((Card) other).getValue() == this.getValue() &&
                ((Card) other).getSuit() == this.getSuit();
    }
}
