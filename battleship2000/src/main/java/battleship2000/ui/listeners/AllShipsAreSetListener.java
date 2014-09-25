package battleship2000.ui.listeners;

import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.ui.panes.VisualGameTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;

/**
 * An action listener for listening the button that will start the game.
 *
 * @author Petri Kallio
 */
public class AllShipsAreSetListener implements ActionListener {
    private GameCommands gameCommands;
    private Player player;
    private VisualGameTable playersSide;
    private VisualGameTable foesSide;
    private List<JButton> buttons;
    
    public AllShipsAreSetListener(GameCommands gameCommands, 
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
