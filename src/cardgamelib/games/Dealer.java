package cardgamelib.games;

import cardgamelib.exceptions.CardNotDealtException;
import cardgamelib.exceptions.CardsInPlayException;
import cardgamelib.exceptions.EmptyDeckException;
import cardgamelib.storage.Deck;
import cardgamelib.storage.Hand;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Dealer {

    @Getter
    private Deck deck;

    public Dealer() {
        this(1);
    }

    public Dealer(int n) {
        this.deck = new Deck(n);
    }

    public void shuffle() {
        this.deck.shuffle();
    }

    public void shuffle(int n) {
        this.deck.shuffle(n);
    }

    public Hand deal(int n) {
        Hand newHand = new Hand();
        newHand.addCards(deck.pick(n));

        return newHand;
    }

    public void deal(int n, Hand hand) {
        hand.addCards(deck.pick(n));
    }

    public void collect(Hand hand)  {
        this.deck.discard(hand.removeCards());
    }

    public void collect(List<Hand> hands) {
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
