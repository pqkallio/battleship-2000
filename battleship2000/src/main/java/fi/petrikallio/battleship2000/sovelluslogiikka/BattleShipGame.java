
package fi.petrikallio.battleship2000.sovelluslogiikka;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Ihminen;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaaja;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Ruutu;
import fi.petrikallio.battleship2000.sovelluslogiikka.ohjaus.PelaaVuoro;
import java.util.ArrayList;
import java.util.List;

public class BattleShipGame {
    private List<Pelaaja> pelaajat;
    private List<Tarkkailija> tarkkailijat;
    private boolean ruutuaVoiPommittaaUseasti;
    private boolean aluksetLiikkuvat;
    private boolean alustenErikoistoiminnot;
    private boolean jatkuu;
    private int vuoro;
    private Ruutu valittuRuutu;
    
    public BattleShipGame() {
        this.pelaajat = new ArrayList<>();
        this.tarkkailijat = new ArrayList<>();
        this.jatkuu = true;
        this.vuoro = 0;
        this.valittuRuutu = null;
    }

    public void lisaaTarkkailija(Tarkkailija tarkkailija) {
        this.tarkkailijat.add(tarkkailija);
    }

    public List<Tarkkailija> haeTarkkailijat() {
        return tarkkailijat;
    }
    
    public void ilmoitaTarkkailijoille() {
        for (Tarkkailija tarkkailija : this.tarkkailijat) {
            tarkkailija.paivita();
        }
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

    public void asetaRuutuaVoiPommittaaUseasti(boolean ruutuaVoiPommittaaUseasti) {
        this.ruutuaVoiPommittaaUseasti = ruutuaVoiPommittaaUseasti;
    }
    
    public boolean haeRuutuaVoiPommittaaUseasti() {
        return this.ruutuaVoiPommittaaUseasti;
    }

    public boolean aluksetLiikkuvat() {
        return aluksetLiikkuvat;
    }

    public boolean alustenErikoistoiminnot() {
        return alustenErikoistoiminnot;
    }
    
    public void kaynnista() throws InterruptedException {
        while (jatkuu) {
            this.vuoro++;
            
            Pelaaja pelaaja = maaritaVuoronPelaaja(vuoro);
            Pelaaja vastustaja = maaritaVuoronVastustaja(vuoro);
            
            if (pelaaja.getClass() == Ihminen.class) {
                while (valittuRuutu == null) {
                    Thread.sleep(500);
                }
            } else {
                valittuRuutu = pelaaja.valitseRuutu(vastustaja.haePelikentta(), 
                        this.ruutuaVoiPommittaaUseasti);
            }
            
            jatkuu = (boolean) new PelaaVuoro(pelaaja, vastustaja, valittuRuutu).suorita();
            valittuRuutu = null;
        }
    }

    private Pelaaja maaritaVuoronPelaaja(int vuoro) {
        if (vuoro % 2 != 0) return this.pelaajat.get(0);
        else return this.pelaajat.get(1);
    }

    private Pelaaja maaritaVuoronVastustaja(int vuoro) {
        if (vuoro % 2 == 0) return this.pelaajat.get(0);
        else return this.pelaajat.get(1);
    }
}
