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
    private int shotsFired;
    
    public abstract Square chooseASquare(Table table, 
            boolean aSquareCanBeHitMultipleTimes);
    public abstract void placeShipsOnTable();
    
    public Player() {
        this(null);
    }
    
    public Player(BattleShipGame game) {
        this.points = 0;
        this.shotsFired = 0;
        this.game = game;
    }

    public boolean shipsArePlaced() {
        for (Ship ship : ships) {
            if (!ship.isOnTable()) return false;
        }
        
        return true;
    }

    public int getShotsFired() {
        return shotsFired;
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
    
    public void addPoints(int points) {
        if (points > -1) {
            this.points += points;
        }
    }
    
    public void addShotsFired(int shotsFired) {
        if (shotsFired > 0) {
            this.shotsFired += shotsFired;
        }
    }   
    
    public boolean allShipsDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isDestroyed()) return false;
        }
        
        return true;
    }
}
