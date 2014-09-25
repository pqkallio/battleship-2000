package battleship2000.programlogic;

import battleship2000.programlogic.observers.LogicObserver;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.domain.player.PlayerType;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains the rules and the players of the game.
 * 
 * @author Petri Kallio
 */
public class BattleShipGame {
    private List<Player> players;
    private List<LogicObserver> observers;
    private boolean aSquareCanBeHitMultipleTimes;
    private boolean shipsAreMovable;
    private boolean shipsAreSpecialized;
    private boolean continues;
    private int turn;
    private Square chosenSquare;
    
    /**
     * A constructor for creating a new instance of the class.
     */
    public BattleShipGame() {
        this.players = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.continues = true;
        this.turn = 0;
        this.chosenSquare = null;
    }
    
    /**
     * Returns a boolean-value of the games status.
     * 
     * @return  true if the game is still running, false otherwise
     */
    public boolean continues() {
        return continues;
    }

    /**
     * Increments the games turn by 1.
     */
    
    public void incrementTurn() {
        this.turn++;
    }
    
    public int getTurn() {
        return turn;
    }

    public Square getChosenSquare() {
        return chosenSquare;
    }
    
    /**
     * Adds a new LogicObserver to the appointed list.
     * 
     * @param observer  the LogicObserver to be added
     */
    public void addObserver(LogicObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Returns the list of observers added to the BattleShipGame object.
     * 
     * @return  a list of the 
     */
    public List<LogicObserver> getObservers() {
        return observers;
    }
    
    /**
     * Notifies the observers to update
     * 
     * @param object    an object passed for an observer to use
     */
    public void notifyObservers(Object object) {
        for (LogicObserver observer : this.observers) {
            observer.update(object);
        }
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Adds a list of players to the game's player list and passes itself as a 
     * parameter to all the players.
     * 
     * @see battleship2000.programlogic.domain.player.Player#setGame(battleship2000.programlogic.BattleShipGame) 
     * @param players   a list of players to be added in the game
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
        for (Player player : this.players) {
            player.setGame(this);
        }
    }

    public void setShipsAreMovable(boolean shipsAreMovable) {
        this.shipsAreMovable = shipsAreMovable;
    }

    public void setShipsAreSpecialized(boolean shipsAreSpecialized) {
        this.shipsAreSpecialized = shipsAreSpecialized;
    }

    public void setASquareCanBeHitMultipleTimes(boolean aSquareCanBeHitMultipleTimes) {
        this.aSquareCanBeHitMultipleTimes = aSquareCanBeHitMultipleTimes;
    }
    
    public boolean aSquareCanBeHitMultipleTimes() {
        return this.aSquareCanBeHitMultipleTimes;
    }

    public boolean shipsAreMovable() {
        return shipsAreMovable;
    }

    public boolean shipsAreSpecialized() {
        return shipsAreSpecialized;
    }
    
    /**
     * Goes through the game's players to return the player whose class is 
     * {@link battleship2000.programlogic.domain.player.Human}
     * 
     * @return  the player whose class is {@link battleship2000.programlogic.domain.player.Human} or null
     */
    public Player getHuman() {
        for (Player player : players) {
            if (player.getClass() == PlayerType.HUMAN.getPlayerClass()) {
                return player;
            }
        }
        
        return null;
    }
    
    /**
     * Goes through the game's players to return the player whose class is 
     * {@link battleship2000.programlogic.domain.player.Computer}
     * 
     * @return  the player whose class is {@link battleship2000.programlogic.domain.player.Computer} or null
     */
    public Player getComputer() {
        for (Player player : players) {
            if (player.getClass() == PlayerType.COMPUTER.getPlayerClass()) {
                return player;
            }
        }
        
        return null;
    }
}
