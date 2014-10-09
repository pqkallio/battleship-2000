
package battleship2000.ui.panes;

import battleship2000.ui.BattleshipGui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingPane extends JPanel {
    private BattleshipGui gui;
    
    public LoadingPane(BattleshipGui gui) {
        this.gui = gui;
    }
    
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
