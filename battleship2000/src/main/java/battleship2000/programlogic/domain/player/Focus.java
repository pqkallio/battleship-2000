
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.domain.table.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Focus {
    private Square squareOfOrigin;
    private Player foe;
    private List<Direction> possibleDirections;
    private Set<Direction> deadEnds;
    private List<Square> prospected;
    private Direction thisWayBoys;
    private boolean active;
    
    Focus(Square squareOfOrigin, Player foe) {
        this.squareOfOrigin = squareOfOrigin;
        this.foe = foe;
        this.deadEnds = new HashSet<>();
        this.prospected = new ArrayList<>();
        this.prospected.add(squareOfOrigin);
        this.possibleDirections = Direction.EAST.getMainDirections();
    }

    public boolean isActive() {
        return active;
    }

    public void prospect() {
        if (prospected.isEmpty()) {
            lookForDeadEnds();
        }
        
        if (thisWayBoys != null) {
            thisWayBoys = chooseARandomDirection();
        }
        
        bombTheNearestSquareToThePointedDirection();
    }

    private void lookForDeadEnds() {
        int shortestEnemyShipSize = Integer.MAX_VALUE;
        
        for (Ship ship : foe.getShips()) {
            shortestEnemyShipSize = Math.min(shortestEnemyShipSize, ship.getShipLength());
        }
        
        for (Direction direction : possibleDirections) {
            if (deadEnds.contains(direction)) continue;
            
            if (!enoughSeaToExplore(direction, shortestEnemyShipSize)) {
                this.deadEnds.add(direction);
                this.deadEnds.add(direction.getOppositeDirection());
            }
        }
    }

    private boolean enoughSeaToExplore(Direction direction, int shortestEnemyShipSize) {
        boolean yesThereIs = (letsLookFromHere(direction, shortestEnemyShipSize)
                + letsLookFromHere(direction, shortestEnemyShipSize) >= shortestEnemyShipSize - 1);
        
        return yesThereIs;
    }

    private int letsLookFromHere(Direction direction, int shortestEnemyShipSize) {
        int dDx = direction.getDx();
        int dDy = direction.getDy();
        int sDx = squareOfOrigin.getX();
        int sDy = squareOfOrigin.getY();
        int seaToExplore = 0;
        boolean prospectable = true;
        Map<Integer, Map<Integer, Square>> foesTable = foe.getTable().getTable();
        
        for (int i = 0; i < shortestEnemyShipSize; i++) {
            int x = 0;
            int y = 0;
            
            if (dDx != 0) {
                x = sDx + dDx + i;
            } else {
                x = sDx;
            }
            
            if (dDy != 0) {
                y = sDy + dDy + i;
            } else {
                y = sDy;
            }
            
            if (x < 0 || x >= foe.getTable().getWidth() 
                    || y < 0 || y >= foe.getTable().getHeight() 
                    || foesTable.get(y).get(x).isHit() || !prospectable) {
                prospectable = false;
            } else {
                seaToExplore++;
            }
            
            if (i == 0 && !prospectable) {
                deadEnds.add(direction);
            }
        }
        
        return seaToExplore;
        
        // remember to check the sum of direction and its opposite direction!
    }

    private Direction chooseARandomDirection() {
        Direction chosenDirection = null;
        
        while (true) {
            int pickADirection = new Random().nextInt(possibleDirections.size());
            
            if (!deadEnds.contains(possibleDirections.get(pickADirection))) {
                chosenDirection = possibleDirections.get(pickADirection);
                break;
            }
        }
        
        return chosenDirection;
    }

    private void bombTheNearestSquareToThePointedDirection() {
        Square lastSquareBombed = prospected.get(prospected.size() - 1);
        Table foesTable = foe.getTable();
        
        Square nextSquare = foesTable.getNextSquare(lastSquareBombed, thisWayBoys);
    }
}
