
package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.points.Points;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {
    private Table gameTable;
    private Square x0y0;
    private Square x0y0ver2;
    private Square x0y1;
    private Square x0y2;
    private Square x1y0;
    private Square x2y0;
    
    public SquareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.gameTable = new GameTable(10, 10);
        x0y0 = new Square(gameTable, 0, 0);
        x0y0ver2 = new Square(gameTable, 0, 0);
        x0y1 = new Square(gameTable, 0, 1);
        x0y2 = new Square(gameTable, 0, 2);
        x1y0 = new Square(gameTable, 1, 0);
        x2y0 = new Square(gameTable, 2, 0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getXReturnsTheRightValue() {
        assertEquals(1, x1y0.getX());
    }
    
    @Test
    public void getYReturnsTheRightValue() {
        assertEquals(1, x0y1.getY());
    }
    
    @Test
    public void whenASquareIsCreatedItIsNotHit() {
        assertFalse(x0y0.isHit());
    }
    
    @Test
    public void afterASquareIsBombedItIsHitReturnsTrue() {
        x0y0.bomb();
        assertTrue(x0y0.isHit());
    }
    
    @Test
    public void ifASquareIsEmptyBombingItReturnsMISSEDPoints() {
        assertEquals(Points.MISSED.getPoints(), x0y1.bomb());
    }
    
    @Test
    public void ifASquaresYIsSmallerThanTheOneItIsComparedToCompareToMethodReturnsMinusOne() {
        assertEquals(-1, x0y0.compareTo(x0y1));
    }
    
    @Test
    public void ifASquaresYIsGreaterThanTheOneItIsComparedToCompareToMethodReturnsOne() {
        assertEquals(1, x0y2.compareTo(x0y1));
    }
    
    @Test
    public void ifASquaresYIsEqualToTheOneItIsComparedToAndXIsSmallerCompareToMethodReturnsMinusOne() {
        assertEquals(-1, x0y0.compareTo(x1y0));
    }
    
    @Test
    public void ifASquaresYIsEqualToTheOneItIsComparedToAndXIsGreaterCompareToMethodReturnsOne() {
        assertEquals(1, x2y0.compareTo(x1y0));
    }
    
    @Test
    public void ifASquaresYIsEqualToTheOneItIsComparedToAndXIsEqualAsWellCompareToMethodReturnsMinusOne() {
        assertEquals(0, x0y0.compareTo(x0y0ver2));
    }
    
    @Test
    public void ifASquareContainsAShipPartAndItIsHitBombingTheSquareReturnsHITPoints() {
        Ship ship = new Ship(2);
        Square testSquare = new Square(gameTable, 1, 1);
        
        testSquare.setShipPart(ship.getParts()[0]);
        
        assertEquals(Points.HIT.getPoints(), testSquare.bomb());
    }
    
    @Test
    public void getTableMethodReturnsTheTableItHasBeenGivenAsAParameter() {
        assertEquals(gameTable, x0y0.getTable());
    }
    
    @Test
    public void getPartReturnsTheShipPartThatHasBeenSetToTheSquare() {
        Ship ship = new Ship(2);
        Square testSquare = new Square(gameTable, 1, 1);
        
        testSquare.setShipPart(ship.getParts()[0]);
        
        assertEquals(ship.getParts()[0], testSquare.getSetShipPart());
    }
    
    @Test
    public void ifASquareContainsTheLastIntactShipPartOfAShipAndItIsHitBombingTheSquareReturnsDESTROYEDPoints() {
        Ship ship = new Ship(2);
        Square testSquare = new Square(gameTable, 1, 1);
        
        ship.getParts()[1].hit();
        testSquare.setShipPart(ship.getParts()[0]);
        
        assertEquals(Points.DESTROYED.getPoints(), testSquare.bomb());
    }
    
    @Test
    public void getFloatingPieceReturnsTheShipPartThatHasBeenSetToTheSquare() {
        Ship ship = new Ship(2);
        Square testSquare = new Square(gameTable, 1, 1);
        
        testSquare.floatAPart(ship.getParts()[0]);
        
        assertEquals(ship.getParts()[0], testSquare.getFloatingPiece());
    }
    
    @Test
    public void getFloatingPieceReturnsNullIfNoneIsFloated() {
        Square testSquare = new Square(gameTable, 1, 1);
        
        assertNull(testSquare.getFloatingPiece());
    }
    
    @Test
    public void getFloatingPieceReturnsNullIfTheFloatingPieceIsRemoved() {
        Ship ship = new Ship(2);
        Square testSquare = new Square(gameTable, 1, 1);
        
        testSquare.floatAPart(ship.getParts()[0]);
        testSquare.removeFloatingPart();
        
        assertNull(testSquare.getFloatingPiece());
    }
}