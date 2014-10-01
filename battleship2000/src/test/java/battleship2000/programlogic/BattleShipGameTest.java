
package battleship2000.programlogic;

import battleship2000.programlogic.observers.LogicObserver;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.player.Computer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.event.ChangeEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BattleShipGameTest {
    private BattleShipGame game;
    private final Player[] players = {new Human(), new Computer()};
    private final Player[] computerPlayer = {new Computer()};
    private final Player[] humanPlayer = {new Human()};
    
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
        setPlayers(this.players);
        
        assertEquals(this.game.getPlayers().size(), players.length);
    }
    
    @Test
    public void getPlayersMethodReturnsTheSamePlayersThatWereSetInTheGame() {
        setPlayers(this.players);
        boolean identical = true;
        
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            if (!(this.game.getPlayers().get(i) == players[i])) {
                identical = false;
            }
        }
        
        assertTrue(identical);
    }
    
    @Test
    public void whenPlayersAreSetTheGameIsSetToThePlayer() {
        setPlayers(this.players);
        
        boolean allIsOk = true;
        
        for (Player player : game.getPlayers()) {
            if (player.getGame() != game) {
                allIsOk = false;
            }
        }
        
        assertTrue(allIsOk);
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
    public void whenIncrementTurnMethodIsCalledGetTurnMethodReturnsOne() {
        game.incrementTurn();
        assertEquals(1, game.getTurn());
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
        game.addObserver(new DummyObserver1());
        
        assertEquals(1, game.getObservers().size());
    }
    
    @Test
    public void whenTwoObserversAreAddedGetObserversSizeEqualsTwo() {
        game.addObserver(new DummyObserver1());
        game.addObserver(new DummyObserver2());
        
        assertEquals(2, game.getObservers().size());
    }
    
    @Test
    public void whenTwoObserversAreNotifiedWithAnObjectTheyBothReactToTheyBothChangeTheGamesState() {
        game.addObserver(new DummyObserver1());
        game.addObserver(new DummyObserver2());
        game.notifyObservers(StateChange.START_GAME);
        
        assertTrue(game.getObservers().get(0).isUpdated() & game.getObservers().get(1).isUpdated());
    }
    
    @Test
    public void whenTwoObserversAreNotifiedWithAnObjectOnlyOneOfThemReactsToOnlyOneChangesTheGamesState() {
        game.addObserver(new DummyObserver1());
        game.addObserver(new DummyObserver2());
        game.notifyObservers(StateChange.UPDATE_TABLE);
        
        assertTrue(game.getObservers().get(0).isUpdated() & !game.getObservers().get(1).isUpdated());
    }
    
    @Test
    public void whenAGameContainsBothHumanAndComputerPlayersGetComputerMethodReturnsComputer() {
        setPlayers(players);
        
        assertEquals(players[1], game.getComputer());
    }
    
    @Test
    public void whenAGameContainsBothHumanAndComputerPlayersGetHumanMethodReturnsHuman() {
        setPlayers(players);
        
        assertEquals(players[0], game.getHuman());
    }
    
    @Test
    public void whenAGameContainsOnlyAHumanPlayerGetComputerMethodReturnsNull() {
        setPlayers(humanPlayer);
        
        assertNull(game.getComputer());
    }
    
    @Test(expected = NullPointerException.class)
    public void whenAGameContainsOnlyAComputerPlayerANullPointerExceptionIsExpected() {
        setPlayers(computerPlayer);
    }

    
    
// classes and methods to help testing
    
    private void setPlayers(Player[] playerArray) {
        List<Player> pelinPelaajat = new ArrayList<>();
        Collections.addAll(pelinPelaajat, playerArray);
        this.game.setPlayers(pelinPelaajat);
    }
}


class DummyObserver1 implements LogicObserver {
    private boolean updated;
    
    DummyObserver1() {
        this.updated = false;
    }
    
    @Override
    public void update(StateChange stateChange, Object... object) {
        if (stateChange == StateChange.START_GAME || stateChange == StateChange.UPDATE_TABLE) {
            updated = true;
        }
    }

    @Override
    public boolean isUpdated() {
        return updated;
    }
}

class DummyObserver2 implements LogicObserver {
    private boolean updated;
    
    DummyObserver2() {
        this.updated = false;
    }
    
    @Override
    public void update(StateChange stateChange, Object... object) {
        if (stateChange == StateChange.START_GAME) {
            updated = true;
        }
    }
    
    @Override
    public boolean isUpdated() {
        return updated;
    }
}
