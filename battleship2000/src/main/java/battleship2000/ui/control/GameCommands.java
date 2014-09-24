/**
 * A class used by the user interface package to communicate with the program logic package.
 */
package battleship2000.ui.control;

import battleship2000.ui.listeners.BombThatMofoingSquareListener;
import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.control.CreateGame;
import battleship2000.programlogic.control.ShipPlacement;
import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.rules.BasicRules;
import battleship2000.ui.panes.VisualGameTable;
import battleship2000.ui.panes.VisualSquare;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;

public class GameCommands {
    private BattleShipGame game;
    
    public GameCommands() {}
    
    public void createANewGame() {
        this.game = (BattleShipGame) new CreateGame(new BasicRules(true)).execute();
    }
    
    public Position[] setShipsPosition(Ship ship, int x, int y) {
        ShipPlacement shipPositioning = new ShipPlacement(ship);
        shipPositioning.setShipsPosition(x, y);
        return shipPositioning.getPositions();
    }
    
    public boolean setShipOnTable(Ship ship, int x, int y) {
        ShipPlacement shipPositioning = new ShipPlacement(ship);
        if (shipPositioning.setShipsPosition(x, y)) {
            shipPositioning.placeShipOnTable();
            return true;
        }
        
        return false;
    }

    public BattleShipGame getGame() {
        return game;
    }

    public void startTheMofoingGame(VisualGameTable playersSide, 
            VisualGameTable foesSide, List<JButton> buttons) {
        game.getPlayers().get(1).placeShipsOnTable();
        foesSide.repaintAll();
        for (VisualSquare vSquare : playersSide.getSquares()) {
            for (MouseListener ml : vSquare.getMouseListeners()) {
                vSquare.removeMouseListener(ml);
            }
        }
        
        for (JButton jButton : buttons) {
            for (ActionListener al : jButton.getActionListeners()) {
                jButton.removeActionListener(al);
            }
        }
        
        for (VisualSquare vs : foesSide.getSquares()) {
            vs.addMouseListener(new BombThatMofoingSquareListener(vs, playersSide));
        }
    }
}
