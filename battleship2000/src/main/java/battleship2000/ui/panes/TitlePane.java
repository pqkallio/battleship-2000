package battleship2000.ui.panes;

import battleship2000.ui.listeners.StartNewGameListener;
import battleship2000.programlogic.GameCommands;
import battleship2000.ui.BattleshipGui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.text.LayeredHighlighter;

/**
 * The game's graphical user interface's title pane. 
 *
 * @author Petri Kallio
 */
public class TitlePane extends JPanel {
    private GameCommands gc;
    private BattleshipGui gui;
    private JPanel cards;
    
    public TitlePane(BattleshipGui gui, GameCommands gameCommands, JPanel cards) {
        this.gui = gui;
        this.gc = gameCommands;
        this.cards = cards;
    }

    public JPanel getTitlePage() {
        JPanel titlePanel = new JPanel();
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
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
        
        titlePanel.setLayout(layout);
        titlePanel.setBorder(new CompoundBorder());
        titlePanel.setBackground(new Color(0, 100, 200));
        
        JLabel title = new JLabel("Battleship 2000");
        Font titleFont = new Font(Font.DIALOG, Font.BOLD, 46);
        title.setFont(titleFont);
        title.setForeground(new Color(0, 200, 255));
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        
        layout.setConstraints(title, gbc);
        titlePanel.add(title);
        
        JButton start = new JButton("START");
        start.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        start.addActionListener(new StartNewGameListener(this.cards, gc, gui));
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        
        layout.setConstraints(start, gbc);
        titlePanel.add(start);
        
        return titlePanel;
    }
}
