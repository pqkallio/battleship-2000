
package battleship2000.programlogic.domain.ship;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CannonShipTest {
    private CannonShip cannonShip;
    
    public CannonShipTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.cannonShip = new CannonShip();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void toStringMethodReturnsCannonShipsName() {
        assertEquals("Cannon ship", this.cannonShip.toString());
    }
    
    @Test
    public void cannonShipsSizeIsThree() {
        assertEquals(3, this.cannonShip.getShipLength());
    }
    
    @Test
    public void cannonShipsShotsPerRoundIsFive() {
        assertEquals(1, this.cannonShip.getShotsPerRound());
    }
}