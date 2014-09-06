
package fi.petrikallio.battleship2000.sovelluslogiikka.ohjaus;

import fi.petrikallio.battleship2000.sovelluslogiikka.BattleShipGame;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alus;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alustyyppi;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaaja;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaajatyyppi;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Pelikentta;
import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Saannot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LuoPeli implements Ohjausluokka {
    private Saannot saannot;
    
    public LuoPeli(Saannot saannot) {
        this.saannot = saannot;
    }
    
    @Override
    public Object suorita() {
        BattleShipGame peli = new BattleShipGame();
        
        try {
            peli.asetaPelaajat(luoPelaajat());
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getClass() + ": " + e.getLocalizedMessage());
        }
        
        peli.asetaAluksetLiikkuvat(this.saannot.liikkuvatkoAlukset());
        peli.asetaAlustenErikoistoiminnot(this.saannot.aluksillaErikoistoiminnot());
        
        return peli;
    }

    private List<Pelaaja> luoPelaajat() throws InstantiationException, IllegalAccessException {
        List<Pelaaja> pelaajat = new ArrayList<>();
        
        for (Pelaajatyyppi tyyppi : this.saannot.haePelaajatyypit()) {
            Pelaaja pelaaja = (Pelaaja) tyyppi.haeLuokka().newInstance();
            pelaaja.asetaAlukset(lisaaPelaajalleAlukset());
            pelaaja.asetaPelikentta(lisaaPelaajallePelikentta());
            pelaajat.add(pelaaja);
        }
        
        return pelaajat;
    }

    private List<Alus> lisaaPelaajalleAlukset() throws InstantiationException, IllegalAccessException {
        List<Alus> pelaajanAlukset = new ArrayList<>();
        
        for (Alustyyppi tyyppi : this.saannot.haeAlustyypit()) {
            pelaajanAlukset.add((Alus)tyyppi.haeLuokka().newInstance());
        }
        
        return pelaajanAlukset;
    }

    private Pelikentta lisaaPelaajallePelikentta() {
        int leveys = this.saannot.haeKentanLeveys();
        int korkeus = this.saannot.haeKentanKorkeus();
        return new Pelikentta(leveys, korkeus);
    }
}
