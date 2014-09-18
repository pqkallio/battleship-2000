
package battleship2000.programlogic.domain.ship;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MissileShipTest {
    private MissileShip missileShip;
    
    public MissileShipTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.missileShip = new MissileShip();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void toStringMethodReturnsMissileShipsName() {
        assertEquals("Missile ship", this.missileShip.toString());
    }
    
    @Test
    public void missileShipsSizeIsFour() {
        assertEquals(4, this.missileShip.getShipLength());
    }
    
    @Test
    public void missileShipsShotsPerRoundIsFive() {
        assertEquals(5, this.missileShip.getShotsPerRound());
    }
}