
package battleship2000.programlogic.domain.points;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Petri
 */
public class PointsTest {
    
    public PointsTest() {
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
    public void getPointsMethodReturnsZeroForMISSED() {
        assertEquals(0, Points.MISSED.getPoints());
    }
    
    @Test
    public void getPointsMethodReturnsFiveForHIT() {
        assertEquals(5, Points.HIT.getPoints());
    }
    
    @Test
    public void getPointsMethodReturnsTenForDESTROYED() {
        assertEquals(10, Points.DESTROYED.getPoints());
    }
}