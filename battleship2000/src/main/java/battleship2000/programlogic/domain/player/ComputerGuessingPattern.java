
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.domain.table.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerGuessingPattern {
    private Table foesTable;
    private List<Focus> hitList;
    private boolean aSquareCanBeHitMultipleTimes;
    
    public ComputerGuessingPattern(Table foesTable, boolean aSquareCanBeHitMultipleTimes) {
        this.hitList = new ArrayList<>();
        this.foesTable = foesTable;
        this.aSquareCanBeHitMultipleTimes = aSquareCanBeHitMultipleTimes;
    }
    
    public Position chooseASquare() {
        boolean outerLoopReady = false;
        boolean innerLoopReady = false;
        int x = 0;
        int y = 0;
        
        for (Focus focus : hitList) {
            if (focus.isActive()) {
                focus.prospect();
            }
        }
        
        while (!outerLoopReady) {
                y = new Random().nextInt(foesTable.getHeight());
                int divider = 0;

                if (y % 2 != 0) {
                    divider = 1;
                } else {
                    divider = 2;
                }

                while (!innerLoopReady) {
                    x = new Random().nextInt(foesTable.getHeight());

                    if (x % divider == 0) {
                        innerLoopReady = true;
                    }
                }

                Square squareToHit = foesTable.getTable().get(y).get(x);

                if (!squareToHit.isHit() || aSquareCanBeHitMultipleTimes) {
                    squareToHit.bomb();

                    if (squareToHit.getShipPart() != null) {
                        this.hitList.add(new Focus(squareToHit));
                    }

                    outerLoopReady = true;
                }
        }
        
        return new Position(x, y);
    }
}
