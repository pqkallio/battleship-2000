
package battleship2000.programlogic.control;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.player.PlayerType;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.rules.BasicRules;
import battleship2000.programlogic.rules.Rules;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateGameTest {
    private BasicRules basicRules;
    private BattleShipGame basicRulesGame;
    private BattleShipGame everythingGoesRulesGame;
    
    public CreateGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Rules everythingGoesRules = new Rules();
        everythingGoesRules.setASquareCanBeHitMultipleTimesRule(true);
        everythingGoesRules.setShipsAreMovableRule(true);
        everythingGoesRules.setSpecializedShips(true);
        List<PlayerType> playerTypes = new ArrayList<>();
        Collections.addAll(playerTypes, PlayerType.HUMAN, PlayerType.COMPUTER);
        everythingGoesRules.setShipTypes((List<ShipType>)new BasicRules(true).getShipTypes());
        everythingGoesRules.setPlayerTypes(playerTypes);
        everythingGoesRules.setTableSize(20, 20);
        this.everythingGoesRulesGame = (BattleShipGame) new CreateGame(everythingGoesRules).execute();
        basicRules = new BasicRules(true);
        this.basicRulesGame = (BattleShipGame) new CreateGame(basicRules).execute();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void whenCreatedWithBasicRulesTheGamesGetShipsAreMovableReturnsFalse() {
        assertFalse(this.basicRulesGame.shipsAreMovable());
    }
    
    @Test
    public void whenCreatedWithBasicRulesTheGamesGetShipsAreSpecializedReturnsFalse() {
        assertFalse(this.basicRulesGame.shipsAreSpecialized());
    }
    
    @Test
    public void whenCreatedWithBasicRulesTheGamesGetASquareCanBeHitMultipleTimesReturnsFalse() {
        assertFalse(this.basicRulesGame.aSquareCanBeHitMultipleTimes());
    }
    
    @Test
    public void whenCreatedWithEverythingGoesRulesTheGamesGetShipsAreMovableReturnsTrue() {
        assertTrue(this.everythingGoesRulesGame.shipsAreMovable());
    }
    
    @Test
    public void whenCreatedWithEverythingGoesRulesTheGamesGetShipsAreSpecializedReturnsTrue() {
        assertTrue(this.everythingGoesRulesGame.shipsAreSpecialized());
    }
    
    @Test
    public void whenCreatedWithEverythingGoesRulesTheGamesGetASquareCanBeHitMultipleTimesReturnsTrue() {
        assertTrue(this.everythingGoesRulesGame.aSquareCanBeHitMultipleTimes());
    }
    
    @Test
    public void createdPlayerClassesAreTheSameAsTheOnesReturnedByThePlayerTypes() {
        boolean allIsOk = true;
        
        for (int i = 0; i < basicRulesGame.getPlayers().size(); i++) {
            if (basicRulesGame.getPlayers().get(i).getClass() != basicRules.getPlayerTypes().get(i).getPlayerClass()) {
                allIsOk = false;
            }
        }
        
        assertTrue(allIsOk);
    }
    
    @Test
    public void createdPlayersTablesAreTheSameSizeAsDefinedInRules() {
        boolean allIsOk = true;
        
        for (int i = 0; i < basicRulesGame.getPlayers().size(); i++) {
            if (basicRulesGame.getPlayers().get(i).getTable().getWidth() != basicRules.getTableWidth()
                    || basicRulesGame.getPlayers().get(i).getTable().getHeight() != basicRules.getTableHeight()) {
                allIsOk = false;
            }
        }
        
        assertTrue(allIsOk);
    }
    
    @Test
    public void allPlayersShipsIsOnTableMethodsReturnFalse() {
        boolean allIsOk = true;
        
        for (Player player : basicRulesGame.getPlayers()) {
            for (Ship ship : player.getShips()) {
                if (ship.isOnTable()) {
                    allIsOk = false;
                }
            }
        }
        
        assertTrue(allIsOk);
    }
}    