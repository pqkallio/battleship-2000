package battleship2000.ui.panes;

/**
 * An interface that is implemented by all the game's different specialized panes.
 *
 * @author Petri Kallio
 */
public interface Pane {
    
    /**
     * This method is used to handle the implementing class's exceptions. 
     * 
     * @param message   the information
     * @param ex        the exception
     */
    public void alertException(String message, Exception ex);
}
