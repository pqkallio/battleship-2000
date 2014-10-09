
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
    public void whenAPlayerIsCreatedGetShotsMissedReturns0() {
        assertEquals(0, this.human.getShotsMissed());
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
    public void ifAddShotsMissedMethodIsGivenAParameterOf1GetShotsFiredIsIncrementedBy1() {
        human.addShotsMissed(1);
        assertEquals(1, human.getShotsMissed());
    }
    
    @Test
    public void ifAddShotsMissedMethodIsGivenAParameterOfMinus1GetShotsFiredIsNotIncremented() {
        human.addShotsMissed(-1);
        assertEquals(0, human.getShotsMissed());
    }
    
    @Test
    public void ifAddShotsMissedMethodIsGivenAParameterOf0GetShotsFiredIsNotIncremented() {
        human.addShotsMissed(0);
        assertEquals(0, human.getShotsMissed());
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
    
    @Test
    public void getShotsHitReturnsZeroWhenANewPlayerIsCreated() {
        assertEquals(0, human.getShotsHit());
    }
    
    @Test
    public void getShotsMissedReturnsZeroWhenANewPlayerIsCreated() {
        assertEquals(0, human.getShotsMissed());
    }
    
    @Test
    public void getShotsHitReturnsOneWhenOneIsAdded() {
        human.addShotsHit(1);
        assertEquals(1, human.getShotsHit());
    }
    
    @Test
    public void getShotsMissedReturnsOneWhenOneIsAdded() {
        human.addShotsMissed(1);
        assertEquals(1, human.getShotsMissed());
    }
    
    @Test
    public void getShotsHitReturnsZeroWhenZeroIsAdded() {
        human.addShotsHit(0);
        assertEquals(0, human.getShotsHit());
    }
    
    @Test
    public void getShotsMissedReturnsZeroWhenZeroIsAdded() {
        human.addShotsMissed(0);
        assertEquals(0, human.getShotsMissed());
    }
    
    @Test
    public void getShotsHitReturnsZeroWhenMinusOneIsAdded() {
        human.addShotsHit(-1);
        assertEquals(0, human.getShotsHit());
    }
    
    @Test
    public void getShotsMissedReturnsZeroWhenMinusOneIsAdded() {
        human.addShotsMissed(-1);
        assertEquals(0, human.getShotsMissed());
    }
    
    @Test
    public void getAccuracyReturns100WhenOneShotIsHitAndZeroMissed() {
        human.addShotsHit(1);
        assertEquals(100.0, human.getAccuracy(), 1);
    }
    
    @Test
    public void getAccuracyReturns100WhenTwoShotsAreHitAndZeroMissed() {
        human.addShotsHit(2);
        assertEquals(100.0, human.getAccuracy(), 1);
    }
    
    @Test
    public void getAccuracyReturns50WhenOneShotIsHitAndOneMissed() {
        human.addShotsHit(1);
        human.addShotsMissed(1);
        assertEquals(50.0, human.getAccuracy(), 1);
    }
    
    @Test
    public void getAccuracyReturns33WhenOneShotIsHitAndTwoMissed() {
        human.addShotsMissed(2);
        human.addShotsHit(1);
        assertEquals(33.0, human.getAccuracy(), 1);
    }
    
    @Test
    public void getAccuracyReturns0WhenOneShotIsMissedAndZeroHit() {
        human.addShotsMissed(1);
        assertEquals(0.0, human.getAccuracy(), 1);
    }
    
    @Test
    public void getAccuracyReturns0WhenTwoShotIsMissedAndZeroHit() {
        human.addShotsMissed(2);
        assertEquals(0.0, human.getAccuracy(), 1);
    }
    
}