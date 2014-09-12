
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

public class CreateGame implements Controller {
    private Rules rules;
    
    public CreateGame(Rules rules) {
        this.rules = rules;
    }
    
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
