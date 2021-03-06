package battleship2000.programlogic.rules;

/**
 * An enum type used as a key in storing and retrieving rules from an instance of
 * the {@link battleship2000.programlogic.rules.Rules} class.
 *
 * @author Petri Kallio
 */
public enum Rule {
    TABLE_SIZE,
    TABLE_WIDTH,
    TABLE_HEIGHT,
    SHIPS,
    SHIPS_ARE_MOVABLE,
    SHIPS_ARE_SPECIALIZED,
    A_SQUARE_CAN_BE_HIT_MULTIPLE_TIMES,
    PLAYERS;
}
