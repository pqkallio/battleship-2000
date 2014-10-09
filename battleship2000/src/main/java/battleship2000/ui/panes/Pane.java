package battleship2000.ui.panes;

/**
 * An interface that is implemented by all the game's different specialized panes.
 *
 * @author Petri Kallio
 */
public interface Pane {
    public void alertException(String message, Exception ex);
}
