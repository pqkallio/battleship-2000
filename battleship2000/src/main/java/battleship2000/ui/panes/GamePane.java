package battleship2000.ui.panes;

import battleship2000.ui.listeners.EndOfGameListener;
import battleship2000.ui.listeners.AllShipsAreSetListener;
import battleship2000.ui.listeners.ShipChooserListener;
import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.StateChange;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.observers.LogicObserver;
import battleship2000.ui.BattleshipGui;
import battleship2000.ui.control.PaneAdministrator;
import battleship2000.ui.domain.VisualShipPartPack;
import battleship2000.ui.listeners.BombASquareListener;
import battleship2000.ui.listeners.StartNewGameListener;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
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
public class GamePane extends JPanel implements LogicObserver, Pane {
    private GameCommands gc;
    private int squareWidth;
    private GridBagLayout mainLayout;
    private JButton allShipsSet;
    private List<JButton> shipChoosingButtons;
    private JLabel humansPoints;
    private JLabel computersPoints;
    private JLabel humanAccuracy;
    private JLabel computerAccuracy;
    private JPanel shipChooserArea;
    private final Font TITLE_FONT = new Font(Font.DIALOG, Font.BOLD, 24);
    private final Font POINTS_HEADER_FONT = new Font(Font.DIALOG, Font.BOLD, 16);
    private final Font POINTS_FONT = new Font(Font.MONOSPACED, Font.BOLD, 16);
    private final Color BACKGROUND_COLOR = new Color(0, 170, 170);
    private final Color TITLE_FONT_COLOR = new Color(0, 255, 255);
    private final Color POINTS_COLOR = new Color(0, 230, 230);
    private final String POINTS = "Points: ";
    private final String ACCURACY = "Accuracy: ";
    private final String CROSSHAIR_PATH = "/graphics/crosshair_25.png";
    private boolean updated;
    private Cursor crosshairCursor;
    private Map<Direction, VisualShipPartPack> visualShipParts;
    private BattleshipGui gui;
    private PaneAdministrator mainAdministrator;
    private GameArea gameArea;
    
    /**
     * Constructs a new instance of the class.
     * 
     * @param gameCommands  the GameCommands object of the game
     * @param squareWidth   the square width and height of Visual Squares
     * @param gui           the gui that displays the pane
     */
    public GamePane(GameCommands gameCommands, int squareWidth, BattleshipGui gui) {
        this.gc = gameCommands;
        this.squareWidth = squareWidth;
        this.gui = gui;
        
        createGameLayout();
        
        this.gc.getGame().addObserver(this);
        
        this.updated = false;
        
        this.visualShipParts = new HashMap<>();
        loadVisualShipParts();
        
        this.crosshairCursor = loadCrosshair();
    }

    public int getSquareWidth() {
        return squareWidth;
    }
    
    private void createGameLayout() {
        mainLayout = setupLayout();
        this.mainAdministrator = new PaneAdministrator(this, mainLayout);
        
        JPanel header = createHeader();
        this.mainAdministrator.addComponent(header, 0, 0);
        
        this.gameArea = new GameArea(this);
        this.mainAdministrator.addComponent(gameArea.getGameArea(), 0, 2);
        
        this.shipChooserArea = createShipChooser(gameArea.getPlayersSide(), 
                gameArea.getComputersSide());
        this.mainAdministrator.addComponent(shipChooserArea, 0, 4);
        
        this.setBackground(BACKGROUND_COLOR);
    }

    public GameCommands getGameCommands() {
        return gc;
    }

    private JPanel createShipChooser(VisualGameTable playersSide, VisualGameTable foesSide) {
        JPanel shipChoosingButtonArea = new JPanel();
        GridBagLayout layout = setupShipChooserLayout();
        PaneAdministrator administrator = new PaneAdministrator(shipChoosingButtonArea, layout);
        
        shipChoosingButtons = createShipChoosingButtons();
        
        Insets insets = new Insets(5, 5, 5, 5);
        
        for (int i = 0; i < shipChoosingButtons.size(); i++) {
            administrator.addComponent(shipChoosingButtons.get(i), i, 0, 
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1, insets);
        }
        
        allShipsSet = createAllShipsSetButton();
        shipChoosingButtons.add(allShipsSet);
        administrator.addComponent(allShipsSet, 2, 1);
        
        shipChoosingButtonArea.setBackground(BACKGROUND_COLOR);
        
        return shipChoosingButtonArea;
    }

    public boolean getAllShipsSet() {
        return allShipsSet.isEnabled();
    }

    /**
     * If the parameter given is true, displays and enables the JButton that starts the game.
     * 
     * @param isSet     the value that defines the visibility and enabling of the "OK" button
     */
    public void setAllShipsSetEnabled(boolean isSet) {
        this.allShipsSet.setEnabled(isSet);
        
        if (isSet) {
            this.allShipsSet.setVisible(true);
        }
    }
    
    /**
     * Ends the ship-setting phase and starts the bombing phase of the game.
     */
    public void startGame() {
        removeMouseListeners(gameArea.getPlayersSide());
        removeActionListeners(shipChoosingButtons);
        
        this.remove(shipChooserArea);
        
        addBombASquareListeners(gameArea.getComputersSide());
        
        gameArea.getComputersSide().setCursor(crosshairCursor);
        
        this.repaint();
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
        if (object[0] == gc.getGame().getHuman()) {
            gameArea.getPlayersSide().repaintAll();
        } else if (object[0] == gc.getGame().getComputer()) {
            gameArea.getComputersSide().repaintAll();
        }
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        GridBagLayout layout = setupHeaderLayout();
        PaneAdministrator administrator = new PaneAdministrator(header, layout);
        
        JLabel[] playerTitleLabels = createPlayerTitleLabels();
        JLabel[] pointAndAccuracyLabels = createPointLabels();
        setupPlayerPointAndAccuracyCounters();
        
        administrator.addComponent(playerTitleLabels[0], 0, 0, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE, 2);
        administrator.addComponent(playerTitleLabels[1], 3, 0);
        administrator.addComponent(pointAndAccuracyLabels[0], 0, 1, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE, 1);
        administrator.addComponent(humansPoints, 1, 1);
        administrator.addComponent(pointAndAccuracyLabels[1], 3, 1);
        administrator.addComponent(computersPoints, 4, 1);
        administrator.addComponent(pointAndAccuracyLabels[2], 0, 2);
        administrator.addComponent(humanAccuracy, 1, 2);
        administrator.addComponent(pointAndAccuracyLabels[3], 3, 2);
        administrator.addComponent(computerAccuracy, 4, 2);
        
        header.setBackground(BACKGROUND_COLOR);
        
        return header;
    }

    public Color getBACKGROUND_COLOR() {
        return BACKGROUND_COLOR;
    }

    private void updatePoints() {
        humansPoints.setText("" + gc.getGame().getHuman().getPoints());
        computersPoints.setText("" + gc.getGame().getComputer().getPoints());
        
        humanAccuracy.setText("" + gc.getGame().getHuman().getAccuracy());
        computerAccuracy.setText("" + gc.getGame().getComputer().getAccuracy());
    }

    private void endGame(Object[] object) {
        removeMouseListeners(gameArea.getComputersSide());
        
        Player player = null;
        
        try {
            player = (Player)object[0];
        } catch (ClassCastException|NullPointerException ex) {
            gui.alertException("Illegal argument", ex);
        }
        
        JPanel done = done(player);
        mainAdministrator.addComponent(done, 0, 4);
        
        this.revalidate();
    }

    public BattleshipGui getGui() {
        return gui;
    }

    private JPanel done(Player player) {
        JPanel gameOver = new JPanel();
        GridBagLayout layout = createGameOverButtonAreaLayout();
        PaneAdministrator administrator = new PaneAdministrator(gameOver, layout);
        
        JLabel winner = createWinnerLabel(player);
        JButton playAgain = new JButton("Play again");
        JButton exit = new JButton("Exit");
        
        ActionListener endOfGameListener = new EndOfGameListener(gui.getCards());
        playAgain.addActionListener(new StartNewGameListener(gui.getCards(), gc, gui));
        exit.addActionListener(endOfGameListener);
        
        administrator.addComponent(winner, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, GridBagConstraints.REMAINDER);
        administrator.addComponent(playAgain, 0, 1, GridBagConstraints.CENTER, 
                GridBagConstraints.BOTH, 1, new Insets(2, 10, 2, 10));
        administrator.addComponent(exit, 1, 1);
        
        gameOver.setBackground(BACKGROUND_COLOR);
        
        return gameOver;
    }

    private void loadVisualShipParts() {
        for (Direction direction : Direction.EAST.getCardinalDirections()) {
            visualShipParts.put(direction, new VisualShipPartPack(direction, squareWidth, gui));
        }
    }

    public Map<Direction, VisualShipPartPack> getVisualShipParts() {
        return visualShipParts;
    }
    
    private Cursor loadCrosshair() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image crosshair = null;
        
        try {
            InputStream is = this.getClass().getResourceAsStream(CROSSHAIR_PATH);
            crosshair = ImageIO.read(is);
        } catch (IOException ex) {
            alertException("Couldn't load graphics", ex);
        }
        
        Point hotSpot = new Point(crosshair.getWidth(null) / 2, crosshair.getHeight(null)/ 2);
        
        Cursor cursor = toolkit.createCustomCursor(crosshair, hotSpot, "Crosshair");
        
        return cursor;
    }
    
    public Cursor getCrosshairCursor() {
        return this.crosshairCursor;
    }

    @Override
    public void alertException(String message, Exception ex) {
        gui.alertException(message, ex);
    }

    private GridBagLayout setupLayout() {
        GridBagLayout layout = new GridBagLayout();
        Player human = gc.getGame().getHuman();
        Player computer = gc.getGame().getComputer();
        
        int[] layoutWidths = {human.getTable().getWidth() * squareWidth + 50 + computer.getTable().getWidth() * squareWidth};
        int[] layoutHeights = {100, 25, human.getTable().getHeight() * squareWidth, 25, 50};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        return layout;
    }

    private GridBagLayout setupShipChooserLayout() {
        GridBagLayout layout = new GridBagLayout();
        int[] layoutWidths = {50, 50, 50, 50, 50, 50, gc.getGame().getComputer().getTable().getWidth() * squareWidth};
        int[] layoutHeights = {25, 25};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        return layout;
    }

    private List<JButton> createShipChoosingButtons() {
        JButton commander = new JButton("Com");
        JButton cannonShip = new JButton("Can");
        JButton submarine = new JButton("Sub");
        JButton missileShip = new JButton("Mis");
        JButton aircarrier = new JButton("Air");
        
        List<JButton> buttons = new ArrayList<>();
        Collections.addAll(buttons, commander, cannonShip, submarine, missileShip, aircarrier);
        
        ActionListener shipChooserListener = new ShipChooserListener(gc, 
                gameArea.getPlayersSide(), commander, cannonShip, submarine, missileShip, aircarrier);
        
        for (JButton button : buttons) {
            button.addActionListener(shipChooserListener);
        }
        
        return buttons;
    }

    private JButton createAllShipsSetButton() {
        JButton button = new JButton("OK");
        button.addActionListener(new AllShipsAreSetListener(gc, gc.getGame().getHuman(), 
                gameArea.getPlayersSide()));
        button.setEnabled(false);
        button.setVisible(false);
        
        return button;
    }

    private void removeMouseListeners(VisualGameTable visualGameTable) {
        for (VisualSquare vSquare : visualGameTable.getSquares()) {
            for (MouseListener ml : vSquare.getMouseListeners()) {
                vSquare.removeMouseListener(ml);
            }
        }
    }

    private void removeActionListeners(List<JButton> buttons) {
        for (JButton button : buttons) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
        }
    }

    private void addBombASquareListeners(VisualGameTable computersSide) {
        for (VisualSquare vs : computersSide.getSquares()) {
            vs.addMouseListener(new BombASquareListener(vs, this, gameArea.getPlayersSide()));
        }
    }

    private GridBagLayout setupHeaderLayout() {
        GridBagLayout layout = new GridBagLayout();
        
        int[] layoutWidths = {
            (gc.getGame().getHuman().getTable().getWidth() * squareWidth) / 2, 
            (gc.getGame().getHuman().getTable().getWidth() * squareWidth) / 2,
            50, 
            gc.getGame().getComputer().getTable().getWidth() * squareWidth / 2,
            gc.getGame().getComputer().getTable().getWidth() * squareWidth / 2};
        int[] layoutHeights = {50, 25, 25};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        return layout;
    }

    private JLabel[] createPlayerTitleLabels() {
        JLabel human = new JLabel(gc.getGame().getHuman().toString());
        JLabel computer = new JLabel(gc.getGame().getComputer().toString());
        JLabel[] titles = {human, computer};
        
        for (JLabel label : titles) {
            label.setFont(TITLE_FONT);
            label.setForeground(TITLE_FONT_COLOR);
        }
        
        return titles;
    }

    private JLabel[] createPointLabels() {
        JLabel[] labels = {new JLabel(POINTS), new JLabel(POINTS),
                           new JLabel(ACCURACY), new JLabel(ACCURACY)};
        
        for (JLabel label : labels) {
            label.setFont(POINTS_HEADER_FONT);
            label.setForeground(POINTS_COLOR);
        }
        
        return labels;
    }

    private void setupPlayerPointAndAccuracyCounters() {
        humansPoints = new JLabel("" + gc.getGame().getHuman().getPoints());
        computersPoints = new JLabel("" + gc.getGame().getComputer().getPoints());
        humanAccuracy = new JLabel("" + gc.getGame().getHuman().getAccuracy());
        computerAccuracy = new JLabel("" + gc.getGame().getComputer().getAccuracy());
        JLabel[] labels = {humansPoints, computersPoints, humanAccuracy, computerAccuracy};
        
        for (JLabel label : labels) {
            label.setFont(POINTS_FONT);
            label.setForeground(POINTS_COLOR);
        }
    }

    private GridBagLayout createGameOverButtonAreaLayout() {
        GridBagLayout layout = new GridBagLayout();
        int fullWidth = this.gui.getFRAME_WIDTH();
        
        int[] layoutWidths = {fullWidth / 2, fullWidth / 2};
        int[] layoutHeights = {25, 25};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        return layout;
    }

    private JLabel createWinnerLabel(Player player) {
        JLabel winner = new JLabel();
        winner.setFont(TITLE_FONT);
        winner.setForeground(new Color(230, 255, 255));
        
        if (player == null) {
            winner.setText("Couldn't decide who won!");
        } else {
            winner.setText(player + " wins!");
        }
        
        return winner;
    }
}