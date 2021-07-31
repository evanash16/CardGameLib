package cardgamelib.evaluation;

import cardgamelib.storage.Hand;

public abstract class Rule {

    public boolean passes(Hand hand) {
        return true;
    }

    public boolean passes(Hand hand, Hand other) {
        return true;
    }
}
