/**
 * A control class used to direct a single turn iteration in the game.
 */

package battleship2000.programlogic.control;

import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.CannonShip;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.table.Square;
import java.util.List;

public class PlayTurn implements Controller {
    private Player player;
    private Player foe;
    private Square square;
    private Ship attackingShip;
    
    public PlayTurn(Player player, Player foe, Square square) {
        this(player, foe, square, new CannonShip());
    }
    
    public PlayTurn(Player player, Player foe, Square square, Ship attackingShip) {
        this.player = player;
        this.foe = foe;
        this.square = square;
        this.attackingShip = attackingShip;
    }
    
    @Override
    public Object execute() {
        List<Square> squaresToBomb = attackingShip.getSquaresToBomb(square);
        int turnsPoints = 0;
        
        for (Square squareToBomb : squaresToBomb) {
            turnsPoints += squareToBomb.bomb();
        }
        
        player.addPoints(turnsPoints);
        
        if (foe.allShipsDestroyed()) {
            return false;
        } else {
            return true;
        }
    }
}
