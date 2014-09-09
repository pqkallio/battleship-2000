
package fi.petrikallio.battleship2000.ui.pages;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class TitlePage {

    public TitlePage() {
    }

    public JPanel getTitlePage() {
        JPanel titlePanel = new JPanel();
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        titlePanel.setLayout(layout);
        return titlePanel;
    }
    
}
