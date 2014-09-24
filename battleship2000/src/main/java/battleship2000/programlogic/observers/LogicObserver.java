/**
 * An observer interface used to pass data from the program logic to the user interface.
 * Note! This interface is implemented by the user interface classes yet it is situated in the program logic package.
 */
package battleship2000.programlogic.observers;

public interface LogicObserver {
    void update(Object object);
}
