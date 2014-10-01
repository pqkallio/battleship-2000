package battleship2000.programlogic.domain.ship;

import java.util.ArrayList;
import java.util.List;

/**
 * An enum type used to store and retrieve the degree when turned to a certain
 * direction and distances as x- and y-coordinates when moved to a certain direction.
 *
 * @author Petri Kallio
 */
public enum Direction {
    NORTH(0.0, 0, -1, InLine.VERTICAL),
    NORTH_NORTHEAST(22.5, 1, -2, InLine.NEITHER),
    NORTHEAST(45.0, 1, -1, InLine.NEITHER),
    EAST_NORTHEAST(67.5, 2, -1, InLine.NEITHER),
    EAST(90.0, 1, 0, InLine.HORIZONTAL),
    EAST_SOUTHEAST(112.5, 2, 1, InLine.NEITHER),
    SOUTHEAST(135, 1, 1, InLine.NEITHER),
    SOUTH_SOUTHEAST(157.5, 1, 2, InLine.NEITHER),
    SOUTH(180, 0, 1, InLine.VERTICAL),
    SOUTH_SOUTHWEST(202.5, -1, 2, InLine.NEITHER),
    SOUTHWEST(225, -1, 1, InLine.NEITHER),
    WEST_SOUTHWEST(247.5, -2, 1, InLine.NEITHER),
    WEST(270.0, -1, 0, InLine.HORIZONTAL),
    WEST_NORTHWEST(292.5, -2, -1, InLine.NEITHER),
    NORTHWEST(315.0, -1, -1, InLine.NEITHER),
    NORTH_NORTHWEST(337.5, -1, -2, InLine.NEITHER);
    
    private double degreesClockwiseFromNorth;
    private int dx;
    private int dy;
    private InLine verticalOrHorizontal;
    
    private Direction(double degreesClockwiseFromNorth, int dx, int dy, InLine verticalOrHorizontal) {
        this.degreesClockwiseFromNorth = degreesClockwiseFromNorth;
        this.dx = dx;
        this.dy = dy;
        this.verticalOrHorizontal = verticalOrHorizontal;
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

    public InLine getVerticalOrHorizontal() {
        return verticalOrHorizontal;
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
    
    public List<Direction> getMainDirections() {
        return chooseDirectionsByAngle(90);
    }
    
    public List<Direction> getMainAndBetweenMainDirections() {
        return chooseDirectionsByAngle(45);
    }

    private List<Direction> chooseDirectionsByAngle(int angleDivision) {
        List<Direction> directions = new ArrayList<>();
        
        for (Direction direction : Direction.values()) {
            if (direction.getAngle() % angleDivision == 0) {
                directions.add(direction);
            }
        }
        
        return directions;
    }
}
