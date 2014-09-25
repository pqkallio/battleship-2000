package battleship2000.programlogic.domain.ship;

import battleship2000.programlogic.control.ShipPlacement;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.rules.SizeLimits;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that defines the size, name heading, capabilities and other attributes
 * of the ships used in the game.
 *
 * @author Petri Kallio
 */
public class Ship implements Movable, Turnable, Hittable {
    protected Direction direction;
    protected ShipPart[] parts;
    protected List<Direction> possibleDirections;
    protected Table table;
    protected int shotsPerRound;
    protected boolean isOnTable;
    protected String name;
    
    public Ship() {
        this(3, 1, "Ship");
    }
    
    public Ship(int length) {
        this(length, 1, "Ship");
    }
    
    public Ship(int length, int shotsPerRound, String name) {
        this.name = name;
        this.possibleDirections = new ArrayList<>();
        addPossibleDirections();
        this.direction = Direction.EAST;
        createShipParts(length, this.direction);
        this.shotsPerRound = shotsPerRound;
        this.isOnTable = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean isOnTable() {
        return isOnTable;
    }

    public void setIsOnTable(boolean isOnTable) {
        this.isOnTable = isOnTable;
        table.removePartsFromField(this);
    }
    
    public void setTable(Table gameTable) {
        this.table = gameTable;
    }

    public Table getTable() {
        return table;
    }
    
    public ShipPart[] getParts() {
        return parts;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if (possibleDirections.contains(direction)) {
            this.direction = direction;
            setPosition(parts[0].getX(), parts[0].getY());
        }
    }

    public List<Direction> getPossibleDirections() {
        return possibleDirections;
    }

    public int getShotsPerRound() {
        return shotsPerRound;
    }
    
    @Override
    public boolean move(int dx, int dy) {
        return setPosition(parts[0].getX() + dx, parts[0].getY() + dy);
    }
    
    @Override
    public boolean move() {
        boolean canMove = true;
        
        for (ShipPart part : parts) {
            if (part.getX() + direction.getDx() < 0
                    || part.getX() + direction.getDx() > table.getWidth() - 1
                    || part.getY() + direction.getDy() < 0
                    || part.getY() + direction.getDy() > table.getHeight() - 1) {
                canMove = false;
            }
        }
        
        if (canMove) return move(this.direction.getDx(), this.direction.getDy());
        else return false;
    }

    private void createShipParts(int length, Direction direction) {
        int amountOfParts = getFinalSize(length);
        
        ShipPart[] shipParts = new ShipPart[amountOfParts];
        
        for (int i = 0; i < shipParts.length; i++) {
            shipParts[i] = new ShipPart(this);
        }
        
        this.parts = shipParts;
    }
    
    public int getShipLength() {
        return parts.length;
    }

    @Override
    public void turnClockwise() {
        this.direction = getNeighborDirection(1);
    }

    @Override
    public void turnCounterClockwise() {
        this.direction = getNeighborDirection(-1);
    }

    private void addPossibleDirections() {
        this.possibleDirections = Direction.EAST.getMainDirections();
    }

    private int getFinalSize(int length) {
        int min = SizeLimits.getShipsMinimumSize();
        int max = SizeLimits.getShipsMaximumSize();
        
        if (length >= min && length <= max) {
            return length;
        }
        
        if (length < min) return min;
        else return max;
    }

    private Direction getNeighborDirection(int i) {
        int indexOfPresentDirection = this.possibleDirections.indexOf(this.direction);
        
        if (indexOfPresentDirection + i >= 0 &&
                indexOfPresentDirection + i < this.possibleDirections.size()) {
            return this.possibleDirections.get(indexOfPresentDirection + i);
        } else if (indexOfPresentDirection + i < 0) {
            return this.possibleDirections.get(this.possibleDirections.size()-1);
        }
        
        return this.possibleDirections.get(0);
    }

    @Override
    public boolean isIntact() {
        for (ShipPart part : parts) {
            if (!(part.isIntact())) return false;
        }
        
        return true;
    }

    public int getX() {
        return parts[0].getX();
    }
    
    public int getY() {
        return parts[0].getY();
    }
    
    public boolean setPosition(int x, int y) {
        ShipPlacement setPosition = new ShipPlacement(this);
        
        if (setPosition.setShipsPosition(x, y)) {
            setIsOnTable(true);
            setPosition.placeShipOnTable();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isDestroyed() {
        for (ShipPart shipPart : parts) {
            if (shipPart.isIntact()) return false;
        }
        
        return true;
    }

    public List<Square> getSquaresToBomb(Square square) {
        List<Square> squaresToBomb = new ArrayList<>();
        squaresToBomb.add(square);
        return squaresToBomb;
    }
}
