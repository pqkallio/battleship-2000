
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja;

import fi.petrikallio.battleship2000.sovelluslogiikka.BattleShipGame;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Kentta;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Ruutu;
import java.util.Random;

public class Tietokone extends Pelaaja {

    public Tietokone() {
        
    }
    
    public Tietokone(BattleShipGame peli) {
        super(peli);
    }
    
    @Override
    public Ruutu valitseRuutu(Kentta pelikentta, boolean ruutuaVoiPommittaaUseasti) {
        int x = 0;
        int y = 0;
        
        while (true) {
            x = arvoLuku(pelikentta.haeLeveys());
            y = arvoLuku(pelikentta.haeKorkeus());
            
            if (ruutuaVoiPommittaaUseasti 
                    || !pelikentta.haeKentta().get(y).get(x).isPommitettu() ) {
                break;
            }
        }
        
        return pelikentta.haeKentta().get(y).get(x);
    }
    
    @Override
    public String toString() {
        return "tietokone";
    }

    @Override
    public void sijoitteleAluksetPelikentalle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int arvoLuku(int luku) {
        int arvottuLuku = new Random().nextInt(luku);
        return arvottuLuku;
    }
}
