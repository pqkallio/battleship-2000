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
    
    /**
     * An instantiation of the class.
     * <p>
     * Creates a set of rules used to create the {@link battleship2000.programlogic.BattleShipGame}.
     * If the boolean value given as parameter is true, the game shall be played
     * against a {@link battleship2000.programlogic.domain.player.Computer}. If false,
     * a {@link battleship2000.programlogic.domain.player.Human} player shall be the
     * opponent
     * <p>
     * <strong>Note!</strong> The program logic doesn't actually yet support a 
     * game between two human players, so only games against a computer player should
     * be started until the multi-player gaming feature has been added.
     * 
     * @param againstTheComputer    if true, played against a computer player
     */
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
