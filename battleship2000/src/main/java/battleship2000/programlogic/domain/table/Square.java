package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.points.Points;
import battleship2000.programlogic.domain.position.Position;

/**
 * A Square is a part of a Table. It contains information about its state and
 * a reference to a maximum of one ship part.
 *
 * @author Petri Kallio
 */
public class Square implements Comparable<Square> {
    private Position position;
    private Table table;
    private ShipPart setPart;
    private ShipPart floatingPart;
    private boolean hit;
    
    /**
     * A new instance of the class.
     * 
     * @param table     the Table object the square is a part of
     * @param x         the x-coordinate of the Square
     * @param y         the y-coordinate of the Square
     */
    public Square(Table table, int x, int y) {
        this.position = new Position(x, y);
        this.table = table;
        this.hit = false;
    }

    public Table getTable() {
        return table;
    }
    
    /**
     * Sets the {@link battleship2000.programlogic.domain.ship.ShipPart} given as
     * parameter as the Square's set ShipPart.
     * <p>
     * The set ShipPart is a ShipPart that is tied to a square and set 
     * on the {@link battleship2000.programlogic.domain.table.Table} that the 
     * square is a part of.
     * 
     * @param part  the ShipPart to set as the Square's set ShipPart
     */
    public void setShipPart(ShipPart part) {
        this.setPart = part;
    }

    public ShipPart getSetShipPart() {
        return setPart;
    }
    
    /**
     * Sets the Square's set ShipPart as null.
     */
    public void removeShipPart() {
        this.setPart = null;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public boolean isHit() {
        return hit;
    }
    
    /**
     * Bombs the square and sets its hit boolean value to true.
     * 
     * @return      points awarded for bombing the square
     */
    public int bomb() {
        this.hit = true;
        
        if (setPart != null) {
            setPart.hit();
            if (setPart.getMotherShip() != null) {
                if (setPart.getMotherShip().isDestroyed()) {
                    return Points.DESTROYED.getPoints();
                } else {
                    return Points.HIT.getPoints();
                }
            }
        }
        
        return Points.MISSED.getPoints();
    }
    
    @Override
    public int compareTo(Square comparedTo) {
        if (this.getY() < comparedTo.getY()) return -1;
        else if (this.getY() > comparedTo.getY()) return 1;
        else {
            if (this.getX() < comparedTo.getX()) return -1;
            else if (this.getX() > comparedTo.getX()) return 1;
            else return 0;
        }
    }
    
    /**
     * Sets the {@link battleship2000.programlogic.domain.ship.ShipPart} given as
     * parameter as the Square's floating ShipPart.
     * <p>
     * The floating part is a ShipPart that is tied to a square, but is not set 
     * on the {@link battleship2000.programlogic.domain.table.Table} that the 
     * square is a part of.
     * 
     * @param part  the ShipPart to set as the Square's floating part
     */
    public void floatAPart(ShipPart part) {
        floatingPart = part;
    }
    
    /**
     * Sets the Square's floating part as null.
     */
    public void removeFloatingPart() {
        floatingPart = null;
    }
    
    public ShipPart getFloatingPiece() {
        return floatingPart;
    }
}
