package battleship2000.programlogic.domain.ship;

/**
 * Every class implementing this interface must provide for methods to turn the
 * object both clockwise and counter clockwise.
 *
 * @author Petri Kallio
 */
public interface Turnable {
    
    /**
     * Turns the object implementing this interface to the next possible 
     * {@link battleship2000.programlogic.domain.ship.Direction} clockwise
     * from its current direction.
     */
    void turnClockwise();
    
    /**
     * Turns the object implementing this interface to the next possible 
     * {@link battleship2000.programlogic.domain.ship.Direction} counterclockwise
     * from its current direction.
     */
    void turnCounterClockwise();
}
