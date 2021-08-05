package cardgamelib.evaluation;

/**
 * An interface for representing the value of a given hand.
 */
public interface Score {

    /**
     * Returns the numerical value of a {@link Score}.
     *
     * @return {@code int} - the numerical value of a {@link Score}
     */
    int getNumericalValue();

    /**
     * Returns {@code true} if the {@link Score} is greater than {@code other}.
     * Otherwise, returns {@code false}.
     *
     * @param other the {@link Score} to compare against
     * @return {@code boolean} - whether the {@link Score} is greater than {@code other}
     */
    boolean isGreaterThan(final Score other);

    /**
     * Returns {@code true} if the {@link Score} is less than {@code other}.
     * Otherwise, returns {@code false}.
     *
     * @param other the {@link Score} to compare against
     * @return {@code boolean} - whether the {@link Score} is less than {@code other}
     */
    boolean isLessThan(final Score other);

    /**
     * Returns {@code true} if the {@link Score} is equal to {@code other}.
     * Otherwise, returns {@code false}.
     *
     * @param other the {@link Score} to compare against
     * @return {@code boolean} - whether the {@link Score} is equal to {@code other}
     */
    boolean isEqualTo(final Score other);
}
