
package battleship2000.ui.listeners;

import battleship2000.ui.control.GameCommands;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.ui.panes.VisualGameTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;

public class AllMofoingShipsAreMofoingSetListener implements ActionListener {
    private GameCommands gameCommands;
    private Player player;
    private VisualGameTable playersSide;
    private VisualGameTable foesSide;
    private List<JButton> buttons;
    
    public AllMofoingShipsAreMofoingSetListener(GameCommands gameCommands, 
            Player player, VisualGameTable playersSide, VisualGameTable foesSide,
            List<JButton> buttons) {
        this.gameCommands = gameCommands;
        this.player = player;
        this.playersSide = playersSide;
        this.foesSide = foesSide;
        this.buttons = buttons;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (Ship ship : player.getShips()) {
            if (!ship.isOnTable()) {
                return;
            }
        }
        
        gameCommands.startTheMofoingGame(playersSide, foesSide, buttons);
    }
}
