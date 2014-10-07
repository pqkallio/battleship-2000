
package battleship2000.ui.domain;

import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.ShipPart;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class VisualShipPartPack {
    private Direction direction;
    private String size;
    private Map<ShipPartPlace, String> fileNames;
    private Map<ShipPartPlace, BufferedImage> images;
    private final String PATH = "src/main/java/battleship2000/media/graphics/";
    private final String FILE_HEAD = "ship_";
    private final String FRONT = ShipPartPlace.FRONT.getFileIndicator();
    private final String MIDDLE = ShipPartPlace.MIDDLE.getFileIndicator();
    private final String REAR = ShipPartPlace.REAR.getFileIndicator();
    private final String EXTENSION = ".png";
    private final Color RED = new Color(255, 0, 0);
    private final Color GREEN = new Color(0, 255, 0);
    private Map<Direction, String> directionIndicators;
    
    public VisualShipPartPack(Direction direction, int size) {
        this.direction = direction;
        this.size = "" + size;
        this.fileNames = new HashMap<>();
        this.directionIndicators = new HashMap<>();
        this.images = new HashMap<>();
        
        addDirectionIndicators();
        addFileNames();
        addImages();
    }

    private void addDirectionIndicators() {
        this.directionIndicators.put(Direction.EAST, "east_");
        this.directionIndicators.put(Direction.SOUTH, "south_");
        this.directionIndicators.put(Direction.WEST, "west_");
        this.directionIndicators.put(Direction.NORTH, "north_");
    }

    private void addFileNames() {
        String front = PATH + FILE_HEAD + FRONT + directionIndicators.get(direction)
                + size + EXTENSION;
        String middle = PATH + FILE_HEAD + MIDDLE + directionIndicators.get(direction)
                + size + EXTENSION;
        String rear = PATH + FILE_HEAD + REAR + directionIndicators.get(direction)
                + size + EXTENSION;
        
        fileNames.put(ShipPartPlace.FRONT, front);
        fileNames.put(ShipPartPlace.MIDDLE, middle);
        fileNames.put(ShipPartPlace.REAR, rear);
    }

    public BufferedImage getSetPiece(ShipPart part) {
        if (part.isShipsFront()) return recoloredImage(images.get(ShipPartPlace.FRONT), Color.DARK_GRAY);
        else if (part.isShipsRear()) return recoloredImage(images.get(ShipPartPlace.REAR), Color.DARK_GRAY);
        else return recoloredImage(images.get(ShipPartPlace.MIDDLE), Color.DARK_GRAY);
    }
    
    public BufferedImage getGrayPiece(ShipPart part) {
        if (part.isShipsFront()) return recoloredImage(images.get(ShipPartPlace.FRONT), GREEN);
        else if (part.isShipsRear()) return recoloredImage(images.get(ShipPartPlace.REAR), GREEN);
        else return recoloredImage(images.get(ShipPartPlace.MIDDLE), GREEN);
    }
    
    public BufferedImage getRedPiece(ShipPart part) {
        if (part.isShipsFront()) return recoloredImage(images.get(ShipPartPlace.FRONT), RED);
        else if (part.isShipsRear()) return recoloredImage(images.get(ShipPartPlace.REAR), RED);
        else return recoloredImage(images.get(ShipPartPlace.MIDDLE), RED);
    }
    
    public BufferedImage getFront() {
        return images.get(ShipPartPlace.FRONT);
    }
    
    public BufferedImage getMiddle() {
        return images.get(ShipPartPlace.MIDDLE);
    }
    
    public BufferedImage getRear() {
        return images.get(ShipPartPlace.REAR);
    }

    private void addImages() {
        BufferedImage front = null; BufferedImage middle = null; BufferedImage rear = null;
        
        try {
            front = ImageIO.read(new File(fileNames.get(ShipPartPlace.FRONT)));
            rear = ImageIO.read(new File(fileNames.get(ShipPartPlace.REAR)));
            middle = ImageIO.read(new File(fileNames.get(ShipPartPlace.MIDDLE)));
        } catch (IOException ex) {
            System.out.println("No no! " + ex.toString());
        }
        
        images.put(ShipPartPlace.REAR, rear);
        images.put(ShipPartPlace.MIDDLE, middle);
        images.put(ShipPartPlace.FRONT, front);
    }
    
    private BufferedImage recoloredImage(BufferedImage image, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixels = raster.getPixel(x, y, (int[]) null);
                pixels[0] = color.getRed();
                pixels[1] = color.getGreen();
                pixels[2] = color.getBlue();
                raster.setPixel(x, y, pixels);
            }
        }
        
        return image;
    }
}
