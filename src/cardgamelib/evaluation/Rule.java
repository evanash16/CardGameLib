package cardgamelib.evaluation;

import cardgamelib.storage.Hand;

public abstract class Rule {

    public static boolean passes(Hand hand) {
        return true;
    }

    public static boolean passes(Hand hand, Hand other) {
        return true;
    }
}
