
package battleship2000.ui.listeners;

import battleship2000.programlogic.domain.player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetShipListener implements ActionListener {
    private Player player;
    
    public SetShipListener(Player player) {
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.player.placeShipsOnTable();
    }
    
}
