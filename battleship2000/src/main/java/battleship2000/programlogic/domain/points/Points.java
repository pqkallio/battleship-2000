/**
 * An enum type used to return the amount of points awarded from bombing an
 * enemy square.
 */
package battleship2000.programlogic.domain.points;

public enum Points {
    MISSED(0),
    HIT(5),
    DESTROYED(10);
    
    private int points;
    
    private Points(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
