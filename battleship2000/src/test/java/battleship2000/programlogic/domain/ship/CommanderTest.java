
package battleship2000.programlogic.domain.ship;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommanderTest {
    private Commander commander;
    
    public CommanderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.commander = new Commander();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void toStringMethodReturnsCommandersName() {
        assertEquals("Commander", this.commander.toString());
    }
    
    @Test
    public void commandersSizeIsTwo() {
        assertEquals(2, this.commander.getShipLength());
    }
    
    @Test
    public void commandersShotsPerRoundIsOne() {
        assertEquals(1, this.commander.getShotsPerRound());
    }
}