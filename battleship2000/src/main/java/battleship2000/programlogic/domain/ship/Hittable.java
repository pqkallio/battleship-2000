package battleship2000.programlogic.domain.ship;

/**
 * An interface implemented by all the Ship classes in the game either directly
 * or by inheritance.
 * <p>
 * This interface makes the implementing classes to return the boolean values
 * used to define whether an instance of the implementing class is intact, hit
 * or destroyed.
 *
 * @author Petri Kallio
 */
public interface Hittable {
    
    /**
     * Returns the Hittable's status of being intact or hit.
     * 
     * @return  true if not hit, false otherwisw
     */
    boolean isIntact();
    
    /**
     * Returns the Hittable's status of being destroyed.
     * 
     * @return  true if destroyed, false otherwise
     */
    boolean isDestroyed();
}
