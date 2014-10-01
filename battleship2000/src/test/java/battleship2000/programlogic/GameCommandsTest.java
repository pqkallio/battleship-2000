
package battleship2000.programlogic;

import battleship2000.programlogic.domain.player.Computer;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.observers.LogicObserver;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameCommandsTest {
    private GameCommands gc;
    
    public GameCommandsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    this.gc = new GameCommands();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void whenANewGameCommandsIsCreatedGetGameMethodReturnsNull() {
        assertNull(gc.getGame());
    }
    
    @Test
    public void whenANewGameIsCreatedGetGameMethodReturnsTheGame() {
        gc.createANewGame();
        
        assertNotNull(gc.getGame());
    }
    
    @Test
    public void ifAShipCanBePlacedOnTheTableTheSetShipOnTableMethodReturnsTrue() {
        gc.createANewGame();
        
        assertTrue(gc.setShipOnTable(gc.getGame().getHuman().getShips().get(0), 1, 1));
    }
    
    @Test
    public void ifAShipCantBePlacedOnTheTableTheSetShipOnTableMethodReturnsFalse() {
        gc.createANewGame();
        gc.setShipOnTable(gc.getGame().getHuman().getShips().get(0), 1, 1);
        
        assertFalse(gc.setShipOnTable(gc.getGame().getHuman().getShips().get(1), 1, 1));
    }
    
    @Test
    public void afterPlayerReadyToStartMethodIsCalledAllComputersShipsWillBeSetOnTable() {
        gc.createANewGame();
        gc.playerReadyToStart();
        boolean allIsOk = true;
        
        for (Ship ship : gc.getGame().getComputer().getShips()) {
            if (!ship.isOnTable()) {
                allIsOk = false;
            }
        }
        
        assertTrue(allIsOk);
    }
    
    @Test
    public void afterPlayerReadyToStartMethodIsCalledUpdateTableObserversWillBeUpdated() {
        gc.createANewGame();
        boolean allIsOk = true;
        
        for (int i = 0; i < 10; i++) {
            gc.getGame().addObserver(new UpdateTableObserver());
        }
        
        gc.playerReadyToStart();
        
        for (LogicObserver observer : gc.getGame().getObservers()) {
            if (!observer.isUpdated()) {
                allIsOk = false;
            }
        }
        
        assertTrue(allIsOk);
    }
    
    @Test
    public void afterPlayerReadyToStartMethodIsCalledStartGameObserverWillBeUpdated() {
        gc.createANewGame();
        boolean allIsOk = true;
        
        gc.getGame().addObserver(new StartGameObserver());
        
        gc.playerReadyToStart();
        
        for (LogicObserver observer : gc.getGame().getObservers()) {
            if (!observer.isUpdated()) {
                allIsOk = false;
            }
        }
        
        assertTrue(allIsOk);
    }
}


class UpdateTableObserver implements LogicObserver {
    private boolean updated;
    
    public UpdateTableObserver() {
        this.updated = false;
    }
    
    @Override
    public void update(StateChange stateChange, Object... object) {
        if (stateChange == StateChange.UPDATE_TABLE) {
            updated = true;
        }
    }

    @Override
    public boolean isUpdated() {
        return updated;
    }
}

class StartGameObserver implements LogicObserver {
    private boolean updated;
    
    public StartGameObserver() {
        this.updated = false;
    }
    
    @Override
    public void update(StateChange stateChange, Object... object) {
        if (stateChange == StateChange.START_GAME) {
            this.updated = true;
        }
    }

    @Override
    public boolean isUpdated() {
        return updated;
    }
}