
package battleship2000.ui.panes;

import battleship2000.ui.listeners.SetShipListener;
import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.LogicObserver;
import battleship2000.programlogic.control.GameCommands;
import battleship2000.programlogic.control.HumanPlaceShips;
import battleship2000.programlogic.control.PlayerStatus;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.table.Table;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GamePane extends JPanel {
    private GameCommands gameCommmands;
    
    public GamePane(GameCommands gameCommands) {
        this.gameCommmands = gameCommands;
        createGameLayout();
    }
    
    private void placeShips(HumanPlaceShips humanPlaceShips) {
        humanPlaceShips.setPlayerStatus(PlayerStatus.READY);
    }

    private void createGameLayout() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(layout);
        
        Player human = gameCommmands.getGame().getPlayers().get(0);
        Player computer = gameCommmands.getGame().getPlayers().get(1);
        
        int[] layoutWidths = {human.getTable().getWidth() * 25};
        int[] layoutHeights = {human.getTable().getHeight() * 25, 50};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        JComponent playersSide = new GraphicGameTable(human, gameCommmands);
//        JComponent foesSide = new GraphicGameTable(foe, false);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 0;
        
        layout.setConstraints(playersSide, gbc);
        
        this.add(playersSide);
    }

    public GameCommands getGameCommmands() {
        return gameCommmands;
    }
}