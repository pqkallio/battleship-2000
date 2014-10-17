
package battleship2000.ui.domain;

/**
 * An enum type used as a container of the String that is used to connect the
 * right graphic file to the right {@link battleship2000.programlogic.domain.ship.ShipPart}.
 * 
 * @author Petri Kallio
 */
public enum ShipPartPlace {
    FRONT("nose_"),
    MIDDLE("middle_"),
    REAR("tail_");
    
    private String fileIndicator;
    
    private ShipPartPlace(String fileIndicator) {
        this.fileIndicator = fileIndicator;
    }

    public String getFileIndicator() {
        return fileIndicator;
    }
}
