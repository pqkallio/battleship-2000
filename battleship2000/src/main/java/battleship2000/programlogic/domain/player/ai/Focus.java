package battleship2000.programlogic.domain.player.ai;

import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.domain.table.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * The class handles situations where the computer player has bombed a square
 * with a ship part in it and needs to figure out where the rest of the ship is.
 *
 * @author Petri Kallio
 */
public class Focus {
    private ComputerGuessingPattern cgp;
    private Square squareOfOrigin;
    private Player foe;
    private List<Direction> possibleDirections;
    private Set<Direction> deadEnds;
    private Map<Direction, List<Square>> prospected;
    private Direction thisWayBoys;
    private boolean active;
    
    public Focus(ComputerGuessingPattern cgp, Square squareOfOrigin, Player foe) {
        this.active = true;
        this.cgp = cgp;
        this.squareOfOrigin = squareOfOrigin;
        this.foe = foe;
        this.deadEnds = new HashSet<>();
        this.prospected = new HashMap<>();
        this.possibleDirections = Direction.EAST.getCardinalDirections();
    }

    public boolean isActive() {
        return active;
    }
    
    /**
     * Decides the next {@link battleship2000.programlogic.domain.table.Square}
     * to bomb based on either:
     * <ol>
     *  <li>guessing the next square if the Focus has no previous hits other than the square given as
     *      a constructor parameter, or</li>
     *  <li>deciding the next possible square based on previous hits</li>
     * </ol>
     * 
     * @return  the chosen {@link battleship2000.programlogic.domain.table.Square}
     */
    public Square prospect() {
        if (squareOfOrigin.getSetShipPart().getMotherShip().isDestroyed()) {
            wereDoneHere();
            return null;
        }
        
        if (prospected.isEmpty()) {
            for (Direction direction : possibleDirections) {
                prospected.put(direction, new ArrayList<Square>());
                prospected.get(direction).add(squareOfOrigin);
            }
            
            lookForDeadEnds();
        }
        
        /* 
         * TODO
         * Must refactor to find the "most" possible direction first
         * Must take in consideration the case of longest possible ship on focus
         */
        if (thisWayBoys == null) {
            thisWayBoys = chooseARandomDirection();
        }
        
        return bombTheNearestSquareToThePointedDirection();
    }

    private void lookForDeadEnds() {
        int shortestEnemyShipSize = Integer.MAX_VALUE;
        
        for (Ship ship : foe.getShips()) {
            if (!ship.isDestroyed()) {
                shortestEnemyShipSize = Math.min(shortestEnemyShipSize, 
                        ship.getShipLength());
            }
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
                + letsLookFromHere(direction.getOppositeDirection(), 
                shortestEnemyShipSize) >= shortestEnemyShipSize - 1);
        
        return yesThereIs;
    }

    private int letsLookFromHere(Direction direction, int shortestEnemyShipSize) {
        int dDx = direction.getDx();
        int dDy = direction.getDy();
        int sDx = squareOfOrigin.getX();
        int sDy = squareOfOrigin.getY();
        int seaToExplore = 0;
        boolean prospectable = true;
        Map<Integer, Map<Integer, Square>> foesTable = foe.getTable().getTableAsMap();
        
        if (checkIfDeadEnd(sDx + (dDx * 2), sDy + (dDy * 2))) {
            deadEnds.add(direction);
            return 0;
        }
        
        
        for (int i = 1; i < shortestEnemyShipSize; i++) {
            int x = sDx + (dDx * i);
            int y = sDy + (dDy * i);
            
            if (!foe.getTable().checkCoordinatesAreOnTheTable(x, y)
                    || foesTable.get(y).get(x).isHit()
                    || !prospectable) {
                prospectable = false;
            } else {
                seaToExplore++;
            }
            
            if (seaToExplore == 0 && !prospectable) {
                deadEnds.add(direction);
            }
        }
        
        return seaToExplore;
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

    private Square bombTheNearestSquareToThePointedDirection() {
        List<Square> directionsBombingHistory = prospected.get(thisWayBoys);
        Square lastSquareBombed = directionsBombingHistory.get(directionsBombingHistory.size() - 1);
        Table foesTable = foe.getTable();
        
        Square nextSquare = foesTable.getNextSquareBasedOnDirection(lastSquareBombed, thisWayBoys);
        
        if (nextSquare == null || nextSquare.isHit()) {
            deadEnds.add(thisWayBoys);
            thisWayBoys = null;
            nextSquare = null;
        } else {
            if (nextSquare.getSetShipPart() == null) {
                deadEnds.add(thisWayBoys);
                
                if (prospected.get(thisWayBoys).size() != 1) {
                    if (!deadEnds.contains(thisWayBoys.getOppositeDirection())) {
                        thisWayBoys = thisWayBoys.getOppositeDirection();
                    }
                } else {
                    thisWayBoys = null;
                }
            } else {
                prospected.get(thisWayBoys).add(nextSquare);
            }
        }
        
        return nextSquare;
    }

    private void wereDoneHere() {
        List<Square> brandNewFoci = new ArrayList<>();
        
        for (List<Square> list : prospected.values()) {
            for (Square square : list) {
                if (square.getSetShipPart().getMotherShip() 
                        != squareOfOrigin.getSetShipPart().getMotherShip()) {
                    brandNewFoci.add(square);
                }
            }
        }
        
        for (Square square : brandNewFoci) {
            cgp.addANewFocus(square);
        }
        
        active = false;
    }
    
    private boolean checkIfDeadEnd(int x, int y) {
        Map<Integer, Map<Integer, Square>> foesTable = foe.getTable().getTableAsMap();
        
        if (foe.getTable().checkCoordinatesAreOnTheTable(x, y)
                && foesTable.get(y).get(x).getSetShipPart() != null) {
            if (foesTable.get(y).get(x).getSetShipPart().getMotherShip().isDestroyed()) {
                return true;
            }
        }
        
        return false;
    }
}
