
package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.points.Points;
import battleship2000.programlogic.domain.position.Position;

public class Square implements Comparable<Square> {
    private Position position;
    private Table table;
    private ShipPart part;
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

    public ShipPart getPart() {
        return part;
    }
    
    public void setShipPart(ShipPart part) {
        this.part = part;
    }

    public ShipPart getShipPart() {
        return part;
    }
    
    public void removeShipPart() {
        this.part = null;
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
        
        if (part != null) {
            if (part.getMotherShip().isDestroyed()) {
                return Points.DESTROYED.getPoints();
            } else {
                return Points.HIT.getPoints();
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
}
