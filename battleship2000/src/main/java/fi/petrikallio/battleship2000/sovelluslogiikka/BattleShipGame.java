
package fi.petrikallio.battleship2000.sovelluslogiikka;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.List;

public class BattleShipGame {
    private List<Pelaaja> pelaajat;
    private boolean aluksetLiikkuvat;
    private boolean alustenErikoistoiminnot;
    
    public BattleShipGame() {
        this.pelaajat = new ArrayList<>();
    }

    public List<Pelaaja> haePelaajat() {
        return pelaajat;
    }

    public void asetaPelaajat(List<Pelaaja> pelaajat) {
        this.pelaajat = pelaajat;
    }

    public void asetaAluksetLiikkuvat(boolean aluksetLiikkuvat) {
        this.aluksetLiikkuvat = aluksetLiikkuvat;
    }

    public void asetaAlustenErikoistoiminnot(boolean alustenErikoistoiminnot) {
        this.alustenErikoistoiminnot = alustenErikoistoiminnot;
    }

    public boolean aluksetLiikkuvat() {
        return aluksetLiikkuvat;
    }

    public boolean alustenErikoistoiminnot() {
        return alustenErikoistoiminnot;
    }
}
