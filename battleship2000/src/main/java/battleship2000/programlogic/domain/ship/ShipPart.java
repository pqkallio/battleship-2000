
package battleship2000.programlogic.domain.ship;

public class ShipPart implements Movable, Hittable {
    private Ship motherShip;
    private int x;
    private int y;
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
        this.x = x;
        this.y = y;
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
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean move(int dx, int dy) {
        setPosition(this.x + dx,
                this.y + dy);
        return true;
    }

    @Override
    public boolean move() {return true;}

//    private int tarkastettuSijaintiX(int sijainti) {
//        int max = emoalus.getPelikentta().haeLeveys() - 1;
//        
//        if (sijainti < 0) return 0;
//        else if (sijainti > max) return max;
//        else return sijainti;
//    }

    @Override
    public boolean isDestroyed() {
        return !this.intact;
    }

    public Ship getMotherShip() {
        return motherShip;
    }

//    private int tarkastettuSijaintiY(int sijainti) {
//        int max = emoalus.getPelikentta().haeKorkeus()- 1;
//        
//        if (sijainti < 0) return 0;
//        else if (sijainti > max) return max;
//        else return sijainti;
//    }
}
