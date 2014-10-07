
package battleship2000.ui.domain;

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
