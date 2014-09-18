
package battleship2000.programlogic.domain.ship;

import battleship2000.programlogic.control.SetShipsPosition;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.rules.SizeLimits;
import java.util.ArrayList;
import java.util.List;

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
        
//        if (setPosition(parts[0].getX(), parts[0].getY())) {
//            return true;
//        } else {
//            this.direction = getNeighborDirection(-1);
//            return false;
//        }
    }

    @Override
    public void turnCounterClockwise() {
        this.direction = getNeighborDirection(-1);
        
//        if (setPosition(parts[0].getX(), parts[0].getY())) {
//            return true;
//        } else {
//            this.direction = getNeighborDirection(1);
//            return false;
//        }
    }

    private void addPossibleDirections() {
        for (Direction directionEnum : Direction.values()) {
            if (directionEnum.getAngle() % 90 == 0) {
                this.possibleDirections.add(directionEnum);
            }
        }
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

//    private ShipPart[] sijoitaOsat(int osienLukumaara, Direction direction, 
//            int x, int y) {
////        ShipPart[] aluksenosat = new ShipPart[osienLukumaara];
////        
////        for (int i = 0; i < aluksenosat.length; i++) {
////            aluksenosat[i] = new ShipPart(x, y);
////        }
//        
//        maaritaOsienSijaintiPelikentalla(aluksenosat, x, y, direction);
//        
//        return aluksenosat;
//    }

    public int getX() {
        return parts[0].getX();
    }
    
    public int getY() {
        return parts[0].getY();
    }
    
    public boolean setPosition(int x, int y) {
        SetShipsPosition setPosition = new SetShipsPosition(this);
        
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

//    private void checkPartPlacementOnTable(ShipPart[] shipParts) {
//        int firstPartX = shipParts[0].getX();
//        int firstPartY = shipParts[0].getY();
//        int lastPartX = shipParts[shipParts.length - 1].getX();
//        int lastPartY = shipParts[shipParts.length - 1].getY();
//        int lastIndex = shipParts.length - 1;
//        int lastTableX = this.table.getWidth() - 1;
//        int lastTableY = this.table.getHeight() - 1;
//        
//        if (lastPartX > lastTableX) {
//            setPosition(lastPartX - (lastPartX - lastTableX) - lastIndex, lastPartY);
//        } else if (lastPartY > lastTableY) {
//            setPosition(lastPartX, lastPartY - (lastPartY - lastTableY) - lastIndex);
//        } else if (firstPartX < 0) {
//            setPosition((-1 * firstPartX) + firstPartX, lastPartY);
//        } else if (firstPartY < 0) {
//            setPosition(0, (-1 * lastPartY) + lastPartY);
//        } else {
//            this.table.placeShipOnTable(this);
//        }
//        
//        if (aluksenosat[aluksenosat.length - 1].getX() > this.table.getWidth() - 1) {
//            move((aluksenosat[aluksenosat.length - 1].getX() - this.table.getWidth()), 0);
//        } else if (aluksenosat[aluksenosat.length - 1].getY() > this.table.getHeight()) {
//            move(0, (aluksenosat[aluksenosat.length - 1].getY() - this.table.getHeight()));
//        } else if (aluksenosat[0].getX() < 0) {
//            move(-1 * (aluksenosat[0].getX()), 0);
//        } else if (aluksenosat[0].getY() < 0) {
//            move(0, -1 * (aluksenosat[0].getY()));
//        } else {
//            this.table.placeShipOnTable(this);
//        }
//    }

    public List<Square> getSquaresToBomb(Square square) {
        List<Square> squaresToBomb = new ArrayList<>();
        squaresToBomb.add(square);
        return squaresToBomb;
    }

//    private void checkPartCollisionOnTable(ShipPart[] shipParts) {
//        List<ShipPart> collidingParts = new ArrayList<>();
//        
//        for (ShipPart shipPart : shipParts) {
//            if (table.getTable().get(shipPart.getY()).get(shipPart.getX()).getShipPart() != null) {
//                collidingParts.add(shipPart);
//            }
//        }
//        
//        if (!collidingParts.isEmpty()) {
//            resolveCollisions(collidingParts);
//        }
//    }
//
//    private void resolveCollisions(List<ShipPart> collidingParts) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
