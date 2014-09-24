
package battleship2000.ui.listeners;

import battleship2000.programlogic.domain.player.Computer;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.ui.panes.VisualGameTable;
import battleship2000.ui.panes.VisualSquare;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BombThatMofoingSquareListener implements MouseListener {
    private VisualSquare vs;
    private VisualGameTable playersTable;
    private boolean isAlreadyHit;
    
    public BombThatMofoingSquareListener(VisualSquare vs, VisualGameTable playersTable) {
        this.vs = vs;
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
            vs.getSquare().bomb();
            this.isAlreadyHit = false;
            vs.repaint();
            if (vs.getPartOf().getPlayer().allShipsDestroyed()) {
                System.out.println("HUMAN BEATS THE MACHINE!!!");
                return;
            }
            
            computerMakeYourMove();
            
            if (vs.getPartOf().getGameCommands().getGame().getPlayers().get(0).allShipsDestroyed()) {
                System.out.println("MACHINE BEATS THE HUMAN!!!");
            }
        }
    }
    
}
