
package battleship2000.ui.listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class StartNewGameListener implements ActionListener {
    private JPanel cards;
    
    public StartNewGameListener(JPanel cards) {
        this.cards = cards;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CardLayout layout = (CardLayout)this.cards.getLayout();
        layout.show(cards, "GAME");
    }
    
}
