/**
 * A Swing-based framework for creating an instance of a graphical user interface.
 */

package battleship2000.ui;

import battleship2000.ui.control.GameCommands;
import battleship2000.ui.control.CreatePanes;
import battleship2000.ui.panes.GamePane;
import battleship2000.ui.panes.TitlePane;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BattleshipGui implements Runnable {
    private JFrame frame;
    
    @Override
    public void run() {
        this.frame = new JFrame("Battleship 2000");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(650, 350));
        
        CreatePanes createMenus = new CreatePanes(this.frame.getContentPane());
        createMenus.execute();
        
        createContent(frame.getContentPane());
        
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void createContent(Container contentPane) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        contentPane.setLayout(layout);
        int[] layoutWidths = {50, 550, 50};
        int[] layoutHeights = {50, 300};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        JPanel titlePane = new TitlePane().getTitlePage();
        GameCommands gameCommands = new GameCommands();
        gameCommands.createANewGame();
        JPanel gamePane = new GamePane(gameCommands);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        layout.setConstraints(gamePane, gbc);
        contentPane.add(gamePane);
    }
}
