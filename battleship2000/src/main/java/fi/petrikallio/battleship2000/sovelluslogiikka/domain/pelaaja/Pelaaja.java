
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja;

import fi.petrikallio.battleship2000.sovelluslogiikka.BattleShipGame;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alus;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Kentta;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Pelikentta;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Ruutu;
import java.util.List;

public abstract class Pelaaja {
    private List<Alus> alukset;
    private int pisteet;
    private Pelikentta pelikentta;
    private BattleShipGame peli;
    private int laukaukset;
    
    public abstract Ruutu valitseRuutu(Kentta pelikentta, 
            boolean ruutuaVoiPommittaaUseasti);
    public abstract void sijoitteleAluksetPelikentalle();
    
    public Pelaaja() {
        this(null);
    }
    
    public Pelaaja(BattleShipGame peli) {
        this.pisteet = 0;
        this.laukaukset = 0;
        this.peli = peli;
    }

    public int haePisteet() {
        return pisteet;
    }

    public void asetaAlukset(List<Alus> alukset) {
        this.alukset = alukset;
    }

    public void asetaPeli(BattleShipGame peli) {
        this.peli = peli;
    }

    public BattleShipGame haePeli() {
        return peli;
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
        if (pisteet > -1) {
            this.pisteet += pisteet;
        }
    }
    
    public void lisaaLaukaukset(int laukaukset) {
        if (laukaukset > 0) {
            this.laukaukset += laukaukset;
        }
    }   
    
    public boolean kaikkiAluksetTuhottu() {
        for (Alus alus : alukset) {
            if (!alus.onTuhottu()) return false;
        }
        
        return true;
    }
}
