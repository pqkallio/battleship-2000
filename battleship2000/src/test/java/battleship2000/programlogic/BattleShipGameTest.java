
package battleship2000.programlogic;

import battleship2000.programlogic.observers.LogicObserver;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.player.Computer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BattleShipGameTest {
    private BattleShipGame game;
    private final Player[] players = {new Human(), new Computer()};
    
    public BattleShipGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.game = new BattleShipGame();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sizeOfTheListOfPlayersIsTheSameAsTheAmountOfPlayersSetInTheGame() {
        setPlayers();
        
        assertEquals(this.game.getPlayers().size(), players.length);
    }
    
    @Test
    public void getPlayersMethodReturnsTheSamePlayersThatWereSetInTheGame() {
        setPlayers();
        boolean identical = true;
        
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            if (!(this.game.getPlayers().get(i) == players[i])) {
                identical = false;
            }
        }
        
        assertTrue(identical);
    }
    
    @Test
    public void ifSetShipsAreMovableMethodReceivesTrueAsParameterTheGetterMethodReturnsTrue() {
        this.game.setShipsAreMovable(true);
        assertTrue(this.game.shipsAreMovable());
    }
    
    @Test
    public void ifSetShipsAreMovableMethodReceivesFalseAsParameterTheGetterMethodReturnsFalse() {
        this.game.setShipsAreMovable(false);
        assertFalse(this.game.shipsAreMovable());
    }
    
    @Test
    public void ifSetShipsAreSpecializedMethodReceivesTrueAsParameterTheGetterMethodReturnsTrue() {
        this.game.setShipsAreSpecialized(true);
        assertTrue(this.game.shipsAreSpecialized());
    }
    
    @Test
    public void ifSetShipsAreSpecializedMethodReceivesFalseAsParameterTheGetterMethodReturnsFalse() {
        this.game.setShipsAreSpecialized(false);
        assertFalse(this.game.shipsAreSpecialized());
    }

    @Test
    public void ifASquareCanBeHitMultipleTimesIsSetTrueItsGetterReturnsTrue() {
        game.setASquareCanBeHitMultipleTimes(true);
        assertTrue(game.aSquareCanBeHitMultipleTimes());
    }
    
    @Test
    public void ifASquareCanBeHitMultipleTimesIsSetFalseItsGetterReturnsFalse() {
        game.setASquareCanBeHitMultipleTimes(false);
        assertFalse(game.aSquareCanBeHitMultipleTimes());
    }
    
    @Test
    public void whenNewGameIsCreatedContinuesIsTrue() {
        assertTrue(game.continues());
    }
    
    @Test
    public void whenNewGameIsCreatedGetTurnReturnsZero() {
        assertEquals(0, game.getTurn());
    }
    
    @Test
    public void whenNewGameIsCreatedGetChosenSquareEqualsNull() {
        assertNull(game.getChosenSquare());
    }
    
    @Test
    public void whenNewGameIsCreatedGetObserversIsEmptyEqualsTrue() {
        assertTrue(game.getObservers().isEmpty());
    }
    
    @Test
    public void whenOneObserverIsAddedGetObserversSizeEqualsOne() {
        game.addObserver(new DummyObserver1(game));
        
        assertEquals(1, game.getObservers().size());
    }
    
    @Test
    public void whenTwoObserversAreAddedGetObserversSizeEqualsTwo() {
        game.addObserver(new DummyObserver1(game));
        game.addObserver(new DummyObserver2(game));
        
        assertEquals(2, game.getObservers().size());
    }
    
    @Test
    public void whenTwoObserversAreNotifiedWithAnObjectTheyBothReactToTheyBothChangeTheGamesState() {
        game.addObserver(new DummyObserver1(game));
        game.addObserver(new DummyObserver2(game));
        game.notifyObservers(true);
        
        assertEquals(2, game.getTurn());
    }
    
    @Test
    public void whenTwoObserversAreNotifiedWithAnObjectOnlyOneOfThemReactsToOnlyOneChangesTheGamesState() {
        game.addObserver(new DummyObserver1(game));
        game.addObserver(new DummyObserver2(game));
        game.notifyObservers(false);
        
        assertEquals(1, game.getTurn());
    }
    
    private void setPlayers() {
        List<Player> pelinPelaajat = new ArrayList<>();
        Collections.addAll(pelinPelaajat, players);
        this.game.setPlayers(pelinPelaajat);
    }
}

class DummyObserver1 implements LogicObserver {
    private BattleShipGame game;
    
    DummyObserver1(BattleShipGame game) {
        this.game = game;
    }
    
    @Override
    public void update(Object object) {
        if (object.getClass() == Boolean.class) {
            if ((boolean)object == true) {
                this.game.incrementTurn();
            } else if ((boolean)object == false) {
                this.game.incrementTurn();
            }
        }
    }
}

class DummyObserver2 implements LogicObserver {
    private BattleShipGame game;
    
    DummyObserver2(BattleShipGame game) {
        this.game = game;
    }
    
    @Override
    public void update(Object object) {
        if (object.getClass() == Boolean.class) {
            if ((boolean)object == true) {
                this.game.incrementTurn();
            }
        }
    }
}
