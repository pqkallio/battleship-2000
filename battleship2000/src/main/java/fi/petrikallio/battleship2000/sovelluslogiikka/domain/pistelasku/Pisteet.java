
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pistelasku;

public enum Pisteet {
    OHI(0),
    OSUI(5),
    TUHOSI(10);
    
    private int pisteet;
    
    private Pisteet(int pisteet) {
        this.pisteet = pisteet;
    }

    public int getPisteet() {
        return pisteet;
    }
}
