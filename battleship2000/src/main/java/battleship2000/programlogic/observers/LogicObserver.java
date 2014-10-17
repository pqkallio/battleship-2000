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
    
    /**
     * The method called by the {@link battleship2000.programlogic.BattleShipGame} object
     * to communicate changes in the game's status to the implementing class.
     * 
     * @see battleship2000.programlogic.BattleShipGame#notifyObservers(battleship2000.programlogic.StateChange, java.lang.Object[]) 
     * @param code      a StateChange enum type used to address the right observer
     * @param object    an array of objects to be parsed by the observer if need be
     */
    void update(StateChange code, Object... object);
    
    /**
     * Returns the updated status of the LogicObserver
     * <p>
     * Note! This method is used mainly in testing the implementing class to find 
     * out whether it's responding to the update method calls. 
     * 
     * @return  false if the LogicObserver hasn't been updated at least once, true otherwise
     */
    boolean isUpdated();
}
