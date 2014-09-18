
package battleship2000.programlogic.domain.ship;

public enum Direction {
    NORTH(0.0, 0, -1),
    NORTH_NORTHEAST(22.5, 1, -2),
    NORTHEAST(45.0, 1, -1),
    EAST_NORTHEAST(67.5, 2, -1),
    EAST(90.0, 1, 0),
    EAST_SOUTHEAST(112.5, 2, 1),
    SOUTHEAST(135, 1, 1),
    SOUTH_SOUTHEAST(157.5, 1, 2),
    SOUTH(180, 0, 1),
    SOUTH_SOUTHWEST(202.5, -1, 2),
    SOUTHWEST(225, -1, 1),
    WEST_SOUTHWEST(247.5, -2, 1),
    WEST(270.0, -1, 0),
    WEST_NORTHWEST(292.5, -2, -1),
    NORTHWEST(315.0, -1, -1),
    NORTH_NORTHWEST(337.5, -1, -2);
    
    private double degreesClockwiseFromNorth;
    private int dx;
    private int dy;
    
    private Direction(double degreesClockwiseFromNorth, int dx, int dy) {
        this.degreesClockwiseFromNorth = degreesClockwiseFromNorth;
        this.dx = dx;
        this.dy = dy;
    }

    public double getAngle() {
        return degreesClockwiseFromNorth;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    
    public Direction getOppositeDirection() {
        Direction oppositeDirection = null;
        if (this.degreesClockwiseFromNorth < 180.0) {
            for (Direction direction : Direction.values()) {
                if (direction.getAngle() == this.degreesClockwiseFromNorth + Math.ceil(180.0)) {
                    oppositeDirection = direction;
                }
            }
        } else {
            for (Direction direction : Direction.values()) {
                if (direction.getAngle() == this.degreesClockwiseFromNorth - Math.ceil(180.0)) {
                    oppositeDirection = direction;
                }
            }
        }
        
        return oppositeDirection;
    }
}
