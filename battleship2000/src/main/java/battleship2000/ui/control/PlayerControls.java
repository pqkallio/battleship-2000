
package battleship2000.ui.control;

import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import java.util.List;

public class PlayerControls {
    private Player player;
    
    public PlayerControls(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    
    public Table getTable() {
        return this.player.getTable();
    }
    
    public List<Ship> getShips() {
        return this.player.getShips();
    }
}
