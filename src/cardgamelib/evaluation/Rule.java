package cardgamelib.evaluation;

import cardgamelib.storage.Hand;

/**
 * An abstract class for evaluating whether a {@link Hand} passes a local condition, or a global condition relative to another {@link Hand}.
 * <p>
 * If a rule is only evaluated locally (no external context required), the "global" rule method should return {@code true}.
 * If a rule is only evaluated globally (external context required), the "local" rule method should return {@code true}.
 */
public abstract class Rule {

    /**
     * Returns {@code true} if {@code hand} passes the rule. Otherwise, returns {@code false}.
     *
     * @param hand the {@link Hand} to check
     * @return {@code boolean} - whether {@code hand} passes the rule
     */
    public static boolean passes(final Hand hand) {
        return true;
    }

    /**
     * Returns {@code true} if {@code hand} and {@code other} passes the rule. Otherwise, returns {@code false}.
     *
     * @param hand  the {@link Hand} to check
     * @param other the {@link Hand} to check against
     * @return {@code boolean} - whether {@code hand} and {@code other} passes the rule
     */
    public static boolean passes(final Hand hand, final Hand other) {
        return true;
    }
}
