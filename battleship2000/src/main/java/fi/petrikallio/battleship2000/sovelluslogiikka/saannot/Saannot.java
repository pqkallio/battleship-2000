
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
    
    public void asetaKentanKoko(int leveys, int korkeus) {
        Map<Saanto, Integer> kentanKoko = new HashMap<>();
        
        kentanKoko.put(Saanto.KENTAN_LEVEYS, tarkistettuKentanSivunPituus(leveys));
        kentanKoko.put(Saanto.KENTAN_KORKEUS, tarkistettuKentanSivunPituus(korkeus));
        
        this.saannot.put(Saanto.KENTAN_KOKO, kentanKoko);
    }
    
    public Map<Saanto, Integer> haeKentanKoko() {
        return (HashMap<Saanto, Integer>)this.saannot.get(Saanto.KENTAN_KOKO);
    }
    
    public void asetaAlukset(List<Alustyyppi> alukset) {
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
    
    public void asetaPelaajat(List<Pelaajatyyppi> pelaajat){
        this.saannot.put(Saanto.PELAAJAT, pelaajat);
    }
    
    public List<Pelaajatyyppi> haePelaajatyypit() {
        return (ArrayList<Pelaajatyyppi>)this.saannot.get(Saanto.PELAAJAT);
    }

    public Map<Saanto, Object> haeSaannot() {
        return saannot;
    }
    
    public int haeKentanLeveys() {
        return haeKentanKoko().get(Saanto.KENTAN_LEVEYS);
    }
    
    public int haeKentanKorkeus() {
        return haeKentanKoko().get(Saanto.KENTAN_KORKEUS);
    }
    // REFAKTOROI NÄMÄ!!!!
    // Kokorajoitteet HashMap<EnumSaanto, HashMap<EnumMinMax, Integer>>
    private Integer tarkistettuKentanSivunPituus(int sivunPituus) {
        int min = Kokorajoitteet.kentanSivunVahimmaispituus();
        int max = Kokorajoitteet.kentanSivunEnimmaispituus();
        
        if (sivunPituus < min) return min;
        else if (sivunPituus > max) return max;
        else return sivunPituus;
    }
    
    private Integer tarkistettuPelaajienMaara(int maara) {
        int min = Kokorajoitteet.pelaajienVahimmaismaara();
        int max = Kokorajoitteet.pelaajienEnimmaismaara();
        
        if (maara < min) return min;
        else if (maara > max) return max;
        else return maara;
    }
    
    private Integer tarkistettuAlustenMaara(int maara) {
        int min = Kokorajoitteet.alustenVahimmaismaara();
        int max = Kokorajoitteet.alustenVahimmaismaara();
        
        if (maara < min) return min;
        else if (maara > max) return max;
        else return maara;
    }
}