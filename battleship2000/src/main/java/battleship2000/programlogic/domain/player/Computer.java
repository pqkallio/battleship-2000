
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.Square;
import java.util.Random;

public class Computer extends Player {

    public Computer() {
        
    }
    
    public Computer(BattleShipGame game) {
        super(game);
    }
    
    @Override
    public Square chooseASquare(Table table, boolean aSquareCanBeHitMultipleTimes) {
        int x = 0;
        int y = 0;
        
        while (true) {
            x = drawANumber(table.getWidth());
            y = drawANumber(table.getHeight());
            
            if (aSquareCanBeHitMultipleTimes 
                    || !table.getTable().get(y).get(x).isHit() ) {
                break;
            }
        }
        
        return table.getTable().get(y).get(x);
    }
    
    @Override
    public String toString() {
        return "computer";
    }

    @Override
    public void placeShipsOnTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int drawANumber(int luku) {
        int luckyNumber = new Random().nextInt(luku);
        return luckyNumber;
    }
}
