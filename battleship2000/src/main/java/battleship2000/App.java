package battleship2000;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.control.CreateGame;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.rules.BasicRules;

public class App 
{
    public static void main( String[] args )
    {
        BattleShipGame peli = (BattleShipGame) new CreateGame(new BasicRules(true)).execute();
        for (Player pelaaja : peli.getPlayers()) {
            System.out.println(pelaaja);
        }
        
        peli.getPlayers().get(0).placeShipsOnTable();
        
        System.out.println(Direction.SOUTHEAST.getOppositeDirection());
        System.out.println(Direction.SOUTHWEST.getOppositeDirection());
        System.out.println(Direction.NORTH_NORTHWEST.getOppositeDirection());
        System.out.println(Direction.SOUTH_SOUTHEAST.getOppositeDirection());
    }
}
