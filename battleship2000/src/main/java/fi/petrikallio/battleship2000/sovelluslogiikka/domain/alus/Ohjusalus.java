
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

public class Ohjusalus extends Alus {
    
    public Ohjusalus() {
        super(4, 5);
    }
    
    @Override
    public String toString() {
        return "ohjustuhooja";
    }
}
