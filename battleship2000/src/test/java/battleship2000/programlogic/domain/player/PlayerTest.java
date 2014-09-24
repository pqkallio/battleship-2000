
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.control.CreateGame;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.rules.BasicRules;
import battleship2000.programlogic.rules.Rules;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player human;
    private Rules rules;
    private BattleShipGame playersGame;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.rules = new BasicRules(false);
        this.playersGame = (BattleShipGame) new CreateGame(rules).execute();
        this.human = this.playersGame.getPlayers().get(0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void newPlayersGetPointsMethodReturnsZero() {
        assertEquals(0, human.getPoints());
    }
    
    @Test
    public void playersPointsDontChangeIfAddPointsMethosIsGivenANegativeValueAsAParameter() {
        this.human.addPoints(-1);
        assertEquals(0, this.human.getPoints());
    }
    
    @Test
    public void getPointsMethodReturns1IfAddPointsMethodIsCalledWithAParameterOf1() {
        this.human.addPoints(1);
        assertEquals(1, this.human.getPoints());
    }
    
    @Test
    public void playersTablesHeightIsTheSameAsDeterminedInTheRules() {
        int playersTablesHeight = this.human.getTable().getHeight();
        int tablesHeightInRules = this.rules.getTableHeight();
        assertEquals(tablesHeightInRules, playersTablesHeight);
    }
    
    @Test
    public void playersTablesWidthIsTheSameADeterminedInTheRules() {
        int playersTablesWidth = this.human.getTable().getWidth();
        int tablesWidthInRules = this.rules.getTableWidth();
        assertEquals(tablesWidthInRules, playersTablesWidth);
    }
    
    @Test
    public void playerHasAsMuchShipsAsDeterminedInRules() {
        assertEquals(this.rules.getShipTypes().size(),
                this.human.getShips().size());
    }
    
    @Test
    public void playersShipTypesAreEqualToTheOnesGivenInRules() {
        boolean same = true;
        List<ShipType> shipTypesInRules 
                = (ArrayList<ShipType>)this.rules.getShipTypes();
        List<Ship> playersShips = this.human.getShips();
        
        for (int i = 0; i < playersShips.size(); i++) {
            if (!(playersShips.get(i).getClass() 
                    == shipTypesInRules.get(i).getShipClass())) {
                same = false;
            }
        }
        
        assertTrue(same);
    }
    
    @Test
    public void whenAPlayerIsCreatedShipsArePlacedMethodReturnsFalse() {
        assertFalse(this.human.shipsArePlaced());
    }
    
    @Test
    public void whenAPlayerIsCreatedGetShotsFiredReturns0() {
        assertEquals(0, this.human.getShotsFired());
    }
    
    @Test
    public void whenAPlayerIsCreatedGetGameMethodReturnsNull() {
        Player player2 = new Human();
        assertNull(player2.getGame());
    }
    
    @Test
    public void whenGameIsSetGetGameMethodReturnsTheGame() {
        Player player2 = new Human();
        BattleShipGame game = new BattleShipGame();
        player2.setGame(game);
        
        assertEquals(game, player2.getGame());
    }
    
    @Test
    public void ifAddShotsFiredMethodIsGivenAParameterOf1GetShotsFiredIsIncrementedBy1() {
        human.addShotsFired(1);
        assertEquals(1, human.getShotsFired());
    }
    
    @Test
    public void ifAddShotsFiredMethodIsGivenAParameterOfMinus1GetShotsFiredIsNotIncremented() {
        human.addShotsFired(-1);
        assertEquals(0, human.getShotsFired());
    }
    
    @Test
    public void ifAddShotsFiredMethodIsGivenAParameterOf0GetShotsFiredIsNotIncremented() {
        human.addShotsFired(0);
        assertEquals(0, human.getShotsFired());
    }
    
    @Test
    public void ifNoShipsAreDestroyedAllShipsDestroyedMethodReturnsFalse() {
        assertFalse(human.allShipsDestroyed());
    }
    
    @Test
    public void ifOneShipIsDestroyedAllShipsDestroyedMethodReturnsFalse() {
        for (ShipPart shipPart : human.getShips().get(0).getParts()) {
            shipPart.hit();
        }
        
        assertFalse(human.allShipsDestroyed());
    }
    
    @Test
    public void ifAllButOneShipIsDestroyedAllShipsDestroyedMethodReturnsFalse() {
        for (int i = 0; i < human.getShips().size() - 2; i++) {
            for (ShipPart shipPart : human.getShips().get(i).getParts()) {
                shipPart.hit();
            }
        }
        
        assertFalse(human.allShipsDestroyed());
    }
    
    @Test
    public void ifAllShipsAreDestroyedAllShipsDestroyedMethodReturnsTrue() {
        for (Ship ship : human.getShips()) {
            for (ShipPart shipPart : ship.getParts()) {
                shipPart.hit();
            }
        }
        
        assertTrue(human.allShipsDestroyed());
    }
    
    
    
}