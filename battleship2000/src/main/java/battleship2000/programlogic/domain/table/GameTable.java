
package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.ship.Ship;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameTable implements Table {
    private Map<Integer, Map<Integer, Square>> table;
    private List<Ship> ships;
    
    public GameTable(int width, int height) {
        this.table = createGameTable(width, height);
    }
            
    @Override
    public Map<Integer, Map<Integer, Square>> getTable() {
        return this.table;
    }

    private Map<Integer, Map<Integer, Square>> 
            createGameTable(int width, int height) {
        Map<Integer, Map<Integer, Square>> field = new TreeMap<>();
        
        for (int i = 0; i < height; i++) {
            field.put(i, new TreeMap<Integer, Square>());
            
            for (int j = 0; j < width; j++) {
                field.get(i).put(j, new Square(j, i));
            }
        }
        
        return field;
    }

    @Override
    public int getWidth() {
        return this.table.get(0).size();
    }

    @Override
    public int getHeight() {
        return this.table.size();
    }
    
    @Override
    public void placeShipOnTable(Ship ship) {
        placeShipOnTable(ship.getParts());
    }

    @Override
    public void placeShipOnTable(ShipPart[] parts) {
        for (ShipPart part : parts) {
            if (part.getMotherShip().isOnTable()) {
                this.table.get(part.getY()).get(part.getX()).setShipPart(part);
            }
        }
    }

    @Override
    public void removePartsFromTable(ShipPart[] parts) {
        for (ShipPart part : parts) {
            if (part.getX() > -1 && part.getX() < getWidth()
                    && part.getY() > -1 && part.getY() < getHeight()) {
                if (part == this.table.get(part.getY()).get(part.getX()).getShipPart()) {
                    this.table.get(part.getY()).get(part.getX()).removeShipPart();
                }
            }
        }
    }

    @Override
    public void removePartsFromField(Ship ship) {
        removePartsFromTable(ship.getParts());
    }
}
