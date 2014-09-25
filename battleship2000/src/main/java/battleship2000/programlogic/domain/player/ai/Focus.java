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
        this.possibleDirections = Direction.EAST.getMainDirections();
    }

    public boolean isActive() {
        return active;
    }

    public Square prospect() {
        if (squareOfOrigin.getShipPart().getMotherShip().isDestroyed()) {
            wereDoneHere();
            return null;
        }
        
        if (prospected.isEmpty()) {
            System.out.println("prospected was empty");
            for (Direction direction : possibleDirections) {
                prospected.put(direction, new ArrayList<Square>());
                prospected.get(direction).add(squareOfOrigin);
            }
            
            lookForDeadEnds();
        }
        
        if (thisWayBoys == null) {
            thisWayBoys = chooseARandomDirection();
        }
        
        return bombTheNearestSquareToThePointedDirection();
    }

    private void lookForDeadEnds() {
        System.out.println("Looking for deadends");
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
                System.out.println("Added deadend " + direction);
                this.deadEnds.add(direction.getOppositeDirection());
                System.out.println("Added deadend " + direction.getOppositeDirection());
            }
        }
    }

    private boolean enoughSeaToExplore(Direction direction, int shortestEnemyShipSize) {
        boolean yesThereIs = (letsLookFromHere(direction, shortestEnemyShipSize)
                + letsLookFromHere(direction.getOppositeDirection(), shortestEnemyShipSize) >= shortestEnemyShipSize - 1);
        
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
    }

    private Direction chooseARandomDirection() {
        Direction chosenDirection = null;
        
        while (true) {
            int pickADirection = new Random().nextInt(possibleDirections.size());
            System.out.println("chooseARandomDirection()");
            if (!deadEnds.contains(possibleDirections.get(pickADirection))) {
                chosenDirection = possibleDirections.get(pickADirection);
                break;
            }
        }
        
        return chosenDirection;
    }

    private Square bombTheNearestSquareToThePointedDirection() {
        List<Square> directionsBombingHistory = prospected.get(thisWayBoys);
        System.out.println(thisWayBoys);
        Square lastSquareBombed = directionsBombingHistory.get(directionsBombingHistory.size() - 1);
        Table foesTable = foe.getTable();
        
        Square nextSquare = foesTable.getNextSquare(lastSquareBombed, thisWayBoys);
        
        if (nextSquare != null && !nextSquare.isHit()) {
            if (nextSquare.getShipPart() == null) {
                deadEnds.add(thisWayBoys);
                
                if (!deadEnds.contains(thisWayBoys.getOppositeDirection())) {
                    thisWayBoys = thisWayBoys.getOppositeDirection();
                } else {
                    thisWayBoys = null;
                }
            } else {
                prospected.get(thisWayBoys).add(nextSquare);
            }
        } else {
            deadEnds.add(thisWayBoys);
            thisWayBoys = null;
        }
        
        return nextSquare;
    }

    private void wereDoneHere() {
        List<Square> brandNewFoci = new ArrayList<>();
        
        for (List<Square> list : prospected.values()) {
            for (Square square : list) {
                if (square.getShipPart().getMotherShip() 
                        != squareOfOrigin.getShipPart().getMotherShip()) {
                    brandNewFoci.add(square);
                }
            }
        }
        
        for (Square square : brandNewFoci) {
            cgp.addANewFocus(square);
        }
        
        active = false;
    }
}
