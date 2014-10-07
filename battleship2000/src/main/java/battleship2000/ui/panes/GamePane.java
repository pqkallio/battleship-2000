package battleship2000.ui.panes;

import battleship2000.ui.listeners.EndOfGameListener;
import battleship2000.ui.listeners.AllShipsAreSetListener;
import battleship2000.ui.listeners.ShipChooserListener;
import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.StateChange;
import battleship2000.programlogic.domain.player.Computer;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.observers.LogicObserver;
import battleship2000.ui.domain.VisualShipPartPack;
import battleship2000.ui.listeners.BombASquareListener;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This pane contains the graphical game environment. It consists of both players'
 * visual game tables and of all the necessary controllers to control the game.
 *
 * @author Petri Kallio
 */
public class GamePane extends JPanel implements LogicObserver  {
    private GameCommands gc;
    private int squareWidth;
    private GridBagLayout mainLayout;
    private GridBagConstraints mainConstraints;
    private JButton allShipsSet;
    private List<JButton> shipChoosingButtons;
    private VisualGameTable playersSide;
    private VisualGameTable computersSide;
    private JLabel humansPoints;
    private JLabel computersPoints;
    private JLabel humanAccuracy;
    private JLabel computerAccuracy;
    private JPanel shipChooserArea;
    private final String POINTS = "Points: ";
    private final String ACCURACY = "Accuracy: ";
    private final String CROSSHAIR_PATH = "src/main/java/battleship2000/media/graphics/crosshair_25.png";
    private boolean updated;
    private Cursor crosshairCursor;
    private Map<Direction, VisualShipPartPack> visualShipParts;
    
    public GamePane(GameCommands gameCommands, int squareWidth) {
        this.gc = gameCommands;
        this.squareWidth = squareWidth;
        createGameLayout();
        this.gc.getGame().addObserver(this);
        this.updated = false;
        this.visualShipParts = new HashMap<>();
        this.crosshairCursor = loadCrosshair();
        
        loadVisualShipParts();
    }

    public int getSquareWidth() {
        return squareWidth;
    }
    
    private void createGameLayout() {
        mainLayout = new GridBagLayout();
        mainConstraints = new GridBagConstraints();
        this.setLayout(mainLayout);
        
        Player human = gc.getGame().getHuman();
        Player computer = gc.getGame().getComputer();
        
        int[] layoutWidths = {human.getTable().getWidth() * squareWidth + 50 + computer.getTable().getWidth() * squareWidth};
        int[] layoutHeights = {100, 25, human.getTable().getHeight() * squareWidth, 25, 50};
        
        mainLayout.columnWidths = layoutWidths;
        mainLayout.rowHeights = layoutHeights;
        
        JPanel header = createHeader();
        
        mainConstraints.gridx = 0; mainConstraints.gridy = 0;
        mainLayout.setConstraints(header, mainConstraints);
        this.add(header);
        
        JPanel createTables = createTables();
        
        mainConstraints.gridy = 2;
        mainLayout.setConstraints(createTables, mainConstraints);
        this.add(createTables);
        
        this.shipChooserArea = createShipChooser(playersSide, computersSide);
        
        mainConstraints.gridy = 4;
        mainLayout.addLayoutComponent(shipChooserArea, mainConstraints);
        this.add(shipChooserArea);
    }

    public GameCommands getGameCommmands() {
        return gc;
    }

    private JPanel createShipChooser(VisualGameTable playersSide, VisualGameTable foesSide) {
        JPanel shipChoosingButtonArea = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        shipChoosingButtonArea.setLayout(layout);
        
        int[] layoutWidths = {50, 50, 50, 50, 50, 50, gc.getGame().getComputer().getTable().getWidth() * squareWidth};
        int[] layoutHeights = {25, 25};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        JButton commander = new JButton("Com");
        JButton cannonShip = new JButton("Can");
        JButton submarine = new JButton("Sub");
        JButton missileShip = new JButton("Mis");
        JButton aircarrier = new JButton("Air");
        
        ActionListener shipChooserListener = new ShipChooserListener(gc, 
                playersSide, commander, cannonShip, submarine, missileShip, aircarrier);
        
        commander.addActionListener(shipChooserListener);
        cannonShip.addActionListener(shipChooserListener);
        submarine.addActionListener(shipChooserListener);
        missileShip.addActionListener(shipChooserListener);
        aircarrier.addActionListener(shipChooserListener);
        
        shipChoosingButtons = new ArrayList<>();
        Collections.addAll(shipChoosingButtons, commander, cannonShip, submarine, missileShip, aircarrier);
        
        gbc.insets = new Insets(5, 2, 5, 2);
        gbc.fill = GridBagConstraints.BOTH;
        
        for (int i = 0; i < shipChoosingButtons.size(); i++) {
            gbc.gridx = i; gbc.gridy = 0;
            layout.addLayoutComponent(shipChoosingButtons.get(i), gbc);
            shipChoosingButtonArea.add(shipChoosingButtons.get(i));
        }
        
        allShipsSet = new JButton("OK");
        shipChoosingButtons.add(allShipsSet);
        allShipsSet.addActionListener(new AllShipsAreSetListener(gc, playersSide.getPlayer(), playersSide, foesSide, shipChoosingButtons));
        allShipsSet.setEnabled(false);
        
        gbc.gridx = 2; gbc.gridy = 1;
        layout.addLayoutComponent(allShipsSet, gbc);
        shipChoosingButtonArea.add(allShipsSet);
        
        return shipChoosingButtonArea;
    }

    public boolean getAllShipsSet() {
        return allShipsSet.isEnabled();
    }

    public void setAllShipsSetEnabled(boolean isSet) {
        this.allShipsSet.setEnabled(isSet);
    }
    
    public void startGame() {
        for (VisualSquare vSquare : playersSide.getSquares()) {
            for (MouseListener ml : vSquare.getMouseListeners()) {
                vSquare.removeMouseListener(ml);
            }
        }
        
        for (JButton jButton : shipChoosingButtons) {
            for (ActionListener al : jButton.getActionListeners()) {
                jButton.removeActionListener(al);
            }
        }
        
        this.remove(shipChooserArea);
        
        for (VisualSquare vs : computersSide.getSquares()) {
            vs.addMouseListener(new BombASquareListener(vs, this, playersSide));
        }
        
        computersSide.setCursor(crosshairCursor);
        
        this.revalidate();
    }

    @Override
    public void update(StateChange stateChange, Object... object) {
        this.updated = true;
        
        if (stateChange.equals(StateChange.START_GAME)) {
            startGame();
        } else if (stateChange.equals(StateChange.UPDATE_TABLE)) {
            updateTable(object);
        } else if (stateChange.equals(StateChange.UPDATE_POINTS)) {
            updatePoints();
        } else if (stateChange.equals(StateChange.END_GAME)) {
            endGame(object);
        }
    }
    
    @Override
    public boolean isUpdated() {
        return updated;
    }

    private void updateTable(Object[] object) {
        for (int i = 0; i < object.length; i++) {
            if (object[i].getClass() == Human.class) {
                playersSide.repaintAll();
            } else if (object[i].getClass() == Computer.class) {
                computersSide.repaintAll();
            }
        }
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        header.setLayout(layout);
        
        int[] layoutWidths = {
            (gc.getGame().getHuman().getTable().getWidth() * squareWidth) / 2, 
            (gc.getGame().getHuman().getTable().getWidth() * squareWidth) / 2,
            50, 
            gc.getGame().getComputer().getTable().getWidth() * squareWidth / 2,
            gc.getGame().getComputer().getTable().getWidth() * squareWidth / 2};
        int[] layoutHeights = {50, 25, 25};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        JLabel human = new JLabel(gc.getGame().getHuman().toString());
        JLabel computer = new JLabel(gc.getGame().getComputer().toString());
        
        JLabel pointLabel1 = new JLabel(POINTS);
        JLabel pointLabel2 = new JLabel(POINTS);
        JLabel accuracyLabel1 = new JLabel(ACCURACY);
        JLabel accuracyLabel2 = new JLabel(ACCURACY);
        
        humansPoints = new JLabel("" + gc.getGame().getHuman().getPoints());
        computersPoints = new JLabel("" + gc.getGame().getComputer().getPoints());
        humanAccuracy = new JLabel("" + gc.getGame().getHuman().getAccuracy());
        computerAccuracy = new JLabel("" + gc.getGame().getComputer().getAccuracy());
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        
        layout.setConstraints(human, gbc);
        header.add(human);
        
        gbc.gridx = 3;
        layout.setConstraints(computer, gbc);
        header.add(computer);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        layout.setConstraints(pointLabel1, gbc);
        header.add(pointLabel1);
        
        gbc.gridx = 1;
        layout.setConstraints(humansPoints, gbc);
        header.add(humansPoints);
        
        gbc.gridx = 3;
        layout.setConstraints(pointLabel2, gbc);
        header.add(pointLabel2);
        
        gbc.gridx = 4;
        layout.setConstraints(computersPoints, gbc);
        header.add(computersPoints);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        layout.setConstraints(accuracyLabel1, gbc);
        header.add(accuracyLabel1);
        
        gbc.gridx = 1;
        layout.setConstraints(humanAccuracy, gbc);
        header.add(humanAccuracy);
        
        gbc.gridx = 3;
        layout.setConstraints(accuracyLabel2, gbc);
        header.add(accuracyLabel2);
        
        gbc.gridx = 4;
        layout.setConstraints(computerAccuracy, gbc);
        header.add(computerAccuracy);
        
        return header;
    }

    private JPanel createTables() {
        Player human = gc.getGame().getHuman();
        Player computer = gc.getGame().getComputer();
        
        JPanel tables = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        tables.setLayout(layout);
        
        int[] layoutWidths = {human.getTable().getWidth() * squareWidth, 50, computer.getTable().getWidth() * squareWidth};
        int[] layoutHeights = {human.getTable().getHeight() * squareWidth};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        playersSide = new VisualGameTable(human, gc, this);
        computersSide = new VisualGameTable(computer, gc, this);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 0;
        
        layout.setConstraints(playersSide, gbc);
        
        tables.add(playersSide);
        
        gbc.gridx = 2;
        
        layout.setConstraints(computersSide, gbc);
        
        tables.add(computersSide);
        
        return tables;
    }

    private void updatePoints() {
        humansPoints.setText("" + gc.getGame().getHuman().getPoints());
        computersPoints.setText("" + gc.getGame().getComputer().getPoints());
        
        humanAccuracy.setText("" + gc.getGame().getHuman().getAccuracy());
        computerAccuracy.setText("" + gc.getGame().getComputer().getAccuracy());
    }

    private void endGame(Object[] object) {
        for (VisualSquare vs : computersSide.getSquares()) {
            for (MouseListener ml : vs.getMouseListeners()) {
                vs.removeMouseListener(ml);
            }
        }
        
        Player player = null;
        
        try {
            player = (Player)object[0];
        } catch (Exception e) {}
        
        JPanel done = done(player);
        
        mainConstraints.gridy = 4;
        mainConstraints.gridx = 0;
        mainLayout.setConstraints(done, mainConstraints);
        this.add(done);
        
        this.revalidate();
    }

    private JPanel done(Player player) {
        JPanel gameOver = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        gameOver.setLayout(layout);
        
        JLabel winner = new JLabel();
        
        if (player == null) {
            winner.setText("Couldn't decide who won!");
        } else {
            winner.setText(player + " wins!");
        }
        
        JButton playAgain = new JButton("Play again");
        JButton exit = new JButton("Exit");
        
        ActionListener endOfGameListener = new EndOfGameListener(gc, playAgain, exit);
        
        playAgain.addActionListener(endOfGameListener);
        exit.addActionListener(endOfGameListener);
        
        int fullWidth = gc.getGame().getHuman().getTable().getWidth() * squareWidth + 50 + gc.getGame().getComputer().getTable().getWidth() * squareWidth;
        
        int[] layoutWidths = {fullWidth / 2, fullWidth / 2};
        int[] layoutHeights = {25, 25};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        
        layout.setConstraints(winner, gbc);
        gameOver.add(winner);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(2, 10, 2, 10);
        gbc.fill = GridBagConstraints.BOTH;
        
        layout.setConstraints(playAgain, gbc);
        gameOver.add(playAgain);
        
        gbc.gridx = 1;
        
        layout.setConstraints(exit, gbc);
        gameOver.add(exit);
        
        return gameOver;
    }

    private void loadVisualShipParts() {
        for (Direction direction : Direction.EAST.getMainDirections()) {
            visualShipParts.put(direction, new VisualShipPartPack(direction, squareWidth));
        }
    }

    public Map<Direction, VisualShipPartPack> getVisualShipParts() {
        return visualShipParts;
    }
    
    

    private Cursor loadCrosshair() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image crosshair = null;
        
        try {
            crosshair = ImageIO.read(new File(CROSSHAIR_PATH));
        } catch (IOException ex) {
            System.out.println("Couldn't load cursor");
        }
        
        Point hotSpot = new Point(crosshair.getWidth(null) / 2, crosshair.getHeight(null)/ 2);
        
        Cursor cursor = toolkit.createCustomCursor(crosshair, hotSpot, "Crosshair");
        
        return cursor;
    }
    
    public Cursor getCrosshairCursor() {
        return this.crosshairCursor;
    }
}