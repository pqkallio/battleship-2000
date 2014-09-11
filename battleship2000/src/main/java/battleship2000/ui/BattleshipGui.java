
package battleship2000.ui;

import battleship2000.ui.control.CreateMenus;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class BattleshipGui implements Runnable {
    private JFrame frame;
    
    @Override
    public void run() {
        this.frame = new JFrame("Battleship 2000");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(800, 600));
        
        CreateMenus createMenus = new CreateMenus(this.frame.getContentPane());
        createMenus.suorita();
        
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
