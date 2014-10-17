
package battleship2000.ui.listeners;

import battleship2000.ui.panes.CardNames;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * An action listener that triggers the return to the gui's title pane.
 * 
 * @author Petri Kallio
 */
public class EndOfGameListener implements ActionListener {
    private JPanel cards;
    
    /**
     * Constructs a new instance of the class.
     * 
     * @param cards     the Panel that contains all the gui's different panes
     */
    public EndOfGameListener(JPanel cards) {
        this.cards = cards;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CardLayout layout = (CardLayout)this.cards.getLayout();
        layout.show(cards, CardNames.TITLE.toString());
    }
}
