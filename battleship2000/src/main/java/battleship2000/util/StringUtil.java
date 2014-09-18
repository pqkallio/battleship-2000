
package battleship2000.util;

public class StringUtil {
    
    public static boolean canBeParsedAsAnInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
}
