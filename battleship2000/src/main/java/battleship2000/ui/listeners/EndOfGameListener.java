
package battleship2000.ui.listeners;

import battleship2000.programlogic.GameCommands;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class EndOfGameListener implements ActionListener {
    private GameCommands gc;
    private JButton playAgain;
    private JButton exit;
    
    public EndOfGameListener(GameCommands gc, JButton playAgain, JButton exit) {
        this.gc = gc;
        this.playAgain = playAgain;
        this.exit = exit;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == playAgain) {
            gc.createANewGame();
        } else if (ae.getSource() == exit) {
            
        }
    }
}
