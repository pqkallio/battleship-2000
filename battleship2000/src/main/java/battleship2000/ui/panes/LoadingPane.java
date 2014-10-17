
package battleship2000.ui.panes;

import battleship2000.ui.BattleshipGui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Pane to display when the game is loading content.
 * 
 * @author Petri Kallio
 */
public class LoadingPane extends JPanel {
    private BattleshipGui gui;
    
    /**
     * Constructs a new instance of the class.
     * 
     * @param gui   the gui that displays the pane
     */
    public LoadingPane(BattleshipGui gui) {
        this.gui = gui;
    }
    
    /**
     * Creates and returns a displayable JPanel.
     * 
     * @return  the loading pane
     */
    public JPanel getPane() {
        JPanel loading = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        loading.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        
        int[] widths = {gui.getFRAME_WIDTH()};
        int[] heights = {gui.getFRAME_HEIGHT()};
        
        layout.columnWidths = widths;
        layout.rowHeights = heights;
        
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel loadingLabel = new JLabel("Loading...");
        
        layout.setConstraints(loadingLabel, gbc);
        loading.add(loadingLabel);
        
        return loading;
    }
}
