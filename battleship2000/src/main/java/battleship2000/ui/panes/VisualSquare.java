package battleship2000.ui.panes;

import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.table.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * A visual representation of a single square that a game table consists of.
 *
 * @author Petri Kallio
 */
public class VisualSquare extends JPanel {
    private VisualGameTable partOf;
    private boolean reddened;
    private boolean gray;
    private Square square;
    private boolean highlighted;
    private boolean destroyed;
    
    public VisualSquare(VisualGameTable partOf, Square square, int squareWidth) {
        this.partOf = partOf;
        this.square = square;
        this.highlighted = false;
        this.reddened = false;
        this.destroyed = false;
    }

    public int getSquareWidth() {
        return partOf.getSquareWidth();
    }
    
    public VisualGameTable getPartOf() {
        return partOf;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2d);
        
        g2d.setColor(Color.black);
        g2d.drawRect(0, 0, getSquareWidth() - 1, getSquareWidth() - 1);
        
        if (partOf.getPlayer().getClass() == Human.class
                || square.isHit()) {
            if (reddened) {
                g2d.setColor(Color.red);
                g2d.fillRect(0, 0, getSquareWidth() - 1, getSquareWidth() - 1);
            } else if (gray) {
                g2d.setColor(Color.gray);
                g2d.fillRect(0, 0, getSquareWidth() - 1, getSquareWidth() - 1);
            } 

            if (square.getShipPart() != null) {
                g2d.fillRect(0, 0, getSquareWidth() - 1, getSquareWidth() - 1);
                if (destroyed) {
                    g2d.setColor(Color.red);
                    g2d.fillRect(0, 0, getSquareWidth() - 1, getSquareWidth() - 1);
                } else if (square.isHit()) {
                    if (square.getShipPart().getMotherShip().isDestroyed()) {
                        Ship destroyedShip = partOf.getPlayer().getShips().get(partOf.getPlayer().getShips().indexOf(square.getShipPart().getMotherShip()));
                        for (ShipPart sp : destroyedShip.getParts()) {
                            for (VisualSquare vs : partOf.getSquares()) {
                                if (vs.getSquare().getShipPart() == sp) {
                                    vs.setDestruction(true);
                                    vs.repaint();
                                }
                            }
                        }
                        setDestruction(true);
                        repaint();
                    } else {
                        g2d.setColor(Color.red);
                        g2d.fillOval(6, 6, 12, 12);
                    }
                }
            } else {
                if (square.isHit()) {
                    g2d.drawLine(0, 0, getSquareWidth() - 1, getSquareWidth() - 1);
                    g2d.drawLine(0, getSquareWidth() - 1, getSquareWidth() - 1, 0);
                }
            }
        }
    }

    public void highlight() {
        this.highlighted = true;
        this.reddened = false;
        repaint();
    }

    public void removeHighlight() {
        this.highlighted = false;
        this.reddened = false;
        this.gray = false;
        repaint();
    }

    public Square getSquare() {
        return square;
    }

    public void redden() {
        this.highlighted = false;
        this.reddened = true;
        repaint();
    }
    
    public void makeGray() {
        this.gray = true;
        this.reddened = false;
        repaint();
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public boolean isGray() {
        return gray;
    }

    public boolean isReddened() {
        return reddened;
    }

    private void setDestruction(boolean destruction) {
        destroyed = destruction;
    }
}
