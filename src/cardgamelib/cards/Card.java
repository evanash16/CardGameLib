package cardgamelib.cards;

import lombok.Getter;
import lombok.Setter;

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

    public int getNumericalValue() {
        return this.value.getNumericalValue();
    }

    public int getCardIndex() {
        return (this.suit.ordinal() * Value.values().length) + this.value.ordinal();
    }

    @Override
    public String toString() {
        if (!this.faceUp) {
            return "??";
        }
        return String.format("%s%s", this.value.toString(), this.suit.toString());
    }

    @Override
    public int hashCode() {
        return this.getCardIndex();
    }
}
