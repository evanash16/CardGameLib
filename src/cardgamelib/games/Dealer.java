package cardgamelib.games;

import cardgamelib.exceptions.CardNotDealtException;
import cardgamelib.exceptions.CardsInPlayException;
import cardgamelib.exceptions.EmptyDeckException;
import cardgamelib.storage.Deck;
import cardgamelib.storage.Hand;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for representing a dealer in a game.
 */
public abstract class Dealer {

    @Getter
    private Deck deck;

    public Dealer() {
        this(1);
    }

    public Dealer(final int n) {
        this.deck = new Deck(n);
    }

    /**
     * Wraps {@code deck.shuffle()}.
     *
     * @throws CardsInPlayException there are still cards in play
     */
    public void shuffle() {
        this.deck.shuffle();
    }

    /**
     * Wraps {@code deck.shuffle(int n)}.
     *
     * @param n the number of cards to deal
     * @throws CardsInPlayException there are still cards in play
     */
    public void shuffle(final int n) {
        this.deck.shuffle(n);
    }

    /**
     * Deals {@code n} cards into a new hand, returning the new hand.
     *
     * @param n the number of cards to deal
     * @return {@link Hand} - a new hand containing {@code n} cards picked from the deck
     * @throws EmptyDeckException there aren't enough cards left in the {@link Deck}
     */
    public Hand deal(final int n) {
        Hand newHand = new Hand();
        newHand.addCards(deck.pick(n));

        return newHand;
    }

    /**
     * Deals {@code n} cards into {@code hand}.
     *
     * @param n the number of cards to deal
     * @throws EmptyDeckException there aren't enough cards left in the {@link Deck}
     */
    public void deal(final int n, final Hand hand) {
        hand.addCards(deck.pick(n));
    }

    /**
     * Removes the cards from {@code hand} and discards them.
     *
     * @param hand the {@link Hand} to collect cards from to discard
     */
    public void collect(final Hand hand) {
        this.deck.discard(hand.removeCards());
    }

    /**
     * Removes the cards from every {@link Hand} in {@code hands} and discards them.
     *
     * @param hands a list of hands to collect cards from to discard
     */
    public void collect(final List<Hand> hands) {
        List<Hand> failedHands = new ArrayList<>();
        for (Hand hand : hands) {
            try {
                this.deck.discard(hand.removeCards());
            } catch (CardNotDealtException e) {
                failedHands.add(hand);
            }
        }

        if (!failedHands.isEmpty()) {
            throw new CardNotDealtException(String.format("The following hands had undealt cars: %s", failedHands));
        }
    }
}
