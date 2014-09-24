/**
 * A control class used to direct the users ship-placement iteration.
 */
package battleship2000.programlogic.control;

import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.ship.Ship;

public class HumanPlaceShips implements Controller {
    private Human player;
    private PlayerStatus playerStatus;
    
    public HumanPlaceShips(Human player) {
        this.player = player;
        this.playerStatus = PlayerStatus.NOT_READY;
    }
    
    @Override
    public Object execute() {
        player.getGame().notifyObservers(this);
        
        while (this.playerStatus == PlayerStatus.NOT_READY) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex.getClass() + ": " + ex.getLocalizedMessage());
            }
        }
        
        if (playerStatus == PlayerStatus.READY) {
            for (Ship ship : this.player.getShips()) {
                new ShipPlacement(ship).placeShipOnTable();
            }
            return true;
        } else {
            return false;
        }
    }

    public void setPlayerStatus(PlayerStatus status) {
        this.playerStatus = status;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public Human getPlayer() {
        return player;
    }
    
    
}