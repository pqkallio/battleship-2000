
package battleship2000.ui.panes;

import battleship2000.ui.listeners.AllMofoingShipsAreMofoingSetListener;
import battleship2000.ui.listeners.ShipChooserListener;
import battleship2000.ui.listeners.SetShipListener;
import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.observers.LogicObserver;
import battleship2000.ui.control.GameCommands;
import battleship2000.programlogic.control.HumanPlaceShips;
import battleship2000.programlogic.control.PlayerStatus;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.table.Table;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GamePane extends JPanel {
    private GameCommands gameCommmands;
    private JButton allMofoingShipsMofoingSet;
    
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
        
        int[] layoutWidths = {human.getTable().getWidth() * 25, 50, computer.getTable().getWidth() * 25};
        int[] layoutHeights = {human.getTable().getHeight() * 25, 50};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        VisualGameTable playersSide = new VisualGameTable(human, gameCommmands, this);
        VisualGameTable foesSide = new VisualGameTable(computer, gameCommmands, this);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 0;
        
        layout.setConstraints(playersSide, gbc);
        
        this.add(playersSide);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2; gbc.gridy = 0;
        
        layout.setConstraints(foesSide, gbc);
        
        this.add(foesSide);
        
        JPanel shipChooserArea = createShipChooser(playersSide, foesSide);
        
        gbc.gridx = 0; gbc.gridy = 1;
        layout.addLayoutComponent(shipChooserArea, gbc);
        this.add(shipChooserArea);
    }

    public GameCommands getGameCommmands() {
        return gameCommmands;
    }

    private JPanel createShipChooser(VisualGameTable playersSide, VisualGameTable foesSide) {
        JPanel shipChoosingButtonArea = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        shipChoosingButtonArea.setLayout(layout);
        
        int[] layoutWidths = {50, 50, 50, 50, 50};
        int[] layoutHeights = {25, 25};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        JButton commander = new JButton("Com");
        JButton cannonShip = new JButton("Can");
        JButton submarine = new JButton("Sub");
        JButton missileShip = new JButton("Mis");
        JButton aircarrier = new JButton("Air");
        
        ActionListener shipChooserListener = new ShipChooserListener(gameCommmands, 
                playersSide, commander, cannonShip, submarine, missileShip, aircarrier);
        
        commander.addActionListener(shipChooserListener);
        cannonShip.addActionListener(shipChooserListener);
        submarine.addActionListener(shipChooserListener);
        missileShip.addActionListener(shipChooserListener);
        aircarrier.addActionListener(shipChooserListener);
        
        List<JButton> buttons = new ArrayList<>();
        Collections.addAll(buttons, commander, cannonShip, submarine, missileShip, aircarrier);
        
        gbc.fill = GridBagConstraints.NONE;
        
        for (int i = 0; i < buttons.size(); i++) {
            gbc.gridx = i; gbc.gridy = 0;
            layout.addLayoutComponent(buttons.get(i), gbc);
            shipChoosingButtonArea.add(buttons.get(i));
        }
        
        allMofoingShipsMofoingSet = new JButton("Start");
        buttons.add(allMofoingShipsMofoingSet);
        allMofoingShipsMofoingSet.addActionListener(new AllMofoingShipsAreMofoingSetListener(gameCommmands, playersSide.getPlayer(), playersSide, foesSide, buttons));
        allMofoingShipsMofoingSet.setEnabled(false);
        
        gbc.gridx = 2; gbc.gridy = 1;
        layout.addLayoutComponent(allMofoingShipsMofoingSet, gbc);
        shipChoosingButtonArea.add(allMofoingShipsMofoingSet);
        
        return shipChoosingButtonArea;
    }

    public boolean getAllMofoingShipsMofoingSet() {
        return allMofoingShipsMofoingSet.isEnabled();
    }

    public void setAllMofoingShipsMofoingSetEnabled(boolean isSet) {
        this.allMofoingShipsMofoingSet.setEnabled(isSet);
    }
}