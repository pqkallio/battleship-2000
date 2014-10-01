package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.ship.Ship;
import java.util.List;
import java.util.Map;

/**
 * This interface makes any implementing class to implement several methods
 * used to store and retrieve data of a specified game table.
 *
 * @author Petri Kallio
 */
public interface Table {
    public Map<Integer, Map<Integer, Square>> getTableAsMap();
    public void placeShipOnTable(Ship alus);
    public void placeShipOnTable(ShipPart[] alus);
    public int getHeight();
    public int getWidth();
    public void removePartsFromTable(ShipPart[] aluksenosat);
    public void removePartsFromTable(Ship alus);
    public boolean allSquaresAreHit();
    public Square getNextSquareBasedOnDirection(Square square, Direction direction);
    public List<Square> getNeighborSquares(Square square);
    public List<Square> getSquaresBasedOnDirection(Square square, Direction direction, int amount);
}
