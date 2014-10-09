package battleship2000.ui.panes;

import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.StateChange;
import battleship2000.ui.BattleshipGui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * The game's graphical user interface's title pane. 
 *
 * @author Petri Kallio
 */
public class TitlePane extends JPanel {

    public TitlePane(BattleshipGui gui, GameCommands gameCommands) {
    }

    public JPanel getTitlePage() {
        JPanel titlePanel = new JPanel();
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        titlePanel.setLayout(layout);
        return titlePanel;
    }
}
