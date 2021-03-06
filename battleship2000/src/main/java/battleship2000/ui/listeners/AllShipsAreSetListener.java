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
    
    /**
     * Constructs a new instance of the class.
     * 
     * @param gameCommands  the game commands of the game
     * @param player        the player object commanded by the user
     * @param playersSide   the user's visual game table   
     */
    public AllShipsAreSetListener(GameCommands gameCommands, 
            Player player, VisualGameTable playersSide) {
        this.gameCommands = gameCommands;
        this.player = player;
        this.playersSide = playersSide;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (Ship ship : player.getShips()) {
            if (!ship.isOnTable()) {
                return;
            }
        }
        playersSide.getGamePane().getGui().getAudioContent().getKlikKlok().play();
        gameCommands.playerReadyToStart();
    }
}
