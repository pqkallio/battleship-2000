package battleship2000.programlogic.domain.ship;

/**
 * Every class implementing this interface must provide for methods to turn the
 * object both clockwise and counter clockwise.
 *
 * @author Petri Kallio
 */
public interface Turnable {
    void turnClockwise();
    void turnCounterClockwise();
}
