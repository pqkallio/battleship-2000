
package battleship2000.programlogic.domain.ship;

import battleship2000.programlogic.domain.table.GameTable;

public class CannonShip extends Ship {
    
    public CannonShip() {
        super(3, 1);
    }
    
    @Override
    public String toString() {
        return "tykilaiva";
    }
}
