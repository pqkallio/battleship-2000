package battleship2000.programlogic;

import battleship2000.programlogic.control.CreateGame;
import battleship2000.programlogic.control.PlayOneRound;
import battleship2000.programlogic.control.ShipPlacement;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.rules.BasicRules;

/**
 * A class used by the user interface package to communicate with the program logic package.
 * <p>
 * This class is responsible for storing the BattleShipGame object and allowing 
 * interaction with it from the user interface.
 * 
 * @author Petri Kallio
 */
public class GameCommands {
    private BattleShipGame game;
    
    public GameCommands() {}
    
    /**
     * Creates a new BattleShipGame object and sets it as an object variable.
     */
    public void createANewGame() {
        this.game = (BattleShipGame) new CreateGame(new BasicRules(true)).execute();
    }
    
    /**
     * Checks if a ship can be placed on the player's game table on the coordinates 
     * given as parameters and places it if possible.
     * <p>
     * The {@link battleship2000.programlogic.control.ShipPlacement} responsible
     * for positioning the ships on a {@link battleship2000.programlogic.domain.table.GameTable}
     * will first check if the ship can be placed to the given coordinates and 
     * returns a boolean value according to the result.
     * 
     * @see {@link battleship2000.programlogic.control.ShipPlacement#setShipsPosition(int, int)}
     * @param ship  the {@link battleship2000.programlogic.domain.ship.Ship} object to be placed
     * @param x     the first ship part's x-coordinate
     * @param y     the first ship part's y-coordinate
     * @return      true if the ship was placed on the table, false otherwise
     */
    public boolean setShipOnTable(Ship ship, int x, int y) {
        ShipPlacement shipPositioning = new ShipPlacement(ship);
        if (shipPositioning.setShipsPosition(x, y)) {
            shipPositioning.placeShipOnTable();
            return true;
        }
        
        return false;
    }

    public BattleShipGame getGame() {
        return game;
    }
    
    /**
     * Tells the game that the user is ready to begin the game.
     * <p>
     * This method calls for the computerSetShips() method and subsequently starts
     * the game with the startGame() method.
     * 
     * @see     battleship2000.programlogic.GameCommands#computerSetShips()
     * @see     battleship2000.programlogic.GameCommands#startGame() 
     */
    public void playerReadyToStart() {
        computerSetShips();
        startGame();
    }
    
    /**
     * Creates a new PlayOneRound object that handles one turns events.
     */
    public void playOneRound(Square usersChoice) {
        new PlayOneRound(game, usersChoice).execute();
    }
    
    /**
     * Tells the computer to place the ships on the game table and subsequently
     * calls the BattleShipGame's notifyObservers() method to update the visual game table's
     * visual squares
     * 
     * @see     battleship2000.programlogic.domain.player.Computer#placeShipsOnTable()
     */
    private void computerSetShips() {
        game.getComputer().placeShipsOnTable();
        game.notifyObservers(StateChange.UPDATE_TABLE, game.getComputer());
    }
    
    /**
     * Notifies the BattleShipGame's observers that the game may commence.
     */
    private void startGame() {
        game.notifyObservers(StateChange.START_GAME);
    }
}
