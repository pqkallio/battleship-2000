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
    boolean move(int dx, int dy);
    boolean move();
}
