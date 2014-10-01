
package battleship2000.programlogic;

/**
 * A set of predefined enum types to help the notifying of {@link battleship2000.programlogic.observers.LogicObserver}s.
 * 
 * @author Petri Kallio
 */
public enum StateChange {
    UPDATE_TABLE,
    UPDATE_POINTS,
    PLACE_SHIPS,
    START_GAME,
    END_GAME;
}