/**
 * GameTable class implements the Table interface and works as a storage to
 * its squares and their placement on the game table.
 */

package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.ship.Ship;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameTable implements Table {
    private Map<Integer, Map<Integer, Square>> table;
    private List<Ship> ships;
    private int width;
    private int height;
    
    public GameTable(int width, int height) {
        this.width = width;
        this.height = height;
        this.table = createGameTable(width, height);
    }
    
    @Override
    public boolean allSquaresAreHit() {
        for (Map<Integer, Square> map : table.values()) {
            for (Square square : map.values()) {
                if (!square.isHit()) {
                    return false;
                }
            }
        }
        
        return true;
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
                field.get(i).put(j, new Square(this, j, i));
            }
        }
        
        return field;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public void placeShipOnTable(Ship ship) {
        ship.setIsOnTable(true);
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
            if (part.getPosition() != null) {
                if (part.getX() > -1 && part.getX() < getWidth()
                        && part.getY() > -1 && part.getY() < getHeight()) {
                    if (part == this.table.get(part.getY()).get(part.getX()).getShipPart()) {
                        this.table.get(part.getY()).get(part.getX()).removeShipPart();
                    }
                }
            }
        }
    }

    @Override
    public void removePartsFromField(Ship ship) {
        removePartsFromTable(ship.getParts());
    }

    @Override
    public Square getNextSquare(Square square, Direction direction) {
        Square nextSquare = null;
        
        if (square.getTable() == this && square.getX() + direction.getDx() > 0
            && square.getX() + direction.getDx() < this.width
            && square.getY() + direction.getDy() > 0
            && square.getY() + direction.getDy() < this.height) {
            nextSquare = table.get(square.getY() + direction.getDy()).get(square.getX() + direction.getDx());
        }
        
        return nextSquare;
    }
}