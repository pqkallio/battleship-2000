
package battleship2000.programlogic.control;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.rules.BasicRules;

public class GameCommands {
    private BattleShipGame game;
    
    public GameCommands() {}
    
    public void createANewGame() {
        this.game = (BattleShipGame) new CreateGame(new BasicRules(true)).execute();
    }
    
    public Position[] setShipsPosition(Ship ship, int x, int y) {
        SetShipsPosition shipPositioning = new SetShipsPosition(ship);
        shipPositioning.setShipsPosition(x, y);
        return shipPositioning.getPositions();
    }
    
    public void setShipOnTable(Ship ship, int x, int y) {
        SetShipsPosition shipPositioning = new SetShipsPosition(ship);
        shipPositioning.setShipsPosition(x, y);
        shipPositioning.placeShipOnTable();
    }

    public BattleShipGame getGame() {
        return game;
    }
}
