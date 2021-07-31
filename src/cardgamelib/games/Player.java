package cardgamelib.games;

import cardgamelib.exceptions.NoHandsException;
import cardgamelib.storage.Hand;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private List<Hand> hands;
    private int currentHand;

    public Player() {
        reset();
    }

    private void reset() {
        this.hands = new ArrayList<>();
        this.currentHand = 0;
    }

    public Hand addHand() {
        Hand newHand = new Hand();
        this.hands.add(newHand);
        return newHand;
    }

    public void addHand(Hand hand) {
        this.hands.add(hand);
    }

    public Hand getHand() throws NoHandsException {
        if (this.hands.isEmpty()) {
            throw new NoHandsException("There are no hands to retrieve.");
        }

        return this.hands.get(this.currentHand);
    }

    public List<Hand> getHands() throws NoHandsException {
        if (this.hands.isEmpty()) {
            throw new NoHandsException("There are no hands to retrieve.");
        }

        return this.hands;
    }

    public List<Hand> removeHands() throws NoHandsException {
        if (this.hands.isEmpty()) {
            throw new NoHandsException("There are no hands to remove.");
        }

        List<Hand> removedHands = new ArrayList<>(this.hands);
        reset();

        return removedHands;
    }

    public Hand nextHand() throws NoHandsException {
        if (this.hands.isEmpty()) {
            throw new NoHandsException("There are no hands to retrieve.");
        }

        if (currentHand + 1 < this.hands.size()) {
            return this.hands.get(++currentHand);
        }
        currentHand = 0;
        return null;
    }
}
