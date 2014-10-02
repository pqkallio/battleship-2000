
package battleship2000.ui.panes;

import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.ShipPart;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class VisualShipPart {
    private Map<Direction, Map<String, String>> picPaths;
    private final String FRONT = "FRONT";
    private final String MIDDLE = "MIDDLE";
    private final String REAR = "REAR";
    private final String PATH = "src/battleship2000/media/graphics/";
    private final String FILE_HEAD = "ship_";
    private final String NORTH = "north_";
    
    public VisualShipPart() {
        this.picPaths = new HashMap<>();
        
        addImagePaths();
    }
    
    public Image getPart(ShipPart part) {
        Image visualShipPart;
        
        try {
            if (part.isShipsFront()) {
                visualShipPart = ImageIO.read(new File(FRONT));
            } else if (part.isShipsRear()) {
                visualShipPart = ImageIO.read(new File(REAR));
            } else {
                visualShipPart = ImageIO.read(new File(MIDDLE));
            }
        } catch (IOException ex) {
            visualShipPart = null;
        }
        
        return visualShipPart;
    }

    private void addImagePaths() {
        for (Direction direction : Direction.EAST.getMainDirections()) {
            picPaths.put(direction, new HashMap<String, String>());
        }
        
//        picPaths.get(Direction.NORTH).put(FRONT, )
    }
}
