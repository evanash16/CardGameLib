package cardgamelib.storage;

import cardgamelib.cards.Card;
import cardgamelib.evaluation.Score;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand {

    @Getter
    private List<Card> cards;
    private Set<Card> present;

    public Hand() {
        this(new ArrayList<>());
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
        this.present = new HashSet<>();
        this.present.addAll(this.cards);
    }

    public void setFaceUp(final boolean faceUp) {
        cards.forEach((card) -> card.setFaceUp(faceUp));
    }

    public void addCard(Card card) {
        this.cards.add(card);
        this.present.add(card);
    }

    public void addCards(List<Card> cards) {
        cards.forEach(this::addCard);
    }

    public Hand moveCard(Card card) {
        return moveCards(Collections.singletonList(card));
    }

    public Hand moveCards(List<Card> cards) {
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

    private Card removeCard(Card card) {
        cards.remove(card);
        present.remove(card);

        return card;
    }

    public List<Card> removeCards() {
        List<Card> cardsCopy = new ArrayList<>(this.cards);
        cardsCopy.forEach(this::removeCard);

        return cardsCopy;
    }

    public boolean isPresent(Card card) {
        return present.contains(card);
    }

    public Score score(Function<Hand, Score> scoringFunction) {
        return scoringFunction.apply(this);
    }

    public Score score(Hand other, BiFunction<Hand, Hand, Score> scoringFunction) {
        return scoringFunction.apply(this, other);
    }

    public String toString() {
        return StringUtils.join(cards.stream()
                .map(Card::toString)
                .collect(Collectors.toList()), " ");
    }
}
