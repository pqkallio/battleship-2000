
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja;

public class Tietokone extends Pelaaja {

    public Tietokone() {
        
    }
    
    @Override
    public void pelaaVuoro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return "tietokone";
    }

    @Override
    public void sijoitteleAluksetPelikentalle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
