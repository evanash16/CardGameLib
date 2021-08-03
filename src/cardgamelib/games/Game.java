package cardgamelib.games;

import cardgamelib.exceptions.NoHandsException;
import lombok.Getter;

import java.util.List;

/**
 * An abstract class for representing a game.
 * @param <T> the type of {@link Dealer} the {@link Game} involves
 * @param <U> the type of {@link Player} the {@link Game} involves
 */
@Getter
public abstract class Game<T extends Dealer, U extends Player> {

    private T dealer;
    private List<U> players;

    public Game(final T dealer, final List<U> players) {
        this.dealer = dealer;
        this.players = players;
    }

    /**
     * Deals the appropriate number of cards to all players, and makes moves for the players or requests user input.
     */
    public abstract void play();

    /**
     * Scores the hands of each player in {@code players}.
     */
    public abstract void score();

    /**
     * Resets the game. By default, it collects the hands from all of the players and shuffles the deck once.
     */
    public void reset() {
        for (Player player : players) {
            try {
                dealer.collect(player.removeHands());
            } catch (NoHandsException ignored) {}
        }
        dealer.shuffle();
    }
}
