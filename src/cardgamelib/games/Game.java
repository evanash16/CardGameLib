package cardgamelib.games;

import cardgamelib.exceptions.CardNotDealtException;
import cardgamelib.exceptions.CardsInPlayException;
import cardgamelib.exceptions.NoHandsException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class Game {

    protected Dealer dealer;
    protected List<Player> players;

    abstract void play();
    abstract void score();

    public void reset() throws CardNotDealtException, CardsInPlayException {
        for (Player player : players) {
            try {
                dealer.collect(player.removeHands());
            } catch (NoHandsException ignored) {}
        }
        dealer.shuffle();
    }
}
