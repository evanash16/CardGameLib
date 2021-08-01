package cardgamelib.games;

import cardgamelib.exceptions.CardNotDealtException;
import cardgamelib.exceptions.CardsInPlayException;
import cardgamelib.exceptions.EmptyDeckException;
import cardgamelib.storage.Deck;
import cardgamelib.storage.Hand;

import java.util.ArrayList;
import java.util.List;

public abstract class Dealer {

    private Deck deck;

    public Dealer() {
        this(1);
    }

    public Dealer(int n) {
        this.deck = new Deck(n);
    }

    public void shuffle() throws CardsInPlayException {
        this.deck.shuffle();
    }

    public void shuffle(int n) throws CardsInPlayException {
        this.deck.shuffle(n);
    }

    public Hand deal(int n) throws EmptyDeckException {
        Hand newHand = new Hand();
        newHand.addCards(deck.pick(n));

        return newHand;
    }

    public void deal(int n, Hand hand) throws EmptyDeckException {
        hand.addCards(deck.pick(n));
    }

    public void collect(Hand hand) throws CardNotDealtException {
        this.deck.discard(hand.removeCards());
    }

    public void collect(List<Hand> hands) throws CardNotDealtException {
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
