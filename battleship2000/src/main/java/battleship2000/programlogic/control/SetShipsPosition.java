
package battleship2000.programlogic.control;

import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.table.Table;

public class SetShipsPosition {
    private Ship ship;
    private Table table;
    private ShipPart[] parts;
    private Position[] positions;
    private Direction direction;
    
    public SetShipsPosition(Ship ship) {
        this.ship = ship;
        this.table = ship.getTable();
        this.parts = ship.getParts();
        this.direction = ship.getDirection();
    }
    
    public boolean setShipsPosition(int x, int y) {
        positions = getPartPlacementBasedOnHeading(x, y);
        checkPartPlacementOnTable(positions);
        
        if (!checkPartCollisionsOnTable(positions)) {
            return false;
        }
        
        return true;
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
        }
    }
    
    private void checkPartPlacementOnTable(Position[] positions) {
        int firstPartX = positions[0].getX();
        int firstPartY = positions[0].getY();
        int lastPartX = positions[positions.length - 1].getX();
        int lastPartY = positions[positions.length - 1].getY();
        int lastIndex = positions.length - 1;
        int lastTableX = this.table.getWidth() - 1;
        int lastTableY = this.table.getHeight() - 1;
        
        if (ship.getDirection() == Direction.EAST 
                || ship.getDirection() == Direction.WEST) {
            if (lastPartX > lastTableX) {
                setShipsPosition(lastPartX - (lastPartX - lastTableX) - lastIndex, lastPartY);
            } else if (lastPartY > lastTableY) {
                setShipsPosition(lastPartX, lastPartY - (lastPartY - lastTableY));
            }
        } else {
            if (lastPartX > lastTableX) {
                setShipsPosition(lastPartX - (lastPartX - lastTableX), lastPartY);
            } else if (lastPartY > lastTableY) {
                setShipsPosition(lastPartX, lastPartY - (lastPartY - lastTableY) - lastIndex);
            }
        }
        
        if (firstPartX < 0) {
            setShipsPosition((-1 * firstPartX) + firstPartX, lastPartY);
        } else if (firstPartY < 0) {
            setShipsPosition(0, (-1 * lastPartY) + lastPartY);
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


    private Position[] getPartPlacementBasedOnHeading(int x, int y) {
        Position[] partPositions = new Position[parts.length];
        
        if (direction == Direction.EAST || direction == Direction.WEST) {
            for (int i = 0; i < partPositions.length; i++) {
                partPositions[i] = new Position(x + i, y);
            }
        } else {
            for (int i = 0; i < partPositions.length; i++) {
                partPositions[i] = new Position(x, y + i);
            }
        }
        
        return partPositions;
    }

    private void addFrontAndRear() {
        if (direction == Direction.WEST || direction == Direction.NORTH) {
            parts[0].setShipsFront();
            parts[parts.length - 1].setShipsRear();
        } else {
            parts[0].setShipsRear();
            parts[parts.length - 1].setShipsFront();
        }
    }
    
    public void placeShipOnTable() {
        if (ship.isOnTable()) {
            table.removePartsFromTable(parts);
        }
        
        for (int i = 0; i < this.positions.length; i++) {
            parts[i].setPosition(positions[i].getX(), positions[i].getY());
        }
        
        addFrontAndRear();
        this.table.placeShipOnTable(ship);
    }

    private boolean checkPartCollisionsOnTable(Position[] positions) {
        for (Position position : positions) {
            if (position.getX() > -1 && position.getX() < table.getWidth()
                    && position.getY() > -1 && position.getY() < table.getHeight()) {
                if (table.getTable().get(position.getY()).get(position.getX()).getShipPart() != null) {
                    if (table.getTable().get(position.getY()).get(position.getX()).getShipPart().getMotherShip() != ship) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
