package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.ship.Ship;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * GameTable class implements the Table interface and works as a storage to
 * its squares and their placement on the game table.
 *
 * @author Petri Kallio
 */
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
    public Map<Integer, Map<Integer, Square>> getTableAsMap() {
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

    private void placeShipOnTable(ShipPart[] parts) {
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
                    if (part == this.table.get(part.getY()).get(part.getX()).getSetShipPart()) {
                        this.table.get(part.getY()).get(part.getX()).removeShipPart();
                    }
                }
            }
        }
    }

    @Override
    public void removePartsFromTable(Ship ship) {
        removePartsFromTable(ship.getParts());
    }

    @Override
    public Square getNextSquareBasedOnDirection(Square square, Direction direction) {
        Square nextSquare = null;
        
        if (square.getTable() == this && square.getX() + direction.getDx() >= 0
            && square.getX() + direction.getDx() < this.width
            && square.getY() + direction.getDy() >= 0
            && square.getY() + direction.getDy() < this.height) {
            nextSquare = table.get(square.getY() + direction.getDy()).get(square.getX() + direction.getDx());
        }
        
        return nextSquare;
    }

    @Override
    public List<Square> getNeighborSquares(Square square) {
        List<Direction> directionsWithNeighborSquares 
                = Direction.EAST.getMainAndBetweenMainDirections();
        List<Square> neighborSquares = new ArrayList<>();
        int x = square.getX();
        int y = square.getY();
        
        for (Direction direction : directionsWithNeighborSquares) {
            int dx = direction.getDx();
            int dy = direction.getDy();
            
            if (checkCoordinatesAreOnTheTable(x + dx, y + dy)) {
                neighborSquares.add(table.get(y + dy).get(x + dx));
            }
        }
        
        return neighborSquares;
    }
    
    private boolean checkCoordinatesAreOnTheTable(int x, int y) {
        if (x > -1 && x < width && y > -1 && y < height) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public List<Square> getSquaresBasedOnDirection(Square square, Direction direction, int amount) {
        List<Square> squaresBasedOnDirection = null;
        
        squaresBasedOnDirection = getNSquaresToTheDirection(square, direction, amount);
        
        return squaresBasedOnDirection;
    }

    private List<Square> getNSquaresToTheDirection(Square square, Direction direction, int amount) {
        List<Square> squares = new ArrayList<>();
        int sx = square.getX();
        int sy = square.getY();
        int dDx = direction.getDx();
        int dDy = direction.getDy();
        
        for (int i = 1; i <= amount; i++) {
            int x = 0;
            int y = 0;
            
            if (dDx != 0) {
                x = sx + (dDx * i);
            } else {
                x = sx;
            }
            
            if (dDy != 0) {
                y = sy + (dDy * i);
            } else {
                y = sy;
            }
            
            if (checkCoordinatesAreOnTheTable(x, y)) {
                squares.add(table.get(y).get(x));
            }
        }
        
        return squares;
    }
}