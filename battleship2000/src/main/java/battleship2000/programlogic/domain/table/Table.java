
package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.ship.Ship;
import java.util.Map;

public interface Table {
    public Map<Integer, Map<Integer, Square>> getTable();
    public void placeShipOnTable(Ship alus);
    public void placeShipOnTable(ShipPart[] alus);
    public int getHeight();
    public int getWidth();
    public void removePartsFromTable(ShipPart[] aluksenosat);
    public void removePartsFromField(Ship alus);
}
