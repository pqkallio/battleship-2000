
package battleship2000.ui.panes;

import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.table.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class TableSquare extends JPanel {
    private GraphicGameTable partOf;
    private boolean reddened;
    private boolean gray;
    private Square square;
    private boolean highlighted;
    
    public TableSquare(GraphicGameTable partOf, Square square) {
        this.partOf = partOf;
        this.square = square;
        this.highlighted = false;
        this.reddened = false;
    }

    public GraphicGameTable getPartOf() {
        return partOf;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        
        g2d.setColor(Color.black);
        g2d.drawRect(0, 0, 24, 24);
        
        if (reddened) {
            g2d.setColor(Color.red);
            g2d.fillRect(0, 0, 25, 25);
        } else if (gray) {
            g2d.setColor(Color.gray);
            g2d.fillRect(0, 0, 25, 25);
        } 
        
        if (square.getShipPart() != null) {
            g2d.fillRect(0, 0, 25, 25);
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
}
