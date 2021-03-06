package battleship2000.programlogic.rules;

/**
 * A class consisting of static methods used to retrieve the possible minimum 
 * and maximum sizes and amounts of different game domain objects.
 *
 * @author Petri Kallio
 */
public class SizeLimits {
    
    public static int getShipsMinimumSize() {
        return 1;
    }
    
    public static int getShipsMaximumSize() {
        return 6;
    }
    
    public static int getMinimumTableWidth() {
        return 10;
    }
    
    public static int getMaximumTableWidth() {
        return 25;
    }
    
    public static int getMinimumAmountOfPlayers() {
        return 2;
    }
    
    public static int getMaximumAmountOfPlayers() {
        return 4;
    }
    
    public static int getMinimumAmountOfShips() {
        return 2;
    }
    
    public static int getMaximumAmountOfShips() {
        return 10;
    }
}
