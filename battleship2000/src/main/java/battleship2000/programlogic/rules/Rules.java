
package battleship2000.programlogic.rules;

import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.domain.player.PlayerType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {
    private Map<Rule, Object> saannot;
    
    public Rules() {
        this.saannot = new HashMap<>();
    }
    
    public void asetaKentanKoko(int leveys, int korkeus) {
        Map<Rule, Integer> kentanKoko = new HashMap<>();
        
        kentanKoko.put(Rule.TABLE_WIDTH, tarkistettuKentanSivunPituus(leveys));
        kentanKoko.put(Rule.TABLE_HEIGHT, tarkistettuKentanSivunPituus(korkeus));
        
        this.saannot.put(Rule.TABLE_SIZE, kentanKoko);
    }
    
    public Map<Rule, Integer> haeKentanKoko() {
        return (HashMap<Rule, Integer>)this.saannot.get(Rule.TABLE_SIZE);
    }
    
    public void createShips(List<ShipType> alukset) {
        this.saannot.put(Rule.SHIPS, alukset);
    }
    
    public List<ShipType> getShipTypes() {
        return (ArrayList<ShipType>)this.saannot.get(Rule.SHIPS);
    }
    
    public void setShipsMoveRule(boolean liikkuvatko) {
        this.saannot.put(Rule.SHIPS_ARE_MOVABLE, liikkuvatko);
    }
    
    public boolean shipsAreMovable() {
        return (boolean)this.saannot.get(Rule.SHIPS_ARE_MOVABLE);
    }
    
    public void setASquareCanBeHitMultipleTimesRule(boolean voiPommittaaUseasti) {
        this.saannot.put(Rule.A_SQUARE_CAN_BE_HIT_MULTIPLE_TIMES, voiPommittaaUseasti);
    }
    
    public boolean aSquareCanBeHitMultipleTimes() {
        return (boolean)this.saannot.get(Rule.A_SQUARE_CAN_BE_HIT_MULTIPLE_TIMES);
    }
    
    public void setSpecializedShips(boolean ovatkoKaytossa) {
        this.saannot.put(Rule.SHIPS_ARE_SPECIALIZED, ovatkoKaytossa);
    }
    
    public boolean shipsAreSpecialized() {
        return (boolean)this.saannot.get(Rule.SHIPS_ARE_SPECIALIZED);
    }
    
    public void createPlayers(List<PlayerType> pelaajat){
        this.saannot.put(Rule.PLAYERS, pelaajat);
    }
    
    public List<PlayerType> getPlayerTypes() {
        return (ArrayList<PlayerType>)this.saannot.get(Rule.PLAYERS);
    }

    public Map<Rule, Object> haeSaannot() {
        return saannot;
    }
    
    public int getTableWidth() {
        return haeKentanKoko().get(Rule.TABLE_WIDTH);
    }
    
    public int getTableHeight() {
        return haeKentanKoko().get(Rule.TABLE_HEIGHT);
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