package battleship2000.programlogic.domain.player.ai;

import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.domain.table.Table;
import java.util.ArrayList;
import java.util.List;
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
    
    public ComputerGuessingPattern(Player foe, boolean aSquareCanBeHitMultipleTimes) {
        this.hitList = new ArrayList<>();
        this.foe = foe;
        this.foesTable = foe.getTable();
        this.aSquareCanBeHitMultipleTimes = aSquareCanBeHitMultipleTimes;
    }
    
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
        boolean squareChosen = false;
        
        while (!squareChosen) {
            y = new Random().nextInt(foesTable.getHeight());
            x = new Random().nextInt(foesTable.getHeight());

            Square squareToHit = foesTable.getTable().get(y).get(x);

            if (!squareToHit.isHit() || aSquareCanBeHitMultipleTimes) {
                squareToHit.bomb();
                if (squareToHit.getShipPart() != null) {
                    addANewFocus(squareToHit);
                }

                squareChosen = true;
            }
        }

        return foesTable.getTable().get(y).get(x);
    }
}
