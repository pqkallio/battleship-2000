
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.domain.table.GameTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComputerTest {
    private Computer computer;
    private GameTable table;
    
    public ComputerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.computer = new Computer();
        this.table = new GameTable(10, 10);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void toStringMethodReturnsTheCorrectString() {
        assertEquals("COMPUTER", computer.toString());
    }
    
    @Test
    public void ifAllTheTablesSquaresAreHitChooseASquareMethodReturnsNull() {
        for (int y : table.getTableAsMap().keySet()) {
            for (int x : table.getTableAsMap().get(y).keySet()) {
                table.getTableAsMap().get(y).get(x).bomb();
            }
        }
        
        assertNull(computer.chooseASquare(table, false));
    }
}