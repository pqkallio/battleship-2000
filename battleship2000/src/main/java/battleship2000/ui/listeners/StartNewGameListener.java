
package battleship2000.ui.listeners;

import battleship2000.programlogic.GameCommands;
import battleship2000.ui.BattleshipGui;
import battleship2000.ui.panes.CardNames;
import battleship2000.ui.panes.GamePane;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * A listener that triggers the beginning of a new game.
 * 
 * @author Petri Kallio
 */
public class StartNewGameListener implements ActionListener {
    private JPanel cards;
    private GameCommands gc;
    private BattleshipGui gui;
    
    /**
     * Constructs a new instance of the class.
     * 
     * @param cards     the gui's main JPanel that contains all the different panes of the game
     * @param gc        the game commands object of the game
     * @param gui       the gui the listener is related to
     */
    public StartNewGameListener(JPanel cards, GameCommands gc, BattleshipGui gui) {
        this.cards = cards;
        this.gc = gc;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        gui.getAudioContent().getKlikOrKlok().play();
        CardLayout layout = (CardLayout)this.cards.getLayout();
        layout.show(cards, CardNames.LOAD.toString());
        cards.repaint();
        gc.createANewGame();
        gc.getGame().addObserver(gui.getAudioContent());
        GamePane gamePane = new GamePane(gc, gui.getSQUARE_WIDTH(), gui);
        cards.add(gamePane, CardNames.GAME.toString());
        layout.show(cards, CardNames.GAME.toString());
    }   
}