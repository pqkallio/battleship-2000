
package battleship2000.ui.panes;

import battleship2000.programlogic.control.GameCommands;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.ui.listeners.ShipPlacementListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GraphicGameTable extends JPanel {
    private Player player;
    private List<TableSquare> squares;
    GameCommands gameCommands;

    GraphicGameTable(Player player, GameCommands gameCommands) {
        this.player = player;
        this.squares = new ArrayList<>();
        this.gameCommands = gameCommands;
        setElements();
    }

    public GameCommands getGameCommands() {
        return gameCommands;
    }
    
    public List<TableSquare> getSquares() {
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
                
                TableSquare square = new TableSquare(this, playersTable.getTable().get(i).get(j));
                square.addMouseListener(new ShipPlacementListener(square));
                layout.setConstraints(square, gbc);
                this.add(square);
                
                this.squares.add(square);
            }
        }
    }
}
