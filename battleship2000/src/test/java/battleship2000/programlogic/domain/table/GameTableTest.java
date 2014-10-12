
package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.control.ShipPlacement;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTableTest {
    private GameTable gt;
    private ShipPart[] shipPartsOnTable = new ShipPart[2];
    private ShipPart[] shipPartsNotOnTable = new ShipPart[2];
    private ShipPart onTable1;
    private ShipPart onTable2;
    private ShipPart notOnTable1;
    private ShipPart notOnTable2;
    
    public GameTableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.gt = new GameTable(3, 3);
        
        this.onTable1 = new ShipPart();
        this.onTable1.setPosition(0, 0);
        this.onTable2 = new ShipPart();
        this.onTable2.setPosition(gt.getWidth() - 1, gt.getHeight() - 1);
        gt.getTableAsMap().get(onTable1.getY()).get(onTable1.getX()).setShipPart(onTable1);
        gt.getTableAsMap().get(onTable2.getY()).get(onTable2.getX()).setShipPart(onTable2);
        this.shipPartsOnTable[0] = this.onTable1;
        this.shipPartsOnTable[1] = this.onTable2;
        
        this.notOnTable1 = new ShipPart();
        this.notOnTable1.setPosition(-1, -1);
        this.notOnTable2 = new ShipPart();
        this.notOnTable2.setPosition(gt.getWidth(), gt.getHeight());
        this.shipPartsNotOnTable[0] = this.notOnTable1;
        this.shipPartsNotOnTable[1] = this.notOnTable2;
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void ifAllSquaresArentHitAllSquaresAreHitMethodReturnsFalse() {
        for (Map<Integer, Square> key : this.gt.getTableAsMap().values()) {
            for (Square square : key.values()) {
                if (square.getX() != gt.getWidth() - 1 && square.getY() != gt.getHeight() - 1) {
                    square.bomb();
                }
            }
        }
        
        assertFalse(gt.allSquaresAreHit());
    }
    
    @Test
    public void ifAllSquaresAreHitAllSquaresAreHitMethodReturnsTrue() {
        for (Map<Integer, Square> key : this.gt.getTableAsMap().values()) {
            for (Square square : key.values()) {
                square.bomb();
            }
        }
        
        assertTrue(gt.allSquaresAreHit());
    }
    
    @Test
    public void ifPartsAreRemovedFromTheTableTheSquaresTheyWereInReturnNullFromGetShipPartMethod() {
        gt.removePartsFromTable(shipPartsOnTable);
        boolean allIsOk = true;
        
        for (ShipPart shipPart : shipPartsOnTable) {
            if (gt.getTableAsMap().get(shipPart.getY()).get(shipPart.getX()).getSetShipPart() != null) {
                allIsOk = false;
            }
        }
        
        assertTrue(allIsOk);
    }
    
    @Test
    public void ifPartsAreRemovedFromTheTableTheSquaresTheyWereInReturnNullFromGetShipPartMethodPart2() {
        Ship testShip = new Ship(2);
        
        testShip.setTable(gt);
        
        ShipPlacement placement = new ShipPlacement(testShip);
        placement.setShipsPosition(0, 0);
        placement.placeShipOnTable();
        
        gt.removePartsFromTable(testShip);
        boolean allIsOk = true;
        
        for (ShipPart shipPart : testShip.getParts()) {
            if (gt.getTableAsMap().get(shipPart.getY()).get(shipPart.getX()).getSetShipPart() != null) {
                allIsOk = false;
            }
        }
        
        assertTrue(allIsOk);
    }
    
    @Test
    public void getNextSquareBasedOnDirectionReturnsTheNextSquareIfOnTablePart1() {
        Square square = gt.getTableAsMap().get(0).get(0);
        
        Square nextSquare = gt.getNextSquareBasedOnDirection(square, Direction.EAST);
        
        boolean allIsOk = false;
        
        if (nextSquare.getX() == square.getX() + 1 && nextSquare.getY() == square.getY()) {
            allIsOk = true;
        }
        
        assertTrue(allIsOk);
    }
    
    @Test
    public void getNextSquareBasedOnDirectionReturnsTheNextSquareIfOnTablePart2() {
        Square square = gt.getTableAsMap().get(gt.getHeight() - 2).get(gt.getWidth()-1);
        
        Square nextSquare = gt.getNextSquareBasedOnDirection(square, Direction.SOUTH);
        
        boolean allIsOk = false;
        
        if (nextSquare.getX() == square.getX() && nextSquare.getY() == square.getY() + 1) {
            allIsOk = true;
        }
        
        assertTrue(allIsOk);
    }
    
    @Test
    public void getNextSquareBasedOnDirectionReturnsNullIfNotOnTablePart1() {
        Square square = gt.getTableAsMap().get(0).get(0);
        
        Square nextSquare = gt.getNextSquareBasedOnDirection(square, Direction.WEST);
        
        assertNull(nextSquare);
    }
    
    @Test
    public void getNextSquareBasedOnDirectionReturnsNullIfNotOnTablePart2() {
        Square square = gt.getTableAsMap().get(0).get(0);
        
        Square nextSquare = gt.getNextSquareBasedOnDirection(square, Direction.NORTH);
        
        assertNull(nextSquare);
    }
    
    @Test
    public void getNextSquareBasedOnDirectionReturnsNullIfNotOnTablePart3() {
        Square square = gt.getTableAsMap().get(gt.getHeight() - 1).get(gt.getWidth() - 1);
        
        Square nextSquare = gt.getNextSquareBasedOnDirection(square, Direction.EAST);
        
        assertNull(nextSquare);
    }
    
    @Test
    public void getNextSquareBasedOnDirectionReturnsNullIfNotOnTablePart4() {
        Square square = gt.getTableAsMap().get(gt.getHeight() - 1).get(gt.getWidth() - 1);
        
        Square nextSquare = gt.getNextSquareBasedOnDirection(square, Direction.SOUTH);
        
        assertNull(nextSquare);
    }
    
    @Test
    public void getNeighborSquaresMethodReturnsAllEightSquaresCirclingASquare() {
        Square square = gt.getTableAsMap().get(1).get(1);
        List<Square> squares = gt.getNeighborSquares(square);
        
        assertEquals(8, squares.size());
    }
    
    @Test
    public void getNeighborSquaresMethodReturnsThreeSquaresIfSquareIsInACorner() {
        Square square = gt.getTableAsMap().get(0).get(0);
        List<Square> squares = gt.getNeighborSquares(square);
        
        assertEquals(3, squares.size());
    }
    
    @Test
    public void getNeighborSquaresMethodReturnsFiveSquaresIfSquareIsOnSide() {
        Square square = gt.getTableAsMap().get(1).get(0);
        List<Square> squares = gt.getNeighborSquares(square);
        
        assertEquals(5, squares.size());
    }
    
    @Test
    public void shipPartsNotOnTableDoesNothingButSatisfiesTheNeedOfTestingOfEveryLineOfCode() {
        gt.removePartsFromTable(shipPartsNotOnTable);
    }
    
    @Test
    public void checkCoordinatesAreOnTheTableMethodReturnsTrueIfCoordinatesOnTheTable() {
        assertTrue(gt.checkCoordinatesAreOnTheTable(0, 0));
    }
    
    @Test
    public void checkCoordinatesAreOnTheTableMethodReturnsTrueIfCoordinatesOnTheTablePart2() {
        assertTrue(gt.checkCoordinatesAreOnTheTable(gt.getWidth() - 1, gt.getHeight() - 1));
    }
    
    @Test
    public void checkCoordinatesAreOnTheTableMethodReturnsFalseIfCoordinatesNotOnTheTable() {
        assertFalse(gt.checkCoordinatesAreOnTheTable(-1, -1));
    }
    
    @Test
    public void checkCoordinatesAreOnTheTableMethodReturnsFalseIfCoordinatesNotOnTheTablePart2() {
        assertFalse(gt.checkCoordinatesAreOnTheTable(gt.getWidth(), gt.getHeight()));
    }
    
    @Test
    public void returnSquaresBasedOnDirectionReturnsAnEmptyListIfNoSquaresCanBeFoundInAppointedDirection() {
        Square square = gt.getTableAsMap().get(0).get(0);
        Direction direction = Direction.WEST;
        
        assertEquals(0, gt.getSquaresBasedOnDirection(square, direction, 10).size());
    }
    
    @Test
    public void returnSquaresBasedOnDirectionReturnsAListWithSizeOfOneIfOneSquareCanBeFoundInAppointedDirection() {
        Square square = gt.getTableAsMap().get(0).get(1);
        Direction direction = Direction.WEST;
        
        assertEquals(1, gt.getSquaresBasedOnDirection(square, direction, 10).size());
    }
    
    @Test
    public void returnSquaresBasedOnDirectionReturnsAListWithSizeOfOneIfAtLeastOneSquareCanBeFoundInAppointedDirection() {
        Square square = gt.getTableAsMap().get(0).get(2);
        Direction direction = Direction.WEST;
        
        assertEquals(1, gt.getSquaresBasedOnDirection(square, direction, 1).size());
    }
}