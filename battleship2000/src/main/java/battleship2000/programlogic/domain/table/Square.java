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
    private ShipPart floatingPiece;
    private boolean hit;
    
    public Square(Table table, int x, int y) {
        if (table != null && x > -1 && x < table.getWidth() 
                && y > -1 && y < table.getHeight()) {
            this.position = new Position(x, y);
        } else {
            throw new IllegalArgumentException("The parameters given aren't valid");
        }
        
        this.table = table;
        this.hit = false;
    }

    public Table getTable() {
        return table;
    }
    
    public void setShipPart(ShipPart part) {
        this.setPart = part;
    }

    public ShipPart getSetShipPart() {
        return setPart;
    }
    
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
    
    public void floatAPiece(ShipPart part) {
        floatingPiece = part;
    }
    
    public void removeFloatingPiece() {
        floatingPiece = null;
    }
    
    public ShipPart getFloatingPiece() {
        return floatingPiece;
    }
}
