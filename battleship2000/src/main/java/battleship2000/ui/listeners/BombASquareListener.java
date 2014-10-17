package battleship2000.ui.listeners;

import battleship2000.ui.panes.GamePane;
import battleship2000.ui.panes.VisualGameTable;
import battleship2000.ui.panes.VisualSquare;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * An action listener added to all computer player's visual game table's
 * visual square's to handle bombing actions.
 *
 * @author Petri Kallio
 */
public class BombASquareListener implements MouseListener {
    private VisualSquare vs;
    private GamePane gamePane;
    private boolean isAlreadyHit;
    
    /**
     * Constructs a new instance of the class.
     * 
     * @param vs            the visual square the listener is added to
     * @param gamePane      the gui's game pane
     * @param playersTable  the user's visual game table
     */
    public BombASquareListener(VisualSquare vs, GamePane gamePane, VisualGameTable playersTable) {
        this.vs = vs;
        this.gamePane = gamePane;
        this.isAlreadyHit = false;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            chooseSquare();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        vs.highlight();
        if (vs.getSquare().isHit()) {
            vs.setCursor(vs.getVisualGameTable().getGrayCrosshair());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        vs.removeHighlight();
    }

    private void chooseSquare() {
        if (!vs.getSquare().isHit() && !this.isAlreadyHit) {
            this.isAlreadyHit = true;
            this.gamePane.getGameCommands().playOneRound(vs.getSquare());
        }
    }
}
