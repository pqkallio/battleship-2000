package battleship2000.programlogic.domain.position;

/**
 * The Position class is used to store and retrieve the x- and y-coordinates of ship parts.
 *
 * @author Petri Kallio
 */
public class Position implements Comparable<Position> {
    private int x;
    private int y;
    
    /**
     * The Positions x- and y-coordinates are given as constructor parameters.
     * 
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * By default, when two Positions are compared, 
     * <ol>
     *  <li>the one that has the smaller y-coordinate value is prior to the other.</li>
     *  <li>If both compared Positions have the same y-coordinate value, the one with
     *      the smaller x-coordinate value is prior to the other.</li>
     *  <li>Otherwise, both of the compared values are equal.</li>
     * </ol>
     * 
     * @param anotherPosition   the Position that this is compared to
     * @return                  -1 = prior, 0 = equal, 1 = posterior
     */
    @Override
    public int compareTo(Position anotherPosition) {
        if (this.y < anotherPosition.getY()) {
            return -1;
        } else if (this.y > anotherPosition.getY()) {
            return 1;
        } else {
            if (this.x < anotherPosition.getX()) {
                return -1;
            } else if (this.x > anotherPosition.getX()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
