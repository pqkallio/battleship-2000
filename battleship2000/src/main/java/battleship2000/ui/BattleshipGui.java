package battleship2000.ui;

import battleship2000.programlogic.GameCommands;
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

/**
 * A Swing-based framework for creating an instance of a graphical user interface.
 *
 * @author Petri Kallio
 */
public class BattleshipGui implements Runnable {
    private JFrame frame;
    
    @Override
    public void run() {
        this.frame = new JFrame("Battleship 2000");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(650, 550));
//        this.frame.setResizable(false);
        
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
        int[] layoutHeights = {50, 400, 50};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        JPanel titlePane = new TitlePane().getTitlePage();
        GameCommands gameCommands = new GameCommands();
        gameCommands.createANewGame();
        JPanel gamePane = new GamePane(gameCommands, 25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        layout.setConstraints(gamePane, gbc);
        contentPane.add(gamePane);
    }
}
