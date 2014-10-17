package battleship2000.ui.panes;

import battleship2000.programlogic.domain.player.Computer;
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
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A visual representation of a single square that a game table consists of.
 *
 * @author Petri Kallio
 */
public class VisualSquare extends JPanel {
    private VisualGameTable gameTable;
    private boolean reddened;
    private boolean gray;
    private Square square;
    private boolean highlighted;
    private boolean destroyed;
    private Image sea;
    private int squareWidth;
    
    /**
     * Constructs a new instantiation of the class.
     * 
     * @param gameTable     the table the visual square is a part of
     * @param square        the square the visual square represents
     * @param squareWidth   the visual square's width and height in pixels
     */
    public VisualSquare(VisualGameTable gameTable, Square square, int squareWidth) {
        this.gameTable = gameTable;
        this.square = square;
        this.highlighted = false;
        this.reddened = false;
        this.destroyed = false;
        this.squareWidth = squareWidth;
        
        try {
            InputStream is = this.getClass().getResourceAsStream("/graphics/sea_25.png");
            this.sea = ImageIO.read(is);
        } catch (NullPointerException|IOException ex) {
            gameTable.alertException("Unable to load graphic content", ex);
        }
    }

    public int getSquareWidth() {
        return gameTable.getSquareWidth();
    }
    
    public VisualGameTable getVisualGameTable() {
        return gameTable;
    }
    
    public Position getPosition() {
        return new Position(square.getX(), square.getY());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2d);
        
        drawBackground(g2d);

        if (gameTable.getPlayer().getClass() == Human.class 
                || gameTable.getPlayer().getClass() == Computer.class
                || square.isHit()) {
            
            if (square.getSetShipPart() != null) {
                if (gameTable.getPlayer() == gameTable.getGameCommands().getGame().getHuman()) {
                    g2d.drawImage(gameTable.getVisualShipParts().get(square.getSetShipPart().getMotherShip().getDirection()).getSetPiece(square.getSetShipPart()), 0, 0, null);
                }
                
                if (destroyed) {
                    g2d.drawImage(gameTable.getVisualShipParts().get(square.getSetShipPart().getMotherShip().getDirection()).getRedPiece(square.getSetShipPart()), 0, 0, null);
                } else if (square.isHit()) {
                    if (square.getSetShipPart().getMotherShip().isDestroyed()) {
                        Ship destroyedShip = gameTable.getPlayer().getShips().get(gameTable.getPlayer().getShips().indexOf(square.getSetShipPart().getMotherShip()));
                        for (ShipPart sp : destroyedShip.getParts()) {
                            for (VisualSquare vs : gameTable.getSquares()) {
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
                g2d.drawImage(gameTable.getVisualShipParts().get(square.getFloatingPiece().getMotherShip().getDirection()).getRedPiece(square.getFloatingPiece()), 0, 0, null);
            } else if (gray) {
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.7f));
                g2d.drawImage(gameTable.getVisualShipParts().get(square.getFloatingPiece().getMotherShip().getDirection()).getGrayPiece(square.getFloatingPiece()), 0, 0, null);
            }
        }
        
        if (highlighted) {
            if (!square.isHit() || gameTable.getGameCommands().getGame().aSquareCanBeHitMultipleTimes()) {
                g2d.setColor(Color.YELLOW);
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                g2d.fillRect(0, 0, 24, 24);
            } else {
                g2d.setColor(Color.RED);
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                g2d.fillRect(0, 0, 24, 24);
            }
        }
        
        drawFrame(g2d);
    }

    /**
     * Highlights the visual square.
     */
    public void highlight() {
        this.highlighted = true;
        this.reddened = false;
        repaint();
    }

    /**
     * Removes the highlight of the visual square.
     */
    public void removeHighlight() {
        this.highlighted = false;
        this.reddened = false;
        this.gray = false;
        repaint();
    }

    public Square getSquare() {
        return square;
    }

    /**
     * Makes the visual square red.
     */
    public void redden() {
        this.highlighted = false;
        this.reddened = true;
        repaint();
    }
    
    /**
     * Makes the visual square grey.
     */
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

    private void drawFrame(Graphics g) {
        g.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        
        if (this.square.getX() == 0) {
            g.fillRect(0, 0, 2, this.squareWidth);
        }
        
        if (this.square.getY() == 0) {
            g.fillRect(0, 0, this.squareWidth, 2);
        }
        
        if (this.square.getX() == gameTable.getPlayer().getTable().getWidth() - 1) {
            g.fillRect(this.squareWidth - 2, 0, 2, this.squareWidth);
        }
        
        if (this.square.getY() == gameTable.getPlayer().getTable().getHeight() - 1) {
            g.fillRect(0, this.squareWidth - 2, this.squareWidth, 2);
        }
    }

    private void drawBackground(Graphics g) {
        g.drawImage(this.sea, 0, 0, null);
        
        g.setColor(new Color(0.2f, 0.2f, 0.2f, 0.35f));
        g.drawRect(0, 0, squareWidth - 1, squareWidth - 1);
    }

}
