
package battleship2000.ui.panes;

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