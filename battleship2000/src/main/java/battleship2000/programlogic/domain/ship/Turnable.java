/**
 * Every class implementing this interface must provide for methods to turn the
 * object both clockwise and counter clockwise.
 */

package battleship2000.programlogic.domain.ship;

public interface Turnable {
    void turnClockwise();
    void turnCounterClockwise();
}
