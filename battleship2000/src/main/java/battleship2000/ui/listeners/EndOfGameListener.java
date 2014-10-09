
package battleship2000.ui.listeners;

import battleship2000.ui.panes.CardNames;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class EndOfGameListener implements ActionListener {
    private JPanel cards;
    
    public EndOfGameListener(JPanel cards) {
        this.cards = cards;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CardLayout layout = (CardLayout)this.cards.getLayout();
        layout.show(cards, CardNames.TITLE.toString());
    }
}
