package battleship2000.ui.panes;

import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.table.Square;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
    private boolean aimedAt;
    private Image sea;
    private Image crosshair; 
    
    public VisualSquare(VisualGameTable partOf, Square square, int squareWidth) {
        this.partOf = partOf;
        this.square = square;
        this.highlighted = false;
        this.reddened = false;
        this.destroyed = false;
        this.aimedAt = false;
        
        try { 
           this.sea = ImageIO.read(new File("src/main/java/battleship2000/media/graphics/sea_25.png"));
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public int getSquareWidth() {
        return partOf.getSquareWidth();
    }
    
    public VisualGameTable getPartOf() {
        return partOf;
    }
    public Position getPosition() {
        return new Position(square.getX(), square.getY());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2d);
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawImage(this.sea, 0, 0, null);
        
        if (partOf.getPlayer().getClass() == Human.class
                || square.isHit()) {
            
            if (square.getSetShipPart() != null) {
                if (partOf.getPlayer() == partOf.getGameCommands().getGame().getHuman()) {
                    g2d.drawImage(partOf.getVisualShipParts().get(square.getSetShipPart().getMotherShip().getDirection()).getSetPiece(square.getSetShipPart()), 0, 0, null);
                }
                
                if (destroyed) {
                    g2d.drawImage(partOf.getVisualShipParts().get(square.getSetShipPart().getMotherShip().getDirection()).getRedPiece(square.getSetShipPart()), 0, 0, null);
                } else if (square.isHit()) {
                    if (square.getSetShipPart().getMotherShip().isDestroyed()) {
                        Ship destroyedShip = partOf.getPlayer().getShips().get(partOf.getPlayer().getShips().indexOf(square.getSetShipPart().getMotherShip()));
                        for (ShipPart sp : destroyedShip.getParts()) {
                            for (VisualSquare vs : partOf.getSquares()) {
                                if (vs.getSquare().getSetShipPart() == sp) {
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
                    g2d.setColor(Color.black);
                    g2d.fillOval(6, 6, 12, 12);
                }
            }
            
            if (reddened) {
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.7f));
                g2d.drawImage(partOf.getVisualShipParts().get(square.getFloatingPiece().getMotherShip().getDirection()).getRedPiece(square.getFloatingPiece()), 0, 0, null);
            } else if (gray) {
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.7f));
                g2d.drawImage(partOf.getVisualShipParts().get(square.getFloatingPiece().getMotherShip().getDirection()).getGrayPiece(square.getFloatingPiece()), 0, 0, null);
            }
        }
        
        if (highlighted) {
            if (!square.isHit() || partOf.getGameCommands().getGame().aSquareCanBeHitMultipleTimes()) {
                g2d.setColor(Color.YELLOW);
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                g2d.fillRect(0, 0, 24, 24);
            } else {
                g2d.setColor(Color.RED);
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                g2d.fillRect(0, 0, 24, 24);
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
