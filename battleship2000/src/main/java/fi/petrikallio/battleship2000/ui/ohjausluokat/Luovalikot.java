
package fi.petrikallio.battleship2000.ui.ohjausluokat;

import fi.petrikallio.battleship2000.ui.pages.TitlePage;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class Luovalikot {
    private Container container;
    
    public Luovalikot(Container container) {
        this.container = container;
    }

    public void suorita() {
        LayoutManager cardLayout = new CardLayout();
        this.container.setLayout(cardLayout);
        
        JPanel titlepage = new TitlePage().getTitlePage();
    }
}
