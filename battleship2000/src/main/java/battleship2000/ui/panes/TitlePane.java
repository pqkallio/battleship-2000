package battleship2000.ui.panes;

import battleship2000.programlogic.StateChange;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * The game's graphical user interface's title pane. 
 *
 * @author Petri Kallio
 */
public class TitlePane extends Pane {

    public TitlePane() {
    }

    public JPanel getTitlePage() {
        JPanel titlePanel = new JPanel();
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        titlePanel.setLayout(layout);
        return titlePanel;
    }

    @Override
    public void update(StateChange stateChange, Object... object) {
        
    }

    @Override
    public boolean isUpdated() {
        return true;
    }
}
