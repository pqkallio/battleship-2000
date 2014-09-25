package battleship2000.util;

/**
 * This class consists of static methods used to help the handling of string-objects.
 *
 * @author Petri Kallio
 */
public class StringUtil {
    
    /**
     * A method that takes one String object as a parameter and checks if it can
     * be parsed as an integer.
     * 
     * @param string    a string to be parsed
     * @return          true if the string can be parsed as an integer, otherwise false
     */
    public static boolean canBeParsedAsAnInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
}
