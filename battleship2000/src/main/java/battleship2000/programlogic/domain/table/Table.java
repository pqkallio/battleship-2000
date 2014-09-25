package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.ship.Ship;
import java.util.Map;

/**
 * This interface makes any implementing class to implement several methods
 * used to store and retrieve data of a specified game table.
 *
 * @author Petri Kallio
 */
public interface Table {
    public Map<Integer, Map<Integer, Square>> getTable();
    public void placeShipOnTable(Ship alus);
    public void placeShipOnTable(ShipPart[] alus);
    public int getHeight();
    public int getWidth();
    public void removePartsFromTable(ShipPart[] aluksenosat);
    public void removePartsFromField(Ship alus);
    public boolean allSquaresAreHit();
    public Square getNextSquare(Square square, Direction direction);
}
