
package battleship2000.programlogic.domain.ship;

public enum ShipType {
    AIRCARRIER(Aircarrier.class),
    MISSILE_SHIP(MissileShip.class),
    SUBMARINE(Submarine.class),
    COMMANDER(Commander.class),
    CANNON_SHIP(CannonShip.class);
    
    private Class luokka;
    
    private ShipType(Class luokka) {
        this.luokka = luokka;
    }

    public Class getShipClass() {
        return luokka;
    }
}
