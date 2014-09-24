/**
 * This class contains the rules used when creating a new instance of CreateGame
 * control class.
 */

package battleship2000.programlogic.rules;

import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.domain.player.PlayerType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {
    private Map<Rule, Object> rules;
    
    public Rules() {
        this.rules = new HashMap<>();
    }
    
    public void setTableSize(int width, int height) {
        Map<Rule, Integer> tableSize = new HashMap<>();
        
        tableSize.put(Rule.TABLE_WIDTH, validatedTableLength(width));
        tableSize.put(Rule.TABLE_HEIGHT, validatedTableLength(height));
        
        this.rules.put(Rule.TABLE_SIZE, tableSize);
    }
    
    public Map<Rule, Integer> getTableSize() {
        return (HashMap<Rule, Integer>)this.rules.get(Rule.TABLE_SIZE);
    }
    
    public void setShipTypes(List<ShipType> shipTypes) {
        this.rules.put(Rule.SHIPS, shipTypes);
    }
    
    public List<ShipType> getShipTypes() {
        return (ArrayList<ShipType>)this.rules.get(Rule.SHIPS);
    }
    
    public void setShipsAreMovableRule(boolean shipsAreMovable) {
        this.rules.put(Rule.SHIPS_ARE_MOVABLE, shipsAreMovable);
    }
    
    public boolean shipsAreMovable() {
        return (boolean)this.rules.get(Rule.SHIPS_ARE_MOVABLE);
    }
    
    public void setASquareCanBeHitMultipleTimesRule(boolean aSquareCanBeHitMultipleTimes) {
        this.rules.put(Rule.A_SQUARE_CAN_BE_HIT_MULTIPLE_TIMES, aSquareCanBeHitMultipleTimes);
    }
    
    public boolean aSquareCanBeHitMultipleTimes() {
        return (boolean)this.rules.get(Rule.A_SQUARE_CAN_BE_HIT_MULTIPLE_TIMES);
    }
    
    public void setSpecializedShips(boolean shipsAreSpecialized) {
        this.rules.put(Rule.SHIPS_ARE_SPECIALIZED, shipsAreSpecialized);
    }
    
    public boolean shipsAreSpecialized() {
        return (boolean)this.rules.get(Rule.SHIPS_ARE_SPECIALIZED);
    }
    
    public void setPlayerTypes(List<PlayerType> playerTypes){
        this.rules.put(Rule.PLAYERS, playerTypes);
    }
    
    public List<PlayerType> getPlayerTypes() {
        return (ArrayList<PlayerType>)this.rules.get(Rule.PLAYERS);
    }

    public Map<Rule, Object> getRules() {
        return rules;
    }
    
    public int getTableWidth() {
        return getTableSize().get(Rule.TABLE_WIDTH);
    }
    
    public int getTableHeight() {
        return getTableSize().get(Rule.TABLE_HEIGHT);
    }
    // REFAKTOROI NÄMÄ!!!!
    // SizeLimits HashMap<EnumSaanto, HashMap<EnumMinMax, Integer>>
    private Integer validatedTableLength(int tableSideLegth) {
        int min = SizeLimits.getMinimumTableWidth();
        int max = SizeLimits.getMaximumTableWidth();
        
        if (tableSideLegth < min) return min;
        else if (tableSideLegth > max) return max;
        else return tableSideLegth;
    }
    
    private Integer validatedAmountOfPlayers(int playerAmount) {
        int min = SizeLimits.getMinimumAmountOfPlayers();
        int max = SizeLimits.getMaximumAmountOfPlayers();
        
        if (playerAmount < min) return min;
        else if (playerAmount > max) return max;
        else return playerAmount;
    }
    
    private Integer validatedAmountOfShips(int shipAmount) {
        int min = SizeLimits.getMinimumAmountOfShips();
        int max = SizeLimits.getMinimumAmountOfShips();
        
        if (shipAmount < min) return min;
        else if (shipAmount > max) return max;
        else return shipAmount;
    }
}