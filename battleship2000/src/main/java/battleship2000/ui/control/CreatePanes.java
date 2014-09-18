
package battleship2000.ui.control;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.control.CreateGame;
import battleship2000.programlogic.rules.BasicRules;
import battleship2000.ui.panes.GamePane;
import battleship2000.ui.panes.TitlePane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class CreatePanes {
    private Container container;
    
    public CreatePanes(Container container) {
        this.container = container;
    }

    public void execute() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        this.container.setLayout(layout);
        int[] layoutWidths = {50, 250, 50};
        int[] layoutHeights = {50, 250, 50};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        JPanel titlePane = new TitlePane().getTitlePage();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
    }
}
