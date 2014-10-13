
package battleship2000.ui;

import battleship2000.programlogic.domain.ship.Ship;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class GraphicContentPack {
    private Map<Integer, Image> shipChooserButtonIcons;
    public BattleshipGui gui;
    
    public GraphicContentPack(BattleshipGui gui) {
        this.shipChooserButtonIcons = new HashMap<>();
        this.gui = gui;
        
        loadshipChooserButtonImages();
    }

    private void loadshipChooserButtonImages() {
        for (int i = 2; i < 6; i++) {
            String path = "/graphics/" + i + "_piece.png";
            Image image = loadAShipChooserImage(path);
            
            shipChooserButtonIcons.put(i, image);
        }
    }

    private Image loadAShipChooserImage(String path) {
        Image image = null;
        
        try {
            InputStream is = this.getClass().getResourceAsStream(path);
            image = ImageIO.read(is);
        } catch (IOException|NullPointerException ex) {
            gui.alertException("Unable to load graphics", ex);
        }
        
        return image;
    }
    
    public Image getShipChooserImage(Ship ship) {
        return shipChooserButtonIcons.get(ship.getShipLength());
    }
}
