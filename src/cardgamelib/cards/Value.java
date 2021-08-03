package cardgamelib.cards;

/**
 * An enum which can be converted to a string, or numerical card value.
 */
public enum Value {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String representation;
    private final int value;

    Value(final String representation, final int value) {
        this.representation = representation;
        this.value = value;
    }

    /**
     * Returns the numerical value of the {@link Value}.
     *
     * <p>
     * <pre>
     *     // Returns 1
     *     Value.ACE.getNumericalValue();
     * </pre>
     * </p>
     *
     * @return {@code int} - the numerical value of the {@link Value}
     */
    public int getNumericalValue() {
        return this.value;
    }

    /**
     * Returns the string representation of the {@link Value}.
     *
     * <p>
     * <pre>
     *     // Returns "A"
     *     Value.ACE.toString();
     * </pre>
     * </p>
     *
     * @return {@code String} - the string representation of the {@link Value}
     */
    public String toString() {
        return this.representation;
    }
}
