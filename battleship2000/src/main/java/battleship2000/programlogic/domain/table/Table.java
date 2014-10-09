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
    
    /**
     * Returns the Table's squares as Map and can be used for example to pinpoint 
     * the square in a specified position on the table.
     * 
     * @return  a map of all the table's squares
     */
    public Map<Integer, Map<Integer, Square>> getTableAsMap();
    
    /**
     * Place a ship on the table 
     * @param ship 
     */
    public void placeShipOnTable(Ship ship);
    public int getHeight();
    public int getWidth();
    public void removePartsFromTable(ShipPart[] shipParts);
    public void removePartsFromTable(Ship ship);
    public boolean allSquaresAreHit();
    public Square getNextSquareBasedOnDirection(Square square, Direction direction);
    public List<Square> getNeighborSquares(Square square);
    public List<Square> getSquaresBasedOnDirection(Square square, Direction direction, int amount);
    public boolean checkCoordinatesAreOnTheTable(int x, int y);
}
