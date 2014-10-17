package battleship2000.ui.listeners;

import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.ui.panes.VisualGameTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * An action listener used to choose a ship for placing it on the game table.
 *
 * @author Petri Kallio
 */
public class ShipChooserListener implements ActionListener {
    private Player player;
    private VisualGameTable playersSide;
    private JButton commander;
    private JButton cannonShip;
    private JButton submarine;
    private JButton missileShip;
    private JButton aircarrier;
    
    /**
     * Constructs a new instance of the class.
     * 
     * @param gameCommands  the game commands object of the game
     * @param playersSide   the user's visual game table
     * @param commander     the user's first ship
     * @param cannonShip    the user's second ship
     * @param submarine     the user's third ship
     * @param missileShip   the user's fourth ship
     * @param aircarrier    the user's fifth ship
     */
    public ShipChooserListener(GameCommands gameCommands, VisualGameTable playersSide, 
            JButton commander, JButton cannonShip, JButton submarine, 
            JButton missileShip, JButton aircarrier) {
        this.player = gameCommands.getGame().getPlayers().get(0);
        this.playersSide = playersSide;
        this.cannonShip = cannonShip;
        this.commander = commander;
        this.submarine = submarine;
        this.missileShip = missileShip;
        this.aircarrier = aircarrier;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == commander) {
            for (Ship ship : player.getShips()) {
                if (ship.getClass() == ShipType.COMMANDER.getShipClass()) {
                    checkIfShipIsAlreadyOnTable(ship);
                    playersSide.setChosenShip(ship);
                }
            }
        } else if (ae.getSource() == cannonShip) {
            for (Ship ship : player.getShips()) {
                if (ship.getClass() == ShipType.CANNON_SHIP.getShipClass()) {
                    checkIfShipIsAlreadyOnTable(ship);
                    playersSide.setChosenShip(ship);
                }
            }
        } else if (ae.getSource() == submarine) {
            for (Ship ship : player.getShips()) {
                if (ship.getClass() == ShipType.SUBMARINE.getShipClass()) {
                    checkIfShipIsAlreadyOnTable(ship);
                    playersSide.setChosenShip(ship);
                }
            }
        } else if (ae.getSource() == missileShip) {
            for (Ship ship : player.getShips()) {
                if (ship.getClass() == ShipType.MISSILE_SHIP.getShipClass()) {
                    checkIfShipIsAlreadyOnTable(ship);
                    playersSide.setChosenShip(ship);
                }
            }
        } else if (ae.getSource() == aircarrier) {
            for (Ship ship : player.getShips()) {
                if (ship.getClass() == ShipType.AIRCARRIER.getShipClass()) {
                    checkIfShipIsAlreadyOnTable(ship);
                    playersSide.setChosenShip(ship);
                }
            }
        }
        
        playersSide.getGamePane().getGui().getAudioContent().getKlikOrKlok().play();
        
    }

    private void checkIfShipIsAlreadyOnTable(Ship ship) {
        if (ship.isOnTable()) {
            ship.setIsOnTable(false);
            player.getTable().removePartsFromTable(ship);
            playersSide.repaintAll();
        }
    }
}
