/**
 * The Position class is used to store and retrieve the x- and y-coordinates of ship parts.
 */
package battleship2000.programlogic.domain.position;

public class Position implements Comparable<Position> {
    private int x;
    private int y;
    
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
