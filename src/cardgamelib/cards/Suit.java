package cardgamelib.cards;

public enum Suit {
    CLUBS("♣"),
    DIAMONDS("♦"),
    HEARTS("♥"),
    SPADES("♠");

    private final String representation;

    Suit(String representation) {
        this.representation = representation;
    }

    public String toString() {
        return this.representation;
    }
}
