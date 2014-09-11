
package battleship2000.programlogic.control;

import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.table.Table;

public class SetShipsPosition {
    private Ship ship;
    private Table table;
    private ShipPart[] parts;
    private Direction heading;
    
    public SetShipsPosition(Ship ship) {
        this.ship = ship;
        this.table = ship.getTable();
        this.parts = ship.getParts();
        this.heading = ship.getHeading();
    }
    
    public boolean setShipsPosition(int x, int y) {
        table.removePartsFromTable(parts);
        
        partPlacementBasedOnHeading(x, y);
        
        addFrontAndRear();
        
        if (!checkPartCollisionsOnTable(parts)) {
            return false;
        } else { 
            checkPartPlacementOnTable(parts);
            return true;
        }
    }
    
    private void checkPartPlacementOnTable(ShipPart[] shipParts) {
        int firstPartX = shipParts[0].getX();
        int firstPartY = shipParts[0].getY();
        int lastPartX = shipParts[shipParts.length - 1].getX();
        int lastPartY = shipParts[shipParts.length - 1].getY();
        int lastIndex = shipParts.length - 1;
        int lastTableX = this.table.getWidth() - 1;
        int lastTableY = this.table.getHeight() - 1;
        
        if (lastPartX > lastTableX) {
            setShipsPosition(lastPartX - (lastPartX - lastTableX) - lastIndex, lastPartY);
        } else if (lastPartY > lastTableY) {
            setShipsPosition(lastPartX, lastPartY - (lastPartY - lastTableY));
        } else if (firstPartX < 0) {
            setShipsPosition((-1 * firstPartX) + firstPartX, lastPartY);
        } else if (firstPartY < 0) {
            setShipsPosition(0, (-1 * lastPartY) + lastPartY);
        } else {
            this.table.placeShipOnTable(ship);
        }
    }
    
    private boolean checkPartCollisionsOnTable(ShipPart[] shipParts) {
        for (ShipPart part : shipParts) {
            if (part.getX() > -1 && part.getX() < table.getWidth()
                    && part.getY() > -1 && part.getY() < table.getHeight()) {
                if (table.getTable().get(part.getY()).get(part.getX()).getShipPart() != null) {
                    return false;
                }
            }
        }
        return true;
    }


    private void partPlacementBasedOnHeading(int x, int y) {
        if (heading == Direction.EAST || heading == Direction.WEST) {
            for (int i = 0; i < parts.length; i++) {
                parts[i].setPosition(x + i, y);
            }
        } else {
            for (int i = 0; i < parts.length; i++) {
                parts[i].setPosition(x, y + i);
            }
        }
    }

    private void addFrontAndRear() {
        if (heading == Direction.WEST || heading == Direction.NORTH) {
            parts[0].setShipsFront();
            parts[parts.length - 1].setShipsRear();
        } else {
            parts[0].setShipsRear();
            parts[parts.length - 1].setShipsFront();
        }
    }
}
