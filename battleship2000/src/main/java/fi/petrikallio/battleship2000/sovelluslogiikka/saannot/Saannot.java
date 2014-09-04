
package fi.petrikallio.battleship2000.sovelluslogiikka.saannot;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alustyyppi;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaajatyyppi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Saannot {
    private Map<Saanto, Object> saannot;
    
    public Saannot() {
        this.saannot = new HashMap<>();
    }
    
    public void asetaKentanKoko(int x, int y) {
        Map<String, Integer> kentanKoko = new HashMap<>();
        kentanKoko.put("x", x);
        kentanKoko.put("y", y);
        this.saannot.put(Saanto.KENTAN_KOKO, kentanKoko);
    }
    
    public Map<String, Integer> haeKentanKoko() {
        return (HashMap<String, Integer>)this.saannot.get(Saanto.KENTAN_KOKO);
    }
    
    public void asetaAlukset(List<Alustyyppi> alukset) 
            throws InstantiationException, IllegalAccessException{
        this.saannot.put(Saanto.ALUKSET, alukset);
    }
    
    public List<Alustyyppi> haeAlustyypit() {
        return (ArrayList<Alustyyppi>)this.saannot.get(Saanto.ALUKSET);
    }
    
    public void asetaLiikkumissaanto(boolean liikkuvatko) {
        this.saannot.put(Saanto.ALUKSET_LIIKKUVAT, liikkuvatko);
    }
    
    public boolean liikkuvatkoAlukset() {
        return (boolean)this.saannot.get(Saanto.ALUKSET_LIIKKUVAT);
    }
    
    public void asetaAlustenErikoistoiminnot(boolean ovatkoKaytossa) {
        this.saannot.put(Saanto.ALUSTEN_ERIKOISTOIMINNOT, ovatkoKaytossa);
    }
    
    public boolean aluksillaErikoistoiminnot() {
        return (boolean)this.saannot.get(Saanto.ALUSTEN_ERIKOISTOIMINNOT);
    }
    
    public void asetaPelaajat(List<Pelaajatyyppi> pelaajat) 
            throws InstantiationException, IllegalAccessException {
        this.saannot.put(Saanto.PELAAJAT, pelaajat);
    }
    
    public List<Pelaajatyyppi> haePelaajatyypit() {
        return (ArrayList<Pelaajatyyppi>)this.saannot.get(Saanto.PELAAJAT);
    }

    public Map<Saanto, Object> haeSaannot() {
        return saannot;
    }
}