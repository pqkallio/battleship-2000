
package battleship2000.ui.panes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class TitlePane extends Pane{

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
    public void update(Object object) {
        
    }
    
}