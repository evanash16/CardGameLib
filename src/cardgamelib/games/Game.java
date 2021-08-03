package cardgamelib.games;

import cardgamelib.exceptions.NoHandsException;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class Game<T extends Dealer, U extends Player> {

    private T dealer;
    private List<U> players;

    public Game(final T dealer, final List<U> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public abstract void play();

    public abstract void score();

    public void reset() {
        for (Player player : players) {
            try {
                dealer.collect(player.removeHands());
            } catch (NoHandsException ignored) {}
        }
        dealer.shuffle();
    }
}
