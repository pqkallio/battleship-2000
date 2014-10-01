package battleship2000.programlogic.observers;

import battleship2000.programlogic.StateChange;

/**
 * An observer interface used to pass data from the program logic to the user interface.
 * <p>
 * Note! This interface is implemented by the user interface classes yet it is situated in the program logic package.
 *
 * @author Petri Kallio
 */
public interface LogicObserver {
    void update(StateChange code, Object... object);
    boolean isUpdated();
}
