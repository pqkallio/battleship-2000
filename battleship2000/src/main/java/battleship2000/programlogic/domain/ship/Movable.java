package battleship2000.programlogic.domain.ship;

/**
 * An interface implemented by all the Ship classes in the game either directly
 * or by inheritance.
 * <p>
 * This interface makes the implementing classes to return the boolean values
 * used to define whether moving an instance of the implementing class to a
 * certain direction is a success or not.
 *
 * @author Petri Kallio
 */
public interface Movable {
    
    /**
     * Moves the object implementing this interface from its original position 
     * to the direction given as parameters.
     * 
     * @param dx    the amount of steps to move the Movable on x-axis
     * @param dy    the amount of steps to move the Movable on y-axis
     * @return      true if Movable is moved, false otherwise
     */
    boolean move(int dx, int dy);
    
    /**
     * Moves the object implementing this interface the way defined in the overridden
     * method.
     * 
     * @return      true if moved, false otherwise 
     */
    boolean move();
}
