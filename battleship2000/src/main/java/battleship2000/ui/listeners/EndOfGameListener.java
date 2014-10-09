
package battleship2000.ui.listeners;

import battleship2000.programlogic.GameCommands;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EndOfGameListener implements ActionListener {
    private GameCommands gc;
    private JButton playAgain;
    private JButton exit;
    private JPanel cards;
    
    public EndOfGameListener(GameCommands gc, JButton playAgain, JButton exit, JPanel cards) {
        this.gc = gc;
        this.playAgain = playAgain;
        this.exit = exit;
        this.cards = cards;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == playAgain) {
            gc.createANewGame();
        } else if (ae.getSource() == exit) {
            CardLayout layout = (CardLayout)this.cards.getLayout();
            layout.show(cards, "TITLE");
        }
    }
}
