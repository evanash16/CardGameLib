package cardgamelib.storage;

import cardgamelib.cards.Card;
import cardgamelib.evaluation.Score;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class which stores cards for players.
 */
public class Hand {

    @Getter
    private List<Card> cards;
    private Set<Card> present;

    public Hand() {
        this(new ArrayList<>());
    }

    public Hand(final List<Card> cards) {
        this.cards = cards;
        this.present = new HashSet<>();
        this.present.addAll(this.cards);
    }

    /**
     * Sets the face up state of all of the cards to {@code faceUp}.
     *
     * @param faceUp the desired face up state of all the cards in the {@link Hand}
     */
    public void setFaceUp(final boolean faceUp) {
        cards.forEach((card) -> card.setFaceUp(faceUp));
    }

    /**
     * Adds a card to the hand.
     *
     * @param card the card to add to the {@link Hand}
     */
    public void addCard(final Card card) {
        this.cards.add(card);
        this.present.add(card);
    }

    /**
     * Adds multiple cards to the hand.
     *
     * @param cards the cards to add to the {@link Hand}
     */
    public void addCards(final List<Card> cards) {
        cards.forEach(this::addCard);
    }

    /**
     * Moves a card into a new hand, returning the new hand.
     *
     * @param card the card to move into a new hand
     * @return {@link Hand} - a new {@link Hand} containing {@code card}
     * @throws IllegalArgumentException {@code card} doesn't exist in the {@link Hand}
     */
    public Hand moveCard(final Card card) {
        return moveCards(Collections.singletonList(card));
    }

    /**
     * Moves cards into a new hand, returning the new hand.
     *
     * @param cards the cards to move into a new hand
     * @return {@link Hand} - a new {@link Hand} containing {@code cards}
     * @throws IllegalArgumentException at least one of the cards in {@code cards} don't exist in the {@link Hand}
     */
    public Hand moveCards(final List<Card> cards) {
        Hand newHand = new Hand();
        for (Card card : cards) {
            if (!isPresent(card)) {
                throw new IllegalArgumentException(String.format("Card '%s' does not exist in the hand.", card));
            }
        }

        cards.forEach(this::removeCard);
        newHand.addCards(cards);
        return newHand;
    }

    /**
     * Moves a card into the specified {@code hand}.
     *
     * @param card the card to move
     * @param hand the hand to move {@code card} into
     * @throws IllegalArgumentException {@code card} doesn't exist in the {@link Hand}
     */
    public void moveCard(final Card card, final Hand hand) {
        moveCards(Collections.singletonList(card), hand);
    }

    /**
     * Moves cards into the specified {@code hand}.
     *
     * @param cards the cards to move
     * @param hand  the hand to move {@code cards} into
     * @throws IllegalArgumentException at least one of the cards in {@code cards} don't exist in the {@link Hand}
     */
    public void moveCards(final List<Card> cards, final Hand hand) {
        for (Card card : cards) {
            if (!isPresent(card)) {
                throw new IllegalArgumentException(String.format("Card '%s' does not exist in the hand.", card));
            }
        }

        cards.forEach(this::removeCard);
        hand.addCards(cards);
    }

    private Card removeCard(final Card card) {
        if (!isPresent(card)) {
            throw new IllegalArgumentException(String.format("Card '%s' does not exist in the hand.", card));
        }
        cards.remove(card);
        present.remove(card);

        return card;
    }

    /**
     * Removes all cards from the hand and returns the removed cards.
     *
     * @return {@code List<Card>} - the removed cards
     * @throws IllegalArgumentException {@code card} doesn't exist in the {@link Hand}
     */
    public List<Card> removeCards() {
        List<Card> cardsCopy = new ArrayList<>(this.cards);
        cardsCopy.forEach(this::removeCard);

        return cardsCopy;
    }

    /**
     * Returns {@code true} if the card is present in the hand.
     * Otherwise, returns {@code false}.
     *
     * @param card the {@link Card} to search for
     * @return {@code boolean} - whether {@code card} is in the hand
     */
    public boolean isPresent(final Card card) {
        return present.contains(card);
    }

    /**
     * Returns the score for the {@link Hand}.
     *
     * @param scoringFunction a function which takes a {@link Hand} and returns its {@link Score}
     * @return {@link Score} - the score for the {@link Hand}
     */
    public Score score(final Function<Hand, Score> scoringFunction) {
        return scoringFunction.apply(this);
    }

    /**
     * Returns the score of the hand against another hand.
     *
     * @param other           the {@link Hand} to compare against
     * @param scoringFunction a function which takes a {@link Hand} and a {@link Hand} to compare against, and returns its {@link Score}
     * @return {@link Score} - the score for the {@link Hand} against the {@code other}
     */
    public Score score(final Hand other, final BiFunction<Hand, Hand, Score> scoringFunction) {
        return scoringFunction.apply(this, other);
    }

    /**
     * Returns the string representation of the hand.
     *
     * <p>
     * <pre>
     *     // returns "A♠ K♠"
     *     BLACKJACK.toString();
     * </pre>
     * </p>
     *
     * @return {@code String} - the String representation of the {@link Hand}
     */
    @Override
    public String toString() {
        return StringUtils.join(cards.stream()
                .map(Card::toString)
                .collect(Collectors.toList()), " ");
    }

    /**
     * Returns {@code true} if {@code other} is a {@link Hand} and contains all the same cards
     *
     * @param other another {@code Object} to check equality to
     * @return - a boolean evaluation of "{@code other} is equivalent to the {@link Hand}"
     */
    @Override
    public boolean equals(final Object other) {
        return other instanceof Hand &&
                this.cards.size() == ((Hand) other).getCards().size() &&
                this.cards.containsAll(((Hand) other).getCards()) &&
                ((Hand) other).getCards().containsAll(this.cards);
    }
}
