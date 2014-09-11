
package battleship2000.programlogic.domain.table;

import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.points.Points;

public class Square implements Comparable<Square> {
    private int x;
    private int y;
    private ShipPart part;
    private boolean hit;
    
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.hit = false;
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
        return x;
    }

    public int getY() {
        return y;
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
        if (this.x < comparedTo.getX()) return -1;
        else if (this.x > comparedTo.getX()) return 1;
        else {
            if (this.y < comparedTo.getY()) return -1;
            else if (this.y > comparedTo.getY()) return 1;
            else return 0;
        }
    }
}
