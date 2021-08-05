package cardgamelib.games;

import cardgamelib.exceptions.NoHandsException;
import cardgamelib.storage.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for representing a player in a game.
 */
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

    /**
     * Adds a new empty {@link Hand}, returning the new hand.
     *
     * @return {@link Hand} - a new empty hand
     */
    public Hand addHand() {
        Hand newHand = new Hand();
        this.hands.add(newHand);
        return newHand;
    }

    /**
     * Adds a predefined hand to the end of {@code hands}.
     *
     * @param hand - a hand to add to the {@link Player}
     */
    public void addHand(final Hand hand) {
        this.hands.add(hand);
    }

    /**
     * Returns the current hand.
     *
     * @return {@link Hand} - the current hand
     * @throws NoHandsException there are no hands
     */
    public Hand getHand() {
        if (this.hands.isEmpty()) {
            throw new NoHandsException("There are no hands to retrieve.");
        }

        return this.hands.get(this.currentHand);
    }

    /**
     * Returns all hands.
     *
     * @return {@code List<Hand>} - a list of all hands
     * @throws NoHandsException there are no hands
     */
    public List<Hand> getHands() {
        if (this.hands.isEmpty()) {
            throw new NoHandsException("There are no hands to retrieve.");
        }

        return this.hands;
    }

    /**
     * Removes all hands and returns the removed hands.
     *
     * @return {@code List<Hand>} - a list of all hands
     */
    public List<Hand> removeHands() {
        if (this.hands.isEmpty()) {
            throw new NoHandsException("There are no hands to remove.");
        }

        List<Hand> removedHands = new ArrayList<>(this.hands);
        reset();

        return removedHands;
    }

    /**
     * Increments {@code currentHand} to point at the next hand, if one exists.
     * If there are no additional hands, this function returns {@code null}, and {@code currentHand} is reset.
     *
     * @return {@link Hand} - the next hand
     * @throws NoHandsException there are no hands
     */
    public Hand nextHand() {
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
