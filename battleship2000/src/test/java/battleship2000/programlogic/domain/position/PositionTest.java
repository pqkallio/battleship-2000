
package battleship2000.programlogic.domain.position;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {
    private Position x0y0;
    private Position x0y1;
    private Position x1y0;
    private Position x1y0_v2;
    
    public PositionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.x0y0 = new Position(0, 0);
        this.x0y1 = new Position(0, 1);
        this.x1y0 = new Position(1, 0);
        this.x1y0_v2 = new Position(1, 0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getXMethodReturnsThePositionsXCoordinate() {
        assertEquals(0, x0y1.getX());
    }
    
    @Test
    public void getYMethodReturnsThePositionsYCoordinate() {
        assertEquals(1, x0y1.getY());
    }
    
    @Test
    public void whenAPositionIsComparedToAnotherWithAGreaterYValueCompareToReturnsMinusOne() {
        assertEquals(-1, x0y0.compareTo(x0y1));
    }
    
    @Test
    public void whenAPositionIsComparedToAnotherWithASmallerYValueCompareToReturnsOne() {
        assertEquals(1, x0y1.compareTo(x0y0));
    }
    
    @Test
    public void whenAPositionIsComparedToAnotherWithTheSameYValueButGreaterXValueCompareToReturnsMinusOne() {
        assertEquals(-1, x0y0.compareTo(x1y0));
    }
    
    @Test
    public void whenAPositionIsComparedToAnotherWithTheSameYValueButSmallerXValueCompareToReturnsOne() {
        assertEquals(1, x1y0.compareTo(x0y0));
    }
    
    @Test
    public void whenAPositionIsComparedToAnotherWithTheSameYAndXValuesCompareToReturnsZero() {
        assertEquals(0, x1y0.compareTo(x1y0_v2));
    }
    
}