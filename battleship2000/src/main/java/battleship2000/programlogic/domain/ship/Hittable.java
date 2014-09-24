/**
 * An interface implemented by all the Ship classes in the game either directly
 * or by inheritance.
 * 
 * This interface makes the implementing classes to return the boolean values
 * used to define whether an instance of the implementing class is intact, hit
 * or destroyed.
 */
package battleship2000.programlogic.domain.ship;

public interface Hittable {
    boolean isIntact();
    boolean isDestroyed();
}
