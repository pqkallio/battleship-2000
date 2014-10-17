
package battleship2000.ui.panes;

/**
 * An enum type used to name the different panes used in the gui's
 * {@link java.awt.CardLayout}.
 * 
 * @author Petri Kallio
 */
public enum CardNames {
    TITLE("TITLE"),
    GAME("GAME"),
    LOAD("LOAD"),
    HISCORES("HISCORES");
    
    private String name;
    
    private CardNames(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}