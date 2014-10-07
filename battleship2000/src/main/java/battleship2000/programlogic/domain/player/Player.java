package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.Square;
import java.util.List;

/**
 * This class is an abstraction of the game's players.
 * The class contains the player's game table, ships, points and all the other
 * relevant data about the player during the game.
 * 
 * @author Petri Kallio
 */
public abstract class Player {
    private List<Ship> ships;
    private int points;
    private Table table;
    private BattleShipGame game;
    private int shotsMissed;
    private int shotsHit;
    
    /**
     * Choose a square to bomb from a table.
     * 
     * @param table                         the {@link battleship2000.programlogic.domain.table.Table} to choose the square from
     * @param aSquareCanBeHitMultipleTimes  defines if a square can be bombed multiple times
     * @return                              the {@link battleship2000.programlogic.domain.table.Square} to be bombed
     */
    public abstract Square chooseASquare(Table table, 
            boolean aSquareCanBeHitMultipleTimes);
    
    /**
     * Place the player's {@link battleship2000.programlogic.domain.ship.Ship}s on
     * the {@link battleship2000.programlogic.domain.table.Table}.
     */
    public abstract void placeShipsOnTable();
    
    /**
     * A constructor to create a new instance of the class without a
     * {@link battleship2000.programlogic.BattleShipGame} object defined.
     */
    public Player() {
        this(null);
    }
    
    /**
     * A constructor to create a new instance of the class with its 
     * {@link battleship2000.programlogic.BattleShipGame} object defined.
     * 
     * @param game  the game to be set as an object variable
     */
    public Player(BattleShipGame game) {
        this.points = 0;
        this.shotsMissed = 0;
        this.shotsHit = 0;
        this.game = game;
    }

    /**
     * Returns the state of the {@link battleship2000.programlogic.domain.ship.Ship}s' 
     * placement on the player's {@link battleship2000.programlogic.domain.table.Table}.
     * 
     * @return      true if all ships are placed on the table, false otherwise
     */
    public boolean shipsArePlaced() {
        for (Ship ship : ships) {
            if (!ship.isOnTable()) return false;
        }
        
        return true;
    }
    
    public int getShotsMissed() {
        return shotsMissed;
    }

    public int getShotsHit() {
        return shotsHit;
    }
    
    public int getPoints() {
        return points;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public void setGame(BattleShipGame game) {
        this.game = game;
    }

    public BattleShipGame getGame() {
        return game;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }
    
    /**
     * Adds points to the player according to the amount given as a parameter.
     * <p>
     * The amount of points granted from different kinds of hits are defined in
     * the {@link battleship2000.programlogic.domain.points.Points} enum type.
     * 
     * @param points    the amount of points to be added
     */
    public void addPoints(int points) {
        if (points > -1) {
            this.points += points;
        }
    }
    
    /**
     * Adds the amount of shots missed during the player's turn.
     * 
     * @param shotsMissed   the amount of missed shots to be added
     */
    public void addShotsMissed(int shotsMissed) {
        if (shotsMissed > 0) {
            this.shotsMissed += shotsMissed;
        }
    }   

    /**
     * Adds the amount of shots that hit a {@link battleship2000.programlogic.domain.ship.ShipPart} 
     * during the player's turn.
     * 
     * @param shotsHit   the amount shots that hit a ship part to be added
     */
    public void addShotsHit(int shotsHit) {
        if (shotsHit > 0) {
            this.shotsHit += shotsHit;
        }
    }
    
    /**
     * Returns the state of the {@link battleship2000.programlogic.domain.ship.Ship}s. 
     * 
     * @return      true if all ships are destroyed, false otherwise
     */
    public boolean allShipsDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isDestroyed()) return false;
        }
        
        return true;
    }

    /**
     * Returns the player's shot accuracy.
     * 
     * @return  the percentage of shots that have hit a ship part
     */
    public Double getAccuracy() {
        if (this.shotsHit == 0 && this.shotsMissed == 0) {
            return 0.0;
        } else {
            return Math.ceil(100.0 * this.shotsHit / (this.shotsHit + this.shotsMissed));
        }
    }
}
