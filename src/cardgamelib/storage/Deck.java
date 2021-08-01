package cardgamelib.storage;

import cardgamelib.cards.Card;
import cardgamelib.cards.Suit;
import cardgamelib.cards.Value;
import cardgamelib.exceptions.CardNotDealtException;
import cardgamelib.exceptions.CardsInPlayException;
import cardgamelib.exceptions.EmptyDeckException;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {

    private List<Card> cards;
    private Map<Card, Integer> numDealtByCard;
    private List<Card> discarded;

    public Deck() {
        this(1);
    }

    public Deck(int n) {
        cards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (Suit suit: Suit.values()) {
                for (Value value : Value.values()) {
                    cards.add(new Card(value, suit));
                }
            }
        }

        numDealtByCard = new HashMap<>();
        discarded = new ArrayList<>();
    }

    public void shuffle() throws CardsInPlayException {
        if (!numDealtByCard.keySet().isEmpty()) {
            throw new CardsInPlayException(String.format("The following cards are still in play: %s", numDealtByCard.keySet()));
        }

        this.cards.addAll(discarded);
        discarded = new ArrayList<>();

        Collections.shuffle(this.cards);
    }

    public void shuffle(int n) throws CardsInPlayException {
        for (int i = 0; i < n; i++) {
            shuffle();
        }
    }

    public Card pick() throws EmptyDeckException {
        if (cards.isEmpty()) {
            throw new EmptyDeckException("The deck is empty.");
        }

        Card card = cards.remove(0);
        numDealtByCard.put(card, numDealtByCard.getOrDefault(card, 0) + 1);
        return card;
    }

    public List<Card> pick(int n) throws EmptyDeckException {
        if (cards.size() < n) {
            throw new EmptyDeckException(String.format("The deck has fewer than %d cards.", n));
        }

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cards.add(pick());
        }

        return cards;
    }

    public void discard(Card card) throws CardNotDealtException {
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

    public void discard(List<Card> cards) throws CardNotDealtException {
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

    public int getRemainingCardCount() {
        return cards.size();
    }
}
