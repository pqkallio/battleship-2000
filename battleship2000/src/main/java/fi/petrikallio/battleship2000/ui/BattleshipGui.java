
package fi.petrikallio.battleship2000.ui;

import fi.petrikallio.battleship2000.ui.ohjausluokat.Luovalikot;
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
        
        Luovalikot luoValikot = new Luovalikot(this.frame.getContentPane());
        luoValikot.suorita();
        
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
