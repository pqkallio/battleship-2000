
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.domain.table.GameTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HumanTest {
    private Human human;
    private GameTable table;
    
    public HumanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.human = new Human();
        this.table = new GameTable(10, 10);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void chooseASquareMethodReturnsNull() {
        assertNull(human.chooseASquare(table, false));
    }
    
    @Test
    public void toStringReturnsTheCorrectString() {
        assertEquals("HUMAN", human.toString());
    }
}