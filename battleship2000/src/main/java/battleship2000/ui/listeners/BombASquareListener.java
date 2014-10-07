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
    private VisualGameTable playersTable;
    private boolean isAlreadyHit;
    
    public BombASquareListener(VisualSquare vs, GamePane gamePane, VisualGameTable playersTable) {
        this.vs = vs;
        this.gamePane = gamePane;
        this.playersTable = playersTable;
        this.isAlreadyHit = false;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            handleShit();
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
            vs.setCursor(vs.getPartOf().getGrayCrosshair());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        vs.removeHighlight();
    }

    private void handleShit() {
        if (!vs.getSquare().isHit() && !this.isAlreadyHit) {
            this.isAlreadyHit = true;
            this.gamePane.getGameCommmands().playOneRound(vs.getSquare());
        }
    }
}
