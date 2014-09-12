
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.GameTable;
import battleship2000.programlogic.domain.table.Square;
import java.util.List;

public abstract class Player {
    private List<Ship> ships;
    private int points;
    private Table table;
    private BattleShipGame game;
    private int shotsFired;
    private boolean shipsArePlaced;
    
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

    public void setShipsArePlaced() {
        this.shipsArePlaced = true;
    }

    public boolean shipsArePlaced() {
        return shipsArePlaced;
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
    
    public void printTable() {
        for (Integer y : table.getTable().keySet()) {
            for (Square square : table.getTable().get(y).values()) {
                if (square.isHit()) {
                    System.out.print("X");
                } else if (square.getShipPart() == null) {
                    System.out.print("O");
                } else {
                    if (square.getShipPart().isShipsFront()) {
                        System.out.print("F");
                    } else if (square.getShipPart().isShipsRear()) {
                        System.out.print("R");
                    } else {
                        System.out.print("M");
                    }
                }
            }
            System.out.println("");
        }
    }
}
