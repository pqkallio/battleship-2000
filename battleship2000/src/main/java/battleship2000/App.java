package battleship2000;

import battleship2000.ui.BattleshipGui;
import javax.swing.SwingUtilities;

/**
 * The main class that runs the game.
 * 
 * @author Petri
 */
public class App 
{
    public static void main( String[] args )
    {

        BattleshipGui gui = new BattleshipGui();
        SwingUtilities.invokeLater(gui);
        
    }
}
