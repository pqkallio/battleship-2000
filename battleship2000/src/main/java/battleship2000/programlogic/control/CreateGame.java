package battleship2000.programlogic.control;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.player.PlayerType;
import battleship2000.programlogic.domain.table.GameTable;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.rules.Rules;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to create a new instance of BattleShipGame class.
 * 
 * @author Petri Kallio
 */
public class CreateGame implements Controller {
    private Rules rules;
    
    /**
     * A constructor that creates a new instance of the class and sets the Rules
     * object variable.
     * 
     * @param rules     the Rules-object to be set as an object variable
     */
    public CreateGame(Rules rules) {
        this.rules = rules;
    }
    
    /**
     * Executes the necessary actions to create a new instance of the BattleShipGame class.
     * 
     * @return new BattleShipGame
     */
    @Override
    public Object execute() {
        BattleShipGame game = new BattleShipGame();
        
        try {
            game.setPlayers(createPlayers());
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getClass() + ": " + e.getLocalizedMessage());
        }
        
        game.setShipsAreMovable(this.rules.shipsAreMovable());
        game.setShipsAreSpecialized(this.rules.shipsAreSpecialized());
        game.setASquareCanBeHitMultipleTimes(this.rules.aSquareCanBeHitMultipleTimes());
        
        return game;
    }
    
    /**
     * Creates new players for the game according to the PlayerTypes given in the Rules.
     * 
     * @return  a list of player objects inheriting the abstract class {@link battleship2000.programlogic.domain.player.Player}
     * @throws  InstantiationException
     * @throws  IllegalAccessException 
     */
    private List<Player> createPlayers() throws InstantiationException, IllegalAccessException {
        List<Player> players = new ArrayList<>();
        
        for (PlayerType type : this.rules.getPlayerTypes()) {
            Player player = (Player) type.getPlayerClass().newInstance();
            player.setTable(addTableToAPlayer());
            player.setShips(addShipsToAPlayer(player));
            players.add(player);
        }
        
        return players;
    }

    private List<Ship> addShipsToAPlayer(Player player) throws InstantiationException, IllegalAccessException {
        List<Ship> playersShips = new ArrayList<>();
        
        for (ShipType type : this.rules.getShipTypes()) {
            Ship shipToAdd = (Ship)type.getShipClass().newInstance();
            shipToAdd.setTable(player.getTable());
            shipToAdd.setIsOnTable(false);
            playersShips.add(shipToAdd);
        }
        
        return playersShips;
    }

    private Table addTableToAPlayer() {
        int width = this.rules.getTableWidth();
        int height = this.rules.getTableHeight();
        return new GameTable(width, height);
    }
}
