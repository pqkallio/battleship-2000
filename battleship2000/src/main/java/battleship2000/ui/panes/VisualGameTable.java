package battleship2000.ui.panes;

import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.ui.control.PaneAdministrator;
import battleship2000.ui.domain.VisualShipPartPack;
import battleship2000.ui.listeners.ShipPlacementListener;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A visual representation of a player's game table and its contents.
 *
 * @author Petri Kallio
 */
public class VisualGameTable extends JPanel {
    private Player player;
    private List<VisualSquare> squares;
    private GameCommands gameCommands;
    private Ship chosenShip;
    private GamePane gamePane;
    private Cursor grayCrosshair;
    private Table playersTable;

    /**
     * Constructs a new instantiation of the class.
     * 
     * @param player        the player that owns this table
     * @param gameCommands  the GameCommands object of the game
     * @param gamePane      the game pane of the game
     */
    public VisualGameTable(Player player, GameCommands gameCommands, GamePane gamePane) {
        this.player = player;
        this.playersTable = player.getTable();
        this.squares = new ArrayList<>();
        this.gameCommands = gameCommands;
        this.gamePane = gamePane;
        this.grayCrosshair = createGrayCrosshair();
        setElements();
    }

    public Cursor getGrayCrosshair() {
        return grayCrosshair;
    }
    
    public GamePane getGamePane() {
        return gamePane;
    }
    
    public int getSquareWidth() {
        return gamePane.getSquareWidth();
    }
    
    public Ship getChosenShip() {
        return chosenShip;
    }

    public void setChosenShip(Ship chosenShip) {
        this.chosenShip = chosenShip;
    }

    public GameCommands getGameCommands() {
        return gameCommands;
    }
    
    public List<VisualSquare> getSquares() {
        return squares;
    }

    public Player getPlayer() {
        return player;
    }
    
    private void setElements() {
        GridBagLayout layout = createLayout();
        PaneAdministrator administrator = new PaneAdministrator(this, layout);
        
        for (int i = 0; i < layout.rowHeights.length; i++) {
            for (int j = 0; j < layout.columnWidths.length; j++) {
                VisualSquare square = new VisualSquare(this, 
                        playersTable.getTableAsMap().get(i).get(j), getSquareWidth());
                
                if (this.player == gameCommands.getGame().getHuman()) {
                    square.addMouseListener(new ShipPlacementListener(square, gameCommands));
                }
                
                administrator.addComponent(square, j, i, 
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH);
                
                this.squares.add(square);
            }
        }
    }
    
    /**
     * Repaints all the VisualGameTable's {@link battleship2000.ui.panes.VisualSquare}s.
     */
    public void repaintAll() {
        for (VisualSquare visualSquare : squares) {
            visualSquare.repaint();
        }
    }
    
    /**
     * Repaints the {@link battleship2000.ui.panes.VisualSquare}s whose positions
     * are the same that are given as parameter.
     * 
     * @param squarePositions   a list of positions on the game table
     */
    public void repaintSquares(List<Position> squarePositions) {
        List<VisualSquare> squaresToRepaint = new ArrayList<>();
        
        for (Position position : squarePositions) {
            for (VisualSquare visualSquare : this.squares) {
                if (position.equals(visualSquare.getPosition())) {
                    squaresToRepaint.add(visualSquare);
                }
            }
        }
        
        for (VisualSquare visualSquare : squaresToRepaint) {
            visualSquare.repaint();
        }
    }
    
    public void setOkToBeginGame(boolean isOk) {
        this.gamePane.setAllShipsSetEnabled(isOk);
    }
    
    public Map<Direction, VisualShipPartPack> getVisualShipParts() {
        return gamePane.getVisualShipParts();
    }

    private Cursor createGrayCrosshair() {
        Image crosshair = null;
        
        try {
            InputStream is = this.getClass().getResourceAsStream(("/graphics/crosshair_gray_25.png"));
            crosshair = ImageIO.read(is);
        } catch (NullPointerException|IOException ex) {
            gamePane.getGui().alertException("Unable to load graphics", ex);
        }
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Point focus = new Point(crosshair.getWidth(null) / 2, crosshair.getHeight(null) / 2);
        Cursor cursor = toolkit.createCustomCursor(crosshair, focus, "Gray Crosshair");
        
        return cursor;
    }

    public void alertException(String message, Exception ex) {
        gamePane.alertException(message, ex);
    }

    private GridBagLayout createLayout() {
        GridBagLayout layout = new GridBagLayout();
        
        int[] layoutWidths = new int[playersTable.getWidth()];
        int[] layoutHeights = new int[playersTable.getHeight()];
        
        for (int i = 0; i < layoutWidths.length; i++) {
            layoutWidths[i] = 25;
        }
        
        for (int i = 0; i < layoutHeights.length; i++) {
            layoutHeights[i] = 25;
        }
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        return layout;
    }
}
