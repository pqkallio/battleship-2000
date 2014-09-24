/**
 * The ShipType enumerator is used to pass different ship types from the
 * abstract Rules class to the CreateGame control class used to create a new game.
 */

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
