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
     * Place a Ship on the Table 
     * 
     * @param ship  the ship to be placed
     */
    public void placeShipOnTable(Ship ship);
    
    public int getHeight();
    public int getWidth();
    public boolean allSquaresAreHit();
    
    /**
     * Removes the {@link battleship2000.programlogic.domain.ship.ShipPart}s from
     * the Squares the Table consists of.
     * 
     * @param shipParts     an array of ShipParts to be removed from the Table
     */
    public void removePartsFromTable(ShipPart[] shipParts);
    
    /**
     * Removes the {@link battleship2000.programlogic.domain.ship.Ship} from
     * the the Table.
     * 
     * @param ship      the Ship to be removed from the Table
     */
    public void removePartsFromTable(Ship ship);
    
    /**
     * Finds and returns the neighbor {@link battleship2000.programlogic.domain.table.Square}
     * of the Square given as parameter to the Direction given as parameter.
     * 
     * @param square        the Square which neighbor is to be returned
     * @param direction     the Direction from which the neighboring square is to be returned
     * @return              the Square next to the Square given as parameter to the Direction given as parameter
     */
    public Square getNextSquareBasedOnDirection(Square square, Direction direction);
    
    /**
     * Returns all the {@link battleship2000.programlogic.domain.table.Square}s 
     * neighboring the Square given as parameter as a list.
     * 
     * @param square    the square whose neighboring Square are to be returned
     * @return          a list of all the neighboring Squares
     */
    public List<Square> getNeighborSquares(Square square);
    
    /**
     * Returns a list of {@link battleship2000.programlogic.domain.table.Square}s
     * counting from the Square given as parameter to the Direction given as parameter.
     * <p>
     * The amount of Squares on the list that is returns may differ from the amount
     * given as parameter, as the Table's width or height might run out before the
     * amount is met.
     * 
     * @param square        the Square from which to start counting
     * @param direction     the Direction to which to start counting
     * @param amount        the amount of Squares to add to the list at maximum
     * @return 
     */
    public List<Square> getSquaresBasedOnDirection(Square square, Direction direction, int amount);
    
    /**
     * Checks if the Table contains a {@link battleship2000.programlogic.domain.table.Square}
     * whose coordinates are the same as given as parameters.
     * 
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     * @return      true if coordinates are on the Table, false otherwise
     */
    public boolean checkCoordinatesAreOnTheTable(int x, int y);
}
