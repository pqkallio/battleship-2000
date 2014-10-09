package battleship2000.ui.control;

import battleship2000.ui.panes.TitlePane;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * A user interface control class for creating necessary panes for the user
 * interface.
 *
 * @author Petri Kallio
 */
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
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
    }
}
