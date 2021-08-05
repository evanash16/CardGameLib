package cardgamelib.storage;

import cardgamelib.cards.Card;
import cardgamelib.cards.Suit;
import cardgamelib.cards.Value;
import cardgamelib.exceptions.CardNotDealtException;
import cardgamelib.exceptions.CardsInPlayException;
import cardgamelib.exceptions.EmptyDeckException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class which stores cards for dealers. A deck can be comprised of more than one "deck".
 */
public class Deck {

    private List<Card> cards;
    private Map<Card, Integer> numDealtByCard;
    private List<Card> discarded;

    public Deck() {
        this(1);
    }

    public Deck(final int n) {
        cards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (Suit suit : Suit.values()) {
                for (Value value : Value.values()) {
                    cards.add(new Card(value, suit));
                }
            }
        }

        numDealtByCard = new HashMap<>();
        discarded = new ArrayList<>();
    }

    /**
     * Recombines the discard pile, and shuffles the deck one time.
     *
     * @throws CardsInPlayException there are cards still in play.
     */
    public void shuffle() {
        if (!numDealtByCard.keySet().isEmpty()) {
            throw new CardsInPlayException(String.format("The following cards are still in play: %s", numDealtByCard.keySet()));
        }

        this.cards.addAll(discarded);
        discarded = new ArrayList<>();

        Collections.shuffle(this.cards);
    }

    /**
     * Recombines the discard pile, and shuffles the deck {@code n} times.
     * If any cards are still in play, a {@link CardsInPlayException} is thrown.
     *
     * @param n the number of times to shuffle
     */
    public void shuffle(final int n) {
        for (int i = 0; i < n; i++) {
            shuffle();
        }
    }

    /**
     * Picks one card and returns it.
     *
     * @return {@link Card} - one card from the deck
     * @throws EmptyDeckException the deck is empty
     */
    public Card pick() {
        if (cards.isEmpty()) {
            throw new EmptyDeckException("The deck is empty.");
        }

        Card card = cards.remove(0);
        numDealtByCard.put(card, numDealtByCard.getOrDefault(card, 0) + 1);
        return card;
    }

    /**
     * Picks {@code n} cards and returns them.
     *
     * @param n the number of cards to pick
     * @return {@code List<Card>} - {@code n} cards from the deck
     * @throws EmptyDeckException there aren't enough cards remaining
     */
    public List<Card> pick(final int n) {
        if (cards.size() < n) {
            throw new EmptyDeckException(String.format("The deck has fewer than %d cards.", n));
        }

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cards.add(pick());
        }

        return cards;
    }

    /**
     * Discards a card. If all cards have been discarded, {@code shuffle()} will succeed.
     *
     * @param card the card to discard
     * @throws CardNotDealtException {@code card} hasn't been dealt
     */
    public void discard(final Card card) {
        if (!numDealtByCard.containsKey(card)) {
            throw new CardNotDealtException(String.format("The card '%s' has not been dealt", card));
        }

        if (numDealtByCard.get(card) > 1) {
            numDealtByCard.put(card, numDealtByCard.get(card) - 1);
        } else {
            numDealtByCard.remove(card);
        }
        discarded.add(card);
    }

    /**
     * Discards a list of cards. If all cards have been discarded, {@code shuffle()} will succeed.
     *
     * @param cards the cards to discard
     * @throws CardNotDealtException {@code cards} contains cards that haven't been dealt
     */
    public void discard(final List<Card> cards) {
        List<Card> undealtCards = cards.stream()
                .filter((card) -> !numDealtByCard.containsKey(card))
                .collect(Collectors.toList());

        if (!undealtCards.isEmpty()) {
            throw new CardNotDealtException(String.format("The following cards have not been dealt: %s", undealtCards));
        }

        for (Card card : cards) {
            discard(card);
        }
    }

    /**
     * Returns the number of cards remaining.
     * @return {@code int} - the number of cards remaining in the {@link Deck}
     */
    public int getRemainingCardCount() {
        return cards.size();
    }
}
