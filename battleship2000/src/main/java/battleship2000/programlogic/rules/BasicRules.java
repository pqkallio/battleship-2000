package battleship2000.programlogic.rules;

import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.domain.player.PlayerType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A set of pre-made basic rules for a standard five-ship two-player battleship 
 * game played on a ten by ten game table.
 *
 * @author Petri Kallio
 */
public class BasicRules extends Rules {
    private final ShipType[] shipTypes = {ShipType.COMMANDER, 
                                             ShipType.CANNON_SHIP,
                                             ShipType.SUBMARINE,
                                             ShipType.MISSILE_SHIP,
                                             ShipType.AIRCARRIER};
    
    public BasicRules(boolean againstTheComputer) {
        setShips();
        setPlayers(againstTheComputer);
        super.setTableSize(8, 8);
        super.setShipsAreMovableRule(false);
        super.setSpecializedShips(false);
        super.setASquareCanBeHitMultipleTimesRule(false);
    }

    private void setShips() {
        List<ShipType> ships = new ArrayList<>();
        Collections.addAll(ships, shipTypes);
        super.setShipTypes(ships);
    }

    private void setPlayers(boolean againstTheComputer) {
        List<PlayerType> players = new ArrayList<>();
        
        players.add(PlayerType.HUMAN);
        
        if (againstTheComputer) {
            players.add(PlayerType.COMPUTER);
        } else {
            players.add(PlayerType.HUMAN);
        }
        
        super.setPlayerTypes(players);
        
    }
}
