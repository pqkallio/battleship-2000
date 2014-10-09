
package battleship2000.programlogic.rules;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SizeLimitTest {
    
    public SizeLimitTest() {
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
    public void getShipsMinimumSizeMethodReturnsOne() {
        assertEquals(1, SizeLimits.getShipsMinimumSize());
    }
    
    @Test
    public void getShipsMaximumSizeMethodReturnsSix() {
        assertEquals(6, SizeLimits.getShipsMaximumSize());
    }
    
    @Test
    public void getMinimumTableWidthMethodReturnsTen() {
        assertEquals(10, SizeLimits.getMinimumTableWidth());
    }
    
    @Test
    public void getMaximumTableWidthMethodReturnsTwentyFive() {
        assertEquals(25, SizeLimits.getMaximumTableWidth());
    }
    
    @Test
    public void getMinimumAmountOfPlayersMethodReturnsTwo() {
        assertEquals(2, SizeLimits.getMinimumAmountOfPlayers());
    }
    
    @Test
    public void getMaximumAmountOfPlayersMethodReturnsFour() {
        assertEquals(4, SizeLimits.getMaximumAmountOfPlayers());
    }
    
    @Test
    public void getMinimumAmountOfShipsMethodReturnsTwo() {
        assertEquals(2, SizeLimits.getMinimumAmountOfShips());
    }
    
    @Test
    public void getMaximumAmountOfShipsMethodReturnsTen() {
        assertEquals(10, SizeLimits.getMaximumAmountOfShips());
    }
}