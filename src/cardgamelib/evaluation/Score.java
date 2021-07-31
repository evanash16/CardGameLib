package cardgamelib.evaluation;

public interface Score {

    int getNumericalValue();

    boolean isGreaterThan(Score other);

    boolean isLessThan(Score other);

    boolean isEqualTo(Score other);
}
