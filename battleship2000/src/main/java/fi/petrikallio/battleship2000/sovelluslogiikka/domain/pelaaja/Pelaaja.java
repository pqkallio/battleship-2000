
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alus;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Pelikentta;
import fi.petrikallio.battleship2000.util.Kokonaislukutyokalut;
import java.util.List;

public abstract class Pelaaja {
    private List<Alus> alukset;
    private int pisteet;
    private Pelikentta pelikentta;
    
    public abstract void pelaaVuoro();
    public abstract void sijoitteleAluksetPelikentalle();
    
    public Pelaaja() {
        this.pisteet = 0;
    }

    public int haePisteet() {
        return pisteet;
    }

    public void asetaAlukset(List<Alus> alukset) {
        this.alukset = alukset;
    }

    public List<Alus> haeAlukset() {
        return alukset;
    }

    public void asetaPelikentta(Pelikentta pelikentta) {
        this.pelikentta = pelikentta;
    }

    public Pelikentta haePelikentta() {
        return pelikentta;
    }
    
    public void lisaaPisteet(int pisteet) {
        if (Kokonaislukutyokalut.onPositiivinen(pisteet)) {
            this.pisteet += pisteet;
        }
    }
}
