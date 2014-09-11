
package battleship2000.ui.control;

import battleship2000.ui.panels.TitlePage;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class CreateMenus {
    private Container container;
    
    public CreateMenus(Container container) {
        this.container = container;
    }

    public void suorita() {
        LayoutManager cardLayout = new CardLayout();
        this.container.setLayout(cardLayout);
        
        JPanel titlepage = new TitlePage().getTitlePage();
    }
}
