
package battleship2000.programlogic.domain.ship;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AircarrierTest {
    private Aircarrier aircarrier;
    
    public AircarrierTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.aircarrier = new Aircarrier();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void toStringMethodReturnsAircarriersName() {
        assertEquals("Aircarrier", this.aircarrier.toString());
    }
    
    @Test
    public void aircarriersSizeIsFive() {
        assertEquals(5, this.aircarrier.getShipLength());
    }
    
    @Test
    public void aircarriersShotsPerRoundIsFive() {
        assertEquals(5, this.aircarrier.getShotsPerRound());
    }
}