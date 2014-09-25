package battleship2000.programlogic.domain.ship;

import battleship2000.programlogic.domain.position.Position;

/**
 * The manifestation of a Ship in the game is a series of ShipParts that can
 * be placed in Squares of a Table.
 * <p>
 * Note! The Ship's direction defines which ShipPart is the Front and which one
 * is the Rear.
 *
 * @author Petri Kallio
 */
public class ShipPart implements Movable, Hittable {
    private Ship motherShip;
    private Position position;
    private boolean intact;
    private boolean shipsFront;
    private boolean shipsRear;
    
    public ShipPart(Ship motherShip) {
        this.intact = true;
        this.shipsFront = false;
        this.shipsRear = false;
        this.motherShip = motherShip;
    }
    
    public ShipPart() {
        this(null);
    }
    
    public void setPosition(int x, int y) {
        this.position = new Position(x, y);
    }
    
    public void hit() {
        this.intact = false;
    }

    @Override
    public boolean isIntact() {
        return intact;
    }

    public void setShipsFront() {
        this.shipsFront = true;
        this.shipsRear = false;
    }

    public void setShipsRear() {
        this.shipsFront = false;
        this.shipsRear = true;
    }
    
    public boolean isShipsFront() {
        return shipsFront;
    }

    public boolean isShipsRear() {
        return shipsRear;
    }
    
    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean move(int dx, int dy) {
        setPosition(position.getX() + dx,
                position.getY() + dy);
        return true;
    }

    @Override
    public boolean move() {return true;}

    @Override
    public boolean isDestroyed() {
        return !this.intact;
    }

    public Ship getMotherShip() {
        return motherShip;
    }
}
