package battleship2000.ui.panes;

import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.ui.listeners.ShipPlacementListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * A visual representation of a player's game table and its contents.
 *
 * @author Petri Kallio
 */
public class VisualGameTable extends JPanel {
    private Player player;
    private List<VisualSquare> squares;
    private GameCommands gameCommands;
    private Ship chosenShip;
    private GamePane gamePane;

    VisualGameTable(Player player, GameCommands gameCommands, GamePane gamePane) {
        this.player = player;
        this.squares = new ArrayList<>();
        this.gameCommands = gameCommands;
        this.gamePane = gamePane;
        setElements();
    }

    public Ship getChosenShip() {
        return chosenShip;
    }

    public void setChosenShip(Ship chosenShip) {
        this.chosenShip = chosenShip;
    }

    public GameCommands getGameCommands() {
        return gameCommands;
    }
    
    public List<VisualSquare> getSquares() {
        return squares;
    }

    public Player getPlayer() {
        return player;
    }
    
    private void setElements() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        Table playersTable = player.getTable();
        this.setLayout(layout);
        
        int[] layoutWidths = new int[playersTable.getWidth()];
        int[] layoutHeights = new int[playersTable.getHeight()];
        
        for (int i = 0; i < layoutWidths.length; i++) {
            layoutWidths[i] = 25;
        }
        
        for (int i = 0; i < layoutHeights.length; i++) {
            layoutHeights[i] = 25;
        }
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        gbc.fill = GridBagConstraints.BOTH;
        
        for (int i = 0; i < layout.rowHeights.length; i++) {
            for (int j = 0; j < layout.columnWidths.length; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                
                VisualSquare square = new VisualSquare(this, playersTable.getTable().get(i).get(j));
                
                if (this.player.getClass() == Human.class) {
                    square.addMouseListener(new ShipPlacementListener(square));
                }
                
                layout.setConstraints(square, gbc);
                this.add(square);
                
                this.squares.add(square);
            }
        }
    }
    
    public void repaintAll() {
        for (VisualSquare visualSquare : squares) {
            visualSquare.repaint();
        }
    }
    
    public void setOkToBeginGame(boolean isOk) {
        this.gamePane.setAllMofoingShipsMofoingSetEnabled(isOk);
    }
}
