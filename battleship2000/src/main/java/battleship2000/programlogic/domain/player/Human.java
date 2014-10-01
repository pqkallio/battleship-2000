package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.Square;

/**
 * A user-controlled player class that inherits the abstract Player class.
 * Controlled by the user, this player class contains the player's choices
 * in placing the ships and choosing
 * 
 * @author Petri Kallio
 */
public class Human extends Player {
    
    public Human() {}
    
    public Human(BattleShipGame game) {
        super(game);
    }
    
    @Override
    public Square chooseASquare(Table table, boolean aSquareCanBeHitMultipleTimes) {
        return null;
    }
    
    @Override
    public String toString() {
        return "HUMAN";
    }

    @Override
    public void placeShipsOnTable() {
    }

}
