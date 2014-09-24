/**
 * A control class designed to direct the user's square-choosing iteration.
 */
package battleship2000.programlogic.control;

import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.domain.table.Table;

public class HumanChooseASquareToBomb implements Controller {
    private Human player;
    private Square square;
    private Table foesTable;

    public HumanChooseASquareToBomb(Human player, Table foesTable) {
        this.player = player;
        this.square = null;
        this.foesTable = foesTable;
    }
    
    @Override
    public Object execute() {
        this.player.getGame().notifyObservers(this);
        
        while (this.square == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex.getClass() + ": " + ex.getLocalizedMessage());
            }
        }
        
        return square;
    }

    public Human getPlayer() {
        return player;
    }

    public Table getFoesTable() {
        return foesTable;
    }

    public void setSquare(Square square) {
        this.square = square;
    }
}
