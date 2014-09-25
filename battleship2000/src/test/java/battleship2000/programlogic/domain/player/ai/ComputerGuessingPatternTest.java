package battleship2000.programlogic.domain.player.ai;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.control.CreateGame;
import battleship2000.programlogic.rules.BasicRules;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComputerGuessingPatternTest {
    private ComputerGuessingPattern cgp;
    
    public ComputerGuessingPatternTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        BattleShipGame game = (BattleShipGame) new CreateGame(new BasicRules(true)).execute();
        this.cgp = new ComputerGuessingPattern(game.getHuman(), true);
    }
    
    @After
    public void tearDown() {
    }
    
}