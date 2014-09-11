
package battleship2000.programlogic.rules;

import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.domain.player.PlayerType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {
    private Map<Saanto, Object> saannot;
    
    public Rules() {
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
    
    public void createShips(List<ShipType> alukset) {
        this.saannot.put(Saanto.ALUKSET, alukset);
    }
    
    public List<ShipType> getShipTypes() {
        return (ArrayList<ShipType>)this.saannot.get(Saanto.ALUKSET);
    }
    
    public void setShipsMoveRule(boolean liikkuvatko) {
        this.saannot.put(Saanto.ALUKSET_LIIKKUVAT, liikkuvatko);
    }
    
    public boolean shipsAreMovable() {
        return (boolean)this.saannot.get(Saanto.ALUKSET_LIIKKUVAT);
    }
    
    public void setASquareCanBeHitMultipleTimesRule(boolean voiPommittaaUseasti) {
        this.saannot.put(Saanto.RUUTUA_VOI_POMMITTAA_USEASTI, voiPommittaaUseasti);
    }
    
    public boolean aSquareCanBeHitMultipleTimes() {
        return (boolean)this.saannot.get(Saanto.RUUTUA_VOI_POMMITTAA_USEASTI);
    }
    
    public void setSpecializedShips(boolean ovatkoKaytossa) {
        this.saannot.put(Saanto.ALUSTEN_ERIKOISTOIMINNOT, ovatkoKaytossa);
    }
    
    public boolean shipsAreSpecialized() {
        return (boolean)this.saannot.get(Saanto.ALUSTEN_ERIKOISTOIMINNOT);
    }
    
    public void createPlayers(List<PlayerType> pelaajat){
        this.saannot.put(Saanto.PELAAJAT, pelaajat);
    }
    
    public List<PlayerType> getPlayerTypes() {
        return (ArrayList<PlayerType>)this.saannot.get(Saanto.PELAAJAT);
    }

    public Map<Saanto, Object> haeSaannot() {
        return saannot;
    }
    
    public int getTableWidth() {
        return haeKentanKoko().get(Saanto.KENTAN_LEVEYS);
    }
    
    public int getTableHeight() {
        return haeKentanKoko().get(Saanto.KENTAN_KORKEUS);
    }
    // REFAKTOROI NÄMÄ!!!!
    // SizeLimits HashMap<EnumSaanto, HashMap<EnumMinMax, Integer>>
    private Integer tarkistettuKentanSivunPituus(int sivunPituus) {
        int min = SizeLimits.getMinimumTableWidth();
        int max = SizeLimits.getMaximumTableWidth();
        
        if (sivunPituus < min) return min;
        else if (sivunPituus > max) return max;
        else return sivunPituus;
    }
    
    private Integer tarkistettuPelaajienMaara(int maara) {
        int min = SizeLimits.getMinimumAmountOfPlayers();
        int max = SizeLimits.getMaximumAmountOfPlayers();
        
        if (maara < min) return min;
        else if (maara > max) return max;
        else return maara;
    }
    
    private Integer tarkistettuAlustenMaara(int maara) {
        int min = SizeLimits.getMinimumAmountOfShips();
        int max = SizeLimits.getMinimumAmountOfShips();
        
        if (maara < min) return min;
        else if (maara > max) return max;
        else return maara;
    }
}