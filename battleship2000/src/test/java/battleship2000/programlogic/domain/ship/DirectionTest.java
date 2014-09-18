
package battleship2000.programlogic.domain.ship;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectionTest {
    private final Direction NORTH = Direction.NORTH;
    private final Direction NORTH_NORTHEAST = Direction.NORTH_NORTHEAST;
    private final Direction NORTHEAST = Direction.NORTHEAST;
    private final Direction EAST_NORTHEAST = Direction.EAST_NORTHEAST;
    private final Direction EAST = Direction.EAST;
    private final Direction EAST_SOUTHEAST = Direction.EAST_SOUTHEAST;
    private final Direction SOUTHEAST = Direction.SOUTHEAST;
    private final Direction SOUTH_SOUTHEAST = Direction.SOUTH_SOUTHEAST;
    private final Direction SOUTH = Direction.SOUTH;
    private final Direction SOUTH_SOUTHWEST = Direction.SOUTH_SOUTHWEST;
    private final Direction SOUTHWEST = Direction.SOUTHWEST;
    private final Direction WEST_SOUTHWEST = Direction.WEST_SOUTHWEST;
    private final Direction WEST = Direction.WEST;
    private final Direction WEST_NORTHWEST = Direction.WEST_NORTHWEST;
    private final Direction NORTHWEST = Direction.NORTHWEST;
    private final Direction NORTH_NORTHWEST = Direction.NORTH_NORTHWEST;
    
    public DirectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getAngleMethodReturnsRightDoubleForNorth() {
        assertEquals(0.0, NORTH.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForNorthNorthEast() {
        assertEquals(22.5, NORTH_NORTHEAST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForNorthEast() {
        assertEquals(45.0, NORTHEAST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForEastNorthEast() {
        assertEquals(67.5, EAST_NORTHEAST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForEast() {
        assertEquals(90.0, EAST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForEastSouthEast() {
        assertEquals(112.5, EAST_SOUTHEAST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForSouthEast() {
        assertEquals(135, SOUTHEAST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForSouthSouthEast() {
        assertEquals(157.5, SOUTH_SOUTHEAST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForSouth() {
        assertEquals(180.0, SOUTH.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForSOUTH_SOUTHWEST() {
        assertEquals(202.5, SOUTH_SOUTHWEST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForSOUTHWEST() {
        assertEquals(225, SOUTHWEST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForWEST_SOUTHWEST() {
        assertEquals(247.5, WEST_SOUTHWEST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForWEST() {
        assertEquals(270.0, WEST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForWEST_NORTHWEST() {
        assertEquals(292.5, WEST_NORTHWEST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForNORTHWEST() {
        assertEquals(315.0, NORTHWEST.getAngle(), 0.1);
    }
    @Test
    public void getAngleMethodReturnsRightDoubleForNORTH_NORTHWEST() {
        assertEquals(337.5, NORTH_NORTHWEST.getAngle(), 0.1);
    }
    
    @Test
    public void getDxReturnsRightIntegerForNorth() {
        assertEquals(0, NORTH.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForNorthNorthEast() {
        assertEquals(1, NORTH_NORTHEAST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForNorthEast() {
        assertEquals(1, NORTHEAST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForEastNorthEast() {
        assertEquals(2, EAST_NORTHEAST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForEast() {
        assertEquals(1, EAST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForEastSouthEast() {
        assertEquals(2, EAST_SOUTHEAST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForSouthEast() {
        assertEquals(1, SOUTHEAST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForSouthSouthEast() {
        assertEquals(1, SOUTH_SOUTHEAST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForSouth() {
        assertEquals(0, SOUTH.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForSOUTH_SOUTHWEST() {
        assertEquals(-1, SOUTH_SOUTHWEST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForSOUTHWEST() {
        assertEquals(-1, SOUTHWEST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForWEST_SOUTHWEST() {
        assertEquals(-2, WEST_SOUTHWEST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForWEST() {
        assertEquals(-1, WEST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForWEST_NORTHWEST() {
        assertEquals(-2, WEST_NORTHWEST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForNORTHWEST() {
        assertEquals(-1, NORTHWEST.getDx());
    }
    @Test
    public void getDxReturnsRightIntegerForNORTH_NORTHWEST() {
        assertEquals(-1, NORTH_NORTHWEST.getDx());
    }
    
    @Test
    public void getDyReturnsRightIntegerForNorth() {
        assertEquals(-1, NORTH.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForNorthNorthEast() {
        assertEquals(-2, NORTH_NORTHEAST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForNorthEast() {
        assertEquals(-1, NORTHEAST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForEastNorthEast() {
        assertEquals(-1, EAST_NORTHEAST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForEast() {
        assertEquals(0, EAST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForEastSouthEast() {
        assertEquals(1, EAST_SOUTHEAST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForSouthEast() {
        assertEquals(1, SOUTHEAST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForSouthSouthEast() {
        assertEquals(2, SOUTH_SOUTHEAST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForSouth() {
        assertEquals(1, SOUTH.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForSOUTH_SOUTHWEST() {
        assertEquals(2, SOUTH_SOUTHWEST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForSOUTHWEST() {
        assertEquals(1, SOUTHWEST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForWEST_SOUTHWEST() {
        assertEquals(1, WEST_SOUTHWEST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForWEST() {
        assertEquals(0, WEST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForWEST_NORTHWEST() {
        assertEquals(-1, WEST_NORTHWEST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForNORTHWEST() {
        assertEquals(-1, NORTHWEST.getDy());
    }
    @Test
    public void getDyReturnsRightIntegerForNORTH_NORTHWEST() {
        assertEquals(-2, NORTH_NORTHWEST.getDy());
    }
    
    @Test
    public void getOppositeDirectionReturnsRightDirectionForNorth() {
        assertEquals(SOUTH, NORTH.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForNorthNorthEast() {
        assertEquals(SOUTH_SOUTHWEST, NORTH_NORTHEAST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForNorthEast() {
        assertEquals(SOUTHWEST, NORTHEAST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForEastNorthEast() {
        assertEquals(WEST_SOUTHWEST, EAST_NORTHEAST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForEast() {
        assertEquals(WEST, EAST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForEastSouthEast() {
        assertEquals(WEST_NORTHWEST, EAST_SOUTHEAST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForSouthEast() {
        assertEquals(NORTHWEST, SOUTHEAST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForSouthSouthEast() {
        assertEquals(NORTH_NORTHWEST, SOUTH_SOUTHEAST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForSouth() {
        assertEquals(NORTH, SOUTH.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForSOUTH_SOUTHWEST() {
        assertEquals(NORTH_NORTHEAST, SOUTH_SOUTHWEST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForSOUTHWEST() {
        assertEquals(NORTHEAST, SOUTHWEST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForWEST_SOUTHWEST() {
        assertEquals(EAST_NORTHEAST, WEST_SOUTHWEST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForWEST() {
        assertEquals(EAST, WEST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForWEST_NORTHWEST() {
        assertEquals(EAST_SOUTHEAST, WEST_NORTHWEST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForNORTHWEST() {
        assertEquals(SOUTHEAST, NORTHWEST.getOppositeDirection());
    }
    @Test
    public void getOppositeDirectionReturnsRightDirectionForNORTH_NORTHWEST() {
        assertEquals(SOUTH_SOUTHEAST, NORTH_NORTHWEST.getOppositeDirection());
    }
}