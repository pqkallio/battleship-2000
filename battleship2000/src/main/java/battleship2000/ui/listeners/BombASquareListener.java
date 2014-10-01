package battleship2000.ui.listeners;

import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.domain.player.Computer;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.table.Square;
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
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private void computerMakeYourMove() {
        Human human = (Human) vs.getPartOf().getGameCommands().getGame().getPlayers().get(0);
        Computer computer = (Computer) vs.getPartOf().getPlayer();
        Square squareToHit = computer.chooseASquare(human.getTable(), vs.getPartOf().getGameCommands().getGame().aSquareCanBeHitMultipleTimes());
        for (VisualSquare square : playersTable.getSquares()) {
            if (square.getSquare() == squareToHit) {
                squareToHit.bomb();
                square.repaint();
            }
        }
    }

    private void handleShit() {
        if (!vs.getSquare().isHit() && !this.isAlreadyHit) {
            this.isAlreadyHit = true;
            this.gamePane.getGameCommmands().playOneRound(vs.getSquare());
        }
    }
}
