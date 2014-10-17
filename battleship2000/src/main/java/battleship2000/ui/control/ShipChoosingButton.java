
package battleship2000.ui.control;

import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.ui.BattleshipGui;
import battleship2000.ui.graphics.GraphicContentPack;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * The class inherits the {@link javax.swing.JPanel} class and is used to create
 * a panel that works as a button to choose a {@link battleship2000.programlogic.domain.ship.Ship}
 * to place on a {@link battleship2000.programlogic.domain.table.GameTable}
 * <p>
 * <strong>Note!</strong> Not yet in use in this version of the game.
 * 
 * @author Petri Kallio
 */
public class ShipChoosingButton extends JPanel {
    private Ship ship;
    private Image image; 
    private final int BUTTON_WIDTH = 50;
    private final int BUTTON_HEIGHT = 30;

    /**
     * Constructs an instantiation of the class.
     * 
     * @param ship  the ship the ShipChoosingButton chooses
     * @param gui   the {@link battleship2000.ui.BattleshipGui} the ShipChoosingButton is related to
     */
    public ShipChoosingButton(Ship ship, BattleshipGui gui) {
        this.ship = ship;
        this.image = loadImage(gui.getGraphicContent());
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        g2d.setColor(Color.YELLOW);
        g2d.fillRoundRect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT, 10, 10);
        
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(3.0f));
        g2d.drawRoundRect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT, 10, 10);
        
        g2d.drawImage(image, 0, 0, null);
    }

    private Image loadImage(GraphicContentPack gcp) {
        return gcp.getShipChooserImage(ship);
    }
}
