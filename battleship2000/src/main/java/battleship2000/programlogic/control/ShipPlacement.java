package battleship2000.programlogic.control;

import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.domain.table.Table;
import java.util.List;
import java.util.Map;

/**
 * A control class used for placing a single ship on a game table. 
 * <p>
 * This control class is created to be used for the positioning and placing a 
 * single ship on a game table and is destroyed immediately after it.
 * 
 * @author Petri Kallio
 */
public class ShipPlacement {
    private Ship ship;
    private Position[] positions;
    
    /**
     * A constructor to create a new instance of the class.
     * 
     * @param ship  a ship to be set as an object variable
     */
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
    
    /**
     * Checks if a ship can be placed on a position given as two integer parameters.
     * 
     * @param x     an x-coordinate on a game table
     * @param y     a y-coordinate on a game table
     * @return      true if the ship can be placed on the coordinates given.
     */
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
    
    /**
     * Sets the {@link battleship2000.programlogic.domain.ship.Ship}'s front and 
     * rear {@link battleship2000.programlogic.domain.ship.ShipPart}s based on 
     * the Ship's {@link battleship2000.programlogic.domain.ship.Direction}.
     */
    public void addFrontAndRear() {
        if (getDirection() == Direction.WEST || getDirection() == Direction.NORTH) {
            getParts()[0].setShipsFront();
            getParts()[getParts().length - 1].setShipsRear();
        } else {
            getParts()[0].setShipsRear();
            getParts()[getParts().length - 1].setShipsFront();
        }
    }
    
    /**
     * Places the ship on the table by removing its parts from their previous
     * squares on the game table and setting them to the new squares.
     */
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
            int x = position.getX();
            int y = position.getY();
            Map<Integer, Map<Integer, Square>> table = getTable().getTableAsMap();
            
            if (checkCoordinatesAreOnTheTable(x, y)) {
                if (table.get(y).get(x).getSetShipPart() != null) {
                    if (table.get(y).get(x).getSetShipPart().getMotherShip() != ship) {
                        return false;
                    }
                } else if (!checkNeighboringSquares(table, x, y)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    /**
     * Returns the positions of the ship's parts.
     * <p>
     * Note! The positions returned are not the positions fixed on the game table's
     * squares but the positions of the ship's parts before they are set on the 
     * table.
     * 
     * @return  the positions of the ship's parts as an array
     */
    public Position[] getPositions() {
        return positions;
    }

    private boolean checkNeighboringSquares(Map<Integer, Map<Integer, Square>> table, int x, int y) {
        List<Direction> directionsWithNeighborSquares 
                    = Direction.EAST.getMainAndBetweenMainDirections();
        
        for (Direction direction : directionsWithNeighborSquares) {
            int dx = direction.getDx();
            int dy = direction.getDy();
            
            if (checkCoordinatesAreOnTheTable(x + dx, y + dy)) {
                if (table.get(y + dy).get(x + dx).getSetShipPart() != null) {
                    if (table.get(y + dy).get(x + dx).getSetShipPart().getMotherShip() != ship) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }

    private boolean checkCoordinatesAreOnTheTable(int x, int y) {
        int tableWidth = getTable().getWidth();
        int tableHeight = getTable().getHeight();
        
        if (x > -1 && x < tableWidth && y > -1 && y < tableHeight) {
            return true;
        } else {
            return false;
        }
    }
}
