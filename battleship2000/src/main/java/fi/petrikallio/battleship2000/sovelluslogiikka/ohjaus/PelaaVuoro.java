
package fi.petrikallio.battleship2000.sovelluslogiikka.ohjaus;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alus;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Tykkilaiva;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaaja;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Ruutu;
import java.util.List;

public class PelaaVuoro implements Ohjausluokka {
    private Pelaaja pelaaja;
    private Pelaaja vastustaja;
    private Ruutu ruutu;
    private Alus kaytettavaAlus;
    
    public PelaaVuoro(Pelaaja pelaaja, Pelaaja vastustaja, Ruutu ruutu) {
        this(pelaaja, vastustaja, ruutu, new Tykkilaiva());
    }
    
    public PelaaVuoro(Pelaaja pelaaja, Pelaaja vastustaja, Ruutu ruutu, Alus kaytettavaAlus) {
        this.pelaaja = pelaaja;
        this.vastustaja = vastustaja;
        this.ruutu = ruutu;
        this.kaytettavaAlus = kaytettavaAlus;
    }
    
    @Override
    public Object suorita() {
        List<Ruutu> pommitettavatRuudut = kaytettavaAlus.getPommitettavatRuudut(ruutu);
        int kierroksenPisteet = 0;
        
        for (Ruutu pommitettavaRuutu : pommitettavatRuudut) {
            kierroksenPisteet += pommitettavaRuutu.pommita();
        }
        
        pelaaja.lisaaPisteet(kierroksenPisteet);
        
        if (vastustaja.kaikkiAluksetTuhottu()) {
            return true;
        } else {
            return false;
        }
    }
}
