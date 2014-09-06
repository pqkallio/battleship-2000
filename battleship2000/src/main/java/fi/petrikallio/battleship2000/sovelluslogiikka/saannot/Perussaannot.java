
package fi.petrikallio.battleship2000.sovelluslogiikka.saannot;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alustyyppi;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaajatyyppi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Perussaannot extends Saannot {
    private final Alustyyppi[] alustyypit = {Alustyyppi.TULENJOHTOLAIVA, 
                                             Alustyyppi.TYKKILAIVA,
                                             Alustyyppi.SUKELLUSVENE,
                                             Alustyyppi.OHJUSALUS,
                                             Alustyyppi.LENTOTUKIALUS};
    
    public Perussaannot(boolean tietokonettaVastaan) {
        alukset();
        pelaajat(tietokonettaVastaan);
        super.asetaKentanKoko(10, 10);
        super.asetaLiikkumissaanto(false);
        super.asetaAlustenErikoistoiminnot(false);
    }

    private void alukset() {
        List<Alustyyppi> alukset = new ArrayList<>();
        Collections.addAll(alukset, alustyypit);
        super.asetaAlukset(alukset);
    }

    private void pelaajat(boolean tietokonettaVastaan) {
        List<Pelaajatyyppi> pelaajat = new ArrayList<>();
        
        pelaajat.add(Pelaajatyyppi.IHMINEN);
        
        if (tietokonettaVastaan) {
            pelaajat.add(Pelaajatyyppi.TIETOKONE);
        } else {
            pelaajat.add(Pelaajatyyppi.IHMINEN);
        }
        
        super.asetaPelaajat(pelaajat);
        
    }
}
