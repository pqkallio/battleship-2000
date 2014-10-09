package battleship2000.programlogic.domain.player.ai;

import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.InLine;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.rules.SizeLimits;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The class provides a square-guessing pattern for the computer player.
 *
 * @author Petri Kallio
 */
public class ComputerGuessingPattern {
    private Player foe;
    private Table foesTable;
    private List<Focus> hitList;
    private boolean aSquareCanBeHitMultipleTimes;
    
    /**
     * Constructs a new instance of the class.
     * <p>
     * The player's foe and the possibility of hitting a square multiple times 
     * are given as parameters to be saved as object variables.
     * 
     * @param foe
     * @param aSquareCanBeHitMultipleTimes 
     */
    public ComputerGuessingPattern(Player foe, boolean aSquareCanBeHitMultipleTimes) {
        this.hitList = new ArrayList<>();
        this.foe = foe;
        this.foesTable = foe.getTable();
        this.aSquareCanBeHitMultipleTimes = aSquareCanBeHitMultipleTimes;
    }
    
    /**
     * Chooses a new {@link  battleship2000.programlogic.domain.table.Square} from the foe's table.
     * <p>
     * The method first checks whether an active focus is found and chooses the 
     * new square based on it's definitions. Otherwise it picks a new random square 
     * and checks if it can contain a {@link battleship2000.programlogic.domain.ship.ShipPart} 
     * before choosing it.
     * 
     * @see     battleship2000.programlogic.domain.player.ai.Focus
     * @return  the chosen square
     */
    public Square chooseASquare() {
        Square chosen = null;
        
        Focus nextToProspect = getFocus();
        
        if (nextToProspect != null) {
            while (chosen == null && nextToProspect.isActive()) {
                chosen = nextToProspect.prospect();
            }
        } else {
            chosen = getRandomSquare();
        }
        
        return chosen;
    }
    
    /**
     * Adds a new {@link battleship2000.programlogic.domain.player.ai.Focus} when a
     * {@link battleship2000.programlogic.domain.table.Square} with a 
     * {@link battleship2000.programlogic.domain.ship.ShipPart} is hit.
     * 
     * @param square    the square to be added as a new focus
     */
    public void addANewFocus(Square square) {
        this.hitList.add(new Focus(this, square, foe));
    }

    private Focus getFocus() {
        Focus nextToProspect = null;
        
        for (Focus focus : hitList) {
            if (focus.isActive()) {
                if (nextToProspect == null) {
                    nextToProspect = focus;
                }
            }
        }
        
        return nextToProspect;
    }

    private Square getRandomSquare() {
        int y = 0;
        int x = 0;
        Square squareToHit = null;
        Random raffle = new Random();
        
        while (squareToHit == null) {
            Double willChoose = 1.0;
            y = raffle.nextInt(foesTable.getHeight());
            x = raffle.nextInt(foesTable.getHeight());
            
            if (foesTable.getNeighborSquares(foesTable.getTableAsMap().get(y).get(x)) != null) {
                willChoose = raffle.nextDouble();
            }
            
            if (willChoose >= 0.7) {
                squareToHit = checkIfTheChosenSquareIsWorthBombing(x, y);
            }
        }
        
        if (squareToHit.getSetShipPart() != null) {
            addANewFocus(squareToHit);
        }
        
        return foesTable.getTableAsMap().get(y).get(x);
    }

    private boolean squareCanContainAShipPart(Square squareToHit) {
        if (!checkSurroundingsAreClear(squareToHit)) {
            return false;
        }
        
        return true;
    }

    private boolean checkSurroundingsAreClear(Square squareToHit) {
        List<Square> neighborSquares = foesTable.getNeighborSquares(squareToHit);
        
        for (Square square : neighborSquares) {
            if (square.getSetShipPart() != null) {
                if (square.getSetShipPart().getMotherShip().isDestroyed()) {
                    return false;
                }
            }
        }
        
        return true;
    }

    private boolean enoughSpaceForAShip(Square square) {
        boolean horizontalSpace = true;
        boolean verticalSpace = true;
        int minShipSize = getMinShipSize();
        
        Map<Direction, List<Square>> squaresBasedOnDirection = new HashMap<>();
        
        for (Direction direction : Direction.EAST.getCardinalDirections()) {
            squaresBasedOnDirection.put(direction, foesTable.getSquaresBasedOnDirection(square, direction, minShipSize));
        }
        
        int horizontalClear = 0;
        int verticalClear = 0;
        
        for (Direction direction : squaresBasedOnDirection.keySet()) {
            int clearSquaresOnDirection = clearSquaresOnDirection(direction, squaresBasedOnDirection);
            
            if (direction.getVerticalOrHorizontal() == InLine.HORIZONTAL) {
                horizontalClear += clearSquaresOnDirection;
            } else if (direction.getVerticalOrHorizontal() == InLine.VERTICAL) {
                verticalClear += clearSquaresOnDirection;
            }
        }
        
        if (!(horizontalClear >= minShipSize - 1)) horizontalSpace = false;
        if (!(verticalClear >= minShipSize - 1)) verticalSpace = false;
        
        return horizontalSpace|verticalSpace;
    }

    private Square checkIfTheChosenSquareIsWorthBombing(int x, int y) {
        Square square = foesTable.getTableAsMap().get(y).get(x);
        
        if (!square.isHit() || aSquareCanBeHitMultipleTimes) {
            if (squareCanContainAShipPart(square) && enoughSpaceForAShip(square)) {
                return square;
            }
        }
        
        return null;
    }

    private int clearSquaresOnDirection(Direction direction, Map <Direction, List<Square>> squaresBasedOnDirection) {
        int clearOnDirection = 0;
        boolean stillClear = true;

        for (Square squareOnList : squaresBasedOnDirection.get(direction)) {
            if (!squareOnList.isHit() && stillClear) {
                clearOnDirection++;
            } else {
                stillClear = false;
            }
        }
        
        return clearOnDirection;
    }

    private int getMinShipSize() {
        int minShipSize = SizeLimits.getShipsMaximumSize();
        
        for (Ship ship : foe.getShips()) {
            if (!ship.isDestroyed()) {
                if (ship.getShipLength() < minShipSize) {
                    minShipSize = ship.getShipLength();
                }
            }
        }
        
        return minShipSize;
    }
}
