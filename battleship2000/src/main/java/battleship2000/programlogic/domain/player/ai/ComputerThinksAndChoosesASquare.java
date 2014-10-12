
package battleship2000.programlogic.domain.player.ai;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.StateChange;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class ComputerThinksAndChoosesASquare extends Timer implements ActionListener {
    private BattleShipGame game;
    
    public ComputerThinksAndChoosesASquare(BattleShipGame game) {
        super(2000, null);
        super.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.notifyObservers(StateChange.UPDATE_TABLE, game.getHuman());
        game.notifyObservers(StateChange.UPDATE_POINTS, 0);
        
        this.stop();
    }
    
    
}
