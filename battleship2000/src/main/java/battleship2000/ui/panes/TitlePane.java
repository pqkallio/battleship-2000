package battleship2000.ui.panes;

import battleship2000.ui.listeners.StartNewGameListener;
import battleship2000.programlogic.GameCommands;
import battleship2000.ui.BattleshipGui;
import battleship2000.ui.control.PaneAdministrator;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The game's graphical user interface's title pane. 
 *
 * @author Petri Kallio
 */
public class TitlePane extends JPanel {
    private GameCommands gc;
    private BattleshipGui gui;
    private JPanel cards;
    private final Color BACKGROUND_COLOR = new Color(0, 100, 200);
    private final Font TITLE_FONT = new Font(Font.DIALOG, Font.BOLD, 46);
    private final Color TITLE_FONT_COLOR = new Color(0, 200, 255);
    private final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 24);
    
    /**
     * Constructs a new instance of the class.
     * 
     * @param gui           the graphical user interface the TitlePane is a part of
     * @param gameCommands  the game commands of the game
     * @param cards         the panel containing the gui's different panes
     */
    public TitlePane(BattleshipGui gui, GameCommands gameCommands, JPanel cards) {
        this.gui = gui;
        this.gc = gameCommands;
        this.cards = cards;
    }

    /**
     * Creates and returns a JPanel that is used as the gui's title pane.
     * 
     * @return  the title pane
     */
    public JPanel getTitlePage() {
        JPanel titlePanel = new JPanel();
        GridBagLayout layout = setupLayout();
        PaneAdministrator administrator = new PaneAdministrator(titlePanel, layout);
        
        JLabel title = createTitle();
        administrator.addComponent(title, 2, 1, GridBagConstraints.CENTER);
        
        JButton start = createStartButton();
        administrator.addComponent(start, 2, 3);
        
        titlePanel.setBackground(BACKGROUND_COLOR);
        
        return titlePanel;
    }

    private GridBagLayout setupLayout() {
        GridBagLayout layout = new GridBagLayout();
        
        int fifthOfTheWidth = gui.getFRAME_WIDTH() / 5;
        int fifthOfTheHeight = gui.getFRAME_WIDTH() / 5;
        
        int[] layoutWidths = new int[5];
        int[] layoutHeights = new int[5];
        
        for (int i = 0; i < layoutWidths.length; i++) {
            layoutWidths[i] = fifthOfTheWidth;
        }
        
        for (int i = 0; i < layoutHeights.length; i++) {
            layoutHeights[i] = fifthOfTheHeight;
        }
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        return layout;
    }

    private JLabel createTitle() {
        JLabel title = new JLabel("Battleship 2000");
        title.setFont(TITLE_FONT);
        title.setForeground(TITLE_FONT_COLOR);
        
        return title;
    }

    private JButton createStartButton() {
        JButton start = new JButton("START");
        start.setFont(BUTTON_FONT);
        start.addActionListener(new StartNewGameListener(this.cards, gc, gui));
        
        return start;
    }
}
