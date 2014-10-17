
package battleship2000.ui.panes;

import battleship2000.programlogic.GameCommands;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.ui.control.PaneAdministrator;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * The graphical user interface's area which contains the players' 
 * {@link battleship2000.ui.panes.VisualGameTable}s.
 * 
 * @author Petri Kallio
 */
public class GameArea {
    private GamePane gamePane;
    private GameCommands gc;
    private VisualGameTable playersSide;
    private VisualGameTable computersSide;
    private JPanel gameArea;
    
    /**
     * A constructor to create an instance of the class.
     * 
     * @param gamePane  the {@link battleship2000.ui.panes.GamePane} the GameArea is related to
     */
    public GameArea(GamePane gamePane) {
        this.gamePane = gamePane;
        this.gc = gamePane.getGameCommands();
        this.gameArea = create();
    }
    
    private JPanel create() {
        Player human = gc.getGame().getHuman();
        Player computer = gc.getGame().getComputer();
        
        JPanel tables = new JPanel();
        GridBagLayout layout = createLayout(human, computer);
        PaneAdministrator administrator = new PaneAdministrator(tables, layout);
        
        playersSide = new VisualGameTable(human, gc, gamePane);
        computersSide = new VisualGameTable(computer, gc, gamePane);
        
        administrator.addComponent(playersSide, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE);
        administrator.addComponent(computersSide, 2, 0);
        
        tables.setBackground(gamePane.getBACKGROUND_COLOR());
        return tables;
    }

    public JPanel getGameArea() {
        return gameArea;
    }

    public VisualGameTable getPlayersSide() {
        return playersSide;
    }

    public VisualGameTable getComputersSide() {
        return computersSide;
    }

    private GridBagLayout createLayout(Player human, Player computer) {
        GridBagLayout layout = new GridBagLayout();
        
        int squareWidth = gamePane.getSquareWidth();
        
        int[] layoutWidths = {human.getTable().getWidth() * squareWidth, 50, 
            computer.getTable().getWidth() * squareWidth};
        int[] layoutHeights = {human.getTable().getHeight() * squareWidth};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        return layout;
    }
}
