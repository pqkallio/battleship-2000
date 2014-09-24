/**
 * A control class used for placing a single ship on a game table.
 */
package battleship2000.programlogic.control;

import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.table.Table;

public class ShipPlacement {
    private Ship ship;
    private Position[] positions;
    
    public ShipPlacement(Ship ship) {
        this.ship = ship;
    }

    private Table getTable() {
        return this.ship.getTable();
    }

    private ShipPart[] getParts() {
        return this.ship.getParts();
    }

    private Direction getDirection() {
        return this.ship.getDirection();
    }
    
    public boolean setShipsPosition(int x, int y) {
        positions = getPartPlacementBasedOnHeading(x, y);
        checkPartPlacementOnTable(positions);
        
        if (!checkPartCollisionsOnTable(positions)) {
            return false;
        }
        
        return true;
    }
    
    private void checkPartPlacementOnTable(Position[] positions) {
        int firstPartX = positions[0].getX();
        int firstPartY = positions[0].getY();
        int lastPartX = positions[positions.length - 1].getX();
        int lastPartY = positions[positions.length - 1].getY();
        int lastIndex = positions.length - 1;
        int lastTableX = getTable().getWidth() - 1;
        int lastTableY = getTable().getHeight() - 1;
        
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

    private Position[] getPartPlacementBasedOnHeading(int x, int y) {
        Position[] partPositions = new Position[getParts().length];
        
        if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {
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
        if (getDirection() == Direction.WEST || getDirection() == Direction.NORTH) {
            getParts()[0].setShipsFront();
            getParts()[getParts().length - 1].setShipsRear();
        } else {
            getParts()[0].setShipsRear();
            getParts()[getParts().length - 1].setShipsFront();
        }
    }
    
    public void placeShipOnTable() {
        if (ship.isOnTable()) {
            getTable().removePartsFromTable(getParts());
        }
        
        for (int i = 0; i < this.positions.length; i++) {
            getParts()[i].setPosition(positions[i].getX(), positions[i].getY());
        }
        
        addFrontAndRear();
        getTable().placeShipOnTable(ship);
    }

    private boolean checkPartCollisionsOnTable(Position[] positions) {
        for (Position position : positions) {
            if (position.getX() > -1 && position.getX() < getTable().getWidth()
                    && position.getY() > -1 && position.getY() < getTable().getHeight()) {
                if (getTable().getTable().get(position.getY()).get(position.getX()).getShipPart() != null) {
                    if (getTable().getTable().get(position.getY()).get(position.getX()).getShipPart().getMotherShip() != ship) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }

    public Position[] getPositions() {
        return positions;
    }
}
