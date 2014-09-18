
package battleship2000.programlogic.domain.ship;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SubmarineTest {
    private Submarine submarine;
    
    public SubmarineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.submarine = new Submarine();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void toStringMethodReturnsSubmarinesName() {
        assertEquals("Submarine", this.submarine.toString());
    }
    
    @Test
    public void SubmarinesSizeIsThree() {
        assertEquals(3, this.submarine.getShipLength());
    }
    
    @Test
    public void submarinesShotsPerRoundIsFive() {
        assertEquals(5, this.submarine.getShotsPerRound());
    }
}