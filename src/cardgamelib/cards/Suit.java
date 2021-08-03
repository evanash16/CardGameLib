package cardgamelib.cards;

/**
 * An enum which can be converted to a string.
 */
public enum Suit {
    CLUBS("♣"),
    DIAMONDS("♦"),
    HEARTS("♥"),
    SPADES("♠");

    private final String representation;

    Suit(final String representation) {
        this.representation = representation;
    }

    /**
     * Returns the string representation of the {@link Suit}.
     *
     * <p>
     * <pre>
     *     // returns "♣"
     *     Suit.CLUBS.toString();
     * </pre>
     * </p>
     *
     * @return {@code String} - the string representation of the {@link Suit}
     */
    public String toString() {
        return this.representation;
    }
}
