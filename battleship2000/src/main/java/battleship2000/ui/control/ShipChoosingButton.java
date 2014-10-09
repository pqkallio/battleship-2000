
package battleship2000.ui.control;

import battleship2000.programlogic.domain.ship.ShipType;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class ShipChoosingButton extends JPanel {
    private ShipType shipType;
    private final int BUTTON_WIDTH = 30;
    private final int BUTTON_HEIGHT = 20;

    public ShipChoosingButton(ShipType shipType) {
        this.shipType = shipType;
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        g2d.setColor(Color.YELLOW);
        g2d.fillRoundRect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT, 5, 5);
        
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(3.0f));
        g2d.drawRoundRect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT, 5, 5);
    }

    public Class getShipClass() {
        return shipType.getShipClass();
    }
}
