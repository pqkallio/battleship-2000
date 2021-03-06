
package battleship2000.programlogic;

/**
 * A set of predefined enum types to help the notifying of {@link battleship2000.programlogic.observers.LogicObserver}s.
 * 
 * @author Petri Kallio
 */
public enum StateChange {
    UPDATE_TABLE,
    UPDATE_POINTS,
    COMPUTERS_TURN,
    PLACE_SHIPS,
    SET_SHIP,
    TAKE_SHIP,
    START_GAME,
    SHIP_HIT,
    SHOT_MISSED,
    END_GAME;
}
