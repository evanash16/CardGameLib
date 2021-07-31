package cardgamelib.cards;

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

    public int getNumericalValue() {
        return this.value;
    }

    public String toString() {
        return this.representation;
    }
}
